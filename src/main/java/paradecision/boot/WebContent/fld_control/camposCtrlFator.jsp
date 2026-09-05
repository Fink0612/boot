<!-- ----------------------------------------------------- -->
<!-- FATOR -->
<div id="cmpControle" style="display:none;">
A06_COD<input type="text" name="ct_A06_CODIGO" id="ct_A06_CODIGO" value="" />
<!-- ----------------------------------------------------- -->
</div>
<%
String ct_A06_CODIGO = request.getParameter("ct_A06_CODIGO");
//----------------------------------------------------
if (ct_A06_CODIGO == null) ct_A06_CODIGO = "";
%>
<script type="text/javascript">
document.getElementById("ct_A06_CODIGO").value = "<%= ct_A06_CODIGO %>";
</script>
