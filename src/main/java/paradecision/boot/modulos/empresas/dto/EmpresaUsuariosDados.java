package paradecision.boot.modulos.empresas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class EmpresaUsuariosDados {

  private Empresa oEmpresaModel = new Empresa();
  private ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
  private ArrayList<EmpresaUsuarioPerfil> arrEmpresaUsuarioPerfilModel =
      new ArrayList<EmpresaUsuarioPerfil>();

  public Empresa getoEmpresaModel() {
    return oEmpresaModel;
  }

  public void setoEmpresaModel(Empresa oEmpresaModel) {
    this.oEmpresaModel = oEmpresaModel;
  }

  public ArrayList<Usuario> getArrUsuarioModel() {
    return arrUsuarioModel;
  }

  public void setArrUsuarioModel(ArrayList<Usuario> arrUsuarioModel) {
    this.arrUsuarioModel = arrUsuarioModel;
  }

  public ArrayList<EmpresaUsuarioPerfil> getArrEmpresaUsuarioPerfilModel() {
    return arrEmpresaUsuarioPerfilModel;
  }

  public void setArrEmpresaUsuarioPerfilModel(
      ArrayList<EmpresaUsuarioPerfil> arrEmpresaUsuarioPerfilModel) {
    this.arrEmpresaUsuarioPerfilModel = arrEmpresaUsuarioPerfilModel;
  }
}
