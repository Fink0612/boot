<!-- ----------------------------------------------------- -->
<!-- Campos de Controle - Geral para todas as Páginas -->
<!-- A04 - TABELA DE AGENDA -->
<!-- A08 - TABELA DE PERFIL (NA AGENDA) -->
<!-- A05 - TABELA DE AGENDA USUÁRIO PERFIL -->
<!-- ----------------------------------------------------- -->
<!-- AGENDA -->
<div id="cmpControle" style="display:none;">
A04_COD<input type="text" name="ct_A04_CODIGO" id="ct_A04_CODIGO" value="" />
A04_TIT<input type="text" name="ct_A04_TITULO" id="ct_A04_TITULO" value="" />
A04_DESC<input type="text" name="ct_A04_DESCRICAO" id="ct_A04_DESCRICAO" value="" />
A04_STT_DT_LIM<input type="text" name="ct_A04_STATUS_DT_LIMITE" id="ct_A04_STATUS_DT_LIMITE" value="" />
A04_DT_LIM<input type="text" name="ct_A04_DATA_LIMITE" id="ct_A04_DATA_LIMITE" value="" />
A04_STT_AGND<input type="text" name="ct_A04_STATUS" id="ct_A04_STATUS" value="" />
A04_TXT_STT<input type="text" name="ct_A04_TXT_STATUS" id="ct_A04_TXT_STATUS" value="" />
<!-- PERFIL DO USUÁRIO NA AGENDA -->
A05_PRF_TIT<input type="text" name="ct_A05_PERFIL_AGENDA_USUARIO_TITULAR" id="ct_A05_PERFIL_AGENDA_USUARIO_TITULAR" value="" />
A05_PRF_FAC<input type="text" name="ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR" id="ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR" value="" />
A05_PRF_ESP<input type="text" name="ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA" id="ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA" value="" />
A05_PRF_ANL<input type="text" name="ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA" id="ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA" value="" />
<!-- FATORES DA AGENDA -->
QTD_FAT_AGND<input type="text" name="ct_QTD_FATORES_AGENDA" id="ct_QTD_FATORES_AGENDA" value="" />
<!-- ESPECIALISTAS DA AGENDA -->
QTD_ESPEC_AGND<input type="text" name="ct_QTD_ESPECIALISTAS_AGENDA" id="ct_QTD_ESPECIALISTAS_AGENDA" value="" />
<!-- ----------------------------------------------------- -->
</div>
<%
String ct_A04_CODIGO = request.getParameter("ct_A04_CODIGO");
String ct_A04_TITULO = request.getParameter("ct_A04_TITULO");
String ct_A04_DESCRICAO = request.getParameter("ct_A04_DESCRICAO");
String ct_A04_STATUS_DT_LIMITE = request.getParameter("ct_A04_STATUS_DT_LIMITE");
String ct_A04_DATA_LIMITE = request.getParameter("ct_A04_DATA_LIMITE");
String ct_A04_STATUS = request.getParameter("ct_A04_STATUS");
String ct_A04_TXT_STATUS = request.getParameter("ct_A04_TXT_STATUS");
String ct_A05_PERFIL_AGENDA_USUARIO_TITULAR = request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
String ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR = request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR");
String ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA = request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA");
String ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA = request.getParameter("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA");
String ct_QTD_FATORES_AGENDA = request.getParameter("ct_QTD_FATORES_AGENDA");
String ct_QTD_ESPECIALISTAS_AGENDA = request.getParameter("ct_QTD_ESPECIALISTAS_AGENDA");
//----------------------------------------------------
if (ct_A04_CODIGO == null) ct_A04_CODIGO = "";
if (ct_A04_TITULO == null) ct_A04_TITULO = "";
if (ct_A04_DESCRICAO == null) ct_A04_DESCRICAO = "";
if (ct_A04_STATUS_DT_LIMITE == null) ct_A04_STATUS_DT_LIMITE = "";
if (ct_A04_DATA_LIMITE == null) ct_A04_DATA_LIMITE = "";
if (ct_A04_STATUS == null) ct_A04_STATUS = "";
if (ct_A04_TXT_STATUS == null) ct_A04_TXT_STATUS = "";
if (ct_A05_PERFIL_AGENDA_USUARIO_TITULAR == null) ct_A05_PERFIL_AGENDA_USUARIO_TITULAR = "";
if (ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR == null) ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR = "";
if (ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA == null) ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA = "";
if (ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA == null) ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA = "";
if (ct_QTD_FATORES_AGENDA == null) ct_QTD_FATORES_AGENDA = "";
if (ct_QTD_ESPECIALISTAS_AGENDA == null) ct_QTD_ESPECIALISTAS_AGENDA = "";
%>
<script type="text/javascript">
document.getElementById("ct_A04_CODIGO").value = "<%= ct_A04_CODIGO %>";
document.getElementById("ct_A04_TITULO").value = "<%= ct_A04_TITULO %>";
document.getElementById("ct_A04_DESCRICAO").value = "<%= ct_A04_DESCRICAO %>";
document.getElementById("ct_A04_STATUS_DT_LIMITE").value = "<%= ct_A04_STATUS_DT_LIMITE %>";
document.getElementById("ct_A04_DATA_LIMITE").value = "<%= ct_A04_DATA_LIMITE %>";
document.getElementById("ct_A04_STATUS").value = "<%= ct_A04_STATUS %>";
document.getElementById("ct_A04_TXT_STATUS").value = "<%= ct_A04_TXT_STATUS %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR").value = "<%= ct_A05_PERFIL_AGENDA_USUARIO_TITULAR %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR").value = "<%= ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA").value = "<%= ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA").value = "<%= ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA %>";
document.getElementById("ct_QTD_FATORES_AGENDA").value = "<%= ct_QTD_FATORES_AGENDA %>";
document.getElementById("ct_QTD_ESPECIALISTAS_AGENDA").value = "<%= ct_QTD_ESPECIALISTAS_AGENDA %>";
</script>
