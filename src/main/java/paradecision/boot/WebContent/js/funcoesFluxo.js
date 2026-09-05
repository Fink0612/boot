// ######## ... FUNÇÕES DE TODAS AS PÁGINAS ... ########
function atualizarPagina(nomePagina, nomeForm) {
	var proximoFrame = "../fld_view/" + nomePagina + ".jsp";
	document.getElementById(nomeForm).action = proximoFrame;
	document.getElementById(nomeForm).submit();
}

// ######## ... FUNÇÕES DE FLUXO ENTRE PÁGINAS ... ########
function abrirUsuarioEmpresas() {
	document.getElementById("ct_A01_CODIGO").value = "";
	document.getElementById("ct_A01_NOME").value = "";
	document.getElementById("ct_A03_PERFIL_PARAVIVERBEM").value = "";
	document.getElementById("ct_A03_PERFIL_ADMINISTRADOR").value = "";
	document.getElementById("ct_A03_PERFIL_CHEFE").value = "";
	document.getElementById("ct_A03_PERFIL_PADRAO").value = "";
	var proximoFrame = "../fld_view/UsuarioEmpresas.jsp";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function abrirEmpresaUsuarios() {
	var proximoFrame = "../fld_view/EmpresaUsuarios.jsp";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function abrirEmpresaDoUsuario(ff_A01_codigo, ff_A01_nome, ff_A02_codigo) {
	var proximoFrame = "../fld_view/interUsuarioEmpresas.jsp";
	document.getElementById("ct_A01_CODIGO").value = ff_A01_codigo;
	document.getElementById("ct_A01_NOME").value = ff_A01_nome;
	document.getElementById("pdAcao").value = "abrirEmpresa";
	document.getElementById("UsuarioEmpresasForm").action = proximoFrame;
	document.getElementById("UsuarioEmpresasForm").submit();
}

function abrirEmpresa_Usu_Agendas(nomeForm) {
	var proximoFrame = "../fld_view/EmpresaAgendas.jsp";
	document.getElementById(nomeForm).action = proximoFrame;
	document.getElementById(nomeForm).submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS ... ########
function cadastrarNovoUsuario() {
	var proximoFrame = "../fld_view/CadastroUsuario.jsp";
	document.getElementById("EmpresaUsuariosForm").action = proximoFrame;
	document.getElementById("EmpresaUsuariosForm").submit();
}

function fecharInterCadastroUsuario(tipoAcao) {
	var proximoFrame = "../fld_view/CadastroUsuario.jsp";
	if (tipoAcao == 1) {
		proximoFrame = "../fld_view/EmpresaUsuarios.jsp";
	} else if (tipoAcao == 2) {
		proximoFrame = "../fld_view/CadastroUsuarioEditar.jsp";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroUsuarioForm").action = proximoFrame;
	document.getElementById("interCadastroUsuarioForm").submit();
}

function editarCadastroUsuario(strCodUsuario) {
	document.getElementById("eu_A02_CODIGO").value = strCodUsuario;
	var proximoFrame = "../fld_view/CadastroUsuarioEditar.jsp";
	document.getElementById("EmpresaUsuariosForm").action = proximoFrame;
	document.getElementById("EmpresaUsuariosForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE AGENDAS ... ########
function cadastrarNovaAgenda() {
	var proximoFrame = "../fld_view/CadastroAgenda.jsp";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function fecharInterCadastroAgenda(tipoAcao) {
	var proximoFrame = "../fld_view/CadastroAgenda.jsp";
	if (tipoAcao == 1) {
		proximoFrame = "../fld_view/EmpresaAgendas.jsp";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = proximoFrame;
	document.getElementById("interCadastroAgendaForm").submit();
}

function fecharInterEditarAgenda(tipoAcao) {
	var proximoFrame = "../fld_view/AgendaEditar.jsp";
	if (tipoAcao == 1) {
		proximoFrame = "../fld_view/AgendaUsuarios.jsp";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = proximoFrame;
	document.getElementById("interCadastroAgendaForm").submit();
}

function abrirAgenda(ff_A04_codigo, ff_A04_titulo, ff_A04_status) {
	if (ff_A04_status == "") ff_A04_status == "0";
	if (ff_A04_status == "null") ff_A04_status == "0";
	document.getElementById("ct_A04_CODIGO").value = ff_A04_codigo;
	document.getElementById("ct_A04_TITULO").value = ff_A04_titulo;
	var proximoFrame = "../fld_view/AgendaUsuarios.jsp";
	if (ff_A04_status == "1") proximoFrame = "../fld_view/AgendaFatores.jsp";
	if (ff_A04_status == "2") proximoFrame = "../fld_view/AgendaFatoresPareceres.jsp";
	if (ff_A04_status == "9") proximoFrame = "../fld_view/AgendaFatoresResultados.jsp";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function editarDadosUsuariosAgenda() {
	var proximoFrame = "../fld_view/AgendaUsuariosEditar.jsp";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function editarDadosAgenda() {
	var proximoFrame = "../fld_view/CadastroAgendaEditar.jsp";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function fecharInterAgendaUsuarios(tipoAcao) {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var proximoFrame = "../fld_view/AgendaUsuariosEditar.jsp";
	if (tipoAcao == 1) {
		proximoFrame = "../fld_view/AgendaUsuarios.jsp";
		if (statusAgenda == "1") {
			proximoFrame = "../fld_view/AgendaFatores.jsp";
		}
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interAgendaUsuariosForm").action = proximoFrame;
	document.getElementById("interAgendaUsuariosForm").submit();
}

function validarAgenda(tipoAg) {
	var ret = false;
	if (tipoAg == 1) {
		var num_qtdEspec = 0;
		var cmp_qtdEspec = document.getElementById("ct_QTD_ESPECIALISTAS_AGENDA");
		var cmp_msg = document.getElementById("au_Mensagens");
		if(ehNumero(cmp_qtdEspec.value)) {
			num_qtdEspec = parseInt(cmp_qtdEspec.value);
		} else {
			num_qtdEspec = 0;
		}
		//alert("num_qtdEspec: " + num_qtdEspec);
		if (num_qtdEspec >= 3) {
			cmp_msg.style.display = "none";
			ret = true;
		} else {
			cmp_msg.style.display = "";
			cmp_msg.innerHTML = "Para encaminhar esta Agenda, deve-se ter no m&iacute;nimo 3 Especialistas!";
		}
	} else {
		ret = false;
	}
	return ret;
}

function encaminharAgenda() {
	document.getElementById("pdAcao").value = "encaminharAgenda";
	var proximoFrame = "../fld_view/interFluxoAgenda.jsp";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function liberarAgenda() {
	document.getElementById("pdAcao").value = "liberarAgenda";
	var proximoFrame = "../fld_view/interFluxoAgenda.jsp";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function encerrarAgenda() {
	document.getElementById("pdAcao").value = "encerrarAgenda";
	var proximoFrame = "../fld_view/interFluxoAgenda.jsp";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function calcularResultadosAgenda() {
	document.getElementById("pdAcao").value = "calcularResultadosAgenda";
	var proximoFrame = "../fld_view/interCalcAgenda.jsp";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function fecharInterAgendaFatores(tipoAcao) {
	var proximoFrame = "../fld_view/AgendaFatoresEditar.jsp";
	if (tipoAcao == 1) {
		proximoFrame = "../fld_view/AgendaFatores.jsp";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interAgendaUsuariosForm").action = proximoFrame;
	document.getElementById("interAgendaUsuariosForm").submit();
}

function fecharInterAgendaFluxo(tipoAcao) {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var proximoFrame = "../fld_view/EmpresaAgendas.jsp";
	if (tipoAcao = 0) {
		proximoFrame = "../fld_view/AgendaUsuarios.jsp";
		if (statusAgenda == "1") {
			proximoFrame = "../fld_view/AgendaFatores.jsp";
		} else if (statusAgenda == "2") {
			proximoFrame = "../fld_view/AgendaFatoresPareceres.jsp";
		}
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interFluxoAgendaForm").action = proximoFrame;
	document.getElementById("interFluxoAgendaForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE FATORES ... ########
function cadastrarNovoFator() {
	var proximoFrame = "../fld_view/CadastroFator.jsp";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function editarCadastroFator(strCodFator) {
	document.getElementById("ct_A06_CODIGO").value = strCodFator;
	var proximoFrame = "../fld_view/CadastroFatorEditar.jsp";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function fecharInterCadastroFator(tipoAcao) {
	var proximoFrame = "../fld_view/AgendaFatores.jsp";
	if (tipoAcao == 0) {
		proximoFrame = "../fld_view/CadastroFator.jsp";
	} else if (tipoAcao == 2) {
		proximoFrame = "../fld_view/CadastroFatorEditar.jsp";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroFatorForm").action = proximoFrame;
	document.getElementById("interCadastroFatorForm").submit();
}

function editarDadosUsuariosAgendaFatores() {
	var proximoFrame = "../fld_view/AgendaUsuariosEditar.jsp";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function abrirAgendaFatores(nomeForm) {
	var proximoFrame = "../fld_view/AgendaFatoresPareceres.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById(nomeForm).action = proximoFrame;
	document.getElementById(nomeForm).submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE PARECERES ... ########

function fecharInterPareceresFatores(tipoAcao) {
	var proximoFrame = "../fld_view/AgendaFatoresPareceres.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interPareceresFatoresForm").action = proximoFrame;
	document.getElementById("interPareceresFatoresForm").submit();
}

// ######## ... FUNÇÕES DE FINAL DE CÁLCULO DE CERTEZA / INCERTEZA ... ########

function fecharInterCalcAgenda(tipoAcao) {
	var proximoFrame = "../fld_view/AgendaFatoresResultados.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCalcAgendaForm").action = proximoFrame;
	document.getElementById("interCalcAgendaForm").submit();
}

