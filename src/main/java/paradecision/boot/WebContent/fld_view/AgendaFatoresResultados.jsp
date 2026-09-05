<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
<script type="text/javascript" src="../js/funcoesConfirm.js"></script>
</head>
<body>
<%@ include file="../fld_control/confirmControle.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="java.sql.Date" %>
<%
AgendaModel oAgendaModel = new AgendaModel();
AgendaControl oAgendaControl = new AgendaControl();
AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
//-------------------------------------------------
long au_ct_A04_CODIGO = Long.parseLong(request.getParameter("ct_A04_CODIGO"));
String au_A04_TITULO = "";
String au_A04_STATUS_DT_LIMITE = "";
String au_A04_DATA_LIMITE = "";
String au_A04_STATUS = "";
String au_A04_TXT_STATUS = "";
oAgendaModel.setA04_codigo(au_ct_A04_CODIGO);
oAgendaModel = oAgendaControl.selectAgenda(oAgendaModel);
try {
	if (oAgendaModel.getA01_codigo() > 0) {
		au_A04_TITULO = oAgendaModel.getA04_titulo();
		au_A04_STATUS_DT_LIMITE = Long.toString(oAgendaModel.getA04_status_dt_limite());
		if (au_A04_STATUS_DT_LIMITE.equals("1")) {
			au_A04_DATA_LIMITE = oAgendaModel.getA04_data_limite().toString();
		}
		au_A04_STATUS = Integer.toString(oAgendaModel.getA04_status());
		au_A04_TXT_STATUS = Pck_Util.MetodosUteis.retornaTxtStatusAgenda(au_A04_STATUS);
	}
} catch (Exception e) {
}
//-------------------------------------------------
long au_ct_A02_CODIGO = Long.parseLong(request.getParameter("ct_A02_CODIGO"));
int au_A05_PERFIL_TITULAR = 0;
int au_A05_PERFIL_FACILITADOR = 0;
int au_A05_PERFIL_ESPECIALISTA = 0;
int au_A05_PERFIL_ANALISTA = 0;
oAgendaUsuarioPerfilModel.setA02_codigo(au_ct_A02_CODIGO);
oAgendaUsuarioPerfilModel.setA04_codigo(au_ct_A04_CODIGO);
oAgendaUsuarioPerfilModel = oAgendaUsuarioPerfilControl.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
try {
	if (oAgendaUsuarioPerfilModel.getA05_codigo() > 0) {
		au_A05_PERFIL_TITULAR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
		au_A05_PERFIL_FACILITADOR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador();
		au_A05_PERFIL_ESPECIALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista();
		au_A05_PERFIL_ANALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista();
	}
} catch (Exception e) {
}
//-------------------------------------------------
AgendaFatoresModel oAgendaFatoresModel = new AgendaFatoresModel();
AgendaFatoresControl oAgendaFatoresControl = new AgendaFatoresControl();
//-------------------------------------------------
int achouFator = 0;
oAgendaFatoresModel.setoAgendaModel(oAgendaModel);
oAgendaFatoresModel = oAgendaFatoresControl.selectFatoresDaAgenda(oAgendaFatoresModel);
if (oAgendaFatoresModel.getArrFatorModel().size() > 0) {
	achouFator = 1;
}
%>
<a onclick="abrirEmpresa_Usu_Agendas('AgendaFatoresForm')" href="javascript:void(0);">Voltar para Lista de Agendas</a>

<table class="tabTitForm"><tr>
<td>
<table>
<tr><td><b>EMPRESA:</b></td><td><span id="au_A01_NOME"></span></td></tr>
<tr><td><b>AGENDA:</b></td><td><span id="au_A04_TITULO"></span><br/></td></tr>
<tr><td><b>STATUS:</b></td><td><span id="au_A04_TXT_STATUS"></span></td></tr>
</table>
</td>
<td style="text-align:right;"><span class="stlCodForm">AgeFatRes</span></td>
</tr></table>

<form id="AgendaFatoresForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<%@ include file="../fld_control/camposCtrlFator.jsp" %>
<h3>RESULTADOS</h3>

<table>
<tr><td><b>Viabilidade:</b></td><td><%= Math.abs(oAgendaModel.getA04_certeza_resultado()) %> %</td></tr>
<tr><td><b>Dúvida:</b></td><td><%= Math.abs(oAgendaModel.getA04_contradicao_resultado()) %> %</td></tr>
<tr><td><b>Resultado Geral:</b></td><td><%= oAgendaModel.getA04_resultado() %></td></tr>
</table>

<div id="afr_CalcularResultados" style="display:none;">
<a onclick="abreConfirmMensagem('Confirma o Cálculo dos Resultados desta Agenda?', 'calcResAg');" 
href="javascript:void(0);">Calcular Resultados</a><br/>
</div>
<h3>LISTA DE CRITÉRIOS PARA ESTA AGENDA</h3>
<table>
<tr style="text-align: left;">
<th>Título</th>
<th>Autor</th>
<th>Descrição</th>
<th>Viabilidade</th>
<th>Dúvida</th>
<th>Resultado Parcial</th>
</tr>
<%
if (achouFator == 1) {
	FatorModel oFatorModel;
	ArrayList<FatorModel> arrFatorModel = new ArrayList<FatorModel>();
	arrFatorModel = oAgendaFatoresModel.getArrFatorModel();
	UsuarioModel oUsuarioModel;
	ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	arrUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel();
	String displayLink = "";
	String displaySoTexto = "";
	for (int i = 0; i < arrFatorModel.size(); i++) {
		oFatorModel = oAgendaFatoresModel.getArrFatorModel().get(i);
		oUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel().get(i);
		displayLink = "none";
		displaySoTexto = "inline";
		if (au_ct_A02_CODIGO == oUsuarioModel.getA02_codigo()) {
			displayLink = "inline";
			displaySoTexto = "none";
		}
%>
<tr>
<td><%= oFatorModel.getA06_titulo() %></td>
<td><%= oUsuarioModel.getA02_nome() %></td>
<td><%= oFatorModel.getA06_descricao() %></td>
<td><%= Math.abs(oFatorModel.getA06_certeza_resultante_fator()) %> %</td>
<td><%= Math.abs(oFatorModel.getA06_contradicao_resultante_fator()) %> %</td>
<td><%= oFatorModel.getA06_resultado_fator() %></td>
</tr>
<%}}%>
</table>
</form>
<script type="text/javascript">
//-----------------------------------------------------------------
document.getElementById("ct_A04_TITULO").value = "<%= au_A04_TITULO %>";
document.getElementById("ct_A04_STATUS_DT_LIMITE").value = "<%= au_A04_STATUS_DT_LIMITE %>";
document.getElementById("ct_A04_DATA_LIMITE").value = "<%= au_A04_DATA_LIMITE %>";
document.getElementById("ct_A04_STATUS").value = "<%= au_A04_STATUS %>";
document.getElementById("ct_A04_TXT_STATUS").value = "<%= au_A04_TXT_STATUS %>";
//-----------------------------------------------------------------
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR").value = "<%= au_A05_PERFIL_TITULAR %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR").value = "<%= au_A05_PERFIL_FACILITADOR %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA").value = "<%= au_A05_PERFIL_ESPECIALISTA %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA").value = "<%= au_A05_PERFIL_ANALISTA %>";
//-----------------------------------------------------------------
var au_A01_NOME = document.getElementById("au_A01_NOME");
au_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
var au_A04_TITULO = document.getElementById("au_A04_TITULO");
au_A04_TITULO.innerHTML = document.getElementById("ct_A04_TITULO").value;
var au_A04_TXT_STATUS = document.getElementById("au_A04_TXT_STATUS");
au_A04_TXT_STATUS.innerHTML = document.getElementById("ct_A04_TXT_STATUS").value;
//  -----------------------------------------------------------------
//  ### controle de permição de visualização da lista de Usuários ###
var afp_A05_PERFIL_TITULAR = document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
if (afp_A05_PERFIL_TITULAR.value == "1") {
	var afr_CalcularResultados = document.getElementById("afr_CalcularResultados");
	afr_CalcularResultados.style.display = "";
}
</script>
</body>
</html>