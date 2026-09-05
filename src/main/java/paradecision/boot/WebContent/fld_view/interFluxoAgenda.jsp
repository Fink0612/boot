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
<td style="text-align:right;"><span class="stlCodForm">intFluxAge</span></td>
</tr></table>
 
<form id="interFluxoAgendaForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterAgendaFluxo(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterAgendaFluxo(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
int okMetodo = 0;
int proxStatus = 0;
String str_a04_codigo = request.getParameter("ct_A04_CODIGO");
long lng_a04_codigo = Long.parseLong(str_a04_codigo);
String str_a04_status = request.getParameter("ct_A04_STATUS");
long lng_a04_status = Long.parseLong(str_a04_status);
String str_pdAcao = request.getParameter("pdAcao");
String msgAcaoOK = "Sucesso!!";
String msgAcaoNOK = "Problemas!!";
if (str_pdAcao.equals("encaminharAgenda")) {
	proxStatus = 1;
	String resultBD = "";
	msgAcaoOK = "Agenda Encaminhada com Sucesso";
	msgAcaoNOK = "Problemas com o Encaminhamento da Agenda!";
	AgendaModel oAgendaModel = new AgendaModel();
	AgendaControl oAgendaControl = new AgendaControl();
	oAgendaModel.setA04_codigo(lng_a04_codigo);
	oAgendaModel.setA04_status(proxStatus);
	resultBD = oAgendaControl.updateStatusAgenda(oAgendaModel);
	if (resultBD.equals("OK")) okMetodo = 1;
} else if (str_pdAcao.equals("liberarAgenda")) {
	proxStatus = 2;
	String resultBD = "";
	msgAcaoOK = "Agenda Liberada com Sucesso";
	msgAcaoNOK = "Problemas com a Liberação da Agenda!";
	AgendaModel oAgendaModel = new AgendaModel();
	AgendaControl oAgendaControl = new AgendaControl();
	oAgendaModel.setA04_codigo(lng_a04_codigo);
	oAgendaModel.setA04_status(proxStatus);
	resultBD = oAgendaControl.updateStatusAgenda(oAgendaModel);
	if (resultBD.equals("OK")) okMetodo = 1;
} else if (str_pdAcao.equals("encerrarAgenda")) {
	proxStatus = 9;
	String resultBD = "";
	msgAcaoOK = "Agenda Encerrada com Sucesso";
	msgAcaoNOK = "Problemas com o Encerramento desta Agenda!";
	AgendaModel oAgendaModel = new AgendaModel();
	AgendaControl oAgendaControl = new AgendaControl();
	oAgendaModel.setA04_codigo(lng_a04_codigo);
	oAgendaModel.setA04_status(proxStatus);
	resultBD = oAgendaControl.updateStatusAgenda(oAgendaModel);
	if (resultBD.equals("OK")) okMetodo = 1;
}
%>
<script type="text/javascript">
var okMetodo = <%= okMetodo %>;
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == 1) {
	document.getElementById("ct_A04_STATUS").value = "<%= proxStatus %>"; 
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "<%= msgAcaoOK %>";
	var interBotaoOK = document.getElementById("interBotaoOK"); 
	interBotaoOK.style.display = "";
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "<%= msgAcaoNOK %>";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>