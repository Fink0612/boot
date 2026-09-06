package paradecision.boot.modulos.agendas.service.pagina;

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
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class AgendaFatoresPaginaService {
  private final AgendaFatoresService agendaFatoresService;
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;

  public AgendaFatoresPaginaService(
      AgendaFatoresService agendaFatoresService,
      AgendaService agendaService,
      AgendaUsuarioPerfilService agendaUsuarioPerfilService) {
    this.agendaFatoresService = agendaFatoresService;
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Agenda oAgendaModel = new Agenda();

    AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();

    long au_ct_A04_CODIGO = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    String au_A04_TITULO = "";
    String au_A04_STATUS_DT_LIMITE = "";
    String au_A04_DATA_LIMITE = "";
    String au_A04_STATUS = "";
    String au_A04_TXT_STATUS = "";
    oAgendaModel.setA04_codigo(au_ct_A04_CODIGO);
    oAgendaModel = agendaService.selectAgenda(oAgendaModel);
    try {
      if (oAgendaModel.getA01_codigo() > 0) {
        au_A04_TITULO = oAgendaModel.getA04_titulo();
        au_A04_STATUS_DT_LIMITE = Long.toString(oAgendaModel.getA04_status_dt_limite());
        if (au_A04_STATUS_DT_LIMITE.equals("1")) {
          au_A04_DATA_LIMITE = oAgendaModel.getA04_data_limite().toString();
        }
        au_A04_STATUS = Integer.toString(oAgendaModel.getA04_status());
        au_A04_TXT_STATUS = MetodosUteis.retornaTxtStatusAgenda(au_A04_STATUS);
      }
    } catch (Exception e) {
    }
    long au_ct_A02_CODIGO = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    int au_A05_PERFIL_TITULAR = 0;
    int au_A05_PERFIL_FACILITADOR = 0;
    int au_A05_PERFIL_ESPECIALISTA = 0;
    int au_A05_PERFIL_ANALISTA = 0;
    oAgendaUsuarioPerfilModel.setA02_codigo(au_ct_A02_CODIGO);
    oAgendaUsuarioPerfilModel.setA04_codigo(au_ct_A04_CODIGO);
    oAgendaUsuarioPerfilModel =
        agendaUsuarioPerfilService.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
    try {
      if (oAgendaUsuarioPerfilModel.getA05_codigo() > 0) {
        au_A05_PERFIL_TITULAR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
        au_A05_PERFIL_FACILITADOR =
            oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador();
        au_A05_PERFIL_ESPECIALISTA =
            oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista();
        au_A05_PERFIL_ANALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista();
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

    if (achouFator == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      Fator oFatorModel;
      ArrayList<Fator> arrFatorModel = new ArrayList<Fator>();
      arrFatorModel = oAgendaFatoresModel.getArrFatorModel();
      Usuario oUsuarioModel;
      ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
      arrUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel();
      String displayLink = "";
      String displaySoTexto = "";
      for (int i = 0; i < arrFatorModel.size(); i++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        oFatorModel = oAgendaFatoresModel.getArrFatorModel().get(i);
        oUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel().get(i);
        displayLink = "none";
        displaySoTexto = "inline";
        if (au_ct_A02_CODIGO == oUsuarioModel.getA02_codigo()) {
          displayLink = "inline";
          displaySoTexto = "none";
        }

        linha2.put("displayLink", String.valueOf(displayLink));

        linha2.put("oFatorModel_A06_codigo", String.valueOf(oFatorModel.getA06_codigo()));

        linha2.put("oFatorModel_A06_titulo", String.valueOf(oFatorModel.getA06_titulo()));

        linha2.put("displaySoTexto", String.valueOf(displaySoTexto));

        linha2.put("oFatorModel_A06_titulo2", String.valueOf(oFatorModel.getA06_titulo()));

        linha2.put("oUsuarioModel_A02_nome", String.valueOf(oUsuarioModel.getA02_nome()));

        linha2.put("oFatorModel_A06_descricao", String.valueOf(oFatorModel.getA06_descricao()));
      }
    }
    pagina.put("au_A04_TITULO", String.valueOf(au_A04_TITULO));

    pagina.put("au_A04_STATUS_DT_LIMITE", String.valueOf(au_A04_STATUS_DT_LIMITE));

    pagina.put("au_A04_DATA_LIMITE", String.valueOf(au_A04_DATA_LIMITE));

    pagina.put("au_A04_STATUS", String.valueOf(au_A04_STATUS));

    pagina.put("au_A04_TXT_STATUS", String.valueOf(au_A04_TXT_STATUS));

    pagina.put("au_A05_PERFIL_TITULAR", String.valueOf(au_A05_PERFIL_TITULAR));

    pagina.put("au_A05_PERFIL_FACILITADOR", String.valueOf(au_A05_PERFIL_FACILITADOR));

    pagina.put("au_A05_PERFIL_ESPECIALISTA", String.valueOf(au_A05_PERFIL_ESPECIALISTA));

    pagina.put("au_A05_PERFIL_ANALISTA", String.valueOf(au_A05_PERFIL_ANALISTA));

    return pagina;
  }
}
