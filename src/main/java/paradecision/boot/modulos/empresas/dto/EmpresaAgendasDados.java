package paradecision.boot.modulos.empresas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class EmpresaAgendasDados {

  private Empresa oEmpresaModel = new Empresa();
  private Usuario oUsuarioModel = new Usuario();
  private ArrayList<Agenda> arrAgendaModel = new ArrayList<Agenda>();

  public Empresa getoEmpresaModel() {
    return oEmpresaModel;
  }

  public void setoEmpresaModel(Empresa oEmpresaModel) {
    this.oEmpresaModel = oEmpresaModel;
  }

  public Usuario getoUsuarioModel() {
    return oUsuarioModel;
  }

  public void setoUsuarioModel(Usuario oUsuarioModel) {
    this.oUsuarioModel = oUsuarioModel;
  }

  public ArrayList<Agenda> getArrAgendaModel() {
    return arrAgendaModel;
  }

  public void setArrAgendaModel(ArrayList<Agenda> arrAgendaModel) {
    this.arrAgendaModel = arrAgendaModel;
  }
}
