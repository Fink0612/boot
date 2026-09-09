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
function atualizarPagina(nomePagina, nomeFormulario) {
	var rotaProximaTela = rotaPagina(nomePagina);
	document.getElementById(nomeFormulario).action = rotaProximaTela;
	document.getElementById(nomeFormulario).submit();
}

// ######## ... FUNÇÕES DE FLUXO ENTRE PÁGINAS ... ########
function abrirUsuarioEmpresas() {
	document.getElementById("ct_A01_CODIGO").value = "";
	document.getElementById("ct_A01_NOME").value = "";
	document.getElementById("ct_A03_PERFIL_PARAVIVERBEM").value = "";
	document.getElementById("ct_A03_PERFIL_ADMINISTRADOR").value = "";
	document.getElementById("ct_A03_PERFIL_CHEFE").value = "";
	document.getElementById("ct_A03_PERFIL_PADRAO").value = "";
	var rotaProximaTela = "../usuarios/UsuarioEmpresas";
	document.getElementById("EmpresaAgendasForm").action = rotaProximaTela;
	document.getElementById("EmpresaAgendasForm").submit();
}

function abrirEmpresaUsuarios() {
	var rotaProximaTela = "../empresas/EmpresaUsuarios";
	document.getElementById("EmpresaAgendasForm").action = rotaProximaTela;
	document.getElementById("EmpresaAgendasForm").submit();
}

function abrirEmpresaDoUsuario(codigoEmpresaSelecionado, nomeEmpresaSelecionado, codigoUsuarioSelecionado) {
	var rotaProximaTela = "../usuarios/interUsuarioEmpresas";
	document.getElementById("ct_A01_CODIGO").value = codigoEmpresaSelecionado;
	document.getElementById("ct_A01_NOME").value = nomeEmpresaSelecionado;
	document.getElementById("pdAcao").value = "abrirEmpresa";
	document.getElementById("UsuarioEmpresasForm").action = rotaProximaTela;
	document.getElementById("UsuarioEmpresasForm").submit();
}

function abrirEmpresa_Usu_Agendas(nomeFormulario) {
	var rotaProximaTela = "../empresas/EmpresaAgendas";
	document.getElementById(nomeFormulario).action = rotaProximaTela;
	document.getElementById(nomeFormulario).submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS ... ########
function cadastrarNovoUsuario() {
	var rotaProximaTela = "../usuarios/CadastroUsuario";
	document.getElementById("EmpresaUsuariosForm").action = rotaProximaTela;
	document.getElementById("EmpresaUsuariosForm").submit();
}

function fecharInterCadastroUsuario(tipoAcao) {
	var rotaProximaTela = "../usuarios/CadastroUsuario";
	if (tipoAcao == 1) {
		rotaProximaTela = "../empresas/EmpresaUsuarios";
	} else if (tipoAcao == 2) {
		rotaProximaTela = "../usuarios/CadastroUsuarioEditar";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroUsuarioForm").action = rotaProximaTela;
	document.getElementById("interCadastroUsuarioForm").submit();
}

function editarCadastroUsuario(codigoUsuarioTexto) {
	document.getElementById("eu_A02_CODIGO").value = codigoUsuarioTexto;
	var rotaProximaTela = "../usuarios/CadastroUsuarioEditar";
	document.getElementById("EmpresaUsuariosForm").action = rotaProximaTela;
	document.getElementById("EmpresaUsuariosForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE AGENDAS ... ########
function cadastrarNovaAgenda() {
	var rotaProximaTela = "../agendas/CadastroAgenda";
	document.getElementById("EmpresaAgendasForm").action = rotaProximaTela;
	document.getElementById("EmpresaAgendasForm").submit();
}

function fecharInterCadastroAgenda(tipoAcao) {
	var rotaProximaTela = "../agendas/CadastroAgenda";
	if (tipoAcao == 1) {
		rotaProximaTela = "../empresas/EmpresaAgendas";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = rotaProximaTela;
	document.getElementById("interCadastroAgendaForm").submit();
}

function fecharInterEditarAgenda(tipoAcao) {
	var rotaProximaTela = "../agendas/CadastroAgendaEditar";
	if (tipoAcao == 1) {
		rotaProximaTela = "../agendas/AgendaUsuarios";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroAgendaForm").action = rotaProximaTela;
	document.getElementById("interCadastroAgendaForm").submit();
}

function abrirAgenda(codigoAgendaSelecionado, tituloAgendaSelecionado, statusAgendaSelecionado) {
	if (statusAgendaSelecionado == "") statusAgendaSelecionado == "0";
	if (statusAgendaSelecionado == "null") statusAgendaSelecionado == "0";
	document.getElementById("ct_A04_CODIGO").value = codigoAgendaSelecionado;
	document.getElementById("ct_A04_TITULO").value = tituloAgendaSelecionado;
	var rotaProximaTela = "../agendas/AgendaUsuarios";
	if (statusAgendaSelecionado == "1") rotaProximaTela = "../agendas/AgendaFatores";
	if (statusAgendaSelecionado == "2") rotaProximaTela = "../pareceres/AgendaFatoresPareceres";
	if (statusAgendaSelecionado == "9") rotaProximaTela = "../agendas/AgendaFatoresResultados";
	document.getElementById("EmpresaAgendasForm").action = rotaProximaTela;
	document.getElementById("EmpresaAgendasForm").submit();
}

function editarDadosUsuariosAgenda() {
	var rotaProximaTela = "../agendas/AgendaUsuariosEditar";
	document.getElementById("AgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("AgendaUsuariosForm").submit();
}

function editarDadosAgenda() {
	var rotaProximaTela = "../agendas/CadastroAgendaEditar";
	document.getElementById("AgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("AgendaUsuariosForm").submit();
}

function fecharInterAgendaUsuarios(tipoAcao) {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var rotaProximaTela = "../agendas/AgendaUsuariosEditar";
	if (tipoAcao == 1) {
		rotaProximaTela = "../agendas/AgendaUsuarios";
		if (statusAgenda == "1") {
			rotaProximaTela = "../agendas/AgendaFatores";
		}
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interAgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("interAgendaUsuariosForm").submit();
}

function validarAgenda(tipoAg) {
	var resultadoOperacao = false;
	if (tipoAg == 1) {
		var quantidadeEspecialistasNumerica = 0;
		var campoQuantidadeEspecialistas = document.getElementById("ct_QTD_ESPECIALISTAS_AGENDA");
		var campoMensagem = document.getElementById("au_Mensagens");
		if(ehNumero(campoQuantidadeEspecialistas.value)) {
			quantidadeEspecialistasNumerica = parseInt(campoQuantidadeEspecialistas.value);
		} else {
			quantidadeEspecialistasNumerica = 0;
		}
		//alert("num_qtdEspec: " + num_qtdEspec);
		if (quantidadeEspecialistasNumerica >= 3) {
			campoMensagem.style.display = "none";
			resultadoOperacao = true;
		} else {
			campoMensagem.style.display = "";
			campoMensagem.innerHTML = "Para encaminhar esta Agenda, deve-se ter no m&iacute;nimo 3 Especialistas!";
		}
	} else {
		resultadoOperacao = false;
	}
	return resultadoOperacao;
}

function encaminharAgenda() {
	document.getElementById("pdAcao").value = "encaminharAgenda";
	var rotaProximaTela = "../agendas/interFluxoAgenda";
	document.getElementById("AgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("AgendaUsuariosForm").submit();
}

function liberarAgenda() {
	document.getElementById("pdAcao").value = "liberarAgenda";
	var rotaProximaTela = "../agendas/interFluxoAgenda";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}

function encerrarAgenda() {
	document.getElementById("pdAcao").value = "encerrarAgenda";
	var rotaProximaTela = "../agendas/interFluxoAgenda";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}

function calcularResultadosAgenda() {
	document.getElementById("pdAcao").value = "calcularResultadosAgenda";
	var rotaProximaTela = "../agendas/interCalcAgenda";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}

function fecharInterAgendaFatores(tipoAcao) {
	var rotaProximaTela = "../fatores/CadastroFatorEditar";
	if (tipoAcao == 1) {
		rotaProximaTela = "../agendas/AgendaFatores";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interAgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("interAgendaUsuariosForm").submit();
}

function fecharInterAgendaFluxo(tipoAcao) {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var rotaProximaTela = "../empresas/EmpresaAgendas";
	if (tipoAcao = 0) {
		rotaProximaTela = "../agendas/AgendaUsuarios";
		if (statusAgenda == "1") {
			rotaProximaTela = "../agendas/AgendaFatores";
		} else if (statusAgenda == "2") {
			rotaProximaTela = "../pareceres/AgendaFatoresPareceres";
		}
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interFluxoAgendaForm").action = rotaProximaTela;
	document.getElementById("interFluxoAgendaForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE FATORES ... ########
function cadastrarNovoFator() {
	var rotaProximaTela = "../fatores/CadastroFator";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}

function editarCadastroFator(strCodFator) {
	document.getElementById("ct_A06_CODIGO").value = strCodFator;
	var rotaProximaTela = "../fatores/CadastroFatorEditar";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}

function fecharInterCadastroFator(tipoAcao) {
	var rotaProximaTela = "../agendas/AgendaFatores";
	if (tipoAcao == 0) {
		rotaProximaTela = "../fatores/CadastroFator";
	} else if (tipoAcao == 2) {
		rotaProximaTela = "../fatores/CadastroFatorEditar";
	}
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCadastroFatorForm").action = rotaProximaTela;
	document.getElementById("interCadastroFatorForm").submit();
}

function editarDadosUsuariosAgendaFatores() {
	var rotaProximaTela = "../agendas/AgendaUsuariosEditar";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}

function abrirAgendaFatores(nomeFormulario) {
	var rotaProximaTela = "../pareceres/AgendaFatoresPareceres";
	document.getElementById("pdAcao").value = "";
	document.getElementById(nomeFormulario).action = rotaProximaTela;
	document.getElementById(nomeFormulario).submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE PARECERES ... ########

function fecharInterPareceresFatores(tipoAcao) {
	var rotaProximaTela = "../pareceres/AgendaFatoresPareceres";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interPareceresFatoresForm").action = rotaProximaTela;
	document.getElementById("interPareceresFatoresForm").submit();
}

// ######## ... FUNÇÕES DE FINAL DE CÁLCULO DE CERTEZA / INCERTEZA ... ########

function fecharInterCalcAgenda(tipoAcao) {
	var rotaProximaTela = "../agendas/AgendaFatoresResultados";
	document.getElementById("pdAcao").value = "";
	document.getElementById("interCalcAgendaForm").action = rotaProximaTela;
	document.getElementById("interCalcAgendaForm").submit();
}

