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
<td style="text-align:right;"><span class="stlCodForm">intAgeUsu</span></td>
</tr></table>

<form id="interAgendaUsuariosForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
<%@ include file="../fld_control/camposCtrlAgenda.jsp" %>
<div id="cjInterMensagem" style="display:none;">
<span id="interMensagem" style="color:red; font-size:20px; font-family:verdana;"></span><br/>
<span id="interBotaoVoltar" style="display:none;">
<input type="button" value="Voltar" onclick="fecharInterAgendaUsuarios(0)" />
</span>
<span id="interBotaoOK" style="display:none;">
<input type="button" value="OK" onclick="fecharInterAgendaUsuarios(1)" />
</span>
</div>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%@ page import="java.sql.Date" %>
<%
//System.out.println("Aqui01");
int okMetodo = 1;
String iau_str_qtd_usuarios = request.getParameter("qtdUsuariosEmpresa");
int iau_int_qtd_usuarios = MetodosUteis.retornaInt(iau_str_qtd_usuarios);
if (iau_int_qtd_usuarios > 0) {
	String str_a04_codigo = request.getParameter("ct_A04_CODIGO");
	long lng_a04_codigo = Long.parseLong(str_a04_codigo);
	for (int ii = 0; ii < iau_int_qtd_usuarios; ii++) {
		String resultBD = "";
		// Valor anterior (ao abrir a página)...
		String val_marc = request.getParameter("a02_marcado_" + ii);
		if (val_marc == null) val_marc = "0";
		if (val_marc == "") val_marc = "0";
		// Valor atual (após o envio)...
		String val_check = request.getParameter("usu_emp_" + ii);
		if (val_check == null) val_check = "0";
		if (val_check == "") val_check = "0";
		//---------------------------------------
		if (!(val_marc.equals(val_check))) {
			if (val_check.equals("0")) {
				String str_a05_codigo = request.getParameter("a05_codigo_" + ii);
				long lng_a05_codigo = Long.parseLong(str_a05_codigo);
				if (lng_a05_codigo > 0) {
					AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
					AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
					oAgendaUsuarioPerfilModel.setA05_codigo(lng_a05_codigo);
					resultBD = oAgendaUsuarioPerfilControl.deleteAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
				}
			} else if (val_check.equals("1")) {
				Date iau_dateToday = MetodosUteis.retornaDataAgora();
				String str_a02_codigo = request.getParameter("a02_codigo_" + ii);
				long lng_a02_codigo = Long.parseLong(str_a02_codigo);
				AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
				AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
				oAgendaUsuarioPerfilModel.setA02_codigo(lng_a02_codigo);
				oAgendaUsuarioPerfilModel.setA04_codigo(lng_a04_codigo);
				oAgendaUsuarioPerfilModel.setA05_num_sequencia(1);
				String usu_per_T = request.getParameter("usu_per_T_" + ii);
				String usu_per_F = request.getParameter("usu_per_F_" + ii);
				String usu_per_E = request.getParameter("usu_per_E_" + ii);
				String usu_per_A = request.getParameter("usu_per_A_" + ii);
				if (usu_per_T == null) usu_per_T = "0";
				if (usu_per_T == "") usu_per_T = "0";
				if (usu_per_F == null) usu_per_F = "0";
				if (usu_per_F == "") usu_per_F = "0";
				if (usu_per_E == null) usu_per_E = "0";
				if (usu_per_E == "") usu_per_E = "0";
				if (usu_per_A == null) usu_per_A = "0";
				if (usu_per_A == "") usu_per_A = "0";
				int num_usu_per_T = Integer.parseInt(usu_per_T);
				int num_usu_per_F = Integer.parseInt(usu_per_F);
				int num_usu_per_E = Integer.parseInt(usu_per_E);
				int num_usu_per_A = Integer.parseInt(usu_per_A);
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(num_usu_per_T);
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(num_usu_per_F);
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(num_usu_per_E);
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(num_usu_per_A);
				oAgendaUsuarioPerfilModel.setA05_dt_cadastro(iau_dateToday);
				resultBD = oAgendaUsuarioPerfilControl.insertPerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
			}
			if (resultBD == "NOK") {
				okMetodo = 0;
			}
		} else {
			if (val_check.equals("1")) {
				String usu_per_T = request.getParameter("usu_per_T_" + ii);
				String usu_per_F = request.getParameter("usu_per_F_" + ii);
				String usu_per_E = request.getParameter("usu_per_E_" + ii);
				String usu_per_A = request.getParameter("usu_per_A_" + ii);
				String aux_per_T = request.getParameter("aux_per_T_" + ii);
				String aux_per_F = request.getParameter("aux_per_F_" + ii);
				String aux_per_E = request.getParameter("aux_per_E_" + ii);
				String aux_per_A = request.getParameter("aux_per_A_" + ii);
				if (usu_per_T == null) usu_per_T = "0";
				if (usu_per_T == "") usu_per_T = "0";
				if (usu_per_F == null) usu_per_F = "0";
				if (usu_per_F == "") usu_per_F = "0";
				if (usu_per_E == null) usu_per_E = "0";
				if (usu_per_E == "") usu_per_E = "0";
				if (usu_per_A == null) usu_per_A = "0";
				if (usu_per_A == "") usu_per_A = "0";
				if (aux_per_T == null) aux_per_T = "0";
				if (aux_per_T == "") aux_per_T = "0";
				if (aux_per_F == null) aux_per_F = "0";
				if (aux_per_F == "") aux_per_F = "0";
				if (aux_per_E == null) aux_per_E = "0";
				if (aux_per_E == "") aux_per_E = "0";
				if (aux_per_A == null) aux_per_A = "0";
				if (aux_per_A == "") aux_per_A = "0";
				if (!usu_per_T.equals(aux_per_T) || !usu_per_F.equals(aux_per_F) || 
						!usu_per_E.equals(aux_per_E) || !usu_per_A.equals(aux_per_A)) {
					Date iau_dateToday = MetodosUteis.retornaDataAgora();
					String str_a05_codigo = request.getParameter("a05_codigo_" + ii);
					long lng_a05_codigo = Long.parseLong(str_a05_codigo);
					AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
					AgendaUsuarioPerfilControl oAgendaUsuarioPerfilControl = new AgendaUsuarioPerfilControl();
					oAgendaUsuarioPerfilModel.setA05_codigo(lng_a05_codigo);
					int num_usu_per_T = Integer.parseInt(usu_per_T);
					int num_usu_per_F = Integer.parseInt(usu_per_F);
					int num_usu_per_E = Integer.parseInt(usu_per_E);
					int num_usu_per_A = Integer.parseInt(usu_per_A);
					oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(num_usu_per_T);
					oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(num_usu_per_F);
					oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(num_usu_per_E);
					oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(num_usu_per_A);
					oAgendaUsuarioPerfilModel.setA05_dt_ultima_alteracao(iau_dateToday);
					resultBD = oAgendaUsuarioPerfilControl.updatePerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
				}
			}
		}
	}
}
//----------------------------------------------------
//System.out.println("Aqui02");
%>
<script type="text/javascript">
var okMetodo = <%= okMetodo %>;
var proximoFrame = "#";
var cjInterMensagem = document.getElementById("cjInterMensagem"); 
cjInterMensagem.style.display = "";
if (okMetodo == 1) {
	//var interMensagem = document.getElementById("interMensagem"); 
	//interMensagem.innerHTML = "Participantes cadastrados com sucesso!";
	//var interBotaoOK = document.getElementById("interBotaoOK"); 
	//interBotaoOK.style.display = "";
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	proximoFrame = "../fld_view/AgendaUsuarios.jsp";
	if (statusAgenda == "1") {
		proximoFrame = "../fld_view/AgendaFatores.jsp";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interAgendaUsuariosForm").action = proximoFrame;
	document.getElementById("interAgendaUsuariosForm").submit();
} else {
	var interMensagem = document.getElementById("interMensagem"); 
	interMensagem.innerHTML = "Problemas com o Cadastro dos Participantes!";
	var interBotaoVoltar = document.getElementById("interBotaoVoltar"); 
	interBotaoVoltar.style.display = "";
}
</script>
</body>
</html>