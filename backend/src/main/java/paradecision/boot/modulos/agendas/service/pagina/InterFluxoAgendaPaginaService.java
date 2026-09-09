package paradecision.boot.modulos.agendas.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
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

    int operacaoConcluida = 0;
    int proxStatus = 0;
    String codigoAgendaTexto = formulario.valor("ct_A04_CODIGO");
    long codigoAgendaNumerico = Long.parseLong(codigoAgendaTexto);
    String statusAgendaTexto = formulario.valor("ct_A04_STATUS");
    long statusAgendaNumerico = Long.parseLong(statusAgendaTexto);
    String str_pdAcao = formulario.valor("pdAcao");
    String msgAcaoOK = "Sucesso!!";
    String msgAcaoNOK = "Problemas!!";
    if (str_pdAcao.equals("encaminharAgenda")) {
      proxStatus = 1;
      String resultadoBanco = "";
      msgAcaoOK = "Agenda Encaminhada com Sucesso";
      msgAcaoNOK = "Problemas com o Encaminhamento da Agenda!";
      Agenda dadosAgenda = new Agenda();

      dadosAgenda.setA04_codigo(codigoAgendaNumerico);
      dadosAgenda.setA04_status(proxStatus);
      resultadoBanco = agendaService.updateStatusAgenda(dadosAgenda);
      if (resultadoBanco.equals("OK")) operacaoConcluida = 1;
    } else if (str_pdAcao.equals("liberarAgenda")) {
      proxStatus = 2;
      String resultadoBanco = "";
      msgAcaoOK = "Agenda Liberada com Sucesso";
      msgAcaoNOK = "Problemas com a Liberação da Agenda!";
      Agenda dadosAgenda = new Agenda();

      dadosAgenda.setA04_codigo(codigoAgendaNumerico);
      dadosAgenda.setA04_status(proxStatus);
      resultadoBanco = agendaService.updateStatusAgenda(dadosAgenda);
      if (resultadoBanco.equals("OK")) operacaoConcluida = 1;
    } else if (str_pdAcao.equals("encerrarAgenda")) {
      proxStatus = 9;
      String resultadoBanco = "";
      msgAcaoOK = "Agenda Encerrada com Sucesso";
      msgAcaoNOK = "Problemas com o Encerramento desta Agenda!";
      Agenda dadosAgenda = new Agenda();

      dadosAgenda.setA04_codigo(codigoAgendaNumerico);
      dadosAgenda.setA04_status(proxStatus);
      resultadoBanco = agendaService.updateStatusAgenda(dadosAgenda);
      if (resultadoBanco.equals("OK")) operacaoConcluida = 1;
    }

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    pagina.put("proxStatus", String.valueOf(proxStatus));

    pagina.put("msgAcaoOK", String.valueOf(msgAcaoOK));

    pagina.put("msgAcaoNOK", String.valueOf(msgAcaoNOK));

    return pagina;
  }
}
