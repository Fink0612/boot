package paradecision.boot.modulos.agendas.service.pagina;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.util.MetodosUteis;
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

    String okMetodo = "NOK";
    String ct_A04_CODIGO = formulario.valor("ct_A04_CODIGO");
    String a_a04_titulo = formulario.valor("a_a04_titulo");
    String a_a04_descricao = formulario.valor("a_a04_descricao");
    String a_a04_status_dt_limite = formulario.valor("a_a04_status_dt_limite");
    String a_a04_data_limite = formulario.valor("a_a04_data_limite");
    String ic_A01_CODIGO = formulario.valor("ct_A01_CODIGO");
    if (a_a04_titulo == null) a_a04_titulo = "";
    if (a_a04_descricao == null) a_a04_descricao = "";
    if (a_a04_status_dt_limite == null) a_a04_status_dt_limite = "0";
    if (a_a04_data_limite == null) a_a04_data_limite = "";
    if ("".equals(a_a04_status_dt_limite)) a_a04_status_dt_limite = "0";
    if (ic_A01_CODIGO == null) ic_A01_CODIGO = "0";
    a_a04_titulo = MetodosUteis.padronizarEspacos(a_a04_titulo);
    a_a04_descricao = MetodosUteis.padronizarEspacos(a_a04_descricao);
    Agenda oAgendaModel = new Agenda();

    long ct_num_A04_CODIGO = Long.parseLong(ct_A04_CODIGO);
    oAgendaModel.setA04_codigo(ct_num_A04_CODIGO);
    oAgendaModel.setA04_titulo(a_a04_titulo);
    oAgendaModel.setA04_descricao(a_a04_descricao);
    int a_num_a04_status_dt_limite = Integer.parseInt(a_a04_status_dt_limite);
    oAgendaModel.setA04_status_dt_limite(a_num_a04_status_dt_limite);
    Date a_dt_a04_data_limite = MetodosUteis.retornaDate(a_a04_data_limite, "yyyy-MM-dd");
    oAgendaModel.setA04_data_limite(a_dt_a04_data_limite);
    long ic_num_A01_CODIGO = Long.parseLong(ic_A01_CODIGO);
    oAgendaModel.setA01_codigo(ic_num_A01_CODIGO);
    okMetodo = agendaService.updateAgenda(oAgendaModel);

    pagina.put("ct_A04_CODIGO", String.valueOf(ct_A04_CODIGO));

    pagina.put("okMetodo", String.valueOf(okMetodo));

    return pagina;
  }
}
