
function apagarSessao() {
	sessionStorage.clear();
	localStorage.clear();
}

function sairSessao() {
	var saidaConfirmada = true;
	//boolSair = confirm("Deseja sair do Sistema ParaDecision2?");
	if (saidaConfirmada) {
		apagarSessao();
		var urlAtual = window.location.href;
		var novaUrl = urlAtual.split('?')[0];
		window.open(novaUrl, "_self");
	}
}
