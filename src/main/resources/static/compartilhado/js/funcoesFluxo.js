// Rotas Spring MVC, agrupadas por módulo.
const ROTAS_PAGINAS = {
  "index": "../index",
  "testebd": "../diagnostico/testebd",
  "testegeral": "../diagnostico/testegeral",
  "testeip": "../diagnostico/testeip",
  "ctrltargetpage": "../compartilhado/ctrltargetpage",
  "login": "../autenticacao/login",
  "loginIni": "../autenticacao/loginIni",
  "AgendaFatores": "../agendas/AgendaFatores",
  "AgendaFatoresPareceres": "../pareceres/AgendaFatoresPareceres",
  "AgendaFatoresResultados": "../agendas/AgendaFatoresResultados",
  "AgendaUsuarios": "../agendas/AgendaUsuarios",
  "AgendaUsuariosEditar": "../agendas/AgendaUsuariosEditar",
  "AgendaUsuariosPareceresPendencia": "../agendas/AgendaUsuariosPareceresPendencia",
  "CadastroAgenda": "../agendas/CadastroAgenda",
  "CadastroAgendaEditar": "../agendas/CadastroAgendaEditar",
  "CadastroFator": "../fatores/CadastroFator",
  "CadastroFatorEditar": "../fatores/CadastroFatorEditar",
  "CadastroUsuario": "../usuarios/CadastroUsuario",
  "CadastroUsuarioEditar": "../usuarios/CadastroUsuarioEditar",
  "EmpresaAgendas": "../empresas/EmpresaAgendas",
  "EmpresaUsuarios": "../empresas/EmpresaUsuarios",
  "interAgendaUsuarios": "../agendas/interAgendaUsuarios",
  "interCadastroAgenda": "../agendas/interCadastroAgenda",
  "interCadastroAgendaEditar": "../agendas/interCadastroAgendaEditar",
  "interCadastroFator": "../fatores/interCadastroFator",
  "interCadastroFatorEditar": "../fatores/interCadastroFatorEditar",
  "interCadastroUsuario": "../usuarios/interCadastroUsuario",
  "interCadastroUsuarioEditar": "../usuarios/interCadastroUsuarioEditar",
  "interCalcAgenda": "../agendas/interCalcAgenda",
  "interFluxoAgenda": "../agendas/interFluxoAgenda",
  "interPareceresFatores": "../pareceres/interPareceresFatores",
  "interUsuarioEmpresas": "../usuarios/interUsuarioEmpresas",
  "UsuarioEmpresas": "../usuarios/UsuarioEmpresas",
  "AgendaEditar": "../agendas/CadastroAgendaEditar",
  "AgendaFatoresEditar": "../fatores/CadastroFatorEditar",
  "EmpresaEmpresas": "../usuarios/UsuarioEmpresas"
};
function rotaPagina(nome) {
    if (!ROTAS_PAGINAS[nome]) throw new Error("Página desconhecida: " + nome);
    return ROTAS_PAGINAS[nome];
}
// ######## ... FUNÇÕES DE TODAS AS PÁGINAS ... ########
function atualizarPagina(nomePagina, nomeForm) {
	var proximoFrame = rotaPagina(nomePagina);
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
	var proximoFrame = "../usuarios/UsuarioEmpresas";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function abrirEmpresaUsuarios() {
	var proximoFrame = "../empresas/EmpresaUsuarios";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function abrirEmpresaDoUsuario(ff_A01_codigo, ff_A01_nome, ff_A02_codigo) {
	var proximoFrame = "../usuarios/interUsuarioEmpresas";
	document.getElementById("ct_A01_CODIGO").value = ff_A01_codigo;
	document.getElementById("ct_A01_NOME").value = ff_A01_nome;
	document.getElementById("pdAcao").value = "abrirEmpresa";
	document.getElementById("UsuarioEmpresasForm").action = proximoFrame;
	document.getElementById("UsuarioEmpresasForm").submit();
}

function abrirEmpresa_Usu_Agendas(nomeForm) {
	var proximoFrame = "../empresas/EmpresaAgendas";
	document.getElementById(nomeForm).action = proximoFrame;
	document.getElementById(nomeForm).submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS ... ########
function cadastrarNovoUsuario() {
	var proximoFrame = "../usuarios/CadastroUsuario";
	document.getElementById("EmpresaUsuariosForm").action = proximoFrame;
	document.getElementById("EmpresaUsuariosForm").submit();
}

function fecharInterCadastroUsuario(tipoAcao) {
	var proximoFrame = "../usuarios/CadastroUsuario";
	if (tipoAcao == 1) {
		proximoFrame = "../empresas/EmpresaUsuarios";
	} else if (tipoAcao == 2) {
		proximoFrame = "../usuarios/CadastroUsuarioEditar";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroUsuarioForm").action = proximoFrame;
	document.getElementById("interCadastroUsuarioForm").submit();
}

function editarCadastroUsuario(strCodUsuario) {
	document.getElementById("eu_A02_CODIGO").value = strCodUsuario;
	var proximoFrame = "../usuarios/CadastroUsuarioEditar";
	document.getElementById("EmpresaUsuariosForm").action = proximoFrame;
	document.getElementById("EmpresaUsuariosForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE AGENDAS ... ########
function cadastrarNovaAgenda() {
	var proximoFrame = "../agendas/CadastroAgenda";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function fecharInterCadastroAgenda(tipoAcao) {
	var proximoFrame = "../agendas/CadastroAgenda";
	if (tipoAcao == 1) {
		proximoFrame = "../empresas/EmpresaAgendas";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = proximoFrame;
	document.getElementById("interCadastroAgendaForm").submit();
}

function fecharInterEditarAgenda(tipoAcao) {
	var proximoFrame = "../agendas/CadastroAgendaEditar";
	if (tipoAcao == 1) {
		proximoFrame = "../agendas/AgendaUsuarios";
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
	var proximoFrame = "../agendas/AgendaUsuarios";
	if (ff_A04_status == "1") proximoFrame = "../agendas/AgendaFatores";
	if (ff_A04_status == "2") proximoFrame = "../pareceres/AgendaFatoresPareceres";
	if (ff_A04_status == "9") proximoFrame = "../agendas/AgendaFatoresResultados";
	document.getElementById("EmpresaAgendasForm").action = proximoFrame;
	document.getElementById("EmpresaAgendasForm").submit();
}

function editarDadosUsuariosAgenda() {
	var proximoFrame = "../agendas/AgendaUsuariosEditar";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function editarDadosAgenda() {
	var proximoFrame = "../agendas/CadastroAgendaEditar";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function fecharInterAgendaUsuarios(tipoAcao) {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var proximoFrame = "../agendas/AgendaUsuariosEditar";
	if (tipoAcao == 1) {
		proximoFrame = "../agendas/AgendaUsuarios";
		if (statusAgenda == "1") {
			proximoFrame = "../agendas/AgendaFatores";
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
	var proximoFrame = "../agendas/interFluxoAgenda";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function liberarAgenda() {
	document.getElementById("pdAcao").value = "liberarAgenda";
	var proximoFrame = "../agendas/interFluxoAgenda";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function encerrarAgenda() {
	document.getElementById("pdAcao").value = "encerrarAgenda";
	var proximoFrame = "../agendas/interFluxoAgenda";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function calcularResultadosAgenda() {
	document.getElementById("pdAcao").value = "calcularResultadosAgenda";
	var proximoFrame = "../agendas/interCalcAgenda";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function fecharInterAgendaFatores(tipoAcao) {
	var proximoFrame = "../fatores/CadastroFatorEditar";
	if (tipoAcao == 1) {
		proximoFrame = "../agendas/AgendaFatores";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interAgendaUsuariosForm").action = proximoFrame;
	document.getElementById("interAgendaUsuariosForm").submit();
}

function fecharInterAgendaFluxo(tipoAcao) {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var proximoFrame = "../empresas/EmpresaAgendas";
	if (tipoAcao = 0) {
		proximoFrame = "../agendas/AgendaUsuarios";
		if (statusAgenda == "1") {
			proximoFrame = "../agendas/AgendaFatores";
		} else if (statusAgenda == "2") {
			proximoFrame = "../pareceres/AgendaFatoresPareceres";
		}
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interFluxoAgendaForm").action = proximoFrame;
	document.getElementById("interFluxoAgendaForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE FATORES ... ########
function cadastrarNovoFator() {
	var proximoFrame = "../fatores/CadastroFator";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function editarCadastroFator(strCodFator) {
	document.getElementById("ct_A06_CODIGO").value = strCodFator;
	var proximoFrame = "../fatores/CadastroFatorEditar";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function fecharInterCadastroFator(tipoAcao) {
	var proximoFrame = "../agendas/AgendaFatores";
	if (tipoAcao == 0) {
		proximoFrame = "../fatores/CadastroFator";
	} else if (tipoAcao == 2) {
		proximoFrame = "../fatores/CadastroFatorEditar";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroFatorForm").action = proximoFrame;
	document.getElementById("interCadastroFatorForm").submit();
}

function editarDadosUsuariosAgendaFatores() {
	var proximoFrame = "../agendas/AgendaUsuariosEditar";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}

function abrirAgendaFatores(nomeForm) {
	var proximoFrame = "../pareceres/AgendaFatoresPareceres";
	document.getElementById("pdAcao").value = "";
	document.getElementById(nomeForm).action = proximoFrame;
	document.getElementById(nomeForm).submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE PARECERES ... ########

function fecharInterPareceresFatores(tipoAcao) {
	var proximoFrame = "../pareceres/AgendaFatoresPareceres";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interPareceresFatoresForm").action = proximoFrame;
	document.getElementById("interPareceresFatoresForm").submit();
}

// ######## ... FUNÇÕES DE FINAL DE CÁLCULO DE CERTEZA / INCERTEZA ... ########

function fecharInterCalcAgenda(tipoAcao) {
	var proximoFrame = "../agendas/AgendaFatoresResultados";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCalcAgendaForm").action = proximoFrame;
	document.getElementById("interCalcAgendaForm").submit();
}

