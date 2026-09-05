<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%
EmpresaModel oEmpresaModel = new EmpresaModel();
UsuarioModel oUsuarioModel = new UsuarioModel();
EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
//-------------------------------------------------
EmpresaUsuariosModel oEmpresaUsuariosModel = new EmpresaUsuariosModel();
EmpresaUsuariosControl oEmpresaUsuariosControl = new EmpresaUsuariosControl();
//-------------------------------------------------
int achouUsuario = 0;
long eu_ct_A01_CODIGO = Long.parseLong(request.getParameter("ct_A01_CODIGO"));
String eu_ct_A01_NOME = request.getParameter("ct_A01_NOME");
oEmpresaModel.setA01_codigo(eu_ct_A01_CODIGO);
oEmpresaModel.setA01_nome(eu_ct_A01_NOME);
oEmpresaUsuariosModel.setoEmpresaModel(oEmpresaModel);
oEmpresaUsuariosModel = oEmpresaUsuariosControl.selectUsuariosDaEmpresa(oEmpresaUsuariosModel);
if (oEmpresaUsuariosModel.getArrUsuarioModel().size() > 0) {
	achouUsuario = 1;
}
%>

<table class="tabTitForm"><tr>
<td>
<a onclick="abrirEmpresa_Usu_Agendas('EmpresaUsuariosForm')" href="javascript:void(0);">Voltar para Lista de Agendas</a>
</td>
<td style="text-align:right;"><span class="stlCodForm">EmpUsu</span></td>
</tr></table>

<p><b>EMPRESA: </b><span id="eu_A01_NOME"></span></p>
<form id="EmpresaUsuariosForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<div id="eu_cadastrarUsuario" style="display:none;">
<a onclick="cadastrarNovoUsuario()" href="javascript:void(0);">Cadastrar Novo Usuário</a>
</div>
<br/><span class="stlTitForm">LISTA DE USUÁRIOS:</span>
<!-- O campo abaixo serve para controle de Edição de Cadastro de Usuário -->
<input type="hidden" name="eu_A02_CODIGO" id="eu_A02_CODIGO" value="" />
<table>
<tr style="text-align: left;">
<th>Nome do Usuário</th>
<th>Perfil de Chefe</th>
<th>Perfil Padrão</th>
</tr>
<%
String eu_ct_A03_PERFIL_PARAVIVERBEM = request.getParameter("ct_A03_PERFIL_PARAVIVERBEM");
String eu_ct_A03_PERFIL_ADMINISTRADOR = request.getParameter("ct_A03_PERFIL_ADMINISTRADOR");
//System.out.println(eu_ct_A03_PERFIL_PARAVIVERBEM + " : " + eu_ct_A03_PERFIL_ADMINISTRADOR);
String displayLink = "none";
String displaySoTexto = "inline";
if (eu_ct_A03_PERFIL_PARAVIVERBEM.equals("1") || eu_ct_A03_PERFIL_ADMINISTRADOR.equals("1")) {
	displayLink = "inline";
	displaySoTexto = "none";
}
if (achouUsuario == 1) {
	ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	arrUsuarioModel = oEmpresaUsuariosModel.getArrUsuarioModel();
	for (int i = 0; i < arrUsuarioModel.size(); i++) {
		oUsuarioModel = oEmpresaUsuariosModel.getArrUsuarioModel().get(i);
		oEmpresaUsuarioPerfilModel = oEmpresaUsuariosModel.getArrEmpresaUsuarioPerfilModel().get(i);
%>
<tr>
<td>
<span style="display:<%= displayLink %>;">
<a onclick="editarCadastroUsuario('<%= oUsuarioModel.getA02_codigo() %>')" href="javascript:void(0);">
<%= oUsuarioModel.getA02_nome() %></a></span>
<span style="display:<%= displaySoTexto %>;">
<%= oUsuarioModel.getA02_nome() %></span>
</td>
<td><%= oEmpresaUsuarioPerfilModel.getA03_perfil_chefe() %></td>
<td><%= oEmpresaUsuarioPerfilModel.getA03_perfil_padrao() %></td>
</tr>
<%}}%>
</table>
</form>
<script type="text/javascript">
var eu_A01_NOME = document.getElementById("eu_A01_NOME");
eu_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
//  -----------------------------------------------------------------
//  ### contorle de permição de visualização da lista de Usuários ###
var eu_A03_PERFIL_PARAVIVERBEM = document.getElementById("ct_A03_PERFIL_PARAVIVERBEM");
var eu_A03_PERFIL_ADMINISTRADOR = document.getElementById("ct_A03_PERFIL_ADMINISTRADOR");
var eu_cadastrarUsuario = document.getElementById("eu_cadastrarUsuario");
if (eu_A03_PERFIL_PARAVIVERBEM.value == "1" || eu_A03_PERFIL_ADMINISTRADOR.value == "1") {
	eu_cadastrarUsuario.style.display = "";
}
</script>
</body>
</html>