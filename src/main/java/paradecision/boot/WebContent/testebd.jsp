<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>TESTE</title>
</head>

<body>
<%@ page import="Pck_Model.*" %>
<input type="hidden" name="pdCodini" id="pdCodini" />
<%!
String tbd = "";
%>
<%
AcessoBD abd = new AcessoBD();
tbd = abd.acessarBD();
%>
<h1>Teste de Acesso a Banco de Dados</h1>
<h2><%= tbd %></h2>
<h3>Atual: <%= abd.conFac.ipAtual %></h3>
<h3>Prncipal: <%= abd.conFac.ipPrincipal %></h3>
<h3>Server: <%= abd.conFac.ipServer %></h3>
<h3>URL: <%= abd.conFac.url %></h3>
</body>
</html>
