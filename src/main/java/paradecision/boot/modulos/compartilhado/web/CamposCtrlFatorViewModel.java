package paradecision.boot.modulos.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/** Prepara os dados do fragmento compartilhado camposCtrlFator. */
public final class CamposCtrlFatorViewModel {
  public static Map<String, Object> preparar(HttpServletRequest request) {
    Map<String, Object> controleFator = new LinkedHashMap<>();

    String ct_A06_CODIGO = request.getParameter("ct_A06_CODIGO");
    if (ct_A06_CODIGO == null) ct_A06_CODIGO = "";

    controleFator.put("ct_A06_CODIGO", String.valueOf(ct_A06_CODIGO));

    return controleFator;
  }
}
