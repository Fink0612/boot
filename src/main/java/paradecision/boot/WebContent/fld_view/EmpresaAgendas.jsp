<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
<script type="text/javascript" src="../js/funcoesCtrl.js"></script>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%
UsuarioModel oUsuarioModel = new UsuarioModel();
EmpresaModel oEmpresaModel = new EmpresaModel();
AgendaModel oAgendaModel = new AgendaModel();
//-------------------------------------------------
EmpresaAgendasModel oEmpresaAgendasModel = new EmpresaAgendasModel();
EmpresaAgendasControl oEmpresaAgendasControl = new EmpresaAgendasControl();
//-------------------------------------------------
int achouAgenda = 0;
String perfP = request.getParameter("ct_A03_PERFIL_PARAVIVERBEM");
String perfA = request.getParameter("ct_A03_PERFIL_ADMINISTRADOR");
String perfC = request.getParameter("ct_A03_PERFIL_CHEFE");
long ea_ct_A01_CODIGO = Long.parseLong(request.getParameter("ct_A01_CODIGO"));
String ea_ct_A01_NOME = request.getParameter("ct_A01_NOME");
long ea_ct_A02_CODIGO = Long.parseLong(request.getParameter("ct_A02_CODIGO"));
//System.out.println(ea_ct_A02_CODIGO);
oUsuarioModel.setA02_codigo(ea_ct_A02_CODIGO);
oEmpresaModel.setA01_codigo(ea_ct_A01_CODIGO);
oEmpresaModel.setA01_nome(ea_ct_A01_NOME);
oEmpresaAgendasModel.setoEmpresaModel(oEmpresaModel);
oEmpresaAgendasModel.setoUsuarioModel(oUsuarioModel);
if (perfP.equals("1") || perfA.equals("1") || perfC.equals("1")) {
	oEmpresaAgendasModel = oEmpresaAgendasControl.selectAgendasDaEmpresa(oEmpresaAgendasModel);
} else {
	oEmpresaAgendasModel = oEmpresaAgendasControl.selectAgendasDaEmpresaUsuario(oEmpresaAgendasModel);
}
if (oEmpresaAgendasModel.getArrAgendaModel().size() > 0) {
	achouAgenda = 1;
}
%>

<table class="tabTitForm"><tr>
<td>
<a onclick="atualizarPagina('EmpresaAgendas', 'EmpresaAgendasForm')" href="javascript:void(0);">Atualizar esta Página</a>
<div id="ea_abrirUsuarioEmpresas" style="display:none;">
<a onclick="abrirUsuarioEmpresas()" href="javascript:void(0);">Voltar para Lista de Empresas</a>
</div>
</td>
<td style="text-align:right;"><span class="stlCodForm">EmpAge</span></td>
</tr></table>

<p><b>EMPRESA: </b> <span id="ea_A01_NOME"></span></p>
<form id="EmpresaAgendasForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<div id="ea_abrirEmpresaUsuarios" style="display:none;">
<a onclick="abrirEmpresaUsuarios()" href="javascript:void(0);">Ver Usuários Cadastrados</a>
</div>
<div id="ea_cadastrarAgenda" style="display:none;">
<a onclick="cadastrarNovaAgenda()" href="javascript:void(0);">Cadastrar Nova Agenda</a>
</div>
<br/><span class="stlTitForm">LISTA DE AGENDAS:</span>
<table>
<tr style="text-align: left;">
<th>Título da Agenda</th>
<th>Descrição da Agenda</th>
<th>Status</th>
</tr>
<%
if (achouAgenda == 1) {
	ArrayList<AgendaModel> arrAgendaModel = new ArrayList<AgendaModel>();
	arrAgendaModel = oEmpresaAgendasModel.getArrAgendaModel();
	for (int i = 0; i < arrAgendaModel.size(); i++) {
		oAgendaModel = oEmpresaAgendasModel.getArrAgendaModel().get(i);
		int numStatus = oAgendaModel.getA04_status();
		String strStatus = Integer.toString(numStatus);
		String txtStatus = MetodosUteis.retornaTxtStatusAgenda(strStatus);
%>
<tr>
<td><a onclick="abrirAgenda('<%= oAgendaModel.getA04_codigo() %>',
 '<%= oAgendaModel.getA04_titulo() %>', 
 '<%= oAgendaModel.getA04_status() %>')" href="javascript:void(0);">
<%= oAgendaModel.getA04_titulo() %></a></td>
<td><%= oAgendaModel.getA04_descricao() %></td>
<td><%= txtStatus %></td>
</tr>
<%}}%>
</table>
</form>
<script type="text/javascript">
apagarCmpCtrlAgenda();
var ea_A01_NOME = document.getElementById("ea_A01_NOME");
ea_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
//-----------------------------------------------------------------
var ea_A03_PERFIL_PARAVIVERBEM = document.getElementById("ct_A03_PERFIL_PARAVIVERBEM");
var ea_A03_PERFIL_ADMINISTRADOR = document.getElementById("ct_A03_PERFIL_ADMINISTRADOR");
var ea_A03_PERFIL_PADRAO = document.getElementById("ct_A03_PERFIL_PADRAO");
//-----------------------------------------------------------------
//### contorle de permição para voltar à lista de Empresas ###
var ea_abrirUsuarioEmpresas = document.getElementById("ea_abrirUsuarioEmpresas");
var ea_ct_QTD_EMPRESAS = document.getElementById("ct_QTD_EMPRESAS");
var ea_QTD_EMPRESAS = 0;
if (ehNumero(ea_ct_QTD_EMPRESAS.value)) {
	ea_QTD_EMPRESAS = parseInt(ea_ct_QTD_EMPRESAS.value);
}
if (ea_QTD_EMPRESAS > "1") {
	ea_abrirUsuarioEmpresas.style.display = "";
}
//-----------------------------------------------------------------
//### contorle de permição de acessar a lista de Usuários da Empresa ###
var ea_abrirEmpresaUsuarios = document.getElementById("ea_abrirEmpresaUsuarios");
if (ea_A03_PERFIL_PARAVIVERBEM.value == "1" || ea_A03_PERFIL_ADMINISTRADOR.value == "1") {
	ea_abrirEmpresaUsuarios.style.display = "";
}
//-----------------------------------------------------------------
//### contorle de permição de cadastrar nova Agenda ###
var ea_cadastrarAgenda = document.getElementById("ea_cadastrarAgenda");
if (ea_A03_PERFIL_PARAVIVERBEM.value == "1" || ea_A03_PERFIL_PADRAO.value == "1") {
	ea_cadastrarAgenda.style.display = "";
}
</script>
</body>
</html>