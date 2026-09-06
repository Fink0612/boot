package paradecision.boot.modulos.empresas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.empresas.dto.EmpresaUsuariosDados;
import paradecision.boot.modulos.empresas.repository.EmpresaUsuariosRepository;

@Service
public class EmpresaUsuariosService {
  private final EmpresaUsuariosRepository empresaUsuariosRepository;

  public EmpresaUsuariosService(EmpresaUsuariosRepository empresaUsuariosRepository) {
    this.empresaUsuariosRepository = empresaUsuariosRepository;
  }

  public EmpresaUsuariosDados selectUsuariosDaEmpresa(EmpresaUsuariosDados oEmpresaUsuariosModel) {
    oEmpresaUsuariosModel =
        empresaUsuariosRepository.selectUsuariosDaEmpresa(oEmpresaUsuariosModel);
    return oEmpresaUsuariosModel;
  }
}
