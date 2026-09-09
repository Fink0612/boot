package paradecision.boot.modulos.agendas.service.pagina;

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
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class AgendaFatoresResultadosPaginaService {
  private final AgendaFatoresService agendaFatoresService;
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;

  public AgendaFatoresResultadosPaginaService(
      AgendaFatoresService agendaFatoresService,
      AgendaService agendaService,
      AgendaUsuarioPerfilService agendaUsuarioPerfilService) {
    this.agendaFatoresService = agendaFatoresService;
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Agenda dadosAgenda = new Agenda();

    AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();

    long codigoAgendaAgendaUsuariosControle = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    String tituloAgendaAgendaUsuarios = "";
    String statusDataLimiteAgendaAgendaUsuarios = "";
    String dataLimiteAgendaAgendaUsuarios = "";
    String statusAgendaAgendaUsuarios = "";
    String textoStatusAgendaAgendaUsuarios = "";
    dadosAgenda.setA04_codigo(codigoAgendaAgendaUsuariosControle);
    dadosAgenda = agendaService.selectAgenda(dadosAgenda);
    try {
      if (dadosAgenda.getA01_codigo() > 0) {
        tituloAgendaAgendaUsuarios = dadosAgenda.getA04_titulo();
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
    AgendaFatoresDados dadosAgendaFatores = new AgendaFatoresDados();

    int achouFator = 0;
    dadosAgendaFatores.setoAgendaModel(dadosAgenda);
    dadosAgendaFatores = agendaFatoresService.selectFatoresDaAgenda(dadosAgendaFatores);
    if (dadosAgendaFatores.getArrFatorModel().size() > 0) {
      achouFator = 1;
    }

    pagina.put(
        "Math_abs_oAgendaModel_A04_certeza_resultado",
        String.valueOf(Math.abs(dadosAgenda.getA04_certeza_resultado())));

    pagina.put(
        "Math_abs_oAgendaModel_A04_contradicao_resultado",
        String.valueOf(Math.abs(dadosAgenda.getA04_contradicao_resultado())));

    pagina.put("oAgendaModel_A04_resultado", String.valueOf(dadosAgenda.getA04_resultado()));

    if (achouFator == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      Fator dadosFator;
      ArrayList<Fator> listaFator = new ArrayList<Fator>();
      listaFator = dadosAgendaFatores.getArrFatorModel();
      Usuario dadosUsuario;
      ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
      listaUsuario = dadosAgendaFatores.getArrUsuarioModel();
      String displayLink = "";
      String displaySoTexto = "";
      for (int indiceElemento = 0; indiceElemento < listaFator.size(); indiceElemento++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        dadosFator = dadosAgendaFatores.getArrFatorModel().get(indiceElemento);
        dadosUsuario = dadosAgendaFatores.getArrUsuarioModel().get(indiceElemento);
        displayLink = "none";
        displaySoTexto = "inline";
        if (codigoUsuarioAgendaUsuariosControle == dadosUsuario.getA02_codigo()) {
          displayLink = "inline";
          displaySoTexto = "none";
        }

        linha2.put("oFatorModel_A06_titulo", String.valueOf(dadosFator.getA06_titulo()));

        linha2.put("oUsuarioModel_A02_nome", String.valueOf(dadosUsuario.getA02_nome()));

        linha2.put("oFatorModel_A06_descricao", String.valueOf(dadosFator.getA06_descricao()));

        linha2.put(
            "Math_abs_oFatorModel_A06_certeza_resultante_fator",
            String.valueOf(Math.abs(dadosFator.getA06_certeza_resultante_fator())));

        linha2.put(
            "Math_abs_oFatorModel_A06_contradicao_resultante_fator",
            String.valueOf(Math.abs(dadosFator.getA06_contradicao_resultante_fator())));

        linha2.put(
            "oFatorModel_A06_resultado_fator",
            String.valueOf(dadosFator.getA06_resultado_fator()));
      }
    }
    pagina.put("au_A04_TITULO", String.valueOf(tituloAgendaAgendaUsuarios));

    pagina.put("au_A04_STATUS_DT_LIMITE", String.valueOf(statusDataLimiteAgendaAgendaUsuarios));

    pagina.put("au_A04_DATA_LIMITE", String.valueOf(dataLimiteAgendaAgendaUsuarios));

    pagina.put("au_A04_STATUS", String.valueOf(statusAgendaAgendaUsuarios));

    pagina.put("au_A04_TXT_STATUS", String.valueOf(textoStatusAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_TITULAR", String.valueOf(perfilTitularParticipacaoAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_FACILITADOR", String.valueOf(perfilFacilitadorParticipacaoAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_ESPECIALISTA", String.valueOf(perfilEspecialistaParticipacaoAgendaAgendaUsuarios));

    pagina.put("au_A05_PERFIL_ANALISTA", String.valueOf(perfilAnalistaParticipacaoAgendaAgendaUsuarios));

    return pagina;
  }
}
