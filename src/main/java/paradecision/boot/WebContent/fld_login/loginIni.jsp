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
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%
UsuarioModel oUsuarioModel = new UsuarioModel();
UsuarioControl oUsuarioControl = new UsuarioControl();
String txt_codini = request.getParameter("liCodini");
String txt_pdUsuario = request.getParameter("pdUsuario");
String txt_pdNovaSenha = request.getParameter("pdNovaSenha");
String txt_pdConfNovaSenha = request.getParameter("pdConfNovaSenha");
String txt_pdAcao = request.getParameter("pdAcao");
String txt_pdMensagens = "";
String txt_pdMsgDisplay = "none";
if (txt_codini == null) txt_codini = "";
if (txt_pdUsuario == null) txt_pdUsuario = "";
if (txt_pdNovaSenha == null) txt_pdNovaSenha = "";
if (txt_pdConfNovaSenha == null) txt_pdConfNovaSenha = "";
if (txt_pdAcao == null) txt_pdAcao = "";
try {
	if (txt_pdAcao.equals("envLoginIni")) {
		//... inserindo um "x" na penúltima posição do código inicial
		int tam = txt_codini.length();
		txt_codini = txt_codini.substring(0, tam - 1) + "x" + txt_codini.substring(tam - 1);
		//-----------------------------------------------------------------------------------
		oUsuarioModel.setA02_usuario(txt_pdUsuario);
		oUsuarioModel.setA02_senha(txt_pdNovaSenha);
		oUsuarioModel.setA02_codigo_link(txt_codini);
		oUsuarioModel = oUsuarioControl.updateSenhaUsuario(oUsuarioModel);
	}
} catch (Exception e) {
}
%>
<div class="card" id="telaLogin">
  <div class="card-body">
  <form id="pdLoginForm" action="loginIni.jsp" method="post">
  <input type="hidden" name="pdUsuario" id="pdUsuario" value="" />
  <input type="hidden" name="liCodini" id="liCodini" value="" />
  <div class="mb-3">
    <label class="form-label">Nova Senha: </label>
    <input type="password" name="pdNovaSenha" id="pdNovaSenha" value="<%= txt_pdNovaSenha %>" class="form-control" placeholder="Nova Senha..." name="usuSen" />
  </div>
  <div class="mb-3">
    <label class="form-label">Confirmar Nova Senha: </label>
    <input type="password" name="pdConfNovaSenha" id="pdConfNovaSenha" onkeypress="submeterForm(2, event);" value="<%= txt_pdConfNovaSenha %>" class="form-control" placeholder="Confirmação da Nova Senha..." name="usuConfSen" />
  </div>
  <button type="button" onclick="enviarPdLoginIni(1);" class="btn btn-paradecision btn-block">Enviar</button>
  <div id="pdMensagens" style="display:<%= txt_pdMsgDisplay %>;"><br/><%= txt_pdMensagens %></div>
  <%@ include file="../fld_control/camposControle.jsp" %>
  </form>
  </div>
</div>
<script type="text/javascript">
	var jsPdAcao = "<%= txt_pdAcao %>";
	document.getElementById("pdUsuario").value = parent.document.getElementById("pdUsuSessao").value;
	document.getElementById("liCodini").value = parent.document.getElementById("pdCodini").value;
	if (jsPdAcao == "envLoginIni") {
		// ###### Preenchendo Campos de Controle ######
		document.getElementById("ct_A02_USUARIO").value = "<%= oUsuarioModel.getA02_usuario() %>";
		document.getElementById("ct_A02_CODIGO").value = "<%= oUsuarioModel.getA02_codigo() %>";
		document.getElementById("ct_A01_NOME").value = "Empresa X";
		document.getElementById("ct_A01_CODIGO").value = "1";
		// ############################################
		enviarPdLoginIni(2);
	}
</script>
</body>
</html>