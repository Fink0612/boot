package paradecision.boot.modulos.usuarios.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Usuario selectUserLogin(Usuario dadosUsuario) {
    Usuario dadosUsuarioAuxiliares = new Usuario();
    dadosUsuarioAuxiliares.setA02_usuario(dadosUsuario.getA02_usuario());
    dadosUsuarioAuxiliares = this.usuarioRepository.selectUserLogin(dadosUsuarioAuxiliares);
    if (dadosUsuarioAuxiliares.getA02_codigo() > 0) {
      if (!(dadosUsuarioAuxiliares.getA02_senha().equals(dadosUsuario.getA02_senha()))) {
        dadosUsuarioAuxiliares = new Usuario();
      }
    }
    return dadosUsuarioAuxiliares;
  }

  public Usuario selectUserIni(Usuario dadosUsuario) {
    Usuario dadosUsuarioAuxiliares = new Usuario();
    dadosUsuarioAuxiliares.setA02_codigo_link(dadosUsuario.getA02_codigo_link());
    dadosUsuarioAuxiliares = this.usuarioRepository.selectUserIni(dadosUsuarioAuxiliares);
    return dadosUsuarioAuxiliares;
  }

  public Usuario selectUserByUser(Usuario dadosUsuario) {
    Usuario dadosUsuarioAuxiliares = new Usuario();
    dadosUsuarioAuxiliares.setA02_usuario(dadosUsuario.getA02_usuario());
    dadosUsuarioAuxiliares = this.usuarioRepository.selectUserLogin(dadosUsuarioAuxiliares);
    return dadosUsuarioAuxiliares;
  }

  public Usuario selectUserByCode(Usuario dadosUsuario) {
    dadosUsuario = this.usuarioRepository.selectUserByCode(dadosUsuario);
    return dadosUsuario;
  }

  public Usuario updateSenhaUsuario(Usuario dadosUsuario) {
    Usuario dadosUsuarioAuxiliares = new Usuario();
    this.usuarioRepository.updateSenhaUsuario(dadosUsuario);
    dadosUsuarioAuxiliares.setA02_usuario(dadosUsuario.getA02_usuario());
    dadosUsuarioAuxiliares = this.selectUserByUser(dadosUsuarioAuxiliares);
    return dadosUsuarioAuxiliares;
  }

  public String updateUsuario(Usuario dadosUsuario) {
    String operacaoConcluida = "";
    operacaoConcluida = usuarioRepository.updateUsuario(dadosUsuario);
    return operacaoConcluida;
  }

  public Usuario insertUsuario(Usuario dadosUsuario) {
    dadosUsuario = this.usuarioRepository.insertUsuario(dadosUsuario);
    return dadosUsuario;
  }
}
