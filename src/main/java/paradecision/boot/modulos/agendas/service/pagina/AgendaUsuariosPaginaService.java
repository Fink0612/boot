package paradecision.boot.modulos.agendas.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.dto.AgendaUsuariosDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;
import paradecision.boot.modulos.agendas.service.AgendaUsuariosService;
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class AgendaUsuariosPaginaService {
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;
  private final AgendaUsuariosService agendaUsuariosService;

  public AgendaUsuariosPaginaService(
      AgendaService agendaService,
      AgendaUsuarioPerfilService agendaUsuarioPerfilService,
      AgendaUsuariosService agendaUsuariosService) {
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
    this.agendaUsuariosService = agendaUsuariosService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Agenda oAgendaModel = new Agenda();

    AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();

    long au_ct_A04_CODIGO = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    String au_A04_TITULO = "";
    String au_A04_DESCRICAO = "";
    String au_A04_STATUS_DT_LIMITE = "";
    String au_A04_DATA_LIMITE = "";
    String au_A04_STATUS = "";
    String au_A04_TXT_STATUS = "";
    oAgendaModel.setA04_codigo(au_ct_A04_CODIGO);
    oAgendaModel = agendaService.selectAgenda(oAgendaModel);
    try {
      if (oAgendaModel.getA01_codigo() > 0) {
        au_A04_TITULO = oAgendaModel.getA04_titulo();
        au_A04_DESCRICAO = oAgendaModel.getA04_descricao();
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
    AgendaUsuariosDados oAgendaUsuariosModel = new AgendaUsuariosDados();

    int achouUsuario = 0;
    oAgendaUsuariosModel.setoAgendaModel(oAgendaModel);
    oAgendaUsuariosModel = agendaUsuariosService.selectUsuariosDaAgenda(oAgendaUsuariosModel);
    if (oAgendaUsuariosModel.getArrUsuarioModel().size() > 0) {
      achouUsuario = 1;
    }

    int num_QTD_ESPECIALISTAS_AGENDA = 0;
    if (achouUsuario == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      Usuario oUsuarioModel;
      ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
      arrUsuarioModel = oAgendaUsuariosModel.getArrUsuarioModel();
      for (int i = 0; i < arrUsuarioModel.size(); i++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        oUsuarioModel = oAgendaUsuariosModel.getArrUsuarioModel().get(i);
        oAgendaUsuarioPerfilModel = oAgendaUsuariosModel.getArrAgendaUsuarioPerfilModel().get(i);
        if (oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista() == 1) {
          num_QTD_ESPECIALISTAS_AGENDA++;
        }

        linha2.put("oUsuarioModel_A02_nome", String.valueOf(oUsuarioModel.getA02_nome()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_titular",
            String.valueOf(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_facilitador",
            String.valueOf(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_especialista",
            String.valueOf(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_analista",
            String.valueOf(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista()));
      }
    }
    pagina.put("au_A04_TITULO", String.valueOf(au_A04_TITULO));

    pagina.put("au_A04_DESCRICAO", String.valueOf(au_A04_DESCRICAO));

    pagina.put("au_A04_STATUS_DT_LIMITE", String.valueOf(au_A04_STATUS_DT_LIMITE));

    pagina.put("au_A04_DATA_LIMITE", String.valueOf(au_A04_DATA_LIMITE));

    pagina.put("au_A04_STATUS", String.valueOf(au_A04_STATUS));

    pagina.put("au_A04_TXT_STATUS", String.valueOf(au_A04_TXT_STATUS));

    pagina.put("au_A05_PERFIL_TITULAR", String.valueOf(au_A05_PERFIL_TITULAR));

    pagina.put("au_A05_PERFIL_FACILITADOR", String.valueOf(au_A05_PERFIL_FACILITADOR));

    pagina.put("au_A05_PERFIL_ESPECIALISTA", String.valueOf(au_A05_PERFIL_ESPECIALISTA));

    pagina.put("au_A05_PERFIL_ANALISTA", String.valueOf(au_A05_PERFIL_ANALISTA));

    pagina.put("num_QTD_ESPECIALISTAS_AGENDA", String.valueOf(num_QTD_ESPECIALISTAS_AGENDA));

    return pagina;
  }
}
