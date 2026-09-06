package paradecision.boot.modulos.agendas.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class CadastroAgendaEditarPaginaService {
  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String ct_A04_CODIGO = formulario.valor("ct_A04_CODIGO");
    String ct_A04_TITULO = formulario.valor("ct_A04_TITULO");
    String ct_A04_DESCRICAO = formulario.valor("ct_A04_DESCRICAO");
    String ct_A04_STATUS_DT_LIMITE = formulario.valor("ct_A04_STATUS_DT_LIMITE");
    String ct_A04_DATA_LIMITE = formulario.valor("ct_A04_DATA_LIMITE");
    if (ct_A04_CODIGO == null) ct_A04_CODIGO = "";
    if (ct_A04_TITULO == null) ct_A04_TITULO = "";
    if (ct_A04_DESCRICAO == null) ct_A04_DESCRICAO = "";
    if (ct_A04_STATUS_DT_LIMITE == null) ct_A04_STATUS_DT_LIMITE = "";
    if (ct_A04_DATA_LIMITE == null) ct_A04_DATA_LIMITE = "";

    pagina.put("ct_A04_CODIGO", String.valueOf(ct_A04_CODIGO));

    pagina.put("ct_A04_TITULO", String.valueOf(ct_A04_TITULO));

    pagina.put("ct_A04_DESCRICAO", String.valueOf(ct_A04_DESCRICAO));

    pagina.put("ct_A04_STATUS_DT_LIMITE", String.valueOf(ct_A04_STATUS_DT_LIMITE));

    pagina.put("ct_A04_DATA_LIMITE", String.valueOf(ct_A04_DATA_LIMITE));

    return pagina;
  }
}
