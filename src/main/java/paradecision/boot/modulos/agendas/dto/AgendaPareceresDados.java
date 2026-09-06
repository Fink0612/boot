package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaPareceresDados {

  private Agenda dadosAgenda = new Agenda();
  private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
  private ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario =
      new ArrayList<ParecerFatorUsuario>();

  public Agenda getoAgendaModel() {
    return dadosAgenda;
  }

  public void setoAgendaModel(Agenda dadosAgenda) {
    this.dadosAgenda = dadosAgenda;
  }

  public ArrayList<Usuario> getArrUsuarioModel() {
    return listaUsuario;
  }

  public void setArrUsuarioModel(ArrayList<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }

  public ArrayList<ParecerFatorUsuario> getArrParecerFatorUsuarioModel() {
    return listaParecerFatorUsuario;
  }

  public void setArrParecerFatorUsuarioModel(
      ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario) {
    this.listaParecerFatorUsuario = listaParecerFatorUsuario;
  }
}
