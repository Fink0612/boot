package paradecision.boot.modulos.agendas.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.service.CalculoResultadoAgendaService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterCalcAgendaPaginaService {
  private final CalculoResultadoAgendaService calculoResultadoAgendaService;

  public InterCalcAgendaPaginaService(CalculoResultadoAgendaService calculoResultadoAgendaService) {
    this.calculoResultadoAgendaService = calculoResultadoAgendaService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    int operacaoConcluida = 0;
    String codigoAgendaTexto = formulario.valor("ct_A04_CODIGO");
    long codigoAgendaNumerico = Long.parseLong(codigoAgendaTexto);

    String msgAcaoOK = "Sucesso!!";
    String msgAcaoNOK = "Problemas!!";
    if (codigoAgendaNumerico > 0) {
      String resultadoBanco = "";
      Agenda dadosAgenda = new Agenda();

      dadosAgenda.setA04_codigo(codigoAgendaNumerico);
      resultadoBanco = calculoResultadoAgendaService.geraResultados(dadosAgenda, 0);
      if (resultadoBanco.equals("OK")) operacaoConcluida = 1;
    }

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    pagina.put("lng_a04_codigo", String.valueOf(codigoAgendaNumerico));

    pagina.put("msgAcaoOK", String.valueOf(msgAcaoOK));

    pagina.put("msgAcaoNOK", String.valueOf(msgAcaoNOK));

    return pagina;
  }
}
