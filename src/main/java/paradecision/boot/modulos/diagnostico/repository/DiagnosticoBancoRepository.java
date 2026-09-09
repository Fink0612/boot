package paradecision.boot.modulos.diagnostico.repository;

import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.BancoDados;
import paradecision.boot.modulos.diagnostico.dto.DiagnosticoBanco;

@Repository
public class DiagnosticoBancoRepository {
  private final BancoDados banco;
  public DiagnosticoBancoRepository(BancoDados banco){this.banco=banco;}
  public DiagnosticoBanco verificar() {
    banco.listar("empresa_01", java.util.Map.of("a01_codigo",-1));
    return new DiagnosticoBanco("Banco conectado", "", "", "", "");
  }
}
