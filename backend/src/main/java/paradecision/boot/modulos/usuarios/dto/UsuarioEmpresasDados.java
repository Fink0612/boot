package paradecision.boot.modulos.usuarios.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class UsuarioEmpresasDados {

  private Usuario dadosUsuario = new Usuario();
  private ArrayList<Empresa> listaEmpresa = new ArrayList<Empresa>();
  private ArrayList<EmpresaUsuarioPerfil> listaEmpresaUsuarioPerfil =
      new ArrayList<EmpresaUsuarioPerfil>();

  public Usuario getoUsuarioModel() {
    return dadosUsuario;
  }

  public void setoUsuarioModel(Usuario dadosUsuario) {
    this.dadosUsuario = dadosUsuario;
  }

  public ArrayList<Empresa> getArrEmpresaModel() {
    return listaEmpresa;
  }

  public void setArrEmpresaModel(ArrayList<Empresa> listaEmpresa) {
    this.listaEmpresa = listaEmpresa;
  }

  public ArrayList<EmpresaUsuarioPerfil> getArrEmpresaUsuarioPerfilModel() {
    return listaEmpresaUsuarioPerfil;
  }

  public void setArrEmpresaUsuarioPerfilModel(
      ArrayList<EmpresaUsuarioPerfil> listaEmpresaUsuarioPerfil) {
    this.listaEmpresaUsuarioPerfil = listaEmpresaUsuarioPerfil;
  }
}
