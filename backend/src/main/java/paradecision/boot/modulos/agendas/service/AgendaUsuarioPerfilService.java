package paradecision.boot.modulos.agendas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.repository.AgendaUsuarioPerfilRepository;

@Service
public class AgendaUsuarioPerfilService {
  private final AgendaUsuarioPerfilRepository agendaUsuarioPerfilRepository;

  public AgendaUsuarioPerfilService(AgendaUsuarioPerfilRepository agendaUsuarioPerfilRepository) {
    this.agendaUsuarioPerfilRepository = agendaUsuarioPerfilRepository;
  }

  public AgendaUsuarioPerfil selectAgendaUsuarioPerfil(
      AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    dadosAgendaUsuarioPerfil =
        agendaUsuarioPerfilRepository.selectAgendaUsuarioPerfil(dadosAgendaUsuarioPerfil);
    return dadosAgendaUsuarioPerfil;
  }

  public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    String mensagemAcao =
        agendaUsuarioPerfilRepository.deleteAgendaUsuarioPerfil(dadosAgendaUsuarioPerfil);
    return mensagemAcao;
  }

  public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    String mensagemAcao =
        agendaUsuarioPerfilRepository.updatePerfilUsuarioAgenda(dadosAgendaUsuarioPerfil);
    return mensagemAcao;
  }

  public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    String mensagemAcao =
        agendaUsuarioPerfilRepository.insertPerfilUsuarioAgenda(dadosAgendaUsuarioPerfil);
    return mensagemAcao;
  }
}
