<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<link rel="stylesheet" type="text/css" href="../css/parecer.css"/>
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
<script type="text/javascript" src="../js/funcoesCadastro.js"></script>
<script type="text/javascript" src="../js/funcoesConfirm.js"></script>
<script type="text/javascript" src="../js/funcoesPareceres.js"></script>
</head>
<body>
<%@ include file="../fld_control/confirmControle.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
long afp_ct_A04_CODIGO = Long.parseLong(request.getParameter("ct_A04_CODIGO"));
long afp_ct_A02_CODIGO = Long.parseLong(request.getParameter("ct_A02_CODIGO"));
//-------------------------------------------------
AgendaModel oAgendaModel = new AgendaModel();
AgendaControl oAgendaControl = new AgendaControl();
AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
//-------------------------------------------------
String afp_A04_TITULO = "";
String afp_A04_STATUS_DT_LIMITE = "";
String afp_A04_DATA_LIMITE = "";
String afp_A04_STATUS = "";
String afp_A04_TXT_STATUS = "";
oAgendaModel.setA04_codigo(afp_ct_A04_CODIGO);
oAgendaModel = oAgendaControl.selectAgenda(oAgendaModel);
try {
	if (oAgendaModel.getA01_codigo() > 0) {
		afp_A04_TITULO = oAgendaModel.getA04_titulo();
		afp_A04_STATUS_DT_LIMITE = Long.toString(oAgendaModel.getA04_status_dt_limite());
		if (afp_A04_STATUS_DT_LIMITE.equals("1")) {
			afp_A04_DATA_LIMITE = oAgendaModel.getA04_data_limite().toString();
		}
		afp_A04_STATUS = Integer.toString(oAgendaModel.getA04_status());
		afp_A04_TXT_STATUS = Pck_Util.MetodosUteis.retornaTxtStatusAgenda(afp_A04_STATUS);
	}
} catch (Exception e) {
}
//-------------------------------------------------
int afp_A05_PERFIL_TITULAR = 0;
int afp_A05_PERFIL_FACILITADOR = 0;
int afp_A05_PERFIL_ESPECIALISTA = 0;
int afp_A05_PERFIL_ANALISTA = 0;
oAgendaUsuarioPerfilModel.setA02_codigo(afp_ct_A02_CODIGO);
oAgendaUsuarioPerfilModel.setA04_codigo(afp_ct_A04_CODIGO);
oAgendaUsuarioPerfilModel = oAgendaUsuarioPerfilControl.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
try {
	if (oAgendaUsuarioPerfilModel.getA05_codigo() > 0) {
		afp_A05_PERFIL_TITULAR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
		afp_A05_PERFIL_FACILITADOR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador();
		afp_A05_PERFIL_ESPECIALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista();
		afp_A05_PERFIL_ANALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista();
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
<tr><td><b>EMPRESA:</b></td><td><span id="afp_A01_NOME"></span></td></tr>
<tr><td><b>AGENDA:</b></td><td><span id="afp_A04_TITULO"></span><br/></td></tr>
<tr><td><b>STATUS:</b></td><td><span id="afp_A04_TXT_STATUS"></span></td></tr>
</table>
</td>
<td style="text-align:right;"><span class="stlCodForm">AgeFatPar</span></td>
</tr></table>
<br/>

<form id="AgendaFatoresForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<%@ include file="../fld_control/camposCtrlFator.jsp" %>
<div id="afp_editarDadosUsuariosAgendaFatores" style="display:none;">
<a onclick="editarDadosUsuariosAgendaFatores()" href="javascript:void(0);">Selecionar Participantes e seus Perfis para esta Agenda</a><br/>
<a onclick="verificarPareceresPendentes()" href="javascript:void(0);">Verificar os Pareceres Pendentes</a>
</div>
<br/>
<div id="afp_SalvarPareceresFatores" style="display:none;">
<a onclick="salvarPareceresFatores()" href="javascript:void(0);">Salvar Pareceres</a>
</div>
<h3>LISTA DE CRITÉRIOS PARA ESTA AGENDA</h3>
<table id="tabParec">
<tr style="text-align: left;">
<th>Título</th>
<th>Descrição</th>
<th>Grau de Confiança</th>
<th>Dúvida na Resposta</th>
</tr>
<%
int qtdFatoresAgenda = 0;
if (achouFator == 1) {
	String txt_afp_A02_CODIGO = request.getParameter("ct_A02_CODIGO");
	long num_afp_A02_CODIGO = MetodosUteis.retornaLong(txt_afp_A02_CODIGO);
	long num_afp_A06_CODIGO = 0;
	FatorModel oFatorModel;
	ArrayList<FatorModel> arrFatorModel = new ArrayList<FatorModel>();
	arrFatorModel = oAgendaFatoresModel.getArrFatorModel();
	qtdFatoresAgenda = arrFatorModel.size();
	UsuarioModel oUsuarioModel;
	ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	arrUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel();
	for (int ii = 0; ii < arrFatorModel.size(); ii++) {
		long codParecer = 0;
		double numCertezaContradicao = -1;
		String valStr = "";
		String strCerteza = "";
		String strContradicao = "";
		oFatorModel = oAgendaFatoresModel.getArrFatorModel().get(ii);
		oUsuarioModel = oAgendaFatoresModel.getArrUsuarioModel().get(ii);
		ParecerFatorUsuarioModel oParecerFatorUsuarioModel = new ParecerFatorUsuarioModel();
		ParecerFatorUsuarioControl oParecerFatorUsuarioControl = new ParecerFatorUsuarioControl();
		oParecerFatorUsuarioModel.setA02_codigo(num_afp_A02_CODIGO);
		num_afp_A06_CODIGO = oFatorModel.getA06_codigo();
		oParecerFatorUsuarioModel.setA06_codigo(num_afp_A06_CODIGO);
		oParecerFatorUsuarioModel = oParecerFatorUsuarioControl.selectParecerFatorUsuario(oParecerFatorUsuarioModel);
		//System.out.println("aqui0: " + oParecerFatorUsuarioModel.getA07_codigo());
		codParecer = oParecerFatorUsuarioModel.getA07_codigo();
		if (codParecer > 0) {
			numCertezaContradicao = oParecerFatorUsuarioModel.getA07_certeza();
			strCerteza = "";
			valStr = oParecerFatorUsuarioModel.getStr_a07_certeza();
			if (valStr != null) {
				if (!(valStr.equals(""))) {
					if (numCertezaContradicao >= 0) strCerteza = Double.toString(numCertezaContradicao);
				}
			}
			numCertezaContradicao = oParecerFatorUsuarioModel.getA07_contradicao();
			strContradicao = "";
			valStr = oParecerFatorUsuarioModel.getStr_a07_contradicao();
			if (valStr != null) {
				if (!(valStr.equals(""))) {
					if (numCertezaContradicao >= 0) strContradicao = Double.toString(numCertezaContradicao);
				}
			}
			//System.out.println(oParecerFatorUsuarioModel.getStr_a07_certeza());
			//System.out.println(oParecerFatorUsuarioModel.getStr_a07_contradicao());
		}
%>
<tr>
<td>
<input type="hidden" name="afp_A06_CODIGO_<%= ii %>" value="<%= num_afp_A06_CODIGO %>" size="5" />
<%= oFatorModel.getA06_titulo() %>
</td>
<td><%= oFatorModel.getA06_descricao() %></td>
<td><ul>
<% 
String chkMark = "";
String corCert = "";
for (int kk = 0; kk <= 10; kk++) {
	double valC = 0;
	int mm = kk*10;
	chkMark = "nchk";
	corCert = MetodosUteis.getCorNivelCert(kk);
	if (!(strCerteza.equals(""))) {
		valC = MetodosUteis.retornaDouble(strCerteza);
		if (mm < valC+1 && mm > valC-1) {
			chkMark = "chk";
		}
	}
%>
<li>
<a class="<%= chkMark %> <%= corCert %>" id="CertL<%= ii %>C<%= kk %>" 
onclick="selecCerteza(<%= mm %>, <%= ii %>, <%= kk %>)" href="javascript:void(0);"><%= kk %></a>
</li>
<%}%>
</ul>
<input type="hidden" name="afp_A07_CERTEZA_<%= ii %>" id="afp_A07_CERTEZA_<%= ii %>" 
value="<%= strCerteza %>" size="5" />
</td>
<td><ul>
<% 
String corContr = "";
for (int kk = 0; kk <= 10; kk++) {
	double valC = 0;
	int mm = kk*10;
	chkMark = "nchk";
	corContr = MetodosUteis.getCorNivelContr(kk);
	if (!(strContradicao.equals(""))) {
		valC = MetodosUteis.retornaDouble(strContradicao);
		if (mm < valC+1 && mm > valC-1) {
			chkMark = "chk";
		}
	}
%>
<li>
<a class="<%= chkMark %> <%= corContr %>" id="ContL<%= ii %>C<%= kk %>" 
onclick="selecContradicao(<%= mm %>, <%= ii %>, <%= kk %>)" href="javascript:void(0);"><%= kk %></a>
</li>
<%}%>
</ul>
<input type="hidden" name="afp_A07_CONTRADICAO_<%= ii %>" id="afp_A07_CONTRADICAO_<%= ii %>" 
value="<%= strContradicao %>" size="5" />
</td>
</tr>
<%}}%>
</table>

<br/>
<div id="afp_encerrarAgenda" style="display:none;">
<a onclick="abreConfirmMensagem('Confirma o Encerramento desta Agenda?', 'encerrAg');" 
href="javascript:void(0);">Encerrar Agenda</a><br/>
</div>
</form>
<script type="text/javascript">
//-----------------------------------------------------------------
document.getElementById("ct_A04_TITULO").value = "<%= afp_A04_TITULO %>";
document.getElementById("ct_A04_STATUS_DT_LIMITE").value = "<%= afp_A04_STATUS_DT_LIMITE %>";
document.getElementById("ct_A04_DATA_LIMITE").value = "<%= afp_A04_DATA_LIMITE %>";
document.getElementById("ct_A04_STATUS").value = "<%= afp_A04_STATUS %>";
document.getElementById("ct_A04_TXT_STATUS").value = "<%= afp_A04_TXT_STATUS %>";
//-----------------------------------------------------------------
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR").value = "<%= afp_A05_PERFIL_TITULAR %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR").value = "<%= afp_A05_PERFIL_FACILITADOR %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA").value = "<%= afp_A05_PERFIL_ESPECIALISTA %>";
document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA").value = "<%= afp_A05_PERFIL_ANALISTA %>";
//-----------------------------------------------------------------
document.getElementById("ct_QTD_FATORES_AGENDA").value = "<%= qtdFatoresAgenda %>";
//-----------------------------------------------------------------
var afp_A01_NOME = document.getElementById("afp_A01_NOME");
afp_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
var afp_A04_TITULO = document.getElementById("afp_A04_TITULO");
afp_A04_TITULO.innerHTML = document.getElementById("ct_A04_TITULO").value;
var afp_A04_TXT_STATUS = document.getElementById("afp_A04_TXT_STATUS");
afp_A04_TXT_STATUS.innerHTML = document.getElementById("ct_A04_TXT_STATUS").value;
//  -----------------------------------------------------------------
//  ### controle de permição de visualização da lista de Usuários ###
var afp_A05_PERFIL_TITULAR = document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
var afp_A05_PERFIL_ESPECIALISTA = document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA");
if (afp_A05_PERFIL_TITULAR.value == "1") {
	var afp_editarDadosUsuariosAgendaFatores = document.getElementById("afp_editarDadosUsuariosAgendaFatores");
	afp_editarDadosUsuariosAgendaFatores.style.display = "";
	var afp_encerrarAgenda = document.getElementById("afp_encerrarAgenda");
	afp_encerrarAgenda.style.display = "";
}
if (afp_A05_PERFIL_TITULAR.value == "1" || afp_A05_PERFIL_ESPECIALISTA.value == "1") {
	var afp_SalvarPareceresFatores = document.getElementById("afp_SalvarPareceresFatores");
	afp_SalvarPareceresFatores.style.display = "";
}
</script>
</body>
</html>