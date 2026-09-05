<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
<script type="text/javascript" src="../js/funcoesCadastro.js"></script>
</head>
<body>

<table class="tabTitForm"><tr>
<td>
<span class="stlTitForm">CADASTRO DE CRITÉRIOS</span>
</td>
<td style="text-align:right;"><span class="stlCodForm">CadFat</span></td>
</tr></table>
<br/>
<table>
<tr><td><b>EMPRESA:</b></td> <td><span id="f_A01_NOME"></span></td></tr>
<tr><td><b>AGENDA:</b></td> <td><span id="f_A04_TITULO"></span></td></tr>
</table>
<br/>
<form id="CadastroFatorForm" action="#" method="post" autocomplete="off">
<span id="f_Mensagens" style="display:none; color:red;"></span>
<table>
<tr><td><label for="f_a06_titulo">Título: </label></td>
<td><input type="text" name="f_a06_titulo" id="f_a06_titulo" value="" /></td></tr>
<tr><td><label for="f_a06_descricao">Descrição: </label></td>
<td><textarea name="f_a06_descricao" id="f_a06_descricao" rows="4" cols="40"></textarea></td></tr>
</table>
<span>------------------------------------------------</span><br/>
<input type="button" id="salvar" value="Salvar e Sair" onclick="salvarCadastroFator(1)" />
<input type="button" id="cancelar" value="Cancelar e Sair" onclick="cancelarCadastroFator()" />
<br/>
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
</form>
<script type="text/javascript">
var f_A01_NOME = document.getElementById("f_A01_NOME");
f_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
var f_A04_TITULO = document.getElementById("f_A04_TITULO");
f_A04_TITULO.innerHTML = document.getElementById("ct_A04_TITULO").value;
</script>
</body>
</html>