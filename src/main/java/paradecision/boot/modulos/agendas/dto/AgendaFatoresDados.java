package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaFatoresDados {

  private Agenda oAgendaModel = new Agenda();
  private ArrayList<Fator> arrFatorModel = new ArrayList<Fator>();
  private ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
  private ArrayList<ParecerFatorUsuario> arrParecerFatorUsuarioModel =
      new ArrayList<ParecerFatorUsuario>();

  public ArrayList<Usuario> getArrUsuarioModel() {
    return arrUsuarioModel;
  }

  public void setArrUsuarioModel(ArrayList<Usuario> arrUsuarioModel) {
    this.arrUsuarioModel = arrUsuarioModel;
  }

  public Agenda getoAgendaModel() {
    return oAgendaModel;
  }

  public void setoAgendaModel(Agenda oAgendaModel) {
    this.oAgendaModel = oAgendaModel;
  }

  public ArrayList<Fator> getArrFatorModel() {
    return arrFatorModel;
  }

  public void setArrFatorModel(ArrayList<Fator> arrFatorModel) {
    this.arrFatorModel = arrFatorModel;
  }

  public ArrayList<ParecerFatorUsuario> getArrParecerFatorUsuarioModel() {
    return arrParecerFatorUsuarioModel;
  }

  public void setArrParecerFatorUsuarioModel(
      ArrayList<ParecerFatorUsuario> arrParecerFatorUsuarioModel) {
    this.arrParecerFatorUsuarioModel = arrParecerFatorUsuarioModel;
  }
}
