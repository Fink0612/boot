package paradecision.boot.modulos.compartilhado.util;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public abstract class MetodosUteis {

  public static String chaveCodificacaoLegada = "HOENIRTYRMAGNISNOTRAVARFORSETISKULDNEHALLENIAKVASIR";

  public static String gerarCodigo(int tamanhoSolicitado) {
    Random geradorAleatorio = new Random();
    String codigoGerado = "";
    String textoAuxiliar = "";
    int quantidadeBlocos = (tamanhoSolicitado / 10);
    if (tamanhoSolicitado % 10 > 0) quantidadeBlocos++;
    for (int indiceElemento = 0; indiceElemento < quantidadeBlocos; indiceElemento++) {
      textoAuxiliar = Double.toString(geradorAleatorio.nextDouble());
      textoAuxiliar = textoAuxiliar.replace("0.", "");
      codigoGerado += textoAuxiliar;
    }
    codigoGerado = codigoGerado.substring(0, tamanhoSolicitado);
    return codigoGerado;
  }

  public static String padronizarMaiusculoCE(String textoRecebido) {
    // Obs.: padr�o COM espa�os
    // retirando espa�os iniciais e finais, e deixando "CAIXA-ALTA"
    String textoNormalizado = textoRecebido.toUpperCase().trim();
    // retirando espa�os extras internos a String
    String espacosExcedentes = "  ";
    int posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    while (posicaoEncontrada >= 0) {
      textoNormalizado = textoNormalizado.replaceAll(espacosExcedentes, " ");
      posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    }
    return textoNormalizado;
  }

  public static String padronizarMinusculoSE(String textoRecebido) {
    // Obs.: padr�o SEM espa�os
    // retirando espa�os iniciais e finais, e deixando "caixa-baixa"
    String textoNormalizado = textoRecebido.toLowerCase().trim();
    // retirando espa�os internos a String
    String espacosExcedentes = " ";
    int posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    while (posicaoEncontrada >= 0) {
      textoNormalizado = textoNormalizado.replaceAll(espacosExcedentes, "");
      posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    }
    return textoNormalizado;
  }

  public static String padronizarMinusculoCE(String textoRecebido) {
    // Obs.: padr�o COM espa�os
    // retirando espa�os iniciais e finais, e deixando "caixa-baixa"
    String textoNormalizado = textoRecebido.toLowerCase().trim();
    // retirando espa�os extras internos a String
    String espacosExcedentes = "  ";
    int posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    while (posicaoEncontrada >= 0) {
      textoNormalizado = textoNormalizado.replaceAll(espacosExcedentes, " ");
      posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    }
    return textoNormalizado;
  }

  public static String padronizarEspacos(String textoRecebido) {
    // Obs.: padr�o COM espa�os
    // retirando espa�os iniciais e finais
    String textoNormalizado = textoRecebido.trim();
    // retirando espa�os extras internos a String
    String espacosExcedentes = "  ";
    int posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    while (posicaoEncontrada >= 0) {
      textoNormalizado = textoNormalizado.replaceAll(espacosExcedentes, " ");
      posicaoEncontrada = textoNormalizado.indexOf(espacosExcedentes);
    }
    return textoNormalizado;
  }

  public static java.sql.Date retornaDate(String dataTexto, String formatoData) {
    // Neste caso, se a data estiver em formato errado, retornar� nulo
    // Exemplo de chamada a este m�todo:
    // Date variavel_Date = MetodosUteis.retornaDate(variavel_String_de_Data, "yyyy-MM-dd");
    java.util.Date dataUtil = new java.util.Date();
    java.sql.Date dataSql = null;
    try {
      dataUtil = new SimpleDateFormat(formatoData).parse(dataTexto);
      dataSql = new java.sql.Date(dataUtil.getTime());
    } catch (ParseException excecao) {
    }
    return dataSql;
  }

  public static java.sql.Date retornaDataAgora() {
    // Retorna a data atual (de hoje)
    java.util.Date dataUtil = new java.util.Date();
    java.sql.Date dataSql = null;
    try {
      dataSql = new java.sql.Date(dataUtil.getTime());
    } catch (Exception excecao) {
    }
    return dataSql;
  }

  public static String getDatNowBD() {
    String resultadoOperacao = "";
    // Retorna a data atual (de hoje)
    java.util.Date dataUtil = new java.util.Date();
    SimpleDateFormat formatadorData = new SimpleDateFormat("yyyy-MM-dd");
    resultadoOperacao = formatadorData.format(dataUtil);
    return resultadoOperacao;
  }

  public static int retornaInt(String valorTexto) {
    int resultadoOperacao = -1;
    try {
      int valorNumerico = Integer.parseInt(valorTexto);
      resultadoOperacao = valorNumerico;
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  public static long retornaLong(String valorTexto) {
    long resultadoOperacao = -1;
    try {
      long valorNumerico = Long.parseLong(valorTexto);
      resultadoOperacao = valorNumerico;
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  public static double retornaDouble(String valorTexto) {
    double resultadoOperacao = -1;
    try {
      double valorNumerico = Double.parseDouble(valorTexto);
      resultadoOperacao = valorNumerico;
    } catch (Exception excecao) {
    }
    return resultadoOperacao;
  }

  public static String getCorNivelCert(int quantidadePareceres) {
    String resultadoOperacao = "";
    if (quantidadePareceres <= 3) {
      resultadoOperacao = "vm"; // vermelho
    } else if (quantidadePareceres <= 5) {
      resultadoOperacao = "lr"; // laranja
    } else if (quantidadePareceres <= 8) {
      resultadoOperacao = "vd"; // verde
    } else if (quantidadePareceres <= 10) {
      resultadoOperacao = "az"; // azul
    }
    return resultadoOperacao;
  }

  public static String getCorNivelContr(int quantidadePareceres) {
    String resultadoOperacao = "";
    if (quantidadePareceres >= 7) {
      resultadoOperacao = "vm"; // vermelho
    } else if (quantidadePareceres >= 5) {
      resultadoOperacao = "lr"; // laranja
    } else if (quantidadePareceres >= 2) {
      resultadoOperacao = "vd"; // verde
    } else if (quantidadePareceres >= 0) {
      resultadoOperacao = "az"; // azul
    }
    return resultadoOperacao;
  }

  public static String retornaTxtStatusAgenda(String sttAg) {
    String resultadoOperacao = "";
    if (sttAg == null) {
      resultadoOperacao = "Aguardando Encaminhamento";
    } else if (sttAg.equals("")) {
      resultadoOperacao = "Aguardando Encaminhamento";
    } else if (sttAg.equals("0")) {
      resultadoOperacao = "Aguardando Encaminhamento";
    } else if (sttAg.equals("1")) {
      resultadoOperacao = "Aguardando Fatores";
    } else if (sttAg.equals("2")) {
      resultadoOperacao = "Aguardando Pareceres";
    } else if (sttAg.equals("9")) {
      resultadoOperacao = "Fechada";
    } else {
      resultadoOperacao = "Indefinido";
    }
    return resultadoOperacao;
  }

  public static String getIpAddress() {
    String resultadoOperacao = "";
    InetAddress addr = null;
    try {
      addr = InetAddress.getLocalHost();
      resultadoOperacao = addr.getHostAddress();
    } catch (Exception excecao) {
      resultadoOperacao = "N�o foi poss�vel resgatar o IP desta m�quina.";
    }
    return resultadoOperacao;
  }
}
