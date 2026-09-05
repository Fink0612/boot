function enviarPdLogin(valTipo) {
	if (valTipo == 1) {
		var txtUsu = document.getElementById("pdUsuario").value;
		var txtSen = document.getElementById("pdSenha").value;
		if (txtUsu.trim() == "" || txtSen.trim() == "") {
			var pdMensagens = document.getElementById("pdMensagens"); 
			pdMensagens.style.display = "";
			pdMensagens.innerHTML = "<br/>Preencher os campos de Usuario e Senha";
		} else {
			document.getElementById("pdAcao").value = "envLogin";
			document.getElementById("pdLoginForm").submit();
		}
	} else if (valTipo == 2) {
		var proximoFrame = "../fld_control/ctrltargetpage.jsp";
		parent.document.getElementById("txt_user_session").innerHTML = parent.document.getElementById("pdNomeUsuSessao").value.toUpperCase();
		parent.document.getElementById("botao_sair").style.display = "";
		document.getElementById("pdLoginForm").action = proximoFrame;
		document.getElementById("pdLoginForm").submit();
	}
}

function enviarPdLoginIni(valTipo) {
	if (valTipo == 1) {
		var pdNovaSenha = document.getElementById("pdNovaSenha").value;
		var pdConfNovaSenha = document.getElementById("pdConfNovaSenha").value;
		if (pdNovaSenha.trim() == "" || pdConfNovaSenha.trim() == "") {
			var pdMensagens = document.getElementById("pdMensagens"); 
			pdMensagens.style.display = "";
			pdMensagens.innerHTML = "<br/>Preencher os campos de 'Nova Senha' e 'Confirmação da Nova Senha'";
		} else if (pdNovaSenha.trim() != pdConfNovaSenha.trim()) {
			var pdMensagens = document.getElementById("pdMensagens"); 
			pdMensagens.style.display = "";
			pdMensagens.innerHTML = "<br/>Os dois campos acima devem ter valores exatamente iguais";
		} else {
			document.getElementById("pdAcao").value = "envLoginIni";
			document.getElementById("pdLoginForm").submit();
		}
	} else if (valTipo == 2) {
		var proximoFrame = "../fld_control/ctrltargetpage.jsp";
		parent.document.getElementById("botao_sair").style.display = "";
		document.getElementById("pdLoginForm").action = proximoFrame;
		document.getElementById("pdLoginForm").submit();
	}
}

function submeterForm(a, e) {
	if (e.keyCode==13) {
		if (a==1) {
			enviarPdLogin(1);
		} else if (a==2) {
			enviarPdLoginIni(1);
		}
	}
}

function editarLinhas() {
	var elementos = document.getElementsByClassName("lin_text");
	n = elementos.length;
	for (var i = 0; i < n; i++) {
		var e = elementos[i];
		e.style.display = "none";
	}
	elementos = document.getElementsByClassName("lin_edit");
	n = elementos.length;
	for (var i = 0; i < n; i++) {
		var e = elementos[i];
		e.style.display = "";
	}
}

function salvarLinhas() {
	var elementos = document.getElementsByClassName("lin_text");
	n = elementos.length;
	for (var i = 0; i < n; i++) {
		var e = elementos[i];
		e.style.display = "";
	}
	elementos = document.getElementsByClassName("lin_edit");
	n = elementos.length;
	for (var i = 0; i < n; i++) {
		var e = elementos[i];
		e.style.display = "none";
	}
}

function cancelarEdicao() {
	var elementos = document.getElementsByClassName("lin_text");
	n = elementos.length;
	for (var i = 0; i < n; i++) {
		var e = elementos[i];
		e.style.display = "";
	}
	elementos = document.getElementsByClassName("lin_edit");
	n = elementos.length;
	for (var i = 0; i < n; i++) {
		var e = elementos[i];
		e.style.display = "none";
	}
}

function verResultados() {
	document.getElementById("form01").submit();
}
