// ######## ... FUNÇÕES DE CONFIRMAÇÃO ... ########
function rodarConfirmMetodo(codigoConfirmacao) {
	fechaConfirmMensagem();
	if (codigoConfirmacao == "libAg") {
		liberarAgenda();
	} else if (codigoConfirmacao == "encAg") {
		encaminharAgenda();
	} else if (codigoConfirmacao == "encerrAg") {
		encerrarAgenda();
	} else if (codigoConfirmacao == "calcResAg") {
		calcularResultadosAgenda();
	}
	
}
