package paradecision.boot.modulos.usuarios.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class UsuarioEmpresasDados {

  private Usuario oUsuarioModel = new Usuario();
  private ArrayList<Empresa> arrEmpresaModel = new ArrayList<Empresa>();
  private ArrayList<EmpresaUsuarioPerfil> arrEmpresaUsuarioPerfilModel =
      new ArrayList<EmpresaUsuarioPerfil>();

  public Usuario getoUsuarioModel() {
    return oUsuarioModel;
  }

  public void setoUsuarioModel(Usuario oUsuarioModel) {
    this.oUsuarioModel = oUsuarioModel;
  }

  public ArrayList<Empresa> getArrEmpresaModel() {
    return arrEmpresaModel;
  }

  public void setArrEmpresaModel(ArrayList<Empresa> arrEmpresaModel) {
    this.arrEmpresaModel = arrEmpresaModel;
  }

  public ArrayList<EmpresaUsuarioPerfil> getArrEmpresaUsuarioPerfilModel() {
    return arrEmpresaUsuarioPerfilModel;
  }

  public void setArrEmpresaUsuarioPerfilModel(
      ArrayList<EmpresaUsuarioPerfil> arrEmpresaUsuarioPerfilModel) {
    this.arrEmpresaUsuarioPerfilModel = arrEmpresaUsuarioPerfilModel;
  }
}
