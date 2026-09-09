function ehNumero(textoRecebido) {
	return !isNaN(textoRecebido);
}

function setTarget() {
	var codigoUsuarioControle = document.getElementById("ct_A02_CODIGO");
	var usuarioUsuarioControle = document.getElementById("ct_A02_USUARIO");
	var ct_QTD_EMPRESAS = document.getElementById("ct_QTD_EMPRESAS");
	var codigoEmpresaControle = document.getElementById("ct_A01_CODIGO");
	var nomeEmpresaControle = document.getElementById("ct_A01_NOME");
	var perfilParaviverbemPerfilEmpresaUsuarioControle = document.getElementById("ct_A03_PERFIL_PARAVIVERBEM");
	var perfilAdministradorPerfilEmpresaUsuarioControle = document.getElementById("ct_A03_PERFIL_ADMINISTRADOR");
	var perfilChefePerfilEmpresaUsuarioControle = document.getElementById("ct_A03_PERFIL_CHEFE");
	var perfilPadraoPerfilEmpresaUsuarioControle = document.getElementById("ct_A03_PERFIL_PADRAO");
	var controleAcao = document.getElementById("pdAcao");
	var textoAcao = controleAcao.value;
	var rotaProximaTela = "#";
	var mensagemExibida = "";
	var quantidadeEmpresasNumerica = 0;
	if (ehNumero(ct_QTD_EMPRESAS.value)) {
		quantidadeEmpresasNumerica = parseInt(ct_QTD_EMPRESAS.value);
	}
	if (textoAcao == "envLogin" || textoAcao == "envLoginIni") {
		if (quantidadeEmpresasNumerica > 1) {
			rotaProximaTela = "../usuarios/UsuarioEmpresas";
		} else {
			rotaProximaTela = "../empresas/EmpresaAgendas";
		}
	}
	if(rotaProximaTela != "#") {
		document.getElementById("pdAcao").value = "";
		document.getElementById("ctrlForm").action = rotaProximaTela;
		document.getElementById("ctrlForm").submit();
	} else {
		mensagemExibida = "Aguarde um instante...";
		document.getElementById("i_ctrlMsg").innerHTML = mensagemExibida;
	}
}

function apagarCmpCtrlAgenda() {
	document.getElementById("ct_A04_CODIGO").value = "";
	document.getElementById("ct_A04_TITULO").value = "";
	document.getElementById("ct_A04_STATUS_DT_LIMITE").value = "";
	document.getElementById("ct_A04_DATA_LIMITE").value = "";
	document.getElementById("ct_A04_STATUS").value = "";
	document.getElementById("ct_A04_TXT_STATUS").value = "";
	document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_TITULAR").value = "";
	document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_FACILITADOR").value = "";
	document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA").value = "";
	document.getElementById("ct_A05_PERFIL_AGENDA_USUARIO_ANALISTA").value = "";
}