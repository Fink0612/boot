package paradecision.boot.modulos.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/** Prepara os dados do fragmento compartilhado camposCtrlFator. */
public final class CamposCtrlFatorViewModel {
  public static Map<String, Object> preparar(HttpServletRequest request) {
    Map<String, Object> controleFator = new LinkedHashMap<>();

    String codigoFatorControle = request.getParameter("ct_A06_CODIGO");
    if (codigoFatorControle == null) codigoFatorControle = "";

    controleFator.put("ct_A06_CODIGO", String.valueOf(codigoFatorControle));

    return controleFator;
  }
}
