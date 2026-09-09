package paradecision.boot.modulos.agendas.service.pagina;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterCadastroAgendaPaginaService {
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;

  public InterCadastroAgendaPaginaService(
      AgendaService agendaService, AgendaUsuarioPerfilService agendaUsuarioPerfilService) {
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    long novoCodigo = 0;
    String tituloAgendaFormularioAgenda = formulario.valor("a_a04_titulo");
    String descricaoAgendaFormularioAgenda = formulario.valor("a_a04_descricao");
    String statusDataLimiteAgendaFormularioAgenda = formulario.valor("a_a04_status_dt_limite");
    String dataLimiteAgendaFormularioAgenda = formulario.valor("a_a04_data_limite");
    String codigoEmpresaCadastro = formulario.valor("ct_A01_CODIGO");
    if (tituloAgendaFormularioAgenda == null) tituloAgendaFormularioAgenda = "";
    if (descricaoAgendaFormularioAgenda == null) descricaoAgendaFormularioAgenda = "";
    if (statusDataLimiteAgendaFormularioAgenda == null) statusDataLimiteAgendaFormularioAgenda = "0";
    if (dataLimiteAgendaFormularioAgenda == null) dataLimiteAgendaFormularioAgenda = "";
    if ("".equals(statusDataLimiteAgendaFormularioAgenda)) statusDataLimiteAgendaFormularioAgenda = "0";
    if (codigoEmpresaCadastro == null) codigoEmpresaCadastro = "0";
    tituloAgendaFormularioAgenda = MetodosUteis.padronizarEspacos(tituloAgendaFormularioAgenda);
    descricaoAgendaFormularioAgenda = MetodosUteis.padronizarEspacos(descricaoAgendaFormularioAgenda);
    Agenda dadosAgenda = new Agenda();

    dadosAgenda.setA04_titulo(tituloAgendaFormularioAgenda);
    dadosAgenda.setA04_descricao(descricaoAgendaFormularioAgenda);
    int statusDataLimiteAgendaFormularioAgendaNumerico = Integer.parseInt(statusDataLimiteAgendaFormularioAgenda);
    dadosAgenda.setA04_status_dt_limite(statusDataLimiteAgendaFormularioAgendaNumerico);
    Date a_dt_a04_data_limite = MetodosUteis.retornaDate(dataLimiteAgendaFormularioAgenda, "yyyy-MM-dd");
    dadosAgenda.setA04_data_limite(a_dt_a04_data_limite);
    long codigoEmpresaCadastroNumerico = Long.parseLong(codigoEmpresaCadastro);
    dadosAgenda.setA01_codigo(codigoEmpresaCadastroNumerico);
    novoCodigo = agendaService.insertAgenda(dadosAgenda);
    if (novoCodigo > 0) {
      String codigoUsuarioCadastro = formulario.valor("ct_A02_CODIGO");
      if (codigoUsuarioCadastro == null) codigoUsuarioCadastro = "0";
      long codigoUsuarioCadastroNumerico = Long.parseLong(codigoUsuarioCadastro);
      AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();
      dadosAgendaUsuarioPerfil.setA02_codigo(codigoUsuarioCadastroNumerico);
      dadosAgendaUsuarioPerfil.setA04_codigo(novoCodigo);
      dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_titular(1);
      dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_facilitador(0);
      dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_especialista(0);
      dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_analista(0);

      agendaUsuarioPerfilService.insertPerfilUsuarioAgenda(dadosAgendaUsuarioPerfil);
    }

    pagina.put("novoCodigo", String.valueOf(novoCodigo));

    return pagina;
  }
}
