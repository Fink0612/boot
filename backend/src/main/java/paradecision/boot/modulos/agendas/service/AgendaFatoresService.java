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

  public AgendaFatoresDados selectFatoresDaAgenda(AgendaFatoresDados dadosAgendaFatores) {
    dadosAgendaFatores = agendaFatoresRepository.selectFatoresDaAgenda(dadosAgendaFatores);
    return dadosAgendaFatores;
  }

  public String updateGrausFatoresDaAgenda(AgendaFatoresDados dadosAgendaFatores) {
    String mensagemAcao = agendaFatoresRepository.updateGrausFatoresDaAgenda(dadosAgendaFatores);
    return mensagemAcao;
  }
}
