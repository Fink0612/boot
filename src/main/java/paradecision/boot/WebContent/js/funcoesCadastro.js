// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS ... ########
function salvarCadastroUsuario(tipoForm) {
	var validacaoOk = 1;
	//----------------------------------------
	var u_a02_nome = document.getElementById("u_a02_nome").value;
	var u_a02_email = document.getElementById("u_a02_email").value;
	var u_a02_usuario = document.getElementById("u_a02_usuario").value;
	var u_a02_senha = document.getElementById("u_a02_senha").value;
	//----------------------------------------
	var u_Mensagens = document.getElementById("u_Mensagens"); 
	if(u_a02_nome.trim() == "") {
		validacaoOk = 0;
		u_Mensagens.style.display = "";
		u_Mensagens.innerHTML = "Preencher o campo de Nome do Usu&aacute;rio<br/>";
	} else if(u_a02_email.trim() == "") {
		validacaoOk = 0;
		u_Mensagens.style.display = "";
		u_Mensagens.innerHTML = "Preencher o campo de E-Mail do Usu&aacute;rio<br/>";
	} else {
		u_Mensagens.style.display = "none";
		u_Mensagens.innerHTML = "";
	}
	//----------------------------------------
	if (validacaoOk == 1) {
		var proximoFrame = "../fld_view/interCadastroUsuario.jsp";
		var pdAcao = "cadastrarUsuario";
		if (tipoForm == 2) {
			proximoFrame = "../fld_view/interCadastroUsuarioEditar.jsp";
			pdAcao = "editarFator";
		}
		document.getElementById("pdAcao").value = pdAcao;
		document.getElementById("CadastroUsuarioForm").action = proximoFrame;
		document.getElementById("CadastroUsuarioForm").submit();
	}
}

function cancelarCadastroUsuario() {
	var proximoFrame = "../fld_view/EmpresaUsuarios.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("CadastroUsuarioForm").action = proximoFrame;
	document.getElementById("CadastroUsuarioForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE AGENDAS ... ########
function ctrlVisualDataLimite() {
	var a_a04_status_dt_limite = document.getElementById("a_a04_status_dt_limite").checked;
	var a_cmpDataLimite = document.getElementById("a_cmpDataLimite");
	var a_a04_data_limite = document.getElementById("a_a04_data_limite");
	if (a_a04_status_dt_limite) {
		a_cmpDataLimite.style.display = "inline";
	} else {
		a_cmpDataLimite.style.display = "none";
		a_a04_data_limite.value = "";
	}
}

function salvarCadastroAgenda(tipoAteracao) {
	var validacaoOk = 1;
	//----------------------------------------
	var a_a04_titulo = document.getElementById("a_a04_titulo").value;
	var a_a04_descricao = document.getElementById("a_a04_descricao").value;
	var a_a04_status_dt_limite = document.getElementById("a_a04_status_dt_limite").checked;
	var a_a04_data_limite = document.getElementById("a_a04_data_limite").value;
	//----------------------------------------
	var a_Mensagens = document.getElementById("a_Mensagens"); 
	if(a_a04_titulo.trim() == "") {
		validacaoOk = 0;
		a_Mensagens.style.display = "";
		a_Mensagens.innerHTML = "Preencher o campo de T&iacute;tulo da Agenda<br/>";
	} else if(a_a04_descricao.trim() == "") {
		validacaoOk = 0;
		a_Mensagens.style.display = "";
		a_Mensagens.innerHTML = "Preencher o campo de Descri&ccedil;&atilde;o da Agenda<br/>";
	} else if(a_a04_status_dt_limite && a_a04_data_limite.trim() == "") {
		validacaoOk = 0;
		a_Mensagens.style.display = "";
		a_Mensagens.innerHTML = "Preencher o campo de Data Limite da Agenda<br/>";
	} else {
		a_Mensagens.style.display = "none";
		a_Mensagens.innerHTML = "";
	}
	//----------------------------------------
	if (validacaoOk == 1) {
		var proximoFrame = "../fld_view/interCadastroAgenda.jsp";
		if (tipoAteracao == 2) {
			proximoFrame = "../fld_view/interCadastroAgendaEditar.jsp";
		}
		document.getElementById("pdAcao").value = "cadastrarAgenda";
		document.getElementById("CadastroAgendaForm").action = proximoFrame;
		document.getElementById("CadastroAgendaForm").submit();
	}
}

function cancelarCadastroAgenda(tipoAteracao) {
	var proximoFrame = "../fld_view/EmpresaAgendas.jsp";
	if (tipoAteracao == 2) {
			proximoFrame = "../fld_view/AgendaUsuarios.jsp";
		}
	document.getElementById("pdAcao").value = "";
	document.getElementById("CadastroAgendaForm").action = proximoFrame;
	document.getElementById("CadastroAgendaForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE USUÁRIOS DA AGENDA ... ########
function cancelarUsuariosAgenda() {
	var statusAgenda = document.getElementById("ct_A04_STATUS").value;
	var proximoFrame = "../fld_view/AgendaUsuarios.jsp";
	if (statusAgenda == "1") {
		proximoFrame = "../fld_view/AgendaFatores.jsp";
	}
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

function salvarUsuariosAgenda() {
	var proximoFrame = "../fld_view/interAgendaUsuarios.jsp";
	document.getElementById("pdAcao").value = "atualizarParticipantes";
	document.getElementById("AgendaUsuariosForm").action = proximoFrame;
	document.getElementById("AgendaUsuariosForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE FATORES ... ########
function salvarCadastroFator(tipoForm) {
	var validacaoOk = 1;
	//----------------------------------------
	var f_a06_titulo = document.getElementById("f_a06_titulo").value;
	var f_a06_descricao = document.getElementById("f_a06_descricao").value;
	//----------------------------------------
	var f_Mensagens = document.getElementById("f_Mensagens"); 
	if(f_a06_titulo.trim() == "") {
		validacaoOk = 0;
		f_Mensagens.style.display = "";
		f_Mensagens.innerHTML = "Preencher o campo de T&iacute;tulo do Fator<br/>";
	} else if(f_a06_descricao.trim() == "") {
		validacaoOk = 0;
		f_Mensagens.style.display = "";
		f_Mensagens.innerHTML = "Preencher o campo de Descri&ccedil;&atilde;o do Fator<br/>";
	} else {
		f_Mensagens.style.display = "none";
		f_Mensagens.innerHTML = "";
	}
	//----------------------------------------
	if (validacaoOk == 1) {
		var proximoFrame = "../fld_view/interCadastroFator.jsp";
		var pdAcao = "cadastrarFator";
		if (tipoForm == 2) {
			proximoFrame = "../fld_view/interCadastroFatorEditar.jsp";
			pdAcao = "editarFator";
		}
		document.getElementById("pdAcao").value = pdAcao;
		document.getElementById("CadastroFatorForm").action = proximoFrame;
		document.getElementById("CadastroFatorForm").submit();
	}
}

function cancelarCadastroFator() {
	var proximoFrame = "../fld_view/AgendaFatores.jsp";
	document.getElementById("pdAcao").value = "";
	document.getElementById("CadastroFatorForm").action = proximoFrame;
	document.getElementById("CadastroFatorForm").submit();
}

// ######## ... FUNÇÕES DE CADASTRO DE PARECERES ... ########
function salvarPareceresFatores() {
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	var proximoFrame = "../fld_view/interPareceresFatores.jsp";
	document.getElementById("pdAcao").value = "salvarPareceresFatores";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}
