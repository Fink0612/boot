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

  public Usuario selectUserLogin(Usuario oUsuarioModel) {
    Usuario auxUsuarioModel = new Usuario();
    auxUsuarioModel.setA02_usuario(oUsuarioModel.getA02_usuario());
    auxUsuarioModel = this.usuarioRepository.selectUserLogin(auxUsuarioModel);
    if (auxUsuarioModel.getA02_codigo() > 0) {
      if (!(auxUsuarioModel.getA02_senha().equals(oUsuarioModel.getA02_senha()))) {
        auxUsuarioModel = new Usuario();
      }
    }
    return auxUsuarioModel;
  }

  public Usuario selectUserIni(Usuario oUsuarioModel) {
    Usuario auxUsuarioModel = new Usuario();
    auxUsuarioModel.setA02_codigo_link(oUsuarioModel.getA02_codigo_link());
    auxUsuarioModel = this.usuarioRepository.selectUserIni(auxUsuarioModel);
    return auxUsuarioModel;
  }

  public Usuario selectUserByUser(Usuario oUsuarioModel) {
    Usuario auxUsuarioModel = new Usuario();
    auxUsuarioModel.setA02_usuario(oUsuarioModel.getA02_usuario());
    auxUsuarioModel = this.usuarioRepository.selectUserLogin(auxUsuarioModel);
    return auxUsuarioModel;
  }

  public Usuario selectUserByCode(Usuario oUsuarioModel) {
    oUsuarioModel = this.usuarioRepository.selectUserByCode(oUsuarioModel);
    return oUsuarioModel;
  }

  public Usuario updateSenhaUsuario(Usuario oUsuarioModel) {
    Usuario auxUsuarioModel = new Usuario();
    this.usuarioRepository.updateSenhaUsuario(oUsuarioModel);
    auxUsuarioModel.setA02_usuario(oUsuarioModel.getA02_usuario());
    auxUsuarioModel = this.selectUserByUser(auxUsuarioModel);
    return auxUsuarioModel;
  }

  public String updateUsuario(Usuario oUsuarioModel) {
    String okMetodo = "";
    okMetodo = usuarioRepository.updateUsuario(oUsuarioModel);
    return okMetodo;
  }

  public Usuario insertUsuario(Usuario oUsuarioModel) {
    oUsuarioModel = this.usuarioRepository.insertUsuario(oUsuarioModel);
    return oUsuarioModel;
  }
}
