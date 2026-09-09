package paradecision.boot.modulos.agendas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class AgendaUsuarioPareceresDados {

  private Agenda dadosAgenda = new Agenda();
  private Usuario dadosUsuario = new Usuario();
  private ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario =
      new ArrayList<ParecerFatorUsuario>();

  public Agenda getoAgendaModel() {
    return dadosAgenda;
  }

  public void setoAgendaModel(Agenda dadosAgenda) {
    this.dadosAgenda = dadosAgenda;
  }

  public Usuario getoUsuarioModel() {
    return dadosUsuario;
  }

  public void setoUsuarioModel(Usuario dadosUsuario) {
    this.dadosUsuario = dadosUsuario;
  }

  public ArrayList<ParecerFatorUsuario> getArrParecerFatorUsuarioModel() {
    return listaParecerFatorUsuario;
  }

  public void setArrParecerFatorUsuarioModel(
      ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario) {
    this.listaParecerFatorUsuario = listaParecerFatorUsuario;
  }
}
