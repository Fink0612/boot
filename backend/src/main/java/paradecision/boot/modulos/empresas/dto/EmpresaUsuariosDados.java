package paradecision.boot.modulos.empresas.dto;

import java.util.ArrayList;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

public class EmpresaUsuariosDados {

  private Empresa dadosEmpresa = new Empresa();
  private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
  private ArrayList<EmpresaUsuarioPerfil> listaEmpresaUsuarioPerfil =
      new ArrayList<EmpresaUsuarioPerfil>();

  public Empresa getoEmpresaModel() {
    return dadosEmpresa;
  }

  public void setoEmpresaModel(Empresa dadosEmpresa) {
    this.dadosEmpresa = dadosEmpresa;
  }

  public ArrayList<Usuario> getArrUsuarioModel() {
    return listaUsuario;
  }

  public void setArrUsuarioModel(ArrayList<Usuario> listaUsuario) {
    this.listaUsuario = listaUsuario;
  }

  public ArrayList<EmpresaUsuarioPerfil> getArrEmpresaUsuarioPerfilModel() {
    return listaEmpresaUsuarioPerfil;
  }

  public void setArrEmpresaUsuarioPerfilModel(
      ArrayList<EmpresaUsuarioPerfil> listaEmpresaUsuarioPerfil) {
    this.listaEmpresaUsuarioPerfil = listaEmpresaUsuarioPerfil;
  }
}
