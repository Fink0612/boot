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
<span class="stlTitForm">CADASTRO DE USUÁRIO</span>
</td>
<td style="text-align:right;"><span class="stlCodForm">CadUsuEdit</span></td>
</tr></table>

<p><b>EMPRESA: </b><span id="u_A01_NOME"></span></p>
<form id="CadastroUsuarioForm" action="#" method="post" autocomplete="off">
<span id="u_Mensagens" style="display:none; color:red;"></span>
<input type="hidden" name="u_A02_CODIGO" id="u_A02_CODIGO" value="" /><br/>
<table><tr>
<td><label for="u_a02_nome">Nome: </label>
</td><td><input type="text" name="u_a02_nome" id="u_a02_nome" value="" size="40" /><br/>
</td></tr><tr>
<td><label for="u_a02_email">E-Mail: </label>
</td><td><input type="email" name="u_a02_email" id="u_a02_email" value="" size="40" /><br/>
</td></tr><tr>
<td><label for="u_a02_usuario">Usuário: </label>
</td><td><input type="text" name="u_a02_usuario" id="u_a02_usuario" value="" size="20" /><br/>
</td></tr><tr>
<td><label for="u_a02_senha">Senha: </label>
</td><td><input type="password" name="u_a02_senha" id="u_a02_senha" value="" size="20" /><br/>
</td></tr>
</table>
<input type="checkbox" checked="checked" name="u_a02_status" id="u_a02_status" value="1" />
<label for="u_a02_status"> Status Ativo</label><br/>
<span>------------------------------------------------</span><br/>
<label for="perfis">Perfil do Usuário: </label><br/>
<input type="checkbox" name="eup_a03_perfil_chefe" id="eup_a03_perfil_chefe" value="1" />
<label for="eup_a03_perfil_chefe"> Chefe</label><br/>
<input type="checkbox" name="eup_a03_perfil_padrao" id="eup_a03_perfil_padrao" value="1" />
<label for="eup_a03_perfil_padrao"> Padrão</label><br/>
<span>------------------------------------------------</span><br/>
<input type="button" id="salvar" value="Salvar e Sair" onclick="salvarCadastroUsuario(2)" />
<input type="button" id="cancelar" value="Cancelar e Sair" onclick="cancelarCadastroUsuario(2)" />
<br/>
<%@ include file="../fld_control/camposControle.jsp" %>
</form>
<%@ page import="Pck_Control.*" %>
<%@ page import="Pck_Model.*" %>
<%@ page import="Pck_Util.*" %>
<%
String okRetorno = "NOK";
String u_A02_CODIGO = request.getParameter("eu_A02_CODIGO");
long u_num_A02_CODIGO = MetodosUteis.retornaLong(u_A02_CODIGO);
String u_A01_CODIGO = request.getParameter("ct_A01_CODIGO");
long u_num_A01_CODIGO = MetodosUteis.retornaLong(u_A01_CODIGO);
UsuarioModel oUsuarioModel = new UsuarioModel();
UsuarioControl oUsuarioControl = new UsuarioControl();
EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
EmpresaUsuarioPerfilControl oEmpresaUsuarioPerfilControl = new EmpresaUsuarioPerfilControl();
if (u_num_A02_CODIGO > 0) {
	oUsuarioModel.setA02_codigo(u_num_A02_CODIGO);
	oUsuarioModel = oUsuarioControl.selectUserByCode(oUsuarioModel);
	if (oUsuarioModel.getA02_nome() == null) oUsuarioModel.setA02_nome("");
	if (oUsuarioModel.getA02_nome().length() > 0) {
		oEmpresaUsuarioPerfilModel.setA02_codigo(u_num_A02_CODIGO);
		oEmpresaUsuarioPerfilModel.setA01_codigo(u_num_A01_CODIGO);
		oEmpresaUsuarioPerfilModel = oEmpresaUsuarioPerfilControl.selectEmpresaUsuario(oEmpresaUsuarioPerfilModel);
		if (oEmpresaUsuarioPerfilControl != null) {
			okRetorno = "OK";
		}
	}
}
%>
<script type="text/javascript">
var okRetorno = "<%= okRetorno %>"
if (okRetorno == "OK") {
	document.getElementById("u_A02_CODIGO").value = "<%= u_A02_CODIGO %>";
	document.getElementById("u_a02_nome").value = "<%= oUsuarioModel.getA02_nome() %>";
	document.getElementById("u_a02_usuario").value = "<%= oUsuarioModel.getA02_usuario() %>";
	document.getElementById("u_a02_senha").value = "<%= oUsuarioModel.getA02_senha() %>";
	document.getElementById("u_a02_email").value = "<%= oUsuarioModel.getA02_email() %>";
	var u_A02_STATUS = "<%= oUsuarioModel.getA02_status() %>";
	if (u_A02_STATUS == "1") {
		document.getElementById("u_a02_status").checked = true;
	} else {
		document.getElementById("u_a02_status").checked = false;
	}
	//------------------------------------------
	var u_A03_PERFIL_CHEFE = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_chefe() %>";
	if (u_A03_PERFIL_CHEFE == "1") {
		document.getElementById("eup_a03_perfil_chefe").checked = true;
	} else {
		document.getElementById("eup_a03_perfil_chefe").checked = false;
	}
	var u_A03_PERFIL_PADRAO = "<%= oEmpresaUsuarioPerfilModel.getA03_perfil_padrao() %>";
	if (u_A03_PERFIL_PADRAO == "1") {
		document.getElementById("eup_a03_perfil_padrao").checked = true;
	} else {
		document.getElementById("eup_a03_perfil_padrao").checked = false;
	}
}
//----------------------------------------------------
var u_A01_NOME = document.getElementById("u_A01_NOME");
u_A01_NOME.innerHTML = document.getElementById("ct_A01_NOME").value;
</script>
</body>
</html>