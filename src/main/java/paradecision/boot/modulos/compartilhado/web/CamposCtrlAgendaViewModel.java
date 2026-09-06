package paradecision.boot.modulos.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/** Prepara os dados do fragmento compartilhado camposCtrlAgenda. */
public final class CamposCtrlAgendaViewModel {
  public static Map<String, Object> preparar(HttpServletRequest request) {
    Map<String, Object> controleAgenda = new LinkedHashMap<>();

    String ct_A04_CODIGO = request.getParameter("ct_A04_CODIGO");
    String ct_A04_TITULO = request.getParameter("ct_A04_TITULO");
    String ct_A04_DESCRICAO = request.getParameter("ct_A04_DESCRICAO");
    String ct_A04_STATUS_DT_LIMITE = request.getParameter("ct_A04_STATUS_DT_LIMITE");
    String ct_A04_DATA_LIMITE = request.getParameter("ct_A04_DATA_LIMITE");
    String ct_A04_STATUS = request.getParameter("ct_A04_STATUS");
    String ct_A04_TXT_STATUS = request.getParameter("ct_A04_TXT_STATUS");
    String ct_A05_PERFIL_AGENDA_USUARIO_TITULAR =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
    String ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR");
    String ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA");
    String ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA");
    String ct_QTD_FATORES_AGENDA = request.getParameter("ct_QTD_FATORES_AGENDA");
    String ct_QTD_ESPECIALISTAS_AGENDA = request.getParameter("ct_QTD_ESPECIALISTAS_AGENDA");
    if (ct_A04_CODIGO == null) ct_A04_CODIGO = "";
    if (ct_A04_TITULO == null) ct_A04_TITULO = "";
    if (ct_A04_DESCRICAO == null) ct_A04_DESCRICAO = "";
    if (ct_A04_STATUS_DT_LIMITE == null) ct_A04_STATUS_DT_LIMITE = "";
    if (ct_A04_DATA_LIMITE == null) ct_A04_DATA_LIMITE = "";
    if (ct_A04_STATUS == null) ct_A04_STATUS = "";
    if (ct_A04_TXT_STATUS == null) ct_A04_TXT_STATUS = "";
    if (ct_A05_PERFIL_AGENDA_USUARIO_TITULAR == null) ct_A05_PERFIL_AGENDA_USUARIO_TITULAR = "";
    if (ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR == null)
      ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR = "";
    if (ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA == null)
      ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA = "";
    if (ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA == null) ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA = "";
    if (ct_QTD_FATORES_AGENDA == null) ct_QTD_FATORES_AGENDA = "";
    if (ct_QTD_ESPECIALISTAS_AGENDA == null) ct_QTD_ESPECIALISTAS_AGENDA = "";

    controleAgenda.put("ct_A04_CODIGO", String.valueOf(ct_A04_CODIGO));

    controleAgenda.put("ct_A04_TITULO", String.valueOf(ct_A04_TITULO));

    controleAgenda.put("ct_A04_DESCRICAO", String.valueOf(ct_A04_DESCRICAO));

    controleAgenda.put("ct_A04_STATUS_DT_LIMITE", String.valueOf(ct_A04_STATUS_DT_LIMITE));

    controleAgenda.put("ct_A04_DATA_LIMITE", String.valueOf(ct_A04_DATA_LIMITE));

    controleAgenda.put("ct_A04_STATUS", String.valueOf(ct_A04_STATUS));

    controleAgenda.put("ct_A04_TXT_STATUS", String.valueOf(ct_A04_TXT_STATUS));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_TITULAR",
        String.valueOf(ct_A05_PERFIL_AGENDA_USUARIO_TITULAR));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR",
        String.valueOf(ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA",
        String.valueOf(ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA",
        String.valueOf(ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA));

    controleAgenda.put("ct_QTD_FATORES_AGENDA", String.valueOf(ct_QTD_FATORES_AGENDA));

    controleAgenda.put("ct_QTD_ESPECIALISTAS_AGENDA", String.valueOf(ct_QTD_ESPECIALISTAS_AGENDA));

    return controleAgenda;
  }
}
