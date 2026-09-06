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
      ParecerFatorUsuario dadosParecerFatorUsuario) {
    dadosParecerFatorUsuario =
        parecerFatorUsuarioRepository.selectParecerFatorUsuario(dadosParecerFatorUsuario);
    return dadosParecerFatorUsuario;
  }

  public String insertParecerFatorUsuario(ParecerFatorUsuario dadosParecerFatorUsuario) {
    String operacaoConcluida = "";
    operacaoConcluida = parecerFatorUsuarioRepository.insertParecerFatorUsuario(dadosParecerFatorUsuario);
    return operacaoConcluida;
  }

  public String updateParecerFatorUsuario(ParecerFatorUsuario dadosParecerFatorUsuario) {
    String operacaoConcluida = "";
    operacaoConcluida = parecerFatorUsuarioRepository.updateParecerFatorUsuario(dadosParecerFatorUsuario);
    return operacaoConcluida;
  }
}
