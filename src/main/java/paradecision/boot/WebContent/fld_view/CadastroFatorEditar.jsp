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
<span class="stlTitForm">CADASTRO DE CRITÉRIOS</span>
</td>
<td style="text-align:right;"><span class="stlCodForm">CadFatEdit</span></td>
</tr></table>
<br/>
<table>
<tr><td><b>EMPRESA:</b></td> <td><span id="f_A01_NOME"></span></td></tr>
<tr><td><b>AGENDA:</b></td> <td><span id="f_A04_TITULO"></span></td></tr>
</table>
<br/>
<form id="CadastroFatorForm" action="#" method="post" autocomplete="off">
<span id="f_Mensagens" style="display:none; color:red;"></span>
<table>
<tr><td><label for="f_a06_titulo">Título: </label></td>
<td><input type="text" name="f_a06_titulo" id="f_a06_titulo" value="" /></td></tr>
<tr><td><label for="f_a06_descricao">Descrição: </label></td>
<td><textarea name="f_a06_descricao" id="f_a06_descricao" rows="4" cols="40"></textarea></td></tr>
</table>
<span>------------------------------------------------</span><br/>
<input type="button" id="salvar" value="Salvar e Sair" onclick="salvarCadastroFator(2)" />
<input type="button" id="cancelar" value="Cancelar e Sair" onclick="cancelarCadastroFator()" />
<br/>
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<%@ include file="../fld_control/camposCtrlFator.jsp" %>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%
String okRetorno = "NOK";
String cfe_A06_CODIGO = request.getParameter("ct_A06_CODIGO");
long num_A06_CODIGO = MetodosUteis.retornaLong(cfe_A06_CODIGO);
FatorModel oFatorModel = new FatorModel();
FatorControl oFatorControl = new FatorControl();
if (num_A06_CODIGO > 0) {
	//----------------------------------------------------
	oFatorModel.setA06_codigo(num_A06_CODIGO);
	oFatorModel = oFatorControl.selectFator(oFatorModel);
	if (oFatorModel.getA06_titulo() == null) oFatorModel.setA06_titulo("");
	if (oFatorModel.getA06_titulo().length() > 0) {
		okRetorno = "OK";
	}
}
%>
<script type="text/javascript">
var f_A01_NOME = document.getElementById("f_A01_NOME");
f_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
var f_A04_TITULO = document.getElementById("f_A04_TITULO");
f_A04_TITULO.innerHTML = document.getElementById("ct_A04_TITULO").value;
//---------------------------------------
var okRetorno = "<%= okRetorno %>";
if (okRetorno == "OK") {
	var f_a06_titulo = document.getElementById("f_a06_titulo");
	f_a06_titulo.value = "<%= oFatorModel.getA06_titulo() %>";
	var f_a06_descricao = document.getElementById("f_a06_descricao");
	f_a06_descricao.value = "<%= oFatorModel.getA06_descricao() %>";
}
</script>
</body>
</html>