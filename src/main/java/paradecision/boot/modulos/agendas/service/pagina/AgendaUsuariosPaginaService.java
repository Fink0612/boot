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

    Agenda dadosAgenda = new Agenda();

    AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();

    long codigoAgendaAgendaUsuariosControle = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    String tituloAgendaAgendaUsuarios = "";
    String descricaoAgendaAgendaUsuarios = "";
    String statusDataLimiteAgendaAgendaUsuarios = "";
    String dataLimiteAgendaAgendaUsuarios = "";
    String statusAgendaAgendaUsuarios = "";
    String textoStatusAgendaAgendaUsuarios = "";
    dadosAgenda.setA04_codigo(codigoAgendaAgendaUsuariosControle);
    dadosAgenda = agendaService.selectAgenda(dadosAgenda);
    try {
      if (dadosAgenda.getA01_codigo() > 0) {
        tituloAgendaAgendaUsuarios = dadosAgenda.getA04_titulo();
        descricaoAgendaAgendaUsuarios = dadosAgenda.getA04_descricao();
        statusDataLimiteAgendaAgendaUsuarios = Long.toString(dadosAgenda.getA04_status_dt_limite());
        if (statusDataLimiteAgendaAgendaUsuarios.equals("1")) {
          dataLimiteAgendaAgendaUsuarios = dadosAgenda.getA04_data_limite().toString();
        }
        statusAgendaAgendaUsuarios = Integer.toString(dadosAgenda.getA04_status());
        textoStatusAgendaAgendaUsuarios = MetodosUteis.retornaTxtStatusAgenda(statusAgendaAgendaUsuarios);
      }
    } catch (Exception excecao) {
    }
    long codigoUsuarioAgendaUsuariosControle = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    int perfilTitularParticipacaoAgendaAgendaUsuarios = 0;
    int perfilFacilitadorParticipacaoAgendaAgendaUsuarios = 0;
    int perfilEspecialistaParticipacaoAgendaAgendaUsuarios = 0;
    int perfilAnalistaParticipacaoAgendaAgendaUsuarios = 0;
    dadosAgendaUsuarioPerfil.setA02_codigo(codigoUsuarioAgendaUsuariosControle);
    dadosAgendaUsuarioPerfil.setA04_codigo(codigoAgendaAgendaUsuariosControle);
    dadosAgendaUsuarioPerfil =
        agendaUsuarioPerfilService.selectAgendaUsuarioPerfil(dadosAgendaUsuarioPerfil);
    try {
      if (dadosAgendaUsuarioPerfil.getA05_codigo() > 0) {
        perfilTitularParticipacaoAgendaAgendaUsuarios = dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_titular();
        perfilFacilitadorParticipacaoAgendaAgendaUsuarios =
            dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_facilitador();
        perfilEspecialistaParticipacaoAgendaAgendaUsuarios =
            dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista();
        perfilAnalistaParticipacaoAgendaAgendaUsuarios = dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_analista();
      }
    } catch (Exception excecao) {
    }
    AgendaUsuariosDados dadosAgendaUsuarios = new AgendaUsuariosDados();

    int achouUsuario = 0;
    dadosAgendaUsuarios.setoAgendaModel(dadosAgenda);
    dadosAgendaUsuarios = agendaUsuariosService.selectUsuariosDaAgenda(dadosAgendaUsuarios);
    if (dadosAgendaUsuarios.getArrUsuarioModel().size() > 0) {
      achouUsuario = 1;
    }

    int num_QTD_ESPECIALISTAS_AGENDA = 0;
    if (achouUsuario == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      Usuario dadosUsuario;
      ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
      listaUsuario = dadosAgendaUsuarios.getArrUsuarioModel();
      for (int indiceElemento = 0; indiceElemento < listaUsuario.size(); indiceElemento++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        dadosUsuario = dadosAgendaUsuarios.getArrUsuarioModel().get(indiceElemento);
        dadosAgendaUsuarioPerfil = dadosAgendaUsuarios.getArrAgendaUsuarioPerfilModel().get(indiceElemento);
        if (dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista() == 1) {
          num_QTD_ESPECIALISTAS_AGENDA++;
        }

        linha2.put("oUsuarioModel_A02_nome", String.valueOf(dadosUsuario.getA02_nome()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_titular",
            String.valueOf(dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_titular()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_facilitador",
            String.valueOf(dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_facilitador()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_especialista",
            String.valueOf(dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista()));

        linha2.put(
            "oAgendaUsuarioPerfilModel_A05_perfil_agenda_usuario_analista",
            String.valueOf(dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_analista()));
      }
    }
    pagina.put("au_A04_TITULO", String.valueOf(tituloAgendaAgendaUsuarios));

    pagina.put("au_A04_DESCRICAO", String.valueOf(descricaoAgendaAgendaUsuarios));

    pagina.put("au_A04_STATUS_DT_LIMITE", String.valueOf(statusDataLimiteAgendaAgendaUsuarios));

    pagina.put("au_A04_DATA_LIMITE", String.valueOf(dataLimiteAgendaAgendaUsuarios));

    pagina.put("au_A04_STATUS", String.valueOf(statusAgendaAgendaUsuarios));

    pagina.put("au_A04_TXT_STATUS", String.valueOf(textoStatusAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_TITULAR", String.valueOf(perfilTitularParticipacaoAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_FACILITADOR", String.valueOf(perfilFacilitadorParticipacaoAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_ESPECIALISTA", String.valueOf(perfilEspecialistaParticipacaoAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_ANALISTA", String.valueOf(perfilAnalistaParticipacaoAgendaAgendaUsuarios));

    pagina.put("num_QTD_ESPECIALISTAS_AGENDA", String.valueOf(num_QTD_ESPECIALISTAS_AGENDA));

    return pagina;
  }
}
