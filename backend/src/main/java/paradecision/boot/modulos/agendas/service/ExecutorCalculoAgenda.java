package paradecision.boot.modulos.agendas.service;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.dto.AgendaPareceresDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.repository.AgendaFatoresRepository;
import paradecision.boot.modulos.agendas.repository.AgendaPareceresRepository;
import paradecision.boot.modulos.agendas.repository.AgendaUsuariosRepository;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

final class ExecutorCalculoAgenda {
  private final AgendaFatoresRepository agendaFatoresRepository;
  private final AgendaFatoresService agendaFatoresService;
  private final AgendaPareceresRepository agendaPareceresRepository;
  private final AgendaUsuariosRepository agendaUsuariosRepository;

  public ExecutorCalculoAgenda(
      AgendaFatoresRepository agendaFatoresRepository,
      AgendaFatoresService agendaFatoresService,
      AgendaPareceresRepository agendaPareceresRepository,
      AgendaUsuariosRepository agendaUsuariosRepository) {
    this.agendaFatoresRepository = agendaFatoresRepository;
    this.agendaFatoresService = agendaFatoresService;
    this.agendaPareceresRepository = agendaPareceresRepository;
    this.agendaUsuariosRepository = agendaUsuariosRepository;
  }

  // ###### Atributos Privados ######
  private int quantidadeUsuarios = 0;
  private int quantidadeFatores = 0;
  private int quantidadeGrupos = 0;
  private int quantidadePareceres = 0;

  private double limiteDecisao = 50;

  // matriz[U][3]
  private long[][] matrizIndicesUsuarios; // matriz{{ind_usu, ind_grup, cod_usu}, { idem } ...}
  // matriz[F][2]
  private long[][] matrizIndicesFatores; // matriz{{ind_fat, cod_fat}, { idem }, ...}

  // ###### Objetos de Base de Dados ######
  private Agenda dadosAgenda; // precisa apenas do c�digo da Agenda para este C�lculo
  private AgendaPareceresDados dadosAgendaPareceres;

  private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
  private ArrayList<Fator> listaFatores = new ArrayList<Fator>();

  private AgendaFatoresDados dadosAgendaFatores; // utilizado no c�lculo dos Graus
  // utilizado no c�lculo dos Graus

  // ###### Matrizes que servir�o em v�rios momentos ######
  // matriz[F][G][U] - Matrizes Iniciais
  private double[][][] matrizCerteza; // Matriz com todos os valores de Certeza (da Agenda)
  private double[][][] matrizContradicao; // Matriz com todos os valores de Contradi��o (da Agenda)
  // matriz[F][G] - Para Maximiza��o
  private double[][]
      matrizCertezaMaximizada; // matriz result da Maximiza��o {{maiCert, maiCert, ...}, { idem }, ...}
  private double[][]
      matrizContradicaoMaximizada; // matriz result da Maximiza��o {{menCont, menCont, ...}, { idem }, ...}
  // matriz[F] - Para Minimiza��o
  private double[] matrizCertezaMinimizada; // matriz result da Minimiza��o {menCert, menCert, ...}
  private double[] matrizContradicaoMinimizada; // matriz result da Minimiza��o {maiCont, maiCont, ...}
  // matriz[F] - Para C�lculo do Grau (para cada Fator)
  private double[] grausCertezaFatores; // matriz result de Graus de Certezas {grauF1, grauF2, ...}
  private double[] grausIncertezaFatores; // matriz result de Graus de Incertezas {grauF1, grauF2, ...}
  // guardando os graus calculados para a Agenda
  double grauCertezaAgenda = 0;
  double grauIncertezaAgenda = 0;

  // ###### M�todos P�blicos ######
  public String geraResultados(Agenda dadosAgendaAuxiliares, int tipoAmostra) {
    this.dadosAgenda = dadosAgendaAuxiliares;
    String resultadoOperacao = "NOK";
    if (!(dadosAgenda == null)) {
      if (dadosAgenda.getA04_codigo() > 0) {
        resultadoOperacao = this.getArrFatoresModel();
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.getArrEspecialistasModel();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.getArrPareceresAgenda();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.montaMatrizesIndices();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.montaMatriz_Fase01();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.realizaMaximizacao();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.realizaMinimizacao();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.calcularGrausFatores();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.calcularGrausAgenda();
        }
        if (resultadoOperacao.equals("OK")) {
          resultadoOperacao = this.atualizarAgendaEFatores();
        }
        if (resultadoOperacao.equals("OK") && tipoAmostra == 1) {
          this.mostraMatrizesValores();
        }
      }
    }
    return resultadoOperacao;
  }

  // ###### M�todos GET (P�blicos) ######
  public void setValLimDecisao(double novoLimiteDecisao) {
    limiteDecisao = novoLimiteDecisao;
  }

  public int getQtdFatores() {
    return quantidadeFatores;
  }

  public int getQtdGrupos() {
    return quantidadeGrupos;
  }

  public int getQtdPareceres() {
    return quantidadePareceres;
  }

  // ###### M�todos SET (P�blicos) ######
  public int getQtdUsuarios() {
    return quantidadeUsuarios;
  }

  // ###### M�todos Privados ######
  private String getArrFatoresModel() {
    String resultadoOperacao = "NOK";

    try {
      listaFatores = agendaFatoresRepository.getArrFatoresModel(dadosAgenda);
      if (!(listaFatores == null)) {
        if (listaFatores.size() > 0) resultadoOperacao = "OK";
      }
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String getArrEspecialistasModel() {
    String resultadoOperacao = "NOK";

    try {
      listaUsuarios = agendaUsuariosRepository.getArrEspecialistasModel(dadosAgenda);
      if (!(listaUsuarios == null)) {
        if (listaUsuarios.size() > 0) resultadoOperacao = "OK";
      }
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String getArrPareceresAgenda() {
    String resultadoOperacao = "NOK";
    dadosAgendaPareceres = new AgendaPareceresDados();

    try {
      dadosAgendaPareceres.setoAgendaModel(dadosAgenda);
      dadosAgendaPareceres =
          agendaPareceresRepository.selectPareceresDaAgenda(dadosAgendaPareceres);
      quantidadePareceres = dadosAgendaPareceres.getArrParecerFatorUsuarioModel().size();
      if (quantidadePareceres > 0) resultadoOperacao = "OK";
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String montaMatrizesIndices() {
    String resultadoOperacao = "NOK";
    int[][] padrao = {{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}};
    quantidadeUsuarios = listaUsuarios.size();
    quantidadeFatores = listaFatores.size();
    matrizIndicesUsuarios = new long[quantidadeUsuarios][3];
    matrizIndicesFatores = new long[quantidadeFatores][2];
    quantidadeGrupos = 2;
    if (quantidadeUsuarios > 6) {
      if (quantidadeUsuarios % 3 == 0) quantidadeGrupos = quantidadeUsuarios / 3;
      else quantidadeGrupos = quantidadeUsuarios / 3 + 1;
    }
    if (quantidadeUsuarios > 0 && quantidadeFatores > 0) {
      try {
        int indiceUsuarioNoGrupo = -1;
        int indiceGrupoAtual = 1;
        for (int indiceRegistro = 0; indiceRegistro < quantidadeUsuarios; indiceRegistro++) {
          if (indiceRegistro < padrao.length) {
            matrizIndicesUsuarios[indiceRegistro][0] = padrao[indiceRegistro][0]; // Indice Usuario
            matrizIndicesUsuarios[indiceRegistro][1] = padrao[indiceRegistro][1]; // Indice Grupo
            matrizIndicesUsuarios[indiceRegistro][2] = listaUsuarios.get(indiceRegistro).getA02_codigo(); // Cod Usu
          } else {
            indiceUsuarioNoGrupo++;
            if (indiceUsuarioNoGrupo == 0) indiceGrupoAtual++;
            matrizIndicesUsuarios[indiceRegistro][0] = indiceUsuarioNoGrupo; // Indice Usuario
            matrizIndicesUsuarios[indiceRegistro][1] = indiceGrupoAtual; // Indice Grupo
            matrizIndicesUsuarios[indiceRegistro][2] = listaUsuarios.get(indiceRegistro).getA02_codigo(); // Cod Usu
            if (indiceUsuarioNoGrupo == 2) indiceUsuarioNoGrupo = -1;
          }
        }
        for (int indiceRegistro = 0; indiceRegistro < quantidadeFatores; indiceRegistro++) {
          matrizIndicesFatores[indiceRegistro][0] = indiceRegistro; // Indice Fator
          matrizIndicesFatores[indiceRegistro][1] = listaFatores.get(indiceRegistro).getA06_codigo();
        }
        resultadoOperacao = "OK";
      } catch (Exception excecao) {
      }
    }
    return resultadoOperacao;
  }

  private String montaMatriz_Fase01() {
    String resultadoOperacao = "NOK";
    matrizCerteza = new double[quantidadeFatores][quantidadeGrupos][3];
    matrizContradicao = new double[quantidadeFatores][quantidadeGrupos][3];
    this.preparaMatriz(matrizCerteza, -10);
    this.preparaMatriz(matrizContradicao, 110);
    if (quantidadePareceres > 0) {
      try {
        ArrayList<Usuario> usuariosDosPareceres = dadosAgendaPareceres.getArrUsuarioModel();
        ArrayList<ParecerFatorUsuario> pareceresDosFatores =
            dadosAgendaPareceres.getArrParecerFatorUsuarioModel();
        long codigoUsuarioParecer = 0;
        long codigoFatorParecer = 0;
        int indiceUsuarioParecer = 0;
        int indiceGrupoParecer = 0;
        int indiceFatorParecer = 0;
        int[] indicesUsuarioEGrupo = new int[2];
        for (int indiceRegistro = 0; indiceRegistro < quantidadePareceres; indiceRegistro++) {
          // pegando as posicoes
          codigoUsuarioParecer = usuariosDosPareceres.get(indiceRegistro).getA02_codigo();
          indicesUsuarioEGrupo = this.getIndiceUsuarioGupo(codigoUsuarioParecer);
          indiceUsuarioParecer = indicesUsuarioEGrupo[0];
          indiceGrupoParecer = indicesUsuarioEGrupo[1];
          codigoFatorParecer = pareceresDosFatores.get(indiceRegistro).getA06_codigo();
          indiceFatorParecer = this.getIndiceFator(codigoFatorParecer);
          // pegando os valores de certeza e contradicao
          if (indiceUsuarioParecer > -1) { // s� para usu�rio Especialista
            // + "]" + "[" + posU + "]");
            matrizCerteza[indiceFatorParecer][indiceGrupoParecer][indiceUsuarioParecer] = pareceresDosFatores.get(indiceRegistro).getA07_certeza();
            matrizContradicao[indiceFatorParecer][indiceGrupoParecer][indiceUsuarioParecer] = pareceresDosFatores.get(indiceRegistro).getA07_contradicao();
          }
        }
        resultadoOperacao = "OK";
      } catch (Exception excecao) {
      }
    }
    return resultadoOperacao;
  }

  private String realizaMaximizacao() {
    String resultadoOperacao = "NOK";
    // matriz[F][G]
    matrizCertezaMaximizada = new double[quantidadeFatores][quantidadeGrupos];
    matrizContradicaoMaximizada = new double[quantidadeFatores][quantidadeGrupos];
    double maior = 0;
    double menor = 0;
    try {
      for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
        for (int indiceGrupo = 0; indiceGrupo < quantidadeGrupos; indiceGrupo++) {
          maior = matrizCerteza[indiceFator][indiceGrupo][0];
          menor = matrizContradicao[indiceFator][indiceGrupo][0];
          for (int indiceUsuario = 0; indiceUsuario < 3; indiceUsuario++) {
            if (matrizCerteza[indiceFator][indiceGrupo][indiceUsuario] > maior) maior = matrizCerteza[indiceFator][indiceGrupo][indiceUsuario];
            if (matrizContradicao[indiceFator][indiceGrupo][indiceUsuario] < menor) menor = matrizContradicao[indiceFator][indiceGrupo][indiceUsuario];
          }
          matrizCertezaMaximizada[indiceFator][indiceGrupo] = maior;
          matrizContradicaoMaximizada[indiceFator][indiceGrupo] = menor;
        }
      }
      resultadoOperacao = "OK";
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String realizaMinimizacao() {
    String resultadoOperacao = "NOK";
    // matriz[F]
    matrizCertezaMinimizada = new double[quantidadeFatores];
    matrizContradicaoMinimizada = new double[quantidadeFatores];
    double menor = 0;
    double maior = 0;
    try {
      for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
        menor = matrizCertezaMaximizada[indiceFator][0];
        maior = matrizContradicaoMaximizada[indiceFator][0];
        for (int indiceGrupo = 0; indiceGrupo < quantidadeGrupos; indiceGrupo++) {
          if (matrizCertezaMaximizada[indiceFator][indiceGrupo] < menor) menor = matrizCertezaMaximizada[indiceFator][indiceGrupo];
          if (matrizContradicaoMaximizada[indiceFator][indiceGrupo] > maior) maior = matrizContradicaoMaximizada[indiceFator][indiceGrupo];
        }
        matrizCertezaMinimizada[indiceFator] = menor;
        matrizContradicaoMinimizada[indiceFator] = maior;
      }
      resultadoOperacao = "OK";
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String calcularGrausFatores() {
    String resultadoOperacao = "NOK";
    grausCertezaFatores = new double[quantidadeFatores];
    grausIncertezaFatores = new double[quantidadeFatores];
    try {
      dadosAgendaFatores = new AgendaFatoresDados();

      Fator dadosFator;
      ArrayList<Fator> listaFator = new ArrayList<Fator>();
      for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
        dadosFator = new Fator();
        dadosFator.setA06_codigo(matrizIndicesFatores[indiceFator][1]);
        grausCertezaFatores[indiceFator] = matrizCertezaMinimizada[indiceFator] - matrizContradicaoMinimizada[indiceFator];
        grausIncertezaFatores[indiceFator] = matrizCertezaMinimizada[indiceFator] + matrizContradicaoMinimizada[indiceFator] - 100;
        dadosFator.setA06_certeza_resultante_fator(grausCertezaFatores[indiceFator]);
        dadosFator.setA06_contradicao_resultante_fator(grausIncertezaFatores[indiceFator]);
        dadosFator.setA06_resultado_fator(
            this.calcularResultadosFinais(grausCertezaFatores[indiceFator], grausIncertezaFatores[indiceFator]));
        listaFator.add(dadosFator);
      }
      dadosAgendaFatores.setArrFatorModel(listaFator);
      resultadoOperacao = "OK";
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String calcularGrausAgenda() {
    String resultadoOperacao = "NOK";
    grauCertezaAgenda = 0;
    grauIncertezaAgenda = 0;
    try {
      // Realizando as Somat�rias
      for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
        grauCertezaAgenda += grausCertezaFatores[indiceFator];
        grauIncertezaAgenda += grausIncertezaFatores[indiceFator];
      }
      // Calculando as M�dias
      grauCertezaAgenda /= (double) quantidadeFatores;
      grauIncertezaAgenda /= (double) quantidadeFatores;
      // Guardando valores na Agenda
      dadosAgenda.setA04_certeza_resultado(grauCertezaAgenda);
      dadosAgenda.setA04_contradicao_resultado(grauIncertezaAgenda);
      dadosAgenda.setA04_resultado(
          this.calcularResultadosFinais(grauCertezaAgenda, grauIncertezaAgenda));
      dadosAgendaFatores.setoAgendaModel(dadosAgenda);
      resultadoOperacao = "OK";
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  private String calcularResultadosFinais(double grauCerteza, double grauIncerteza) {
    String resultadoOperacao = "Invi�vel";
    if (grauCerteza > Math.abs(limiteDecisao)) {
      resultadoOperacao = "Vi�vel";
    } else if (grauCerteza > 0) {
      resultadoOperacao = "Tend�ncia a ser Vi�vel";
    } else if (grauCerteza < (-1) * Math.abs(limiteDecisao)) {
      resultadoOperacao = "Invi�vel";
    } else if (grauCerteza <= 0) {
      resultadoOperacao = "Tend�ncia a ser Invi�vel";
    }
    if (Math.abs(grauIncerteza) > Math.abs(limiteDecisao)) {
      resultadoOperacao = "Invi�vel";
    } else if (Math.abs(grauIncerteza) > 10) {
      resultadoOperacao += ", com risco de Insucesso.";
    }
    return resultadoOperacao;
  }

  private String atualizarAgendaEFatores() {
    String resultadoOperacao = "NOK";
    try {
      resultadoOperacao = agendaFatoresService.updateGrausFatoresDaAgenda(dadosAgendaFatores);
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  // ###### M�todos Auxiliares ######
  private int[] getIndiceUsuarioGupo(long codigoUsuarioProcurado) {
    int[] posicaoEncontrada = {-1, -1}; // (posUsuario, posGrupo)
    try {
      for (int indiceRegistro = 0; indiceRegistro < quantidadeUsuarios; indiceRegistro++) {
        if (codigoUsuarioProcurado == matrizIndicesUsuarios[indiceRegistro][2]) {
          posicaoEncontrada[0] = (int) matrizIndicesUsuarios[indiceRegistro][0];
          posicaoEncontrada[1] = (int) matrizIndicesUsuarios[indiceRegistro][1];
          break;
        }
      }
    } catch (Exception excecao) {
    }
    return posicaoEncontrada;
  }

  private int getIndiceFator(long codigoFatorProcurado) {
    int posicaoEncontrada = -1;
    try {
      for (int indiceRegistro = 0; indiceRegistro < quantidadeFatores; indiceRegistro++) {
        if (codigoFatorProcurado == matrizIndicesFatores[indiceRegistro][1]) {
          posicaoEncontrada = (int) matrizIndicesFatores[indiceRegistro][0];
          break;
        }
      }
    } catch (Exception excecao) {
    }
    return posicaoEncontrada;
  }

  private void preparaMatriz(double[][][] matriz, double valor) {
    try {
      int quantidadeFatoresMatriz = matriz.length;
      int quantidadeGruposMatriz = matriz[0].length;
      int quantidadeUsuariosMatriz = matriz[0][0].length;
      for (int indiceFatorMatriz = 0; indiceFatorMatriz < quantidadeFatoresMatriz; indiceFatorMatriz++) {
        for (int indiceGrupoMatriz = 0; indiceGrupoMatriz < quantidadeGruposMatriz; indiceGrupoMatriz++) {
          for (int indiceUsuarioMatriz = 0; indiceUsuarioMatriz < quantidadeUsuariosMatriz; indiceUsuarioMatriz++) {
            matriz[indiceFatorMatriz][indiceGrupoMatriz][indiceUsuarioMatriz] = valor;
          }
        }
      }
    } catch (Exception excecao) {
    }
  }

  private void mostraMatrizesValores() {
    String txtCert = "";
    String txtContr = "";
    for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
      txtCert += "[";
      txtContr += "[";
      for (int indiceGrupo = 0; indiceGrupo < quantidadeGrupos; indiceGrupo++) {
        txtCert += "[";
        txtContr += "[";
        for (int indiceUsuario = 0; indiceUsuario < 3; indiceUsuario++) {
          String certezaTexto = String.format("%,.1f", matrizCerteza[indiceFator][indiceGrupo][indiceUsuario]);
          txtCert += certezaTexto + ";\t";
          String contradicaoTexto = String.format("%,.1f", matrizContradicao[indiceFator][indiceGrupo][indiceUsuario]);
          txtContr += contradicaoTexto + ";\t";
        }
        txtCert += "]";
        txtContr += "]";
      }
      txtCert += "]\n";
      txtContr += "]\n";
    }
    System.out.println("Valor de Certeza");
    System.out.println(txtCert);
    System.out.println("Valor de Contradi��o");
    System.out.println(txtContr);
    txtCert = "";
    txtContr = "";
    for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
      txtCert += "[";
      txtContr += "[";
      for (int indiceGrupo = 0; indiceGrupo < quantidadeGrupos; indiceGrupo++) {
        txtCert += "[";
        txtContr += "[";
        String certezaTexto = String.format("%,.1f", matrizCertezaMaximizada[indiceFator][indiceGrupo]);
        txtCert += certezaTexto + "\t";
        String contradicaoTexto = String.format("%,.1f", matrizContradicaoMaximizada[indiceFator][indiceGrupo]);
        txtContr += contradicaoTexto + "\t";
        txtCert += "]";
        txtContr += "]";
      }
      txtCert += "]\n";
      txtContr += "]\n";
    }
    System.out.println("MAXIMIZA��O:");
    System.out.println("Maior Certeza");
    System.out.println(txtCert);
    System.out.println("Menor Contradi��o");
    System.out.println(txtContr);
    txtCert = "";
    txtContr = "";
    for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
      txtCert += "[";
      txtContr += "[";
      String certezaTexto = String.format("%,.1f", matrizCertezaMinimizada[indiceFator]);
      txtCert += certezaTexto + "\t";
      String contradicaoTexto = String.format("%,.1f", matrizContradicaoMinimizada[indiceFator]);
      txtContr += contradicaoTexto + "\t";
      txtCert += "]\n";
      txtContr += "]\n";
    }
    System.out.println("MINIMIZA��O:");
    System.out.println("Menor Certeza");
    System.out.println(txtCert);
    System.out.println("Maior Contradi��o");
    System.out.println(txtContr);
    txtCert = "";
    txtContr = "";
    for (int indiceFator = 0; indiceFator < quantidadeFatores; indiceFator++) {
      txtCert += "[";
      txtContr += "[";
      String certezaTexto = String.format("%,.1f", grausCertezaFatores[indiceFator]);
      txtCert += certezaTexto + "\t";
      String contradicaoTexto = String.format("%,.1f", grausIncertezaFatores[indiceFator]);
      txtContr += contradicaoTexto + "\t";
      txtCert += "]\n";
      txtContr += "]\n";
    }
    System.out.println("GRAU POR FATOR:");
    System.out.println("Grau de Certeza");
    System.out.println(txtCert);
    System.out.println("Grau de Incerteza");
    System.out.println(txtContr);
    txtCert = "";
    txtContr = "";
    String certezaTexto = String.format("%,.1f", grauCertezaAgenda);
    txtCert += certezaTexto;
    String contradicaoTexto = String.format("%,.1f", grauIncertezaAgenda);
    txtContr += contradicaoTexto;
    System.out.println("GRAU DA AGENDA:");
    System.out.println("Grau de Certeza");
    System.out.println(txtCert);
    System.out.println("Grau de Incerteza");
    System.out.println(txtContr);
  }
}
