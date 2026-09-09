package paradecision.boot.modulos.diagnostico.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.diagnostico.dto.DiagnosticoBanco;
import paradecision.boot.modulos.diagnostico.repository.DiagnosticoBancoRepository;

@Service
public class AcessoBancoService {
  private final DiagnosticoBancoRepository repository;

  public AcessoBancoService(DiagnosticoBancoRepository repository) {
    this.repository = repository;
  }

  public DiagnosticoBanco verificar() {
    return repository.verificar();
  }
}
