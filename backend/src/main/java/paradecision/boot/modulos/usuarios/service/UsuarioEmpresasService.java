package paradecision.boot.modulos.usuarios.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.usuarios.dto.UsuarioEmpresasDados;
import paradecision.boot.modulos.usuarios.repository.UsuarioEmpresasRepository;

@Service
public class UsuarioEmpresasService {
  private final UsuarioEmpresasRepository usuarioEmpresasRepository;

  public UsuarioEmpresasService(UsuarioEmpresasRepository usuarioEmpresasRepository) {
    this.usuarioEmpresasRepository = usuarioEmpresasRepository;
  }

  public UsuarioEmpresasDados selectEmpresasDoUsuario(UsuarioEmpresasDados dadosUsuarioEmpresas) {

    dadosUsuarioEmpresas =
        usuarioEmpresasRepository.selectEmpresasDoUsuario(dadosUsuarioEmpresas);
    return dadosUsuarioEmpresas;
  }
}
