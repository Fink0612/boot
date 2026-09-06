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

    int okMetodo = 0;
    String str_a04_codigo = formulario.valor("ct_A04_CODIGO");
    long lng_a04_codigo = Long.parseLong(str_a04_codigo);

    String msgAcaoOK = "Sucesso!!";
    String msgAcaoNOK = "Problemas!!";
    if (lng_a04_codigo > 0) {
      String resultBD = "";
      Agenda oAgendaModel = new Agenda();

      oAgendaModel.setA04_codigo(lng_a04_codigo);
      resultBD = calculoResultadoAgendaService.geraResultados(oAgendaModel, 0);
      if (resultBD.equals("OK")) okMetodo = 1;
    }

    pagina.put("okMetodo", String.valueOf(okMetodo));

    pagina.put("lng_a04_codigo", String.valueOf(lng_a04_codigo));

    pagina.put("msgAcaoOK", String.valueOf(msgAcaoOK));

    pagina.put("msgAcaoNOK", String.valueOf(msgAcaoNOK));

    return pagina;
  }
}
