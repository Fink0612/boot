package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaUsuariosDados {

  private Agenda dadosAgenda = new Agenda();
  private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
  private ArrayList<AgendaUsuarioPerfil> listaAgendaUsuarioPerfil =
      new ArrayList<AgendaUsuarioPerfil>();

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

  public ArrayList<AgendaUsuarioPerfil> getArrAgendaUsuarioPerfilModel() {
    return listaAgendaUsuarioPerfil;
  }

  public void setArrAgendaUsuarioPerfilModel(
      ArrayList<AgendaUsuarioPerfil> listaAgendaUsuarioPerfil) {
    this.listaAgendaUsuarioPerfil = listaAgendaUsuarioPerfil;
  }
}
