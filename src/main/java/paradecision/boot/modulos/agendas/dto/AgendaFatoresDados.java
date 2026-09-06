package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaFatoresDados {

  private Agenda dadosAgenda = new Agenda();
  private ArrayList<Fator> listaFator = new ArrayList<Fator>();
  private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
  private ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario =
      new ArrayList<ParecerFatorUsuario>();

  public ArrayList<Usuario> getArrUsuarioModel() {
    return listaUsuario;
  }

  public void setArrUsuarioModel(ArrayList<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }

  public Agenda getoAgendaModel() {
    return dadosAgenda;
  }

  public void setoAgendaModel(Agenda dadosAgenda) {
    this.dadosAgenda = dadosAgenda;
  }

  public ArrayList<Fator> getArrFatorModel() {
    return listaFator;
  }

  public void setArrFatorModel(ArrayList<Fator> listaFator) {
    this.listaFator = listaFator;
  }

  public ArrayList<ParecerFatorUsuario> getArrParecerFatorUsuarioModel() {
    return listaParecerFatorUsuario;
  }

  public void setArrParecerFatorUsuarioModel(
      ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario) {
    this.listaParecerFatorUsuario = listaParecerFatorUsuario;
  }
}
