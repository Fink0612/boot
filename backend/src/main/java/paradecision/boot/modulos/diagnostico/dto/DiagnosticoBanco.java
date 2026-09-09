package paradecision.boot.modulos.diagnostico.dto;

public record DiagnosticoBanco(
    String mensagem, String ipAtual, String ipPrincipal, String ipServer, String url) {}
