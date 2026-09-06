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

  public long insertAgenda(Agenda dadosAgenda) {
    long resultadoProcessamento = this.agendaRepository.insertAgenda(dadosAgenda);
    return resultadoProcessamento;
  }

  public Agenda selectAgenda(Agenda dadosAgenda) {
    dadosAgenda = this.agendaRepository.selectAgenda(dadosAgenda);
    return dadosAgenda;
  }

  public String updateStatusAgenda(Agenda dadosAgenda) {
    String mensagemAcao = this.agendaRepository.updateStatusAgenda(dadosAgenda);
    return mensagemAcao;
  }

  public String updateAgenda(Agenda dadosAgenda) {
    String mensagemAcao = this.agendaRepository.updateAgenda(dadosAgenda);
    return mensagemAcao;
  }
}
