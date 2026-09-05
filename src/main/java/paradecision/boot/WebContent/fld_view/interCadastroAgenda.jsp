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
<td style="text-align:right;"><span class="stlCodForm">intCadAge</span></td>
</tr></table>
 
<form id="interCadastroAgendaForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterCadastroAgenda(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterCadastroAgenda(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
long novoCodigo = 0;
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
novoCodigo = oAgendaControl.insertAgenda(oAgendaModel);
//System.out.println("oi1: " + novoCodigo);
//----------------------------------------------------
if (novoCodigo > 0) {
	String ic_A02_CODIGO = request.getParameter("ct_A02_CODIGO");
	if (ic_A02_CODIGO == null) ic_A02_CODIGO = "0";
	long ic_num_A02_CODIGO = Long.parseLong(ic_A02_CODIGO);
	AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
	oAgendaUsuarioPerfilModel.setA02_codigo(ic_num_A02_CODIGO);
	oAgendaUsuarioPerfilModel.setA04_codigo(novoCodigo);
	oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(1);
	oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(0);
	oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(0);
	oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(0);
	AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
	//System.out.println("oi2");
	oAgendaUsuarioPerfilControl.insertPerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
}
%>
<script type="text/javascript">
var novoCodigo = <%= novoCodigo %>;
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (novoCodigo > 0) {
	//var interMensagem = document.getElementById("interMensagem"); 
	//interMensagem.innerHTML = "Agenda cadastrada com sucesso!";
	//var interBotaoOK = document.getElementById("interBotaoOK"); 
	//interBotaoOK.style.display = "";
	proximoFrame = "../fld_view/EmpresaAgendas.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = proximoFrame;
	document.getElementById("interCadastroAgendaForm").submit();
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Problemas com o Cadastro da Agenda!";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>