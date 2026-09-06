package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaUsuariosDados {

  private Agenda oAgendaModel = new Agenda();
  private ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
  private ArrayList<AgendaUsuarioPerfil> arrAgendaUsuarioPerfilModel =
      new ArrayList<AgendaUsuarioPerfil>();

  public Agenda getoAgendaModel() {
    return oAgendaModel;
  }

  public void setoAgendaModel(Agenda oAgendaModel) {
    this.oAgendaModel = oAgendaModel;
  }

  public ArrayList<Usuario> getArrUsuarioModel() {
    return arrUsuarioModel;
  }

  public void setArrUsuarioModel(ArrayList<Usuario> arrUsuarioModel) {
    this.arrUsuarioModel = arrUsuarioModel;
  }

  public ArrayList<AgendaUsuarioPerfil> getArrAgendaUsuarioPerfilModel() {
    return arrAgendaUsuarioPerfilModel;
  }

  public void setArrAgendaUsuarioPerfilModel(
      ArrayList<AgendaUsuarioPerfil> arrAgendaUsuarioPerfilModel) {
    this.arrAgendaUsuarioPerfilModel = arrAgendaUsuarioPerfilModel;
  }
}
