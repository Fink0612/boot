package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaUsuarioPareceresDados {

  private Agenda oAgendaModel = new Agenda();
  private Usuario oUsuarioModel = new Usuario();
  private ArrayList<ParecerFatorUsuario> arrParecerFatorUsuarioModel =
      new ArrayList<ParecerFatorUsuario>();

  public Agenda getoAgendaModel() {
    return oAgendaModel;
  }

  public void setoAgendaModel(Agenda oAgendaModel) {
    this.oAgendaModel = oAgendaModel;
  }

  public Usuario getoUsuarioModel() {
    return oUsuarioModel;
  }

  public void setoUsuarioModel(Usuario oUsuarioModel) {
    this.oUsuarioModel = oUsuarioModel;
  }

  public ArrayList<ParecerFatorUsuario> getArrParecerFatorUsuarioModel() {
    return arrParecerFatorUsuarioModel;
  }

  public void setArrParecerFatorUsuarioModel(
      ArrayList<ParecerFatorUsuario> arrParecerFatorUsuarioModel) {
    this.arrParecerFatorUsuarioModel = arrParecerFatorUsuarioModel;
  }
}
