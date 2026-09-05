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
AgendaUsuariosModel oAgendaUsuariosModel = new AgendaUsuariosModel();
AgendaUsuariosControl oAgendaUsuariosControl = new AgendaUsuariosControl();
//-------------------------------------------------
int achouUsuario = 0;
oAgendaUsuariosModel.setoAgendaModel(oAgendaModel);
oAgendaUsuariosModel = oAgendaUsuariosControl.selectUsuariosDaAgenda(oAgendaUsuariosModel);
if (oAgendaUsuariosModel.getArrUsuarioModel().size() > 0) {
	achouUsuario = 1;
}
%>
<a onclick="abrirAgendaFatores('AgendaEspecialistasForm')" href="javascript:void(0);">Voltar para Lista de Critérios</a>

<table class="tabTitForm"><tr>
<td>
<table>
<tr><td><b>EMPRESA:</b></td><td><span id="au_A01_NOME"></span></td></tr>
<tr><td><b>AGENDA:</b></td><td><span id="au_A04_TITULO"></span><br/></td></tr>
<tr><td><b>STATUS:</b></td><td><span id="au_A04_TXT_STATUS"></span></td></tr>
</table>
</td>
<td style="text-align:right;"><span class="stlCodForm">AgeUsuParPend</span></td>
</tr></table>
<br/>

<form id="AgendaEspecialistasForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<h3>LISTA DE PENDÊNCIAS DE CRITÉRIOS DOS PARTICIPANTES ESPECIALISTAS</h3>
<table>
<tr style="text-align: left;">
<th>Nome dos Especialistas</th>
<th>Quantidade dos Pareceres que Faltam ser Respondidos</th>
</tr>
<%
if (achouUsuario == 1) {
	AgendaFatoresModel oAgendaFatoresModel = new AgendaFatoresModel();
	AgendaFatoresControl oAgendaFatoresControl = new AgendaFatoresControl();
	//-------------------------------------------------
	oAgendaFatoresModel.setoAgendaModel(oAgendaModel);
	oAgendaFatoresModel = oAgendaFatoresControl.selectFatoresDaAgenda(oAgendaFatoresModel);
	int qtdFatores = oAgendaFatoresModel.getArrFatorModel().size();
	int qtdPareceresFaltantes = 0;
	int qtdPareceresUsuario = 0;
	if (qtdFatores > 0) {
		UsuarioModel oUsuarioModel;
		ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
		arrUsuarioModel = oAgendaUsuariosModel.getArrUsuarioModel();
		for (int i = 0; i < arrUsuarioModel.size(); i++) {
			oUsuarioModel = oAgendaUsuariosModel.getArrUsuarioModel().get(i);
			oAgendaUsuarioPerfilModel = oAgendaUsuariosModel.getArrAgendaUsuarioPerfilModel().get(i);
			int tit = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
			int espec = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista();
			if (espec == 1 || tit == 1) {
				AgendaUsuarioPareceresModel oAgendaUsuarioPareceresModel = new AgendaUsuarioPareceresModel();
				AgendaUsuarioPareceresControl oAgendaUsuarioPareceresControl = new AgendaUsuarioPareceresControl();
				oAgendaUsuarioPareceresModel.setoAgendaModel(oAgendaModel);
				oAgendaUsuarioPareceresModel.setoUsuarioModel(oUsuarioModel);
				oAgendaUsuarioPareceresModel = oAgendaUsuarioPareceresControl.selectPareceresAgUsu(oAgendaUsuarioPareceresModel);
				ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = oAgendaUsuarioPareceresModel.getArrParecerFatorUsuarioModel();
				//---------------------------------------------------------
				qtdPareceresUsuario = arrParecerFatorUsuarioModel.size();
				qtdPareceresFaltantes = qtdFatores;
				if (qtdPareceresUsuario > 0) {
					ParecerFatorUsuarioModel oParecerFatorUsuarioModel;
					for (int k = 0; k < qtdPareceresUsuario; k++) {
						oParecerFatorUsuarioModel = arrParecerFatorUsuarioModel.get(k);
						String str_a07_certeza = oParecerFatorUsuarioModel.getStr_a07_certeza();
						String str_a07_contradicao = oParecerFatorUsuarioModel.getStr_a07_contradicao();
						if(str_a07_contradicao != null && str_a07_certeza != null) {
							if(!(str_a07_contradicao.equals("")) && !(str_a07_certeza.equals(""))) {
								qtdPareceresFaltantes--;
							}
						}
					}
				}
%>
<tr>
<td><%= oUsuarioModel.getA02_nome() %></td>
<td><%= qtdPareceresFaltantes %></td>
</tr>
<%}}}}%>
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
//  ### contorle de permição de visualização da lista de Usuários ###
var au_A05_PERFIL_TITULAR = document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
if (au_A05_PERFIL_TITULAR.value == "1") {
}
</script>
</body>
</html>