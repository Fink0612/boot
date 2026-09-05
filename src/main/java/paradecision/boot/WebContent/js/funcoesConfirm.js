// ######## ... FUNÇÕES DE CONFIRMAÇÃO ... ########
function rodarConfirmMetodo(codConfirm) {
	fechaConfirmMensagem();
	if (codConfirm == "libAg") {
		liberarAgenda();
	} else if (codConfirm == "encAg") {
		encaminharAgenda();
	} else if (codConfirm == "encerrAg") {
		encerrarAgenda();
	} else if (codConfirm == "calcResAg") {
		calcularResultadosAgenda();
	}
	
}
