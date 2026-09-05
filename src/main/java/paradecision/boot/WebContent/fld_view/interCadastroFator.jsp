<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<table class="tabTitForm"><tr>
<td>&nbsp;</td>
<td style="text-align:right;"><span class="stlCodForm">intCadFat</span></td>
</tr></table>
 
<form id="interCadastroFatorForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<%@ include file="../fld_control/camposCtrlFator.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterCadastroFator(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterCadastroFator(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%
String okMetodo = "NOK";
String f_a06_titulo = request.getParameter("f_a06_titulo");
String f_a06_descricao = request.getParameter("f_a06_descricao");
String ic_A02_CODIGO = request.getParameter("ct_A02_CODIGO");
String ic_A04_CODIGO = request.getParameter("ct_A04_CODIGO");
//----------------------------------------------------
if (f_a06_titulo == null) f_a06_titulo = "";
if (f_a06_descricao == null) f_a06_descricao = "";
if (ic_A02_CODIGO == null) ic_A02_CODIGO = "0";
if (ic_A04_CODIGO == null) ic_A04_CODIGO = "0";
//----------------------------------------------------
f_a06_titulo = MetodosUteis.padronizarEspacos(f_a06_titulo);
f_a06_descricao = MetodosUteis.padronizarEspacos(f_a06_descricao);
long num_a02_codigo = MetodosUteis.retornaLong(ic_A02_CODIGO);
long num_a04_codigo = MetodosUteis.retornaLong(ic_A04_CODIGO);
//----------------------------------------------------
FatorModel oFatorModel = new FatorModel();
FatorControl oFatorControl = new FatorControl();
//----------------------------------------------------
oFatorModel.setA06_titulo(f_a06_titulo);
oFatorModel.setA06_descricao(f_a06_descricao);
oFatorModel.setA02_codigo(num_a02_codigo);
oFatorModel.setA04_codigo(num_a04_codigo);
//----------------------------------------------------
okMetodo = oFatorControl.insertFator(oFatorModel);
//----------------------------------------------------
%>
<script type="text/javascript">
var okMetodo = "<%= okMetodo %>";
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == "OK") {
	//var interMensagem = document.getElementById("interMensagem"); 
	//interMensagem.innerHTML = "Fator cadastrado com sucesso!";
	//var interBotaoOK = document.getElementById("interBotaoOK"); 
	//interBotaoOK.style.display = "";
	var proximoFrame = "../fld_view/AgendaFatores.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroFatorForm").action = proximoFrame;
	document.getElementById("interCadastroFatorForm").submit();
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Problemas com o Cadastro do Fator!";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>