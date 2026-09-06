package paradecision.boot.modulos.agendas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.dto.AgendaUsuarioPareceresDados;
import paradecision.boot.modulos.agendas.repository.AgendaUsuarioPareceresRepository;

@Service
public class AgendaUsuarioPareceresService {
  private final AgendaUsuarioPareceresRepository agendaUsuarioPareceresRepository;

  public AgendaUsuarioPareceresService(
      AgendaUsuarioPareceresRepository agendaUsuarioPareceresRepository) {
    this.agendaUsuarioPareceresRepository = agendaUsuarioPareceresRepository;
  }

  public AgendaUsuarioPareceresDados selectPareceresAgUsu(
      AgendaUsuarioPareceresDados oAgendaUsuarioPareceresModel) {
    oAgendaUsuarioPareceresModel =
        agendaUsuarioPareceresRepository.selectPareceresAgUsu(oAgendaUsuarioPareceresModel);
    return oAgendaUsuarioPareceresModel;
  }
}
