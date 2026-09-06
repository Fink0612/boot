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
      AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    oAgendaUsuarioPerfilModel =
        agendaUsuarioPerfilRepository.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
    return oAgendaUsuarioPerfilModel;
  }

  public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    String msgAction =
        agendaUsuarioPerfilRepository.deleteAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
    return msgAction;
  }

  public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    String msgAction =
        agendaUsuarioPerfilRepository.updatePerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
    return msgAction;
  }

  public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    String msgAction =
        agendaUsuarioPerfilRepository.insertPerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
    return msgAction;
  }
}
