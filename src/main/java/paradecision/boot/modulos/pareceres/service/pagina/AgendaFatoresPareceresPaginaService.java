package paradecision.boot.modulos.pareceres.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.dto.DadosPagina;
import paradecision.boot.compartilhado.util.MetodosUteis;
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

    long afp_ct_A04_CODIGO = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    long afp_ct_A02_CODIGO = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    Agenda oAgendaModel = new Agenda();

    AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();

    String afp_A04_TITULO = "";
    String afp_A04_STATUS_DT_LIMITE = "";
    String afp_A04_DATA_LIMITE = "";
    String afp_A04_STATUS = "";
    String afp_A04_TXT_STATUS = "";
    oAgendaModel.setA04_codigo(afp_ct_A04_CODIGO);
    oAgendaModel = agendaService.selectAgenda(oAgendaModel);
    try {
      if (oAgendaModel.getA01_codigo() > 0) {
        afp_A04_TITULO = oAgendaModel.getA04_titulo();
        afp_A04_STATUS_DT_LIMITE = Long.toString(oAgendaModel.getA04_status_dt_limite());
        if (afp_A04_STATUS_DT_LIMITE.equals("1")) {
          afp_A04_DATA_LIMITE = oAgendaModel.getA04_data_limite().toString();
        }
        afp_A04_STATUS = Integer.toString(oAgendaModel.getA04_status());
        afp_A04_TXT_STATUS = MetodosUteis.retornaTxtStatusAgenda(afp_A04_STATUS);
      }
    } catch (Exception e) {
    }
    int afp_A05_PERFIL_TITULAR = 0;
    int afp_A05_PERFIL_FACILITADOR = 0;
    int afp_A05_PERFIL_ESPECIALISTA = 0;
    int afp_A05_PERFIL_ANALISTA = 0;
    oAgendaUsuarioPerfilModel.setA02_codigo(afp_ct_A02_CODIGO);
    oAgendaUsuarioPerfilModel.setA04_codigo(afp_ct_A04_CODIGO);
    oAgendaUsuarioPerfilModel =
        agendaUsuarioPerfilService.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
    try {
      if (oAgendaUsuarioPerfilModel.getA05_codigo() > 0) {
        afp_A05_PERFIL_TITULAR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
        afp_A05_PERFIL_FACILITADOR =
            oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador();
        afp_A05_PERFIL_ESPECIALISTA =
            oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista();
        afp_A05_PERFIL_ANALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista();
      }
    } catch (Exception e) {
    }
    AgendaFatoresDados oAgendaFatoresModel = new AgendaFatoresDados();

    int achouFator = 0;
    oAgendaFatoresModel.setoAgendaModel(oAgendaModel);
    oAgendaFatoresModel = agendaFatoresService.selectFatoresDaAgenda(oAgendaFatoresModel);
    if (oAgendaFatoresModel.getArrFatorModel().size() > 0) {
      achouFator = 1;
    }

    int qtdFatoresAgenda = 0;
    if (achouFator == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      String txt_afp_A02_CODIGO = formulario.valor("ct_A02_CODIGO");
      long num_afp_A02_CODIGO = MetodosUteis.retornaLong(txt_afp_A02_CODIGO);
      long num_afp_A06_CODIGO = 0;
      Fator oFatorModel;
      ArrayList<Fator> arrFatorModel = new ArrayList<Fator>();
      arrFatorModel = oAgendaFatoresModel.getArrFatorModel();
      qtdFatoresAgenda = arrFatorModel.size();
      Usuario oUsuarioModel;
      ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
      arrUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel();
      for (int ii = 0; ii < arrFatorModel.size(); ii++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        long codParecer = 0;
        double numCertezaContradicao = -1;
        String valStr = "";
        String strCerteza = "";
        String strContradicao = "";
        oFatorModel = oAgendaFatoresModel.getArrFatorModel().get(ii);
        oUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel().get(ii);
        ParecerFatorUsuario oParecerFatorUsuarioModel = new ParecerFatorUsuario();

        oParecerFatorUsuarioModel.setA02_codigo(num_afp_A02_CODIGO);
        num_afp_A06_CODIGO = oFatorModel.getA06_codigo();
        oParecerFatorUsuarioModel.setA06_codigo(num_afp_A06_CODIGO);
        oParecerFatorUsuarioModel =
            parecerFatorUsuarioService.selectParecerFatorUsuario(oParecerFatorUsuarioModel);
        codParecer = oParecerFatorUsuarioModel.getA07_codigo();
        if (codParecer > 0) {
          numCertezaContradicao = oParecerFatorUsuarioModel.getA07_certeza();
          strCerteza = "";
          valStr = oParecerFatorUsuarioModel.getStr_a07_certeza();
          if (valStr != null) {
            if (!(valStr.equals(""))) {
              if (numCertezaContradicao >= 0) strCerteza = Double.toString(numCertezaContradicao);
            }
          }
          numCertezaContradicao = oParecerFatorUsuarioModel.getA07_contradicao();
          strContradicao = "";
          valStr = oParecerFatorUsuarioModel.getStr_a07_contradicao();
          if (valStr != null) {
            if (!(valStr.equals(""))) {
              if (numCertezaContradicao >= 0)
                strContradicao = Double.toString(numCertezaContradicao);
            }
          }
        }

        linha2.put("ii", String.valueOf(ii));

        linha2.put("num_afp_A06_CODIGO", String.valueOf(num_afp_A06_CODIGO));

        linha2.put("oFatorModel_A06_titulo", String.valueOf(oFatorModel.getA06_titulo()));

        linha2.put("oFatorModel_A06_descricao", String.valueOf(oFatorModel.getA06_descricao()));

        String chkMark = "";
        String corCert = "";
        for (int kk = 0; kk <= 10; kk++) {
          Map<String, Object> linha3 = DadosPagina.novaLinha(linha2, "linhas3");

          double valC = 0;
          int mm = kk * 10;
          chkMark = "nchk";
          corCert = MetodosUteis.getCorNivelCert(kk);
          if (!(strCerteza.equals(""))) {
            valC = MetodosUteis.retornaDouble(strCerteza);
            if (mm < valC + 1 && mm > valC - 1) {
              chkMark = "chk";
            }
          }

          linha3.put("chkMark", String.valueOf(chkMark));

          linha3.put("corCert", String.valueOf(corCert));

          linha3.put("ii", String.valueOf(ii));

          linha3.put("kk", String.valueOf(kk));

          linha3.put("mm", String.valueOf(mm));

          linha3.put("ii2", String.valueOf(ii));

          linha3.put("kk2", String.valueOf(kk));

          linha3.put("kk3", String.valueOf(kk));
        }
        linha2.put("ii2", String.valueOf(ii));

        linha2.put("ii3", String.valueOf(ii));

        linha2.put("strCerteza", String.valueOf(strCerteza));

        String corContr = "";
        for (int kk = 0; kk <= 10; kk++) {
          Map<String, Object> linha4 = DadosPagina.novaLinha(linha2, "linhas4");

          double valC = 0;
          int mm = kk * 10;
          chkMark = "nchk";
          corContr = MetodosUteis.getCorNivelContr(kk);
          if (!(strContradicao.equals(""))) {
            valC = MetodosUteis.retornaDouble(strContradicao);
            if (mm < valC + 1 && mm > valC - 1) {
              chkMark = "chk";
            }
          }

          linha4.put("chkMark", String.valueOf(chkMark));

          linha4.put("corContr", String.valueOf(corContr));

          linha4.put("ii", String.valueOf(ii));

          linha4.put("kk", String.valueOf(kk));

          linha4.put("mm", String.valueOf(mm));

          linha4.put("ii2", String.valueOf(ii));

          linha4.put("kk2", String.valueOf(kk));

          linha4.put("kk3", String.valueOf(kk));
        }
        linha2.put("ii4", String.valueOf(ii));

        linha2.put("ii5", String.valueOf(ii));

        linha2.put("strContradicao", String.valueOf(strContradicao));
      }
    }
    pagina.put("afp_A04_TITULO", String.valueOf(afp_A04_TITULO));

    pagina.put("afp_A04_STATUS_DT_LIMITE", String.valueOf(afp_A04_STATUS_DT_LIMITE));

    pagina.put("afp_A04_DATA_LIMITE", String.valueOf(afp_A04_DATA_LIMITE));

    pagina.put("afp_A04_STATUS", String.valueOf(afp_A04_STATUS));

    pagina.put("afp_A04_TXT_STATUS", String.valueOf(afp_A04_TXT_STATUS));

    pagina.put("afp_A05_PERFIL_TITULAR", String.valueOf(afp_A05_PERFIL_TITULAR));

    pagina.put("afp_A05_PERFIL_FACILITADOR", String.valueOf(afp_A05_PERFIL_FACILITADOR));

    pagina.put("afp_A05_PERFIL_ESPECIALISTA", String.valueOf(afp_A05_PERFIL_ESPECIALISTA));

    pagina.put("afp_A05_PERFIL_ANALISTA", String.valueOf(afp_A05_PERFIL_ANALISTA));

    pagina.put("qtdFatoresAgenda", String.valueOf(qtdFatoresAgenda));

    return pagina;
  }
}
