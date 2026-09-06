package paradecision.boot.modulos.diagnostico.repository;

import org.springframework.stereotype.Repository;
import paradecision.boot.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.diagnostico.dto.DiagnosticoBanco;

@Repository
public class DiagnosticoBancoRepository {
  public DiagnosticoBanco verificar() {
    var fabrica = new ConnectionFactory();
    String mensagem;
    try (var conexao = fabrica.getConnection()) {
      mensagem =
          conexao != null
              ? "Sucesso!! Banco de Dados Conectado!"
              : "OPS!! Problemas com acesso ao Banco de Dados!";
    } catch (java.sql.SQLException e) {
      mensagem = "OPS!! Problemas com acesso ao Banco de Dados!";
    }
    return new DiagnosticoBanco(
        mensagem, fabrica.ipAtual, fabrica.ipPrincipal, fabrica.ipServer, fabrica.url);
  }
}
