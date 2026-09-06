function verifCheckCerteza(valCol, valLin, qtdTotLin) {
	var nomCmp = "chk_CERTEZA_" + valCol + "_" + valLin;
	var cmpChk = document.getElementById(nomCmp);
	var cmpAux;
	var cmpCerteza = document.getElementById("afp_A07_CERTEZA_" + valLin);
	if(cmpChk.checked) {
		//alert(cmpCerteza);
		cmpCerteza.value = Number(cmpChk.value) * 10;
		var ii;
		for (ii = 0; ii <= 10; ii++) {
			if (ii != valCol) {
				nomCmp = "chk_CERTEZA_" + ii + "_" + valLin;
				cmpAux = document.getElementById(nomCmp);
				if(cmpAux.checked) {
					cmpAux.checked = false;
				}
			}
		}
	} else {
		cmpCerteza.value = "";
	}
}

function verifCheckContradicao(valCol, valLin, qtdTotLin) {
	var nomCmp = "chk_CONTRADICAO_" + valCol + "_" + valLin;
	var cmpChk = document.getElementById(nomCmp);
	var cmpAux;
	var cmpContradicao = document.getElementById("afp_A07_CONTRADICAO_" + valLin);
	if(cmpChk.checked) {
		cmpContradicao.value = Number(cmpChk.value) * 10;
		var ii;
		for (ii = 0; ii <= 10; ii++) {
			if (ii != valCol) {
				nomCmp = "chk_CONTRADICAO_" + ii + "_" + valLin;
				cmpAux = document.getElementById(nomCmp);
				if(cmpAux.checked) {
					cmpAux.checked = false;
				}
			}
		}
	} else {
		cmpContradicao.value = "";
	}
}

function selecCerteza(numVal, numCmp, numCol) {
	var cmp = document.getElementById("afp_A07_CERTEZA_" + numCmp);
	var anc;
	if (cmp.value == numVal) {
		cmp.value = "";
		anc = document.getElementById("CertL" + numCmp + "C" + numCol);
		var classes = anc.className;
		var arrClass = classes.split(" ");
		clsFinal = "nchk ";
		for (ii = 1; ii < arrClass.length; ii++) {
			clsFinal += arrClass[ii] + " ";
		}
		clsFinal = clsFinal.trim();
		anc.className = clsFinal;
	} else {
		cmp.value = numVal;
		//-------------------------------------
		anc = document.getElementById("CertL" + numCmp + "C" + numCol);
		var classes = anc.className;
		var arrClass = classes.split(" ");
		clsFinal = "chk ";
		for (ii = 1; ii < arrClass.length; ii++) {
			clsFinal += arrClass[ii] + " ";
		}
		clsFinal = clsFinal.trim();
		//alert("[" + clsFinal + "]");
		anc.className = clsFinal;
		//-------------------------------------
		for (ii = 0; ii <= 10; ii++) {
			if (ii != numCol) {
				anc = document.getElementById("CertL" + numCmp + "C" + ii);
				classes = anc.className;
				arrClass = classes.split(" ");
				clsFinal = "nchk ";
				for (kk = 1; kk < arrClass.length; kk++) {
					clsFinal += arrClass[kk] + " ";
				}
				anc.className = clsFinal;
				clsFinal = clsFinal.trim();
				//alert("[" + clsFinal + "]");
				anc.className = clsFinal;
			}
		}
	}
}

function selecContradicao(numVal, numCmp, numCol) {
	var cmp = document.getElementById("afp_A07_CONTRADICAO_" + numCmp);
	var anc;
	if (cmp.value == numVal) {
		cmp.value = "";
		anc = document.getElementById("ContL" + numCmp + "C" + numCol);
		var classes = anc.className;
		var arrClass = classes.split(" ");
		clsFinal = "nchk ";
		for (ii = 1; ii < arrClass.length; ii++) {
			clsFinal += arrClass[ii] + " ";
		}
		clsFinal = clsFinal.trim();
		anc.className = clsFinal;
	} else {
		cmp.value = numVal;
		//-------------------------------------
		anc = document.getElementById("ContL" + numCmp + "C" + numCol);
		var classes = anc.className;
		var arrClass = classes.split(" ");
		clsFinal = "chk ";
		for (ii = 1; ii < arrClass.length; ii++) {
			clsFinal += arrClass[ii] + " ";
		}
		clsFinal = clsFinal.trim();
		//alert("[" + clsFinal + "]");
		anc.className = clsFinal;
		//-------------------------------------
		for (ii = 0; ii <= 10; ii++) {
			if (ii != numCol) {
				anc = document.getElementById("ContL" + numCmp + "C" + ii);
				classes = anc.className;
				arrClass = classes.split(" ");
				clsFinal = "nchk ";
				for (kk = 1; kk < arrClass.length; kk++) {
					clsFinal += arrClass[kk] + " ";
				}
				anc.className = clsFinal;
				clsFinal = clsFinal.trim();
				//alert("[" + clsFinal + "]");
				anc.className = clsFinal;
			}
		}
	}
}

function verificarPareceresPendentes() {
	var proximoFrame = "../agendas/AgendaUsuariosPareceresPendencia";
	document.getElementById("pdAcao").value = "";
	document.getElementById("AgendaFatoresForm").action = proximoFrame;
	document.getElementById("AgendaFatoresForm").submit();
}
