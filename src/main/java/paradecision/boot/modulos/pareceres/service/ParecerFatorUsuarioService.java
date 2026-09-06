package paradecision.boot.modulos.pareceres.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.pareceres.repository.ParecerFatorUsuarioRepository;

@Service
public class ParecerFatorUsuarioService {
  private final ParecerFatorUsuarioRepository parecerFatorUsuarioRepository;

  public ParecerFatorUsuarioService(ParecerFatorUsuarioRepository parecerFatorUsuarioRepository) {
    this.parecerFatorUsuarioRepository = parecerFatorUsuarioRepository;
  }

  public ParecerFatorUsuario selectParecerFatorUsuario(
      ParecerFatorUsuario oParecerFatorUsuarioModel) {
    oParecerFatorUsuarioModel =
        parecerFatorUsuarioRepository.selectParecerFatorUsuario(oParecerFatorUsuarioModel);
    return oParecerFatorUsuarioModel;
  }

  public String insertParecerFatorUsuario(ParecerFatorUsuario oParecerFatorUsuarioModel) {
    String okMetodo = "";
    okMetodo = parecerFatorUsuarioRepository.insertParecerFatorUsuario(oParecerFatorUsuarioModel);
    return okMetodo;
  }

  public String updateParecerFatorUsuario(ParecerFatorUsuario oParecerFatorUsuarioModel) {
    String okMetodo = "";
    okMetodo = parecerFatorUsuarioRepository.updateParecerFatorUsuario(oParecerFatorUsuarioModel);
    return okMetodo;
  }
}
