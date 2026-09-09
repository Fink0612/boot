package paradecision.boot.modulos.agendas.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.dto.AgendaUsuarioPareceresDados;
import paradecision.boot.modulos.agendas.dto.AgendaUsuariosDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaFatoresService;
import paradecision.boot.modulos.agendas.service.AgendaService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPareceresService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;
import paradecision.boot.modulos.agendas.service.AgendaUsuariosService;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class AgendaUsuariosPareceresPendenciaPaginaService {
  private final AgendaFatoresService agendaFatoresService;
  private final AgendaService agendaService;
  private final AgendaUsuarioPareceresService agendaUsuarioPareceresService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;
  private final AgendaUsuariosService agendaUsuariosService;

  public AgendaUsuariosPareceresPendenciaPaginaService(
      AgendaFatoresService agendaFatoresService,
      AgendaService agendaService,
      AgendaUsuarioPareceresService agendaUsuarioPareceresService,
      AgendaUsuarioPerfilService agendaUsuarioPerfilService,
      AgendaUsuariosService agendaUsuariosService) {
    this.agendaFatoresService = agendaFatoresService;
    this.agendaService = agendaService;
    this.agendaUsuarioPareceresService = agendaUsuarioPareceresService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
    this.agendaUsuariosService = agendaUsuariosService;
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
    AgendaUsuariosDados dadosAgendaUsuarios = new AgendaUsuariosDados();

    int achouUsuario = 0;
    dadosAgendaUsuarios.setoAgendaModel(dadosAgenda);
    dadosAgendaUsuarios = agendaUsuariosService.selectUsuariosDaAgenda(dadosAgendaUsuarios);
    if (dadosAgendaUsuarios.getArrUsuarioModel().size() > 0) {
      achouUsuario = 1;
    }

    if (achouUsuario == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      AgendaFatoresDados dadosAgendaFatores = new AgendaFatoresDados();

      dadosAgendaFatores.setoAgendaModel(dadosAgenda);
      dadosAgendaFatores = agendaFatoresService.selectFatoresDaAgenda(dadosAgendaFatores);
      int quantidadeFatores = dadosAgendaFatores.getArrFatorModel().size();
      int quantidadePareceresFaltantes = 0;
      int quantidadePareceresUsuario = 0;
      if (quantidadeFatores > 0) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        Usuario dadosUsuario;
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        listaUsuario = dadosAgendaUsuarios.getArrUsuarioModel();
        for (int indiceElemento = 0; indiceElemento < listaUsuario.size(); indiceElemento++) {
          Map<String, Object> linha3 = DadosPagina.novaLinha(linha2, "linhas3");

          dadosUsuario = dadosAgendaUsuarios.getArrUsuarioModel().get(indiceElemento);
          dadosAgendaUsuarioPerfil = dadosAgendaUsuarios.getArrAgendaUsuarioPerfilModel().get(indiceElemento);
          int tit = dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_titular();
          int espec = dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista();
          if (espec == 1 || tit == 1) {
            Map<String, Object> linha4 = DadosPagina.novaLinha(linha3, "linhas4");

            AgendaUsuarioPareceresDados dadosAgendaUsuarioPareceres =
                new AgendaUsuarioPareceresDados();

            dadosAgendaUsuarioPareceres.setoAgendaModel(dadosAgenda);
            dadosAgendaUsuarioPareceres.setoUsuarioModel(dadosUsuario);
            dadosAgendaUsuarioPareceres =
                agendaUsuarioPareceresService.selectPareceresAgUsu(dadosAgendaUsuarioPareceres);
            ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario =
                dadosAgendaUsuarioPareceres.getArrParecerFatorUsuarioModel();
            quantidadePareceresUsuario = listaParecerFatorUsuario.size();
            quantidadePareceresFaltantes = quantidadeFatores;
            if (quantidadePareceresUsuario > 0) {
              ParecerFatorUsuario dadosParecerFatorUsuario;
              for (int indiceParecerUsuario = 0; indiceParecerUsuario < quantidadePareceresUsuario; indiceParecerUsuario++) {
                dadosParecerFatorUsuario = listaParecerFatorUsuario.get(indiceParecerUsuario);
                String certezaParecerTexto = dadosParecerFatorUsuario.getStr_a07_certeza();
                String contradicaoParecerTexto = dadosParecerFatorUsuario.getStr_a07_contradicao();
                if (contradicaoParecerTexto != null && certezaParecerTexto != null) {
                  if (!(contradicaoParecerTexto.equals("")) && !(certezaParecerTexto.equals(""))) {
                    quantidadePareceresFaltantes--;
                  }
                }
              }
            }

            linha4.put("oUsuarioModel_A02_nome", String.valueOf(dadosUsuario.getA02_nome()));

            linha4.put("qtdPareceresFaltantes", String.valueOf(quantidadePareceresFaltantes));
          }
        }
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
