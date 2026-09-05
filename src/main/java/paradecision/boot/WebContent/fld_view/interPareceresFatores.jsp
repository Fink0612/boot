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
<td style="text-align:right;"><span class="stlCodForm">intParFat</span></td>
</tr></table>
 
<form id="interPareceresFatoresForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterPareceresFatores(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterPareceresFatores(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
String okIteracao = "";
String okMetodo = "OK";
String txt_ct_QTD_FATORES_AGENDA = request.getParameter("ct_QTD_FATORES_AGENDA");
int num_ct_QTD_FATORES_AGENDA = MetodosUteis.retornaInt(txt_ct_QTD_FATORES_AGENDA);
if (num_ct_QTD_FATORES_AGENDA > 0) {
	for (int ii = 0; ii < num_ct_QTD_FATORES_AGENDA; ii++) {
		okIteracao = "";
		String txt_afp_A02_CODIGO = request.getParameter("ct_A02_CODIGO");
		long num_afp_A02_CODIGO = MetodosUteis.retornaLong(txt_afp_A02_CODIGO);
		String txt_afp_A06_CODIGO = request.getParameter("afp_A06_CODIGO_" + ii);
		long num_afp_A06_CODIGO = MetodosUteis.retornaLong(txt_afp_A06_CODIGO);
		String txt_afp_A07_CERTEZA = request.getParameter("afp_A07_CERTEZA_" + ii);
		double num_afp_A07_CERTEZA = MetodosUteis.retornaDouble(txt_afp_A07_CERTEZA);
		String txt_afp_A07_CONTRADICAO = request.getParameter("afp_A07_CONTRADICAO_" + ii);
		double num_afp_A07_CONTRADICAO = MetodosUteis.retornaDouble(txt_afp_A07_CONTRADICAO);
		//-----------------------------------------------------
//		if (!(num_afp_A07_CERTEZA < 0 && num_afp_A07_CONTRADICAO < 0)) {
		long codParecer = 0;
		ParecerFatorUsuarioModel oParecerFatorUsuarioModel = new ParecerFatorUsuarioModel();
		ParecerFatorUsuarioControl oParecerFatorUsuarioControl = new ParecerFatorUsuarioControl();
		oParecerFatorUsuarioModel.setA02_codigo(num_afp_A02_CODIGO);
		oParecerFatorUsuarioModel.setA06_codigo(num_afp_A06_CODIGO);
		oParecerFatorUsuarioModel = oParecerFatorUsuarioControl.selectParecerFatorUsuario(oParecerFatorUsuarioModel);
		oParecerFatorUsuarioModel.setA07_certeza(num_afp_A07_CERTEZA);
		oParecerFatorUsuarioModel.setA07_contradicao(num_afp_A07_CONTRADICAO);
		codParecer = oParecerFatorUsuarioModel.getA07_codigo();
		if (codParecer > 0) {
			okIteracao = oParecerFatorUsuarioControl.updateParecerFatorUsuario(oParecerFatorUsuarioModel);
		} else {
			okIteracao = oParecerFatorUsuarioControl.insertParecerFatorUsuario(oParecerFatorUsuarioModel);
		}
		if (okIteracao.equals("NOK")) {
			okMetodo = "NOK";
		}
//		}
	}
}
//----------------------------------------------------
%>
<script type="text/javascript">
var okMetodo = "<%= okMetodo %>";
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == "OK") {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Pareceres salvos com sucesso!";
	var interBotaoOK = document.getElementById("interBotaoOK"); 
	interBotaoOK.style.display = "";
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Problemas com o registro dos Pareceres!";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>