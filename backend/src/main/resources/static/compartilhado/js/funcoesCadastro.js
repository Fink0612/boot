// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS ... ########
function salvarCadastroUsuario(tipoFormulario) {
	var validacaoOk = 1;
	//----------------------------------------
	var nomeUsuarioFormularioUsuario = document.getElementById("u_a02_nome").value;
	var emailUsuarioFormularioUsuario = document.getElementById("u_a02_email").value;
	var usuarioUsuarioFormularioUsuario = document.getElementById("u_a02_usuario").value;
	var senhaUsuarioFormularioUsuario = document.getElementById("u_a02_senha").value;
	//----------------------------------------
	var mensagensFormularioUsuario = document.getElementById("u_Mensagens"); 
	if(nomeUsuarioFormularioUsuario.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioUsuario.style.display = "";
		mensagensFormularioUsuario.innerHTML = "Preencher o campo de Nome do Usu&aacute;rio<br/>";
	} else if(emailUsuarioFormularioUsuario.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioUsuario.style.display = "";
		mensagensFormularioUsuario.innerHTML = "Preencher o campo de E-Mail do Usu&aacute;rio<br/>";
	} else {
		mensagensFormularioUsuario.style.display = "none";
		mensagensFormularioUsuario.innerHTML = "";
	}
	//----------------------------------------
	if (validacaoOk == 1) {
		var rotaProximaTela = "../usuarios/interCadastroUsuario";
		var pdAcao = "cadastrarUsuario";
		if (tipoFormulario == 2) {
			rotaProximaTela = "../usuarios/interCadastroUsuarioEditar";
			pdAcao = "editarFator";
		}
		document.getElementById("pdAcao").value = pdAcao;
		document.getElementById("CadastroUsuarioForm").action = rotaProximaTela;
		document.getElementById("CadastroUsuarioForm").submit();
	}
}

function cancelarCadastroUsuario() {
	var rotaProximaTela = "../empresas/EmpresaUsuarios";
	document.getElementById("pdAcao").value = "";
	document.getElementById("CadastroUsuarioForm").action = rotaProximaTela;
	document.getElementById("CadastroUsuarioForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE AGENDAS ... ########
function ctrlVisualDataLimite() {
	var statusDataLimiteAgendaFormularioAgenda = document.getElementById("a_a04_status_dt_limite").checked;
	var containerDataLimite = document.getElementById("a_cmpDataLimite");
	var dataLimiteAgendaFormularioAgenda = document.getElementById("a_a04_data_limite");
	if (statusDataLimiteAgendaFormularioAgenda) {
		containerDataLimite.style.display = "inline";
	} else {
		containerDataLimite.style.display = "none";
		dataLimiteAgendaFormularioAgenda.value = "";
	}
}

function salvarCadastroAgenda(tipoAlteracao) {
	var validacaoOk = 1;
	//----------------------------------------
	var tituloAgendaFormularioAgenda = document.getElementById("a_a04_titulo").value;
	var descricaoAgendaFormularioAgenda = document.getElementById("a_a04_descricao").value;
	var statusDataLimiteAgendaFormularioAgenda = document.getElementById("a_a04_status_dt_limite").checked;
	var dataLimiteAgendaFormularioAgenda = document.getElementById("a_a04_data_limite").value;
	//----------------------------------------
	var mensagensFormularioAgenda = document.getElementById("a_Mensagens"); 
	if(tituloAgendaFormularioAgenda.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioAgenda.style.display = "";
		mensagensFormularioAgenda.innerHTML = "Preencher o campo de T&iacute;tulo da Agenda<br/>";
	} else if(descricaoAgendaFormularioAgenda.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioAgenda.style.display = "";
		mensagensFormularioAgenda.innerHTML = "Preencher o campo de Descri&ccedil;&atilde;o da Agenda<br/>";
	} else if(statusDataLimiteAgendaFormularioAgenda && dataLimiteAgendaFormularioAgenda.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioAgenda.style.display = "";
		mensagensFormularioAgenda.innerHTML = "Preencher o campo de Data Limite da Agenda<br/>";
	} else {
		mensagensFormularioAgenda.style.display = "none";
		mensagensFormularioAgenda.innerHTML = "";
	}
	//----------------------------------------
	if (validacaoOk == 1) {
		var rotaProximaTela = "../agendas/interCadastroAgenda";
		if (tipoAlteracao == 2) {
			rotaProximaTela = "../agendas/interCadastroAgendaEditar";
		}
		document.getElementById("pdAcao").value = "cadastrarAgenda";
		document.getElementById("CadastroAgendaForm").action = rotaProximaTela;
		document.getElementById("CadastroAgendaForm").submit();
	}
}

function cancelarCadastroAgenda(tipoAlteracao) {
	var rotaProximaTela = "../empresas/EmpresaAgendas";
	if (tipoAlteracao == 2) {
			rotaProximaTela = "../agendas/AgendaUsuarios";
		}
	document.getElementById("pdAcao").value = "";
	document.getElementById("CadastroAgendaForm").action = rotaProximaTela;
	document.getElementById("CadastroAgendaForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS DA AGENDA ... ########
function cancelarUsuariosAgenda() {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var rotaProximaTela = "../agendas/AgendaUsuarios";
	if (statusAgenda == "1") {
		rotaProximaTela = "../agendas/AgendaFatores";
	}
	document.getElementById("AgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("AgendaUsuariosForm").submit();
}

function salvarUsuariosAgenda() {
	var rotaProximaTela = "../agendas/interAgendaUsuarios";
	document.getElementById("pdAcao").value = "atualizarParticipantes";
	document.getElementById("AgendaUsuariosForm").action = rotaProximaTela;
	document.getElementById("AgendaUsuariosForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE FATORES ... ########
function salvarCadastroFator(tipoFormulario) {
	var validacaoOk = 1;
	//----------------------------------------
	var tituloFatorFormularioFator = document.getElementById("f_a06_titulo").value;
	var descricaoFatorFormularioFator = document.getElementById("f_a06_descricao").value;
	//----------------------------------------
	var mensagensFormularioFator = document.getElementById("f_Mensagens"); 
	if(tituloFatorFormularioFator.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioFator.style.display = "";
		mensagensFormularioFator.innerHTML = "Preencher o campo de T&iacute;tulo do Fator<br/>";
	} else if(descricaoFatorFormularioFator.trim() == "") {
		validacaoOk = 0;
		mensagensFormularioFator.style.display = "";
		mensagensFormularioFator.innerHTML = "Preencher o campo de Descri&ccedil;&atilde;o do Fator<br/>";
	} else {
		mensagensFormularioFator.style.display = "none";
		mensagensFormularioFator.innerHTML = "";
	}
	//----------------------------------------
	if (validacaoOk == 1) {
		var rotaProximaTela = "../fatores/interCadastroFator";
		var pdAcao = "cadastrarFator";
		if (tipoFormulario == 2) {
			rotaProximaTela = "../fatores/interCadastroFatorEditar";
			pdAcao = "editarFator";
		}
		document.getElementById("pdAcao").value = pdAcao;
		document.getElementById("CadastroFatorForm").action = rotaProximaTela;
		document.getElementById("CadastroFatorForm").submit();
	}
}

function cancelarCadastroFator() {
	var rotaProximaTela = "../agendas/AgendaFatores";
	document.getElementById("pdAcao").value = "";
	document.getElementById("CadastroFatorForm").action = rotaProximaTela;
	document.getElementById("CadastroFatorForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE PARECERES ... ########
function salvarPareceresFatores() {
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	var rotaProximaTela = "../pareceres/interPareceresFatores";
	document.getElementById("pdAcao").value = "salvarPareceresFatores";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}
