package paradecision.boot.modulos.agendas.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class CadastroAgendaEditarPaginaService {
  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String codigoAgendaControle = formulario.valor("ct_A04_CODIGO");
    String tituloAgendaControle = formulario.valor("ct_A04_TITULO");
    String descricaoAgendaControle = formulario.valor("ct_A04_DESCRICAO");
    String statusDataLimiteAgendaControle = formulario.valor("ct_A04_STATUS_DT_LIMITE");
    String dataLimiteAgendaControle = formulario.valor("ct_A04_DATA_LIMITE");
    if (codigoAgendaControle == null) codigoAgendaControle = "";
    if (tituloAgendaControle == null) tituloAgendaControle = "";
    if (descricaoAgendaControle == null) descricaoAgendaControle = "";
    if (statusDataLimiteAgendaControle == null) statusDataLimiteAgendaControle = "";
    if (dataLimiteAgendaControle == null) dataLimiteAgendaControle = "";

    pagina.put("ct_A04_CODIGO", String.valueOf(codigoAgendaControle));

    pagina.put("ct_A04_TITULO", String.valueOf(tituloAgendaControle));

    pagina.put("ct_A04_DESCRICAO", String.valueOf(descricaoAgendaControle));

    pagina.put("ct_A04_STATUS_DT_LIMITE", String.valueOf(statusDataLimiteAgendaControle));

    pagina.put("ct_A04_DATA_LIMITE", String.valueOf(dataLimiteAgendaControle));

    return pagina;
  }
}
