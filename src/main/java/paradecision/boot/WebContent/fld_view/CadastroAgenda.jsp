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
<span class="stlTitForm">CADASTRO DE AGENDA</span>
</td>
<td style="text-align:right;"><span class="stlCodForm">CadAge</span></td>
</tr></table>

<p><b>EMPRESA: </b><span id="a_A01_NOME"></span></p>
<form id="CadastroAgendaForm" action="#" method="post" autocomplete="off">
<span id="a_Mensagens" style="display:none; color:red;"></span>
<table><tr>
<td><label for="a_a04_titulo">Título: </label>
</td><td><input type="text" name="a_a04_titulo" id="a_a04_titulo" value="" /><br/>
</td></tr><tr>
<td><label for="a_a04_descricao">Descrição: </label>
</td><td><textarea name="a_a04_descricao" id="a_a04_descricao" rows="4" cols="40"></textarea><br/>
</td></tr>
</table>
<input type="checkbox" name="a_a04_status_dt_limite" 
	id="a_a04_status_dt_limite" value="1" onchange="ctrlVisualDataLimite();" />
<label for="a_a04_status_dt_limite"> - com Data Limite</label><br/>
<span id="a_cmpDataLimite" style="display:none;">
<label for="a_a04_data_limite">Data Limite: </label>
<input type="date" name="a_a04_data_limite" id="a_a04_data_limite" value="" /><br/>
</span>
<span>------------------------------------------------</span><br/>
<input type="button" id="salvar" value="Salvar e Sair" onclick="salvarCadastroAgenda(1)" />
<input type="button" id="cancelar" value="Cancelar e Sair" onclick="cancelarCadastroAgenda(1)" />
<br/>
<%@ include file="../fld_control/camposControle.jsp" %>
</form>
<script type="text/javascript">
var a_A01_NOME = document.getElementById("a_A01_NOME");
a_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
</script>
</body>
</html>