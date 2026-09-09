function verifCheckCerteza(indiceColunaSelecionada, indiceLinhaSelecionada, quantidadeTotalLinhas) {
	var nomeCampo = "chk_CERTEZA_" + indiceColunaSelecionada + "_" + indiceLinhaSelecionada;
	var campoSelecao = document.getElementById(nomeCampo);
	var campoAuxiliar;
	var campoCerteza = document.getElementById("afp_A07_CERTEZA_" + indiceLinhaSelecionada);
	if(campoSelecao.checked) {
		//alert(cmpCerteza);
		campoCerteza.value = Number(campoSelecao.value) * 10;
		var indiceRegistro;
		for (indiceRegistro = 0; indiceRegistro <= 10; indiceRegistro++) {
			if (indiceRegistro != indiceColunaSelecionada) {
				nomeCampo = "chk_CERTEZA_" + indiceRegistro + "_" + indiceLinhaSelecionada;
				campoAuxiliar = document.getElementById(nomeCampo);
				if(campoAuxiliar.checked) {
					campoAuxiliar.checked = false;
				}
			}
		}
	} else {
		campoCerteza.value = "";
	}
}

function verifCheckContradicao(indiceColunaSelecionada, indiceLinhaSelecionada, quantidadeTotalLinhas) {
	var nomeCampo = "chk_CONTRADICAO_" + indiceColunaSelecionada + "_" + indiceLinhaSelecionada;
	var campoSelecao = document.getElementById(nomeCampo);
	var campoAuxiliar;
	var campoContradicao = document.getElementById("afp_A07_CONTRADICAO_" + indiceLinhaSelecionada);
	if(campoSelecao.checked) {
		campoContradicao.value = Number(campoSelecao.value) * 10;
		var indiceRegistro;
		for (indiceRegistro = 0; indiceRegistro <= 10; indiceRegistro++) {
			if (indiceRegistro != indiceColunaSelecionada) {
				nomeCampo = "chk_CONTRADICAO_" + indiceRegistro + "_" + indiceLinhaSelecionada;
				campoAuxiliar = document.getElementById(nomeCampo);
				if(campoAuxiliar.checked) {
					campoAuxiliar.checked = false;
				}
			}
		}
	} else {
		campoContradicao.value = "";
	}
}

function selecCerteza(valorNumerico, indiceCampoParecer, indiceColunaParecer) {
	var campoFormulario = document.getElementById("afp_A07_CERTEZA_" + indiceCampoParecer);
	var linkNavegacao;
	if (campoFormulario.value == valorNumerico) {
		campoFormulario.value = "";
		linkNavegacao = document.getElementById("CertL" + indiceCampoParecer + "C" + indiceColunaParecer);
		var classes = linkNavegacao.className;
		var elementosDaClasse = classes.split(" ");
		classesCssAtualizadas = "nchk ";
		for (indiceRegistro = 1; indiceRegistro < elementosDaClasse.length; indiceRegistro++) {
			classesCssAtualizadas += elementosDaClasse[indiceRegistro] + " ";
		}
		classesCssAtualizadas = classesCssAtualizadas.trim();
		linkNavegacao.className = classesCssAtualizadas;
	} else {
		campoFormulario.value = valorNumerico;
		//-------------------------------------
		linkNavegacao = document.getElementById("CertL" + indiceCampoParecer + "C" + indiceColunaParecer);
		var classes = linkNavegacao.className;
		var elementosDaClasse = classes.split(" ");
		classesCssAtualizadas = "chk ";
		for (indiceRegistro = 1; indiceRegistro < elementosDaClasse.length; indiceRegistro++) {
			classesCssAtualizadas += elementosDaClasse[indiceRegistro] + " ";
		}
		classesCssAtualizadas = classesCssAtualizadas.trim();
		//alert("[" + clsFinal + "]");
		linkNavegacao.className = classesCssAtualizadas;
		//-------------------------------------
		for (indiceRegistro = 0; indiceRegistro <= 10; indiceRegistro++) {
			if (indiceRegistro != indiceColunaParecer) {
				linkNavegacao = document.getElementById("CertL" + indiceCampoParecer + "C" + indiceRegistro);
				classes = linkNavegacao.className;
				elementosDaClasse = classes.split(" ");
				classesCssAtualizadas = "nchk ";
				for (indiceNivelParecer = 1; indiceNivelParecer < elementosDaClasse.length; indiceNivelParecer++) {
					classesCssAtualizadas += elementosDaClasse[indiceNivelParecer] + " ";
				}
				linkNavegacao.className = classesCssAtualizadas;
				classesCssAtualizadas = classesCssAtualizadas.trim();
				//alert("[" + clsFinal + "]");
				linkNavegacao.className = classesCssAtualizadas;
			}
		}
	}
}

function selecContradicao(valorNumerico, indiceCampoParecer, indiceColunaParecer) {
	var campoFormulario = document.getElementById("afp_A07_CONTRADICAO_" + indiceCampoParecer);
	var linkNavegacao;
	if (campoFormulario.value == valorNumerico) {
		campoFormulario.value = "";
		linkNavegacao = document.getElementById("ContL" + indiceCampoParecer + "C" + indiceColunaParecer);
		var classes = linkNavegacao.className;
		var elementosDaClasse = classes.split(" ");
		classesCssAtualizadas = "nchk ";
		for (indiceRegistro = 1; indiceRegistro < elementosDaClasse.length; indiceRegistro++) {
			classesCssAtualizadas += elementosDaClasse[indiceRegistro] + " ";
		}
		classesCssAtualizadas = classesCssAtualizadas.trim();
		linkNavegacao.className = classesCssAtualizadas;
	} else {
		campoFormulario.value = valorNumerico;
		//-------------------------------------
		linkNavegacao = document.getElementById("ContL" + indiceCampoParecer + "C" + indiceColunaParecer);
		var classes = linkNavegacao.className;
		var elementosDaClasse = classes.split(" ");
		classesCssAtualizadas = "chk ";
		for (indiceRegistro = 1; indiceRegistro < elementosDaClasse.length; indiceRegistro++) {
			classesCssAtualizadas += elementosDaClasse[indiceRegistro] + " ";
		}
		classesCssAtualizadas = classesCssAtualizadas.trim();
		//alert("[" + clsFinal + "]");
		linkNavegacao.className = classesCssAtualizadas;
		//-------------------------------------
		for (indiceRegistro = 0; indiceRegistro <= 10; indiceRegistro++) {
			if (indiceRegistro != indiceColunaParecer) {
				linkNavegacao = document.getElementById("ContL" + indiceCampoParecer + "C" + indiceRegistro);
				classes = linkNavegacao.className;
				elementosDaClasse = classes.split(" ");
				classesCssAtualizadas = "nchk ";
				for (indiceNivelParecer = 1; indiceNivelParecer < elementosDaClasse.length; indiceNivelParecer++) {
					classesCssAtualizadas += elementosDaClasse[indiceNivelParecer] + " ";
				}
				linkNavegacao.className = classesCssAtualizadas;
				classesCssAtualizadas = classesCssAtualizadas.trim();
				//alert("[" + clsFinal + "]");
				linkNavegacao.className = classesCssAtualizadas;
			}
		}
	}
}

function verificarPareceresPendentes() {
	var rotaProximaTela = "../agendas/AgendaUsuariosPareceresPendencia";
	document.getElementById("pdAcao").value = "";
	document.getElementById("AgendaFatoresForm").action = rotaProximaTela;
	document.getElementById("AgendaFatoresForm").submit();
}
