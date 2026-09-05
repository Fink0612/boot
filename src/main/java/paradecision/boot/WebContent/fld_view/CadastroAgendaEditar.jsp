<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
<script type="text/javascript" src="../js/funcoesCadastro.js"></script>
</head>
<body>

<table class="tabTitForm"><tr>
<td>
<span class="stlTitForm">CADASTRO DE AGENDA</span>
</td>
<td style="text-align:right;"><span class="stlCodForm">CadAgeEdit</span></td>
</tr></table>

<p><b>EMPRESA: </b><span id="a_A01_NOME"></span></p>
<form id="CadastroAgendaForm" action="#" method="post" autocomplete="off">
<span id="a_Mensagens" style="display:none; color:red;"></span>
<input type="hidden" name="ct_A04_CODIGO" id="ct_A04_CODIGO" value="" /><br/>
<table><tr>
<td><label for="a_a04_titulo">Título: </label>
</td><td><input type="text" name="a_a04_titulo" id="a_a04_titulo" value="" /><br/>
</td></tr><tr>
<td><label for="a_a04_descricao">Descrição: </label>
</td><td><textarea name="a_a04_descricao" id="a_a04_descricao" rows="4" cols="40"></textarea><br/>
</td></tr>
</table>
<input type="checkbox" name="a_a04_status_dt_limite" 
	id="a_a04_status_dt_limite" value="1" onchange="ctrlVisualDataLimite();" />
<label for="a_a04_status_dt_limite"> - com Data Limite</label><br/>
<span id="a_cmpDataLimite" style="display:none;">
<label for="a_a04_data_limite">Data Limite: </label>
<input type="date" name="a_a04_data_limite" id="a_a04_data_limite" value="" /><br/>
</span>
<span>------------------------------------------------</span><br/>
<input type="button" id="salvar" value="Salvar e Sair" onclick="salvarCadastroAgenda(2)" />
<input type="button" id="cancelar" value="Cancelar e Sair" onclick="cancelarCadastroAgenda(2)" />
<br/>
<%@ include file="../fld_control/camposControle.jsp" %>
<%
String ct_A04_CODIGO = request.getParameter("ct_A04_CODIGO");
String ct_A04_TITULO = request.getParameter("ct_A04_TITULO");
String ct_A04_DESCRICAO = request.getParameter("ct_A04_DESCRICAO");
String ct_A04_STATUS_DT_LIMITE = request.getParameter("ct_A04_STATUS_DT_LIMITE");
String ct_A04_DATA_LIMITE = request.getParameter("ct_A04_DATA_LIMITE");
//----------------------------------------------------
if (ct_A04_CODIGO == null) ct_A04_CODIGO = "";
if (ct_A04_TITULO == null) ct_A04_TITULO = "";
if (ct_A04_DESCRICAO == null) ct_A04_DESCRICAO = "";
if (ct_A04_STATUS_DT_LIMITE == null) ct_A04_STATUS_DT_LIMITE = "";
if (ct_A04_DATA_LIMITE == null) ct_A04_DATA_LIMITE = "";
%>
</form>
<script type="text/javascript">
document.getElementById("ct_A04_CODIGO").value = "<%= ct_A04_CODIGO %>";
document.getElementById("a_a04_titulo").value = "<%= ct_A04_TITULO %>";
document.getElementById("a_a04_descricao").value = "<%= ct_A04_DESCRICAO %>";
var ct_A04_STATUS_DT_LIMITE = "<%= ct_A04_STATUS_DT_LIMITE %>";
var a_cmpDataLimite = document.getElementById("a_cmpDataLimite");
if (ct_A04_STATUS_DT_LIMITE == "1") {
	document.getElementById("a_a04_status_dt_limite").checked = true;
	a_cmpDataLimite.style.display = "inline";
} else {
	document.getElementById("a_a04_status_dt_limite").checked = false;
	a_cmpDataLimite.style.display = "none";
}
document.getElementById("a_a04_data_limite").value = "<%= ct_A04_DATA_LIMITE %>";
//----------------------------------------------------
var a_A01_NOME = document.getElementById("a_A01_NOME");
a_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
</script>
</body>
</html>