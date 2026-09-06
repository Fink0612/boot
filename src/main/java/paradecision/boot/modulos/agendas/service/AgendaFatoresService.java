package paradecision.boot.modulos.agendas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.repository.AgendaFatoresRepository;

@Service
public class AgendaFatoresService {
  private final AgendaFatoresRepository agendaFatoresRepository;

  public AgendaFatoresService(AgendaFatoresRepository agendaFatoresRepository) {
    this.agendaFatoresRepository = agendaFatoresRepository;
  }

  public AgendaFatoresDados selectFatoresDaAgenda(AgendaFatoresDados oAgendaFatoresModel) {
    oAgendaFatoresModel = agendaFatoresRepository.selectFatoresDaAgenda(oAgendaFatoresModel);
    return oAgendaFatoresModel;
  }

  public String updateGrausFatoresDaAgenda(AgendaFatoresDados oAgendaFatoresModel) {
    String msgAction = agendaFatoresRepository.updateGrausFatoresDaAgenda(oAgendaFatoresModel);
    return msgAction;
  }
}
