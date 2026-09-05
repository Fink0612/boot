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
<%@ page import="java.util.ArrayList" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="java.sql.Date" %>
<%
EmpresaModel oEmpresaModel = new EmpresaModel();
AgendaModel oAgendaModel = new AgendaModel();
//AgendaControl oAgendaControl = new AgendaControl();
UsuarioModel oEmpUsuarioModel = new UsuarioModel();
UsuarioModel oAgeUsuarioModel = new UsuarioModel();
//-------------------------------------------------
AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
//AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
//-------------------------------------------------
EmpresaUsuariosModel oEmpresaUsuariosModel = new EmpresaUsuariosModel();
EmpresaUsuariosControl oEmpresaUsuariosControl = new EmpresaUsuariosControl();
//-------------------------------------------------
AgendaUsuariosModel oAgendaUsuariosModel = new AgendaUsuariosModel();
AgendaUsuariosControl oAgendaUsuariosControl = new AgendaUsuariosControl();
//-------------------------------------------------
//..Capturando dados da Agenda
long au_ct_A04_CODIGO = Long.parseLong(request.getParameter("ct_A04_CODIGO"));
//String au_A04_TITULO = "";
//String au_A04_STATUS_DT_LIMITE = "";
//String au_A04_DATA_LIMITE = "";
//String au_A04_STATUS = "";
oAgendaModel.setA04_codigo(au_ct_A04_CODIGO);
//oAgendaModel = oAgendaControl.selectAgenda(oAgendaModel);
//try {
//	if (oAgendaModel.getA01_codigo() > 0) {
//		au_A04_TITULO = oAgendaModel.getA04_titulo();
//		au_A04_STATUS_DT_LIMITE = Long.toString(oAgendaModel.getA04_status_dt_limite());
//		au_A04_DATA_LIMITE = oAgendaModel.getA04_data_limite().toString();
//		au_A04_STATUS = Integer.toString(oAgendaModel.getA04_status());
//	}
//} catch (Exception e) {
//}
//-------------------------------------------------
//..Capturando dados do Usuário Atual em relação à Agenda
long au_ct_A02_CODIGO = Long.parseLong(request.getParameter("ct_A02_CODIGO"));
//int au_A05_PERFIL_TITULAR = 0;
//int au_A05_PERFIL_FACILITADOR = 0;
//int au_A05_PERFIL_ESPECIALISTA = 0;
//int au_A05_PERFIL_ANALISTA = 0;
oAgendaUsuarioPerfilModel.setA02_codigo(au_ct_A02_CODIGO);
oAgendaUsuarioPerfilModel.setA04_codigo(au_ct_A04_CODIGO);
//oAgendaUsuarioPerfilModel = oAgendaUsuarioPerfilControl.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
//try {
//	if (oAgendaUsuarioPerfilModel.getA05_codigo() > 0) {
//		au_A05_PERFIL_TITULAR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
//		au_A05_PERFIL_FACILITADOR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador();
//		au_A05_PERFIL_ESPECIALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista();
//		au_A05_PERFIL_ANALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista();
//	}
//} catch (Exception e) {
//}
//-------------------------------------------------
//..Capturando os Usuários da Empresa (para permitir seleção)
int achouUsuariosEmpresa = 0;
long aue_ct_A01_CODIGO = Long.parseLong(request.getParameter("ct_A01_CODIGO"));
oEmpresaModel.setA01_codigo(aue_ct_A01_CODIGO);
oEmpresaUsuariosModel.setoEmpresaModel(oEmpresaModel);
oEmpresaUsuariosModel = oEmpresaUsuariosControl.selectUsuariosDaEmpresa(oEmpresaUsuariosModel);
if (oEmpresaUsuariosModel.getArrUsuarioModel().size() > 0) {
	achouUsuariosEmpresa = 1;
}
//-------------------------------------------------
int achouUsuariosAgenda = 0;
oAgendaUsuariosModel.setoAgendaModel(oAgendaModel);
oAgendaUsuariosModel = oAgendaUsuariosControl.selectUsuariosDaAgenda(oAgendaUsuariosModel);
if (oAgendaUsuariosModel.getArrUsuarioModel().size() > 0) {
	achouUsuariosAgenda = 1;
}
%>

<table class="tabTitForm"><tr>
<td>
<table>
<tr><td><b>EMPRESA:</b></td><td><span id="au_A01_NOME"></span></td></tr>
<tr><td><b>AGENDA:</b></td><td><span id="au_A04_TITULO"></span></td></tr>
</table>
</td>
<td style="text-align:right;"><span class="stlCodForm">AgeUsuEdit</span></td>
</tr></table>

<form id="AgendaUsuariosForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<h3>LISTA DE PARTICIPANTES E SEUS RESPECTIVOS PERFIS PARA ESTA AGENDA</h3>
<table>
<tr style="text-align: left;">
<th>Selec</th>
<th>Nome do Usuário</th>
<th>Titular</th>
<th>Facilitador</th>
<th>Especialista</th>
<th>Analista</th>
</tr>
<%
int qtdUsuariosEmpresa = 0;
if (achouUsuariosEmpresa == 1) {
	ArrayList<UsuarioModel> arrEmpUsuariosModel = new ArrayList<UsuarioModel>();
	arrEmpUsuariosModel = oEmpresaUsuariosModel.getArrUsuarioModel();
	ArrayList<UsuarioModel> arrAgeUsuariosModel = new ArrayList<UsuarioModel>();
	arrAgeUsuariosModel = oAgendaUsuariosModel.getArrUsuarioModel();
	String checkUsu = "";
	String checkT = "";
	String checkF = "";
	String checkE = "";
	String checkA = "";
	String ncheckT = "";
	String ncheckF = "";
	String ncheckE = "";
	String ncheckA = "";
	qtdUsuariosEmpresa = arrEmpUsuariosModel.size();
	for (int emp = 0; emp < qtdUsuariosEmpresa; emp++) {
		int ageArrLoc = -1;
		long val_a02_codigo = -1;
		long val_a05_codigo = -1;
		int val_usu_marcado = 0;
		oEmpUsuarioModel = oEmpresaUsuariosModel.getArrUsuarioModel().get(emp);
		val_a02_codigo = oEmpUsuarioModel.getA02_codigo();
		for (int age = 0; age < arrAgeUsuariosModel.size(); age++) {
			oAgeUsuarioModel = oAgendaUsuariosModel.getArrUsuarioModel().get(age);
			if (oEmpUsuarioModel.getA02_codigo() == oAgeUsuarioModel.getA02_codigo()) {
				ageArrLoc = age;
			}
		}
		checkUsu = "";
		checkT = "";
		checkF = "";
		checkE = "";
		checkA = "";
		ncheckT = "0";
		ncheckF = "0";
		ncheckE = "0";
		ncheckA = "0";
		if (ageArrLoc != -1) {
			val_usu_marcado = 1;
			String txtChecked = "checked=\"checked\" ";
			checkUsu = txtChecked;
			oAgendaUsuarioPerfilModel = oAgendaUsuariosModel.getArrAgendaUsuarioPerfilModel().get(ageArrLoc);
			val_a05_codigo = oAgendaUsuarioPerfilModel.getA05_codigo();
			if(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular()==1) {
				checkT = txtChecked;
				ncheckT = "1";
			}
			if(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador()==1) {
				checkF = txtChecked;
				ncheckF = "1";
			}
			if(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista()==1) {
				checkE = txtChecked;
				ncheckE = "1";
			}
			if(oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista()==1) {
				checkA = txtChecked;
				ncheckA = "1";
			}
		}
%>
<tr>
<td>
<span style="display:none;">
marc<input type="text" name="a02_marcado_<%= emp %>" value="<%= val_usu_marcado %>" size="8" />
a02Cod<input type="text" name="a02_codigo_<%= emp %>" value="<%= val_a02_codigo %>" size="8" />
a05Cod<input type="text" name="a05_codigo_<%= emp %>" value="<%= val_a05_codigo %>" size="8" />
</span>
<input type="checkbox" name="usu_emp_<%= emp %>" value="1" <%= checkUsu %>/>
</td>
<td><%= oEmpUsuarioModel.getA02_nome() %></td>
<td>
<input type="hidden" name="aux_per_T_<%= emp %>" value="<%= ncheckT %>" />
<span class="cCheckTitular" style="display:none;">
<input type="checkbox" name="usu_per_T_<%= emp %>" value="1" <%= checkT %>/>
</span>
<span class="cTextTitular" style="display:inline;"><%= ncheckT %></span>
</td>
<td>
<input type="hidden" name="aux_per_F_<%= emp %>" value="<%= ncheckF %>" />
<input type="checkbox" name="usu_per_F_<%= emp %>" value="1" <%= checkF %>/>
</td>
<td>
<input type="hidden" name="aux_per_E_<%= emp %>" value="<%= ncheckE %>" />
<input type="checkbox" name="usu_per_E_<%= emp %>" value="1" <%= checkE %>/>
</td>
<td>
<input type="hidden" name="aux_per_A_<%= emp %>" value="<%= ncheckA %>" />
<input type="checkbox" name="usu_per_A_<%= emp %>" value="1" <%= checkA %>/>
</td>
</tr>
<%}} // fechando o for e o if acima%>
</table>
<input type="hidden" name="qtdUsuariosEmpresa" value="<%= qtdUsuariosEmpresa %>"/>
<input type="button" id="salvar" value="Salvar e Sair" onclick="salvarUsuariosAgenda()" />
<input type="button" id="cancelar" value="Cancelar e Sair" onclick="cancelarUsuariosAgenda()" />
</form>
<script type="text/javascript">
//-----------------------------------------------------------------
var au_A01_NOME = document.getElementById("au_A01_NOME");
au_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
var au_A04_TITULO = document.getElementById("au_A04_TITULO");
au_A04_TITULO.innerHTML = document.getElementById("ct_A04_TITULO").value;
//  -----------------------------------------------------------------
//  ### contorle de permição de visualização da lista de Usuários ###
var au_A05_PERFIL_TITULAR = document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR");
var au_editarDadosUsuarios = document.getElementById("au_editarDadosUsuarios");
if (au_A05_PERFIL_TITULAR.value == "1") {
	au_editarDadosUsuarios.style.display = "";
}
</script>
</body>
</html>