<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/forms.css"/>
<link rel="stylesheet" type="text/css" href="../css/print.css" media="print">
<script type="text/javascript" src="../js/funcoesFluxo.js"></script>
</head>
<body>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%
String ue_pdAcao = request.getParameter("pdAcao");
EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
EmpresaUsuarioPerfilControl oEmpresaUsuarioPerfilControl = new EmpresaUsuarioPerfilControl();
//-------------------------------------------------
int achouEmpresa = 0;
long iue_ct_A01_CODIGO = Long.parseLong(request.getParameter("ct_A01_CODIGO"));
long iue_ct_A02_CODIGO = Long.parseLong(request.getParameter("ct_A02_CODIGO"));
oEmpresaUsuarioPerfilModel.setA01_codigo(iue_ct_A01_CODIGO);
oEmpresaUsuarioPerfilModel.setA02_codigo(iue_ct_A02_CODIGO);
oEmpresaUsuarioPerfilModel = oEmpresaUsuarioPerfilControl.selectEmpresaUsuario(oEmpresaUsuarioPerfilModel);
if (oEmpresaUsuarioPerfilModel != null) {
	achouEmpresa = 1;
} else {
	oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
}
%>

<table class="tabTitForm"><tr>
<td>&nbsp;</td>
<td style="text-align:right;"><span class="stlCodForm">intUsuEmp</span></td>
</tr></table>
 
<form id="interUsuarioEmpresasForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>
</form>
<script type="text/javascript">
var achouEmpresa = "<%= achouEmpresa %>";
var proximoFrame = "../fld_view/EmpresaEmpresas.jsp";
if (achouEmpresa == "1") {
	iue_txt = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_paraviverbem() %>";
	document.getElementById("ct_A03_PERFIL_PARAVIVERBEM").value = iue_txt;
	iue_txt = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_administrador() %>";
	document.getElementById("ct_A03_PERFIL_ADMINISTRADOR").value = iue_txt;
	iue_txt = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_chefe() %>";
	document.getElementById("ct_A03_PERFIL_CHEFE").value = iue_txt;
	iue_txt = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_padrao() %>";
	document.getElementById("ct_A03_PERFIL_PADRAO").value = iue_txt;
	proximoFrame = "../fld_view/EmpresaAgendas.jsp";
}
document.getElementById("pdAcao").value = "";
document.getElementById("interUsuarioEmpresasForm").action = proximoFrame;
document.getElementById("interUsuarioEmpresasForm").submit();
</script>
</body>
</html>