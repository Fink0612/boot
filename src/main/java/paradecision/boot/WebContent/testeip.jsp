<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/principal.css" />
<script type="text/javascript" src="js/funcoesGerais.js"></script>
<title>Tomada de Decisão Colaborativa - Teste IP</title>
</head>

<body>
<input type="hidden" name="pdCodini" id="pdCodini" />
<%@ include file="fld_util/header.jsp" %>
<%@ page import="java.net.InetAddress" %>
<h2>
<%
InetAddress addr=null;
try {
	addr = InetAddress.getLocalHost();
	out.println("Address: " + addr.getHostAddress() + "<br/>");
	out.println("Host Name: " + addr.getHostName() + "<br/>");
} catch (Exception e) {
	out.println("OPS!!");
}
%>
</h2>
</body>
</html>
