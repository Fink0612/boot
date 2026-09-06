function enviarPdLogin(tipoAcao) {
	if (tipoAcao == 1) {
		var usuarioInformado = document.getElementById("pdUsuario").value;
		var senhaInformada = document.getElementById("pdSenha").value;
		if (usuarioInformado.trim() == "" || senhaInformada.trim() == "") {
			var pdMensagens = document.getElementById("pdMensagens"); 
			pdMensagens.style.display = "";
			pdMensagens.innerHTML = "<br/>Preencher os campos de Usuario e Senha";
		} else {
			document.getElementById("pdAcao").value = "envLogin";
			document.getElementById("pdLoginForm").submit();
		}
	} else if (tipoAcao == 2) {
		var rotaProximaTela = "../compartilhado/ctrltargetpage";
		parent.document.getElementById("txt_user_session").innerHTML = parent.document.getElementById("pdNomeUsuSessao").value.toUpperCase();
		parent.document.getElementById("botao_sair").style.display = "";
		document.getElementById("pdLoginForm").action = rotaProximaTela;
		document.getElementById("pdLoginForm").submit();
	}
}

function enviarPdLoginIni(tipoAcao) {
	if (tipoAcao == 1) {
		var novaSenhaInformada = document.getElementById("pdNovaSenha").value;
		var confirmacaoNovaSenhaInformada = document.getElementById("pdConfNovaSenha").value;
		if (novaSenhaInformada.trim() == "" || confirmacaoNovaSenhaInformada.trim() == "") {
			var pdMensagens = document.getElementById("pdMensagens"); 
			pdMensagens.style.display = "";
			pdMensagens.innerHTML = "<br/>Preencher os campos de 'Nova Senha' e 'Confirmação da Nova Senha'";
		} else if (novaSenhaInformada.trim() != confirmacaoNovaSenhaInformada.trim()) {
			var pdMensagens = document.getElementById("pdMensagens"); 
			pdMensagens.style.display = "";
			pdMensagens.innerHTML = "<br/>Os dois campos acima devem ter valores exatamente iguais";
		} else {
			document.getElementById("pdAcao").value = "envLoginIni";
			document.getElementById("pdLoginForm").submit();
		}
	} else if (tipoAcao == 2) {
		var rotaProximaTela = "../compartilhado/ctrltargetpage";
		parent.document.getElementById("botao_sair").style.display = "";
		document.getElementById("pdLoginForm").action = rotaProximaTela;
		document.getElementById("pdLoginForm").submit();
	}
}

function submeterForm(tipoFormulario, eventoTeclado) {
	if (eventoTeclado.keyCode==13) {
		if (tipoFormulario==1) {
			enviarPdLogin(1);
		} else if (tipoFormulario==2) {
			enviarPdLoginIni(1);
		}
	}
}

function editarLinhas() {
	var elementos = document.getElementsByClassName("lin_text");
	quantidadeElementos = elementos.length;
	for (var indiceElemento = 0; indiceElemento < quantidadeElementos; indiceElemento++) {
		var elemento = elementos[indiceElemento];
		elemento.style.display = "none";
	}
	elementos = document.getElementsByClassName("lin_edit");
	quantidadeElementos = elementos.length;
	for (var indiceElemento = 0; indiceElemento < quantidadeElementos; indiceElemento++) {
		var elemento = elementos[indiceElemento];
		elemento.style.display = "";
	}
}

function salvarLinhas() {
	var elementos = document.getElementsByClassName("lin_text");
	quantidadeElementos = elementos.length;
	for (var indiceElemento = 0; indiceElemento < quantidadeElementos; indiceElemento++) {
		var elemento = elementos[indiceElemento];
		elemento.style.display = "";
	}
	elementos = document.getElementsByClassName("lin_edit");
	quantidadeElementos = elementos.length;
	for (var indiceElemento = 0; indiceElemento < quantidadeElementos; indiceElemento++) {
		var elemento = elementos[indiceElemento];
		elemento.style.display = "none";
	}
}

function cancelarEdicao() {
	var elementos = document.getElementsByClassName("lin_text");
	quantidadeElementos = elementos.length;
	for (var indiceElemento = 0; indiceElemento < quantidadeElementos; indiceElemento++) {
		var elemento = elementos[indiceElemento];
		elemento.style.display = "";
	}
	elementos = document.getElementsByClassName("lin_edit");
	quantidadeElementos = elementos.length;
	for (var indiceElemento = 0; indiceElemento < quantidadeElementos; indiceElemento++) {
		var elemento = elementos[indiceElemento];
		elemento.style.display = "none";
	}
}

function verResultados() {
	document.getElementById("form01").submit();
}
