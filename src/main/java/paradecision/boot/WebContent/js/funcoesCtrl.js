function ehNumero(txt) {
	return !isNaN(txt);
}

function setTarget() {
	var ct_A02_CODIGO = document.getElementById("ct_A02_CODIGO");
	var ct_A02_USUARIO = document.getElementById("ct_A02_USUARIO");
	var ct_QTD_EMPRESAS = document.getElementById("ct_QTD_EMPRESAS");
	var ct_A01_CODIGO = document.getElementById("ct_A01_CODIGO");
	var ct_A01_NOME = document.getElementById("ct_A01_NOME");
	var ct_A03_PERFIL_PARAVIVERBEM = document.getElementById("ct_A03_PERFIL_PARAVIVERBEM");
	var ct_A03_PERFIL_ADMINISTRADOR = document.getElementById("ct_A03_PERFIL_ADMINISTRADOR");
	var ct_A03_PERFIL_CHEFE = document.getElementById("ct_A03_PERFIL_CHEFE");
	var ct_A03_PERFIL_PADRAO = document.getElementById("ct_A03_PERFIL_PADRAO");
	var ctrlAcao = document.getElementById("pdAcao");
	var txtAcao = ctrlAcao.value;
	var proximoFrame = "#";
	var msg = "";
	var num_QTD_EMPRESAS = 0;
	if (ehNumero(ct_QTD_EMPRESAS.value)) {
		num_QTD_EMPRESAS = parseInt(ct_QTD_EMPRESAS.value);
	}
	if (txtAcao == "envLogin" || txtAcao == "envLoginIni") {
		if (num_QTD_EMPRESAS > 1) {
			proximoFrame = "../fld_view/UsuarioEmpresas.jsp";
		} else {
			proximoFrame = "../fld_view/EmpresaAgendas.jsp";
		}
	}
	if(proximoFrame != "#") {
		document.getElementById("pdAcao").value = "";
		document.getElementById("ctrlForm").action = proximoFrame;
		document.getElementById("ctrlForm").submit();
	} else {
		msg = "Aguarde um instante...";
		document.getElementById("i_ctrlMsg").innerHTML = msg;
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