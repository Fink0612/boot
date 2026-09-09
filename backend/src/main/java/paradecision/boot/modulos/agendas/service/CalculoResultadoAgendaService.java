package paradecision.boot.modulos.agendas.service;

import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.repository.AgendaFatoresRepository;
import paradecision.boot.modulos.agendas.repository.AgendaPareceresRepository;
import paradecision.boot.modulos.agendas.repository.AgendaUsuariosRepository;

@Service
public class CalculoResultadoAgendaService {
  private final AgendaFatoresRepository fatores;
  private final AgendaUsuariosRepository usuarios;
  private final AgendaPareceresRepository pareceres;
  private final AgendaFatoresService graus;

  public CalculoResultadoAgendaService(
      AgendaFatoresRepository fatores,
      AgendaUsuariosRepository usuarios,
      AgendaPareceresRepository pareceres,
      AgendaFatoresService graus) {
    this.fatores = fatores;
    this.usuarios = usuarios;
    this.pareceres = pareceres;
    this.graus = graus;
  }

  public String geraResultados(Agenda agenda, int tipoAmostra) {
    return new ExecutorCalculoAgenda(fatores, graus, pareceres, usuarios)
        .geraResultados(agenda, tipoAmostra);
  }
}
