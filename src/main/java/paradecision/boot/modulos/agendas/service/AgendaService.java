package paradecision.boot.modulos.agendas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.repository.AgendaRepository;

@Service
public class AgendaService {
  private final AgendaRepository agendaRepository;

  public AgendaService(AgendaRepository agendaRepository) {
    this.agendaRepository = agendaRepository;
  }

  public long insertAgenda(Agenda oAgendaModel) {
    long res = this.agendaRepository.insertAgenda(oAgendaModel);
    return res;
  }

  public Agenda selectAgenda(Agenda oAgendaModel) {
    oAgendaModel = this.agendaRepository.selectAgenda(oAgendaModel);
    return oAgendaModel;
  }

  public String updateStatusAgenda(Agenda oAgendaModel) {
    String msgAction = this.agendaRepository.updateStatusAgenda(oAgendaModel);
    return msgAction;
  }

  public String updateAgenda(Agenda oAgendaModel) {
    String msgAction = this.agendaRepository.updateAgenda(oAgendaModel);
    return msgAction;
  }
}
