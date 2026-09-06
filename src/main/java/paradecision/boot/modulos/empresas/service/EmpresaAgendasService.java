package paradecision.boot.modulos.empresas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.empresas.dto.EmpresaAgendasDados;
import paradecision.boot.modulos.empresas.repository.EmpresaAgendasRepository;

@Service
public class EmpresaAgendasService {
  private final EmpresaAgendasRepository empresaAgendasRepository;

  public EmpresaAgendasService(EmpresaAgendasRepository empresaAgendasRepository) {
    this.empresaAgendasRepository = empresaAgendasRepository;
  }

  public EmpresaAgendasDados selectAgendasDaEmpresa(EmpresaAgendasDados dadosEmpresaAgendas) {
    dadosEmpresaAgendas = empresaAgendasRepository.selectAgendasDaEmpresa(dadosEmpresaAgendas);
    return dadosEmpresaAgendas;
  }

  public EmpresaAgendasDados selectAgendasDaEmpresaUsuario(
      EmpresaAgendasDados dadosEmpresaAgendas) {
    dadosEmpresaAgendas =
        empresaAgendasRepository.selectAgendasDaEmpresaUsuario(dadosEmpresaAgendas);
    return dadosEmpresaAgendas;
  }
}
