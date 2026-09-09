package paradecision.boot.modulos.empresas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class EmpresaAgendasDados {

  private Empresa dadosEmpresa = new Empresa();
  private Usuario dadosUsuario = new Usuario();
  private ArrayList<Agenda> listaAgenda = new ArrayList<Agenda>();

  public Empresa getoEmpresaModel() {
    return dadosEmpresa;
  }

  public void setoEmpresaModel(Empresa dadosEmpresa) {
    this.dadosEmpresa = dadosEmpresa;
  }

  public Usuario getoUsuarioModel() {
    return dadosUsuario;
  }

  public void setoUsuarioModel(Usuario dadosUsuario) {
    this.dadosUsuario = dadosUsuario;
  }

  public ArrayList<Agenda> getArrAgendaModel() {
    return listaAgenda;
  }

  public void setArrAgendaModel(ArrayList<Agenda> listaAgenda) {
    this.listaAgenda = listaAgenda;
  }
}
