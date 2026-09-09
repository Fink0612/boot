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
      EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    dadosEmpresaUsuarioPerfil =
        empresaUsuarioPerfilRepository.selectEmpresaUsuario(dadosEmpresaUsuarioPerfil);
    return dadosEmpresaUsuarioPerfil;
  }

  public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    int operacaoConcluida = 1;
    operacaoConcluida =
        empresaUsuarioPerfilRepository.insertEmpresaUsuarioPerfil(dadosEmpresaUsuarioPerfil);
    return operacaoConcluida;
  }

  public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    String operacaoConcluida = "";
    operacaoConcluida =
        empresaUsuarioPerfilRepository.updateEmpresaUsuarioPerfil(dadosEmpresaUsuarioPerfil);
    return operacaoConcluida;
  }
}
