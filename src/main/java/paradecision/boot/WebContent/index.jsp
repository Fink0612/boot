<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/principal.css" />
<script type="text/javascript" src="js/funcoesGerais.js"></script>
<title>Zeen Storm</title>
</head>

<body>
<input type="hidden" name="pdCodini" id="pdCodini" />
<%@ include file="fld_util/header.jsp" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Control.*" %>
<%!
String indCodini = "";
String indIFrame = "";
String indUsuSessao = "";
String indNomeUsuSessao = "";
%>
<%
indCodini = request.getParameter("codini");
if (indCodini == null) indCodini = "";
//-------------------------------------
UsuarioModel indUsuarioModel = new UsuarioModel();
UsuarioControl indUsuarioControl = new UsuarioControl();
//-------------------------------------
if (indCodini.trim() != "") {
	indUsuarioModel.setA02_codigo_link(indCodini);
	indUsuarioModel = indUsuarioControl.selectUserIni(indUsuarioModel);
}
//-------------------------------------
indIFrame = "fld_login/login.jsp";
if (indUsuarioModel.getA02_codigo() != 0) {
	indIFrame = "fld_login/loginIni.jsp";
	indUsuSessao = indUsuarioModel.getA02_usuario().trim();
	indNomeUsuSessao = indUsuarioModel.getA02_nome().trim();
}
%>
<iframe id="indIFrame" src="" width="100%" style="height:90vh;">
    Your browser doesn't support iframes
</iframe>
<script type="text/javascript">
	var jsCodini = "<%= indCodini %>";
	var jsIFrame = "<%= indIFrame %>";
	var jsPdUsuSessao = "<%= indUsuSessao %>";
	var indIFrame = document.getElementById("indIFrame");
	document.getElementById("pdCodini").value = jsCodini;
	if (jsPdUsuSessao != "") {
		var jsPdNomeUsuSessao = "<%= indNomeUsuSessao %>";
		var pdUsuSessao = document.getElementById("pdUsuSessao");
		var pdNomeUsuSessao = document.getElementById("pdNomeUsuSessao");
		var txt_user_session = document.getElementById("txt_user_session");
		pdUsuSessao.value = jsPdUsuSessao;
		pdNomeUsuSessao.value = jsPdNomeUsuSessao;
		txt_user_session.innerHTML = jsPdNomeUsuSessao.toUpperCase();
	}
	//-------------------------------------------
	indIFrame.src = jsIFrame;
</script>
</body>
</html>
