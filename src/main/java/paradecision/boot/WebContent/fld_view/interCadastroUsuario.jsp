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
<td style="text-align:right;"><span class="stlCodForm">intCadUsu</span></td>
</tr></table>
 
<form id="interCadastroUsuarioForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterCadastroUsuario(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterCadastroUsuario(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%
int okMetodo = 0;
String u_a02_nome = request.getParameter("u_a02_nome");
String u_a02_email = request.getParameter("u_a02_email");
String u_a02_usuario = request.getParameter("u_a02_usuario");
String u_a02_senha = request.getParameter("u_a02_senha");
String u_a02_status = request.getParameter("u_a02_status");
String eup_a03_perfil_chefe = request.getParameter("eup_a03_perfil_chefe");
String eup_a03_perfil_padrao = request.getParameter("eup_a03_perfil_padrao");
//----------------------------------------------------
String ic_A01_CODIGO = request.getParameter("ct_A01_CODIGO");
//----------------------------------------------------
if (u_a02_nome == null) u_a02_nome = "";
if (u_a02_email == null) u_a02_email = "";
if (u_a02_usuario == null) u_a02_usuario = "";
if (u_a02_senha == null) u_a02_senha = "";
if (u_a02_status == null) u_a02_status = "0";
if (eup_a03_perfil_chefe == null) eup_a03_perfil_chefe = "0";
if (eup_a03_perfil_padrao == null) eup_a03_perfil_padrao = "0";
if (u_a02_status == "") u_a02_status = "0";
if (eup_a03_perfil_chefe == "") eup_a03_perfil_chefe = "0";
if (eup_a03_perfil_padrao == "") eup_a03_perfil_padrao = "0";
if (ic_A01_CODIGO == null) ic_A01_CODIGO = "0";
//----------------------------------------------------
u_a02_nome = MetodosUteis.padronizarMaiusculoCE(u_a02_nome);
u_a02_email = MetodosUteis.padronizarMinusculoSE(u_a02_email);
u_a02_usuario = MetodosUteis.padronizarMinusculoSE(u_a02_usuario);
//----------------------------------------------------
UsuarioModel oUsuarioModel = new UsuarioModel();
UsuarioControl oUsuarioControl = new UsuarioControl();
EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
EmpresaUsuarioPerfilControl oEmpresaUsuarioPerfilControl = new EmpresaUsuarioPerfilControl();
//----------------------------------------------------
oUsuarioModel.setA02_nome(u_a02_nome);
oUsuarioModel.setA02_email(u_a02_email);
oUsuarioModel.setA02_usuario(u_a02_usuario);
oUsuarioModel.setA02_senha(u_a02_senha);
//----------------------------------------------------
//oUsuarioModel.setA02_dt_cadastro(new Date());
//oUsuarioModel.setA02_dt_ultima_alteracao(new Date());
oUsuarioModel.setA02_codigo_link(MetodosUteis.gerarCodigo(25));
oUsuarioModel.setA02_status(Integer.parseInt(u_a02_status));
oUsuarioModel = oUsuarioControl.insertUsuario(oUsuarioModel);
//----------------------------------------------------
if (oUsuarioModel.getA02_codigo() != 0) {
	oEmpresaUsuarioPerfilModel.setA01_codigo(Long.parseLong(ic_A01_CODIGO));
	oEmpresaUsuarioPerfilModel.setA02_codigo(oUsuarioModel.getA02_codigo());
	oEmpresaUsuarioPerfilModel.setA03_perfil_paraviverbem(0);
	oEmpresaUsuarioPerfilModel.setA03_perfil_chefe(Integer.parseInt(eup_a03_perfil_chefe));
	oEmpresaUsuarioPerfilModel.setA03_perfil_padrao(Integer.parseInt(eup_a03_perfil_padrao));
	//... continuar pegando o control deste ultimo objeto
	okMetodo = oEmpresaUsuarioPerfilControl.insertEmpresaUsuarioPerfil(oEmpresaUsuarioPerfilModel);
}
%>
<script type="text/javascript">
var okMetodo = "<%= okMetodo %>";
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == 1) {
	//var interMensagem = document.getElementById("interMensagem"); 
	//interMensagem.innerHTML = "Usuário cadastrado com sucesso!";
	//var interBotaoOK = document.getElementById("interBotaoOK"); 
	//interBotaoOK.style.display = "";
	proximoFrame = "../fld_view/EmpresaUsuarios.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroUsuarioForm").action = proximoFrame;
	document.getElementById("interCadastroUsuarioForm").submit();
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Problemas com o Cadastro do Usuário!";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>