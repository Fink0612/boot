package paradecision.boot.modulos.agendas.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.service.AgendaService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterFluxoAgendaPaginaService {
  private final AgendaService agendaService;

  public InterFluxoAgendaPaginaService(AgendaService agendaService) {
    this.agendaService = agendaService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    int okMetodo = 0;
    int proxStatus = 0;
    String str_a04_codigo = formulario.valor("ct_A04_CODIGO");
    long lng_a04_codigo = Long.parseLong(str_a04_codigo);
    String str_a04_status = formulario.valor("ct_A04_STATUS");
    long lng_a04_status = Long.parseLong(str_a04_status);
    String str_pdAcao = formulario.valor("pdAcao");
    String msgAcaoOK = "Sucesso!!";
    String msgAcaoNOK = "Problemas!!";
    if (str_pdAcao.equals("encaminharAgenda")) {
      proxStatus = 1;
      String resultBD = "";
      msgAcaoOK = "Agenda Encaminhada com Sucesso";
      msgAcaoNOK = "Problemas com o Encaminhamento da Agenda!";
      Agenda oAgendaModel = new Agenda();

      oAgendaModel.setA04_codigo(lng_a04_codigo);
      oAgendaModel.setA04_status(proxStatus);
      resultBD = agendaService.updateStatusAgenda(oAgendaModel);
      if (resultBD.equals("OK")) okMetodo = 1;
    } else if (str_pdAcao.equals("liberarAgenda")) {
      proxStatus = 2;
      String resultBD = "";
      msgAcaoOK = "Agenda Liberada com Sucesso";
      msgAcaoNOK = "Problemas com a Liberação da Agenda!";
      Agenda oAgendaModel = new Agenda();

      oAgendaModel.setA04_codigo(lng_a04_codigo);
      oAgendaModel.setA04_status(proxStatus);
      resultBD = agendaService.updateStatusAgenda(oAgendaModel);
      if (resultBD.equals("OK")) okMetodo = 1;
    } else if (str_pdAcao.equals("encerrarAgenda")) {
      proxStatus = 9;
      String resultBD = "";
      msgAcaoOK = "Agenda Encerrada com Sucesso";
      msgAcaoNOK = "Problemas com o Encerramento desta Agenda!";
      Agenda oAgendaModel = new Agenda();

      oAgendaModel.setA04_codigo(lng_a04_codigo);
      oAgendaModel.setA04_status(proxStatus);
      resultBD = agendaService.updateStatusAgenda(oAgendaModel);
      if (resultBD.equals("OK")) okMetodo = 1;
    }

    pagina.put("okMetodo", String.valueOf(okMetodo));

    pagina.put("proxStatus", String.valueOf(proxStatus));

    pagina.put("msgAcaoOK", String.valueOf(msgAcaoOK));

    pagina.put("msgAcaoNOK", String.valueOf(msgAcaoNOK));

    return pagina;
  }
}
