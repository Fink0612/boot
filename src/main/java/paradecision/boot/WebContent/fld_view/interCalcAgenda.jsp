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
<td style="text-align:right;"><span class="stlCodForm">intCalcAge</span></td>
</tr></table>
 
<form id="interCalcAgendaForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterCalcAgenda(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterCalcAgenda(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
int okMetodo = 0;
String str_a04_codigo = request.getParameter("ct_A04_CODIGO");
long lng_a04_codigo = Long.parseLong(str_a04_codigo);
String str_pdAcao = request.getParameter("pdAcao");
String msgAcaoOK = "Sucesso!!";
String msgAcaoNOK = "Problemas!!";
if (lng_a04_codigo > 0) {
	String resultBD = "";
	AgendaModel oAgendaModel = new AgendaModel();
	CalculoResultadoAgendaControl oCalculoResultadoAgendaControl = new CalculoResultadoAgendaControl();
	//----------------------------------------------------
	oAgendaModel.setA04_codigo(lng_a04_codigo);
	//----------------------------------------------------
	resultBD = oCalculoResultadoAgendaControl.geraResultados(oAgendaModel, 0);
	//----------------------------------------------------
	if (resultBD.equals("OK")) okMetodo = 1;
}
%>
<script type="text/javascript">
var okMetodo = <%= okMetodo %>;
//alert("cod: " + "<%= lng_a04_codigo %>");
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == 1) {
	//var interMensagem = document.getElementById("interMensagem"); 
	//interMensagem.innerHTML = "<%= msgAcaoOK %>";
	//var interBotaoOK = document.getElementById("interBotaoOK"); 
	//interBotaoOK.style.display = "";
	var proximoFrame = "../fld_view/AgendaFatoresResultados.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCalcAgendaForm").action = proximoFrame;
	document.getElementById("interCalcAgendaForm").submit();
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "<%= msgAcaoNOK %>";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>