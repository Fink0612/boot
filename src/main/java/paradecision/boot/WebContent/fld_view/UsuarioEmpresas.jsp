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
UsuarioModel oUsuarioModel = new UsuarioModel();
EmpresaModel oEmpresaModel = new EmpresaModel();
EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
//-------------------------------------------------
UsuarioEmpresasModel oUsuarioEmpresasModel = new UsuarioEmpresasModel();
UsuarioEmpresasControl oUsuarioEmpresasControl = new UsuarioEmpresasControl();
//-------------------------------------------------
int achouEmpresa = 0;
long ue_ct_A02_CODIGO = Long.parseLong(request.getParameter("ct_A02_CODIGO"));
oUsuarioModel.setA02_codigo(ue_ct_A02_CODIGO);
oUsuarioEmpresasModel.setoUsuarioModel(oUsuarioModel);
oUsuarioEmpresasModel = oUsuarioEmpresasControl.selectEmpresasDoUsuario(oUsuarioEmpresasModel);
if (oUsuarioEmpresasModel.getArrEmpresaModel().size() > 0) {
	achouEmpresa = 1;
}
%>
<form id="UsuarioEmpresasForm" action="#" method="post">
<%@ include file="../fld_control/camposControle.jsp" %>

<table class="tabTitForm"><tr>
<td><span class="stlTitForm">LISTA DE EMPRESAS:</span></td>
<td style="text-align:right;"><span class="stlCodForm">UsuEmp</span></td>
</tr></table>

<%
if (achouEmpresa == 1) {
	ArrayList<EmpresaModel> arrEmpresaModel = new ArrayList<EmpresaModel>();
	arrEmpresaModel = oUsuarioEmpresasModel.getArrEmpresaModel();
	for (int i = 0; i < arrEmpresaModel.size(); i++) {
		oEmpresaModel = oUsuarioEmpresasModel.getArrEmpresaModel().get(i);
%>
<a onclick="abrirEmpresaDoUsuario('<%= oEmpresaModel.getA01_codigo() %>', '<%= oEmpresaModel.getA01_nome() %>', '<%= ue_ct_A02_CODIGO %>')" href="javascript:void(0);">
<%= oEmpresaModel.getA01_nome() %>
</a><br/>
<%
	}
}
%>
</form>
</body>
</html>