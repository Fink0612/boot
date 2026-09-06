package paradecision.boot.modulos.empresas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.empresas.repository.EmpresaUsuarioPerfilRepository;

@Service
public class EmpresaUsuarioPerfilService {
  private final EmpresaUsuarioPerfilRepository empresaUsuarioPerfilRepository;

  public EmpresaUsuarioPerfilService(
      EmpresaUsuarioPerfilRepository empresaUsuarioPerfilRepository) {
    this.empresaUsuarioPerfilRepository = empresaUsuarioPerfilRepository;
  }

  public EmpresaUsuarioPerfil selectEmpresaUsuario(
      EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel) {
    oEmpresaUsuarioPerfilModel =
        empresaUsuarioPerfilRepository.selectEmpresaUsuario(oEmpresaUsuarioPerfilModel);
    return oEmpresaUsuarioPerfilModel;
  }

  public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel) {
    int okMetodo = 1;
    okMetodo =
        empresaUsuarioPerfilRepository.insertEmpresaUsuarioPerfil(oEmpresaUsuarioPerfilModel);
    return okMetodo;
  }

  public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel) {
    String okMetodo = "";
    okMetodo =
        empresaUsuarioPerfilRepository.updateEmpresaUsuarioPerfil(oEmpresaUsuarioPerfilModel);
    return okMetodo;
  }
}
