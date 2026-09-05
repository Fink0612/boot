
function apagarSessao() {
	sessionStorage.clear();
	localStorage.clear();
}

function sairSessao() {
	var boolSair = true;
	//boolSair = confirm("Deseja sair do Sistema ParaDecision2?");
	if (boolSair) {
		apagarSessao();
		var urlAtual = window.location.href;
		var novaUrl = urlAtual.split('?')[0];
		window.open(novaUrl, "_self");
	}
}
