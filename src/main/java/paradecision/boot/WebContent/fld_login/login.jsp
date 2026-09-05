<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/principal.css" />
<script type="text/javascript" src="../js/funcoesLogin.js"></script>
</head>
<body>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="java.util.ArrayList" %>
<%
UsuarioModel oUsuarioModel = new UsuarioModel();
EmpresaModel oEmpresaModel = new EmpresaModel();
EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
//-------------------------------------------------
UsuarioControl oUsuarioControl = new UsuarioControl();
UsuarioEmpresasModel oUsuarioEmpresasModel = new UsuarioEmpresasModel();
UsuarioEmpresasControl oUsuarioEmpresasControl = new UsuarioEmpresasControl();
//-------------------------------------------------
int qtdEmpresas = 0;
String txt_pdUsuario = request.getParameter("pdUsuario");
String txt_pdSenha = request.getParameter("pdSenha");
String txt_pdAcao = request.getParameter("pdAcao");
String txt_pdMensagens = "";
String txt_pdMsgDisplay = "none";
if (txt_pdUsuario == null) txt_pdUsuario = "";
if (txt_pdSenha == null) txt_pdSenha = "";
if (txt_pdAcao == null) txt_pdAcao = "";
if (txt_pdAcao.equals("envLogin")) {
	oUsuarioModel.setA02_usuario(txt_pdUsuario);
	oUsuarioModel.setA02_senha(txt_pdSenha);
	oUsuarioModel = oUsuarioControl.selectUserLogin(oUsuarioModel);
	if (oUsuarioModel.getA02_codigo() == 0) {
		txt_pdMensagens = "Usuario e/ou Senha Invalidos";
		txt_pdMsgDisplay = "";
	} else {
		oUsuarioEmpresasModel.setoUsuarioModel(oUsuarioModel);
		oUsuarioEmpresasModel = oUsuarioEmpresasControl.selectEmpresasDoUsuario(oUsuarioEmpresasModel);
		qtdEmpresas = oUsuarioEmpresasModel.getArrEmpresaModel().size();
		if (qtdEmpresas == 1) {
			oEmpresaModel = oUsuarioEmpresasModel.getArrEmpresaModel().get(0);
			oEmpresaUsuarioPerfilModel = oUsuarioEmpresasModel.getArrEmpresaUsuarioPerfilModel().get(0);
		}
	}
}
%>
<div class="card" id="telaLogin">
  <div class="card-body">
  <form id="pdLoginForm" action="login.jsp" method="post">
  <div class="mb-3">
    <label class="form-label">Usuário: </label>
    <input type="text" name="pdUsuario" id="pdUsuario" value="<%= txt_pdUsuario %>" class="form-control" placeholder="Digite seu Usuário..." />
  </div>
  <div class="mb-3">
    <label class="form-label">Senha</label>
    <input type="password" name="pdSenha" id="pdSenha" onkeypress="submeterForm(1, event);" value="" class="form-control" placeholder="Digite sua Senha..." />
  </div>
  <button type="button" onclick="enviarPdLogin(1);" class="btn btn-paradecision btn-block">Enviar</button>
  <div id="pdMensagens" style="display:<%= txt_pdMsgDisplay %>;"><br/><%= txt_pdMensagens %></div>
  <%@ include file="../fld_control/camposControle.jsp" %>
  </form>
  </div>
</div>
<script type="text/javascript">
var jsUsuSessao = "<%= oUsuarioModel.getA02_nome() %>";
if (jsUsuSessao == "null") jsUsuSessao = "";
if (jsUsuSessao != "") {
	var txtAux = "";
	var sess_pdUsuSessao = parent.document.getElementById("pdUsuSessao"); 
	var sess_pdNomeUsuSessao = parent.document.getElementById("pdNomeUsuSessao"); 
	var sess_pdUsuPerfil = parent.document.getElementById("pdUsuPerfil"); 
	sess_pdUsuSessao.value = "<%= oUsuarioModel.getA02_usuario() %>";
	sess_pdNomeUsuSessao.value = "<%= oUsuarioModel.getA02_nome() %>";
	sess_pdUsuPerfil.value = "Analista";
	// ###### Preenchendo Campos de Controle ######
	var qtdEmpresas = "<%= qtdEmpresas %>";
	txtAux = "<%= oUsuarioModel.getA02_codigo() %>";
	document.getElementById("ct_A02_CODIGO").value = txtAux;
	txtAux = "<%= oUsuarioModel.getA02_usuario() %>";
	document.getElementById("ct_A02_USUARIO").value = txtAux;
	document.getElementById("ct_QTD_EMPRESAS").value = qtdEmpresas;
	if (qtdEmpresas == "1") {
		txtAux = "<%= oEmpresaModel.getA01_codigo() %>";
		document.getElementById("ct_A01_CODIGO").value = txtAux;
		txtAux = "<%= oEmpresaModel.getA01_nome() %>";
		document.getElementById("ct_A01_NOME").value = txtAux;
		txtAux = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_paraviverbem() %>";
		document.getElementById("ct_A03_PERFIL_PARAVIVERBEM").value = txtAux;
		txtAux = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_administrador() %>";
		document.getElementById("ct_A03_PERFIL_ADMINISTRADOR").value = txtAux;
		txtAux = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_chefe() %>";
		document.getElementById("ct_A03_PERFIL_CHEFE").value = txtAux;
		txtAux = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_padrao() %>";
		document.getElementById("ct_A03_PERFIL_PADRAO").value = txtAux;
	}
	// ############################################
	enviarPdLogin(2);
}
</script>
</body>
</html>