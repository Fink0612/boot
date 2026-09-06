package paradecision.boot.modulos.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/** Prepara os dados do fragmento compartilhado camposCtrlAgenda. */
public final class CamposCtrlAgendaViewModel {
  public static Map<String, Object> preparar(HttpServletRequest request) {
    Map<String, Object> controleAgenda = new LinkedHashMap<>();

    String codigoAgendaControle = request.getParameter("ct_A04_CODIGO");
    String tituloAgendaControle = request.getParameter("ct_A04_TITULO");
    String descricaoAgendaControle = request.getParameter("ct_A04_DESCRICAO");
    String statusDataLimiteAgendaControle = request.getParameter("ct_A04_STATUS_DT_LIMITE");
    String dataLimiteAgendaControle = request.getParameter("ct_A04_DATA_LIMITE");
    String statusAgendaControle = request.getParameter("ct_A04_STATUS");
    String textoStatusAgendaControle = request.getParameter("ct_A04_TXT_STATUS");
    String perfilAgendaUsuarioTitularParticipacaoAgendaControle =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
    String perfilAgendaUsuarioFacilitadorParticipacaoAgendaControle =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR");
    String perfilAgendaUsuarioEspecialistaParticipacaoAgendaControle =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA");
    String perfilAgendaUsuarioAnalistaParticipacaoAgendaControle =
        request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA");
    String ct_QTD_FATORES_AGENDA = request.getParameter("ct_QTD_FATORES_AGENDA");
    String ct_QTD_ESPECIALISTAS_AGENDA = request.getParameter("ct_QTD_ESPECIALISTAS_AGENDA");
    if (codigoAgendaControle == null) codigoAgendaControle = "";
    if (tituloAgendaControle == null) tituloAgendaControle = "";
    if (descricaoAgendaControle == null) descricaoAgendaControle = "";
    if (statusDataLimiteAgendaControle == null) statusDataLimiteAgendaControle = "";
    if (dataLimiteAgendaControle == null) dataLimiteAgendaControle = "";
    if (statusAgendaControle == null) statusAgendaControle = "";
    if (textoStatusAgendaControle == null) textoStatusAgendaControle = "";
    if (perfilAgendaUsuarioTitularParticipacaoAgendaControle == null) perfilAgendaUsuarioTitularParticipacaoAgendaControle = "";
    if (perfilAgendaUsuarioFacilitadorParticipacaoAgendaControle == null)
      perfilAgendaUsuarioFacilitadorParticipacaoAgendaControle = "";
    if (perfilAgendaUsuarioEspecialistaParticipacaoAgendaControle == null)
      perfilAgendaUsuarioEspecialistaParticipacaoAgendaControle = "";
    if (perfilAgendaUsuarioAnalistaParticipacaoAgendaControle == null) perfilAgendaUsuarioAnalistaParticipacaoAgendaControle = "";
    if (ct_QTD_FATORES_AGENDA == null) ct_QTD_FATORES_AGENDA = "";
    if (ct_QTD_ESPECIALISTAS_AGENDA == null) ct_QTD_ESPECIALISTAS_AGENDA = "";

    controleAgenda.put("ct_A04_CODIGO", String.valueOf(codigoAgendaControle));

    controleAgenda.put("ct_A04_TITULO", String.valueOf(tituloAgendaControle));

    controleAgenda.put("ct_A04_DESCRICAO", String.valueOf(descricaoAgendaControle));

    controleAgenda.put("ct_A04_STATUS_DT_LIMITE", String.valueOf(statusDataLimiteAgendaControle));

    controleAgenda.put("ct_A04_DATA_LIMITE", String.valueOf(dataLimiteAgendaControle));

    controleAgenda.put("ct_A04_STATUS", String.valueOf(statusAgendaControle));

    controleAgenda.put("ct_A04_TXT_STATUS", String.valueOf(textoStatusAgendaControle));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_TITULAR",
        String.valueOf(perfilAgendaUsuarioTitularParticipacaoAgendaControle));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR",
        String.valueOf(perfilAgendaUsuarioFacilitadorParticipacaoAgendaControle));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA",
        String.valueOf(perfilAgendaUsuarioEspecialistaParticipacaoAgendaControle));

    controleAgenda.put(
        "ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA",
        String.valueOf(perfilAgendaUsuarioAnalistaParticipacaoAgendaControle));

    controleAgenda.put("ct_QTD_FATORES_AGENDA", String.valueOf(ct_QTD_FATORES_AGENDA));

    controleAgenda.put("ct_QTD_ESPECIALISTAS_AGENDA", String.valueOf(ct_QTD_ESPECIALISTAS_AGENDA));

    return controleAgenda;
  }
}
