package paradecision.boot.modulos.agendas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.dto.AgendaUsuariosDados;
import paradecision.boot.modulos.agendas.repository.AgendaUsuariosRepository;

@Service
public class AgendaUsuariosService {
  private final AgendaUsuariosRepository agendaUsuariosRepository;

  public AgendaUsuariosService(AgendaUsuariosRepository agendaUsuariosRepository) {
    this.agendaUsuariosRepository = agendaUsuariosRepository;
  }

  public AgendaUsuariosDados selectUsuariosDaAgenda(AgendaUsuariosDados dadosAgendaUsuarios) {
    dadosAgendaUsuarios = agendaUsuariosRepository.selectUsuariosDaAgenda(dadosAgendaUsuarios);
    return dadosAgendaUsuarios;
  }
}
