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
<td style="text-align:right;"><span class="stlCodForm">intCadAgeEdit</span></td>
</tr></table>
 
<form id="interCadastroAgendaForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<input type="hidden" name="ct_A04_CODIGO" id="ct_A04_CODIGO" value="" /><br/>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterEditarAgenda(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterEditarAgenda(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
String okMetodo = "NOK";
String ct_A04_CODIGO = request.getParameter("ct_A04_CODIGO");
String a_a04_titulo = request.getParameter("a_a04_titulo");
String a_a04_descricao = request.getParameter("a_a04_descricao");
String a_a04_status_dt_limite = request.getParameter("a_a04_status_dt_limite");
String a_a04_data_limite = request.getParameter("a_a04_data_limite");
//----------------------------------------------------
String ic_A01_CODIGO = request.getParameter("ct_A01_CODIGO");
//----------------------------------------------------
if (a_a04_titulo == null) a_a04_titulo = "";
if (a_a04_descricao == null) a_a04_descricao = "";
if (a_a04_status_dt_limite == null) a_a04_status_dt_limite = "0";
if (a_a04_data_limite == null) a_a04_data_limite = "";
if (a_a04_status_dt_limite == "") a_a04_status_dt_limite = "0";
if (ic_A01_CODIGO == null) ic_A01_CODIGO = "0";
//----------------------------------------------------
a_a04_titulo = MetodosUteis.padronizarEspacos(a_a04_titulo);
a_a04_descricao = MetodosUteis.padronizarEspacos(a_a04_descricao);
//----------------------------------------------------
AgendaModel oAgendaModel = new AgendaModel();
AgendaControl oAgendaControl = new AgendaControl();
//----------------------------------------------------
long ct_num_A04_CODIGO = Long.parseLong(ct_A04_CODIGO);
oAgendaModel.setA04_codigo(ct_num_A04_CODIGO);
oAgendaModel.setA04_titulo(a_a04_titulo);
oAgendaModel.setA04_descricao(a_a04_descricao);
int a_num_a04_status_dt_limite = Integer.parseInt(a_a04_status_dt_limite);
oAgendaModel.setA04_status_dt_limite(a_num_a04_status_dt_limite);
Date a_dt_a04_data_limite = MetodosUteis.retornaDate(a_a04_data_limite, "yyyy-MM-dd");
oAgendaModel.setA04_data_limite(a_dt_a04_data_limite);
//----------------------------------------------------
long ic_num_A01_CODIGO = Long.parseLong(ic_A01_CODIGO);
oAgendaModel.setA01_codigo(ic_num_A01_CODIGO);
//----------------------------------------------------
okMetodo = oAgendaControl.updateAgenda(oAgendaModel);
%>
<script type="text/javascript">
document.getElementById("ct_A04_CODIGO").value = "<%= ct_A04_CODIGO %>";
var okMetodo = "<%= okMetodo %>";
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == "OK") {
	//var interMensagem = document.getElementById("interMensagem"); 
	//interMensagem.innerHTML = "Agenda alterada com sucesso!";
	//var interBotaoOK = document.getElementById("interBotaoOK"); 
	//interBotaoOK.style.display = "";
	proximoFrame = "../fld_view/AgendaUsuarios.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = proximoFrame;
	document.getElementById("interCadastroAgendaForm").submit();
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Problemas com a Altera&ccedil;&atilde;o da Agenda!";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>