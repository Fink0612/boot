package paradecision.boot.modulos.agendas.service.pagina;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.service.AgendaService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterCadastroAgendaEditarPaginaService {
  private final AgendaService agendaService;

  public InterCadastroAgendaEditarPaginaService(AgendaService agendaService) {
    this.agendaService = agendaService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String operacaoConcluida = "NOK";
    String codigoAgendaControle = formulario.valor("ct_A04_CODIGO");
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

    long codigoAgendaControleNumerico = Long.parseLong(codigoAgendaControle);
    dadosAgenda.setA04_codigo(codigoAgendaControleNumerico);
    dadosAgenda.setA04_titulo(tituloAgendaFormularioAgenda);
    dadosAgenda.setA04_descricao(descricaoAgendaFormularioAgenda);
    int statusDataLimiteAgendaFormularioAgendaNumerico = Integer.parseInt(statusDataLimiteAgendaFormularioAgenda);
    dadosAgenda.setA04_status_dt_limite(statusDataLimiteAgendaFormularioAgendaNumerico);
    Date a_dt_a04_data_limite = MetodosUteis.retornaDate(dataLimiteAgendaFormularioAgenda, "yyyy-MM-dd");
    dadosAgenda.setA04_data_limite(a_dt_a04_data_limite);
    long codigoEmpresaCadastroNumerico = Long.parseLong(codigoEmpresaCadastro);
    dadosAgenda.setA01_codigo(codigoEmpresaCadastroNumerico);
    operacaoConcluida = agendaService.updateAgenda(dadosAgenda);

    pagina.put("ct_A04_CODIGO", String.valueOf(codigoAgendaControle));

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    return pagina;
  }
}
