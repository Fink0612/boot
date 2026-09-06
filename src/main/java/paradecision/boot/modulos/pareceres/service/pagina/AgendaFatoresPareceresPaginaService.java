package paradecision.boot.modulos.pareceres.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaFatoresService;
import paradecision.boot.modulos.agendas.service.AgendaService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.pareceres.service.ParecerFatorUsuarioService;
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class AgendaFatoresPareceresPaginaService {
  private final AgendaFatoresService agendaFatoresService;
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;
  private final ParecerFatorUsuarioService parecerFatorUsuarioService;

  public AgendaFatoresPareceresPaginaService(
      AgendaFatoresService agendaFatoresService,
      AgendaService agendaService,
      AgendaUsuarioPerfilService agendaUsuarioPerfilService,
      ParecerFatorUsuarioService parecerFatorUsuarioService) {
    this.agendaFatoresService = agendaFatoresService;
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
    this.parecerFatorUsuarioService = parecerFatorUsuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    long codigoAgendaPareceresAgendaControle = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    long codigoUsuarioPareceresAgendaControle = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    Agenda dadosAgenda = new Agenda();

    AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();

    String tituloAgendaPareceresAgenda = "";
    String statusDataLimiteAgendaPareceresAgenda = "";
    String dataLimiteAgendaPareceresAgenda = "";
    String statusAgendaPareceresAgenda = "";
    String textoStatusAgendaPareceresAgenda = "";
    dadosAgenda.setA04_codigo(codigoAgendaPareceresAgendaControle);
    dadosAgenda = agendaService.selectAgenda(dadosAgenda);
    try {
      if (dadosAgenda.getA01_codigo() > 0) {
        tituloAgendaPareceresAgenda = dadosAgenda.getA04_titulo();
        statusDataLimiteAgendaPareceresAgenda = Long.toString(dadosAgenda.getA04_status_dt_limite());
        if (statusDataLimiteAgendaPareceresAgenda.equals("1")) {
          dataLimiteAgendaPareceresAgenda = dadosAgenda.getA04_data_limite().toString();
        }
        statusAgendaPareceresAgenda = Integer.toString(dadosAgenda.getA04_status());
        textoStatusAgendaPareceresAgenda = MetodosUteis.retornaTxtStatusAgenda(statusAgendaPareceresAgenda);
      }
    } catch (Exception excecao) {
    }
    int perfilTitularParticipacaoAgendaPareceresAgenda = 0;
    int perfilFacilitadorParticipacaoAgendaPareceresAgenda = 0;
    int perfilEspecialistaParticipacaoAgendaPareceresAgenda = 0;
    int perfilAnalistaParticipacaoAgendaPareceresAgenda = 0;
    dadosAgendaUsuarioPerfil.setA02_codigo(codigoUsuarioPareceresAgendaControle);
    dadosAgendaUsuarioPerfil.setA04_codigo(codigoAgendaPareceresAgendaControle);
    dadosAgendaUsuarioPerfil =
        agendaUsuarioPerfilService.selectAgendaUsuarioPerfil(dadosAgendaUsuarioPerfil);
    try {
      if (dadosAgendaUsuarioPerfil.getA05_codigo() > 0) {
        perfilTitularParticipacaoAgendaPareceresAgenda = dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_titular();
        perfilFacilitadorParticipacaoAgendaPareceresAgenda =
            dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_facilitador();
        perfilEspecialistaParticipacaoAgendaPareceresAgenda =
            dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista();
        perfilAnalistaParticipacaoAgendaPareceresAgenda = dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_analista();
      }
    } catch (Exception excecao) {
    }
    AgendaFatoresDados dadosAgendaFatores = new AgendaFatoresDados();

    int achouFator = 0;
    dadosAgendaFatores.setoAgendaModel(dadosAgenda);
    dadosAgendaFatores = agendaFatoresService.selectFatoresDaAgenda(dadosAgendaFatores);
    if (dadosAgendaFatores.getArrFatorModel().size() > 0) {
      achouFator = 1;
    }

    int quantidadeFatoresAgenda = 0;
    if (achouFator == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      String codigoUsuarioTextoPareceresAgenda = formulario.valor("ct_A02_CODIGO");
      long codigoUsuarioNumericoPareceresAgenda = MetodosUteis.retornaLong(codigoUsuarioTextoPareceresAgenda);
      long codigoFatorNumericoPareceresAgenda = 0;
      Fator dadosFator;
      ArrayList<Fator> listaFator = new ArrayList<Fator>();
      listaFator = dadosAgendaFatores.getArrFatorModel();
      quantidadeFatoresAgenda = listaFator.size();
      Usuario dadosUsuario;
      ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
      listaUsuario = dadosAgendaFatores.getArrUsuarioModel();
      for (int indiceRegistro = 0; indiceRegistro < listaFator.size(); indiceRegistro++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        long codigoParecer = 0;
        double numCertezaContradicao = -1;
        String valStr = "";
        String strCerteza = "";
        String strContradicao = "";
        dadosFator = dadosAgendaFatores.getArrFatorModel().get(indiceRegistro);
        dadosUsuario = dadosAgendaFatores.getArrUsuarioModel().get(indiceRegistro);
        ParecerFatorUsuario dadosParecerFatorUsuario = new ParecerFatorUsuario();

        dadosParecerFatorUsuario.setA02_codigo(codigoUsuarioNumericoPareceresAgenda);
        codigoFatorNumericoPareceresAgenda = dadosFator.getA06_codigo();
        dadosParecerFatorUsuario.setA06_codigo(codigoFatorNumericoPareceresAgenda);
        dadosParecerFatorUsuario =
            parecerFatorUsuarioService.selectParecerFatorUsuario(dadosParecerFatorUsuario);
        codigoParecer = dadosParecerFatorUsuario.getA07_codigo();
        if (codigoParecer > 0) {
          numCertezaContradicao = dadosParecerFatorUsuario.getA07_certeza();
          strCerteza = "";
          valStr = dadosParecerFatorUsuario.getStr_a07_certeza();
          if (valStr != null) {
            if (!(valStr.equals(""))) {
              if (numCertezaContradicao >= 0) strCerteza = Double.toString(numCertezaContradicao);
            }
          }
          numCertezaContradicao = dadosParecerFatorUsuario.getA07_contradicao();
          strContradicao = "";
          valStr = dadosParecerFatorUsuario.getStr_a07_contradicao();
          if (valStr != null) {
            if (!(valStr.equals(""))) {
              if (numCertezaContradicao >= 0)
                strContradicao = Double.toString(numCertezaContradicao);
            }
          }
        }

        linha2.put("ii", String.valueOf(indiceRegistro));

        linha2.put("num_afp_A06_CODIGO", String.valueOf(codigoFatorNumericoPareceresAgenda));

        linha2.put("oFatorModel_A06_titulo", String.valueOf(dadosFator.getA06_titulo()));

        linha2.put("oFatorModel_A06_descricao", String.valueOf(dadosFator.getA06_descricao()));

        String chkMark = "";
        String corCerteza = "";
        for (int indiceNivelParecer = 0; indiceNivelParecer <= 10; indiceNivelParecer++) {
          Map<String, Object> linha3 = DadosPagina.novaLinha(linha2, "linhas3");

          double valorGrauParecer = 0;
          int percentualNivelParecer = indiceNivelParecer * 10;
          chkMark = "nchk";
          corCerteza = MetodosUteis.getCorNivelCert(indiceNivelParecer);
          if (!(strCerteza.equals(""))) {
            valorGrauParecer = MetodosUteis.retornaDouble(strCerteza);
            if (percentualNivelParecer < valorGrauParecer + 1 && percentualNivelParecer > valorGrauParecer - 1) {
              chkMark = "chk";
            }
          }

          linha3.put("chkMark", String.valueOf(chkMark));

          linha3.put("corCert", String.valueOf(corCerteza));

          linha3.put("ii", String.valueOf(indiceRegistro));

          linha3.put("kk", String.valueOf(indiceNivelParecer));

          linha3.put("mm", String.valueOf(percentualNivelParecer));

          linha3.put("ii2", String.valueOf(indiceRegistro));

          linha3.put("kk2", String.valueOf(indiceNivelParecer));

          linha3.put("kk3", String.valueOf(indiceNivelParecer));
        }
        linha2.put("ii2", String.valueOf(indiceRegistro));

        linha2.put("ii3", String.valueOf(indiceRegistro));

        linha2.put("strCerteza", String.valueOf(strCerteza));

        String corContradicao = "";
        for (int indiceNivelParecer = 0; indiceNivelParecer <= 10; indiceNivelParecer++) {
          Map<String, Object> linha4 = DadosPagina.novaLinha(linha2, "linhas4");

          double valorGrauParecer = 0;
          int percentualNivelParecer = indiceNivelParecer * 10;
          chkMark = "nchk";
          corContradicao = MetodosUteis.getCorNivelContr(indiceNivelParecer);
          if (!(strContradicao.equals(""))) {
            valorGrauParecer = MetodosUteis.retornaDouble(strContradicao);
            if (percentualNivelParecer < valorGrauParecer + 1 && percentualNivelParecer > valorGrauParecer - 1) {
              chkMark = "chk";
            }
          }

          linha4.put("chkMark", String.valueOf(chkMark));

          linha4.put("corContr", String.valueOf(corContradicao));

          linha4.put("ii", String.valueOf(indiceRegistro));

          linha4.put("kk", String.valueOf(indiceNivelParecer));

          linha4.put("mm", String.valueOf(percentualNivelParecer));

          linha4.put("ii2", String.valueOf(indiceRegistro));

          linha4.put("kk2", String.valueOf(indiceNivelParecer));

          linha4.put("kk3", String.valueOf(indiceNivelParecer));
        }
        linha2.put("ii4", String.valueOf(indiceRegistro));

        linha2.put("ii5", String.valueOf(indiceRegistro));

        linha2.put("strContradicao", String.valueOf(strContradicao));
      }
    }
    pagina.put("afp_A04_TITULO", String.valueOf(tituloAgendaPareceresAgenda));

    pagina.put("afp_A04_STATUS_DT_LIMITE", String.valueOf(statusDataLimiteAgendaPareceresAgenda));

    pagina.put("afp_A04_DATA_LIMITE", String.valueOf(dataLimiteAgendaPareceresAgenda));

    pagina.put("afp_A04_STATUS", String.valueOf(statusAgendaPareceresAgenda));

    pagina.put("afp_A04_TXT_STATUS", String.valueOf(textoStatusAgendaPareceresAgenda));

    pagina.put("afp_A05_PERFIL_TITULAR", String.valueOf(perfilTitularParticipacaoAgendaPareceresAgenda));

    pagina.put("afp_A05_PERFIL_FACILITADOR", String.valueOf(perfilFacilitadorParticipacaoAgendaPareceresAgenda));

    pagina.put("afp_A05_PERFIL_ESPECIALISTA", String.valueOf(perfilEspecialistaParticipacaoAgendaPareceresAgenda));

    pagina.put("afp_A05_PERFIL_ANALISTA", String.valueOf(perfilAnalistaParticipacaoAgendaPareceresAgenda));

    pagina.put("qtdFatoresAgenda", String.valueOf(quantidadeFatoresAgenda));

    return pagina;
  }
}
