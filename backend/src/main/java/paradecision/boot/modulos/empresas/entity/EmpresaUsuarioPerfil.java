package paradecision.boot.modulos.empresas.entity;

import java.sql.Date;

public class EmpresaUsuarioPerfil {

  private long codigoEmpresa;
  private long codigoUsuario;
  private Date dataCadastroPerfilEmpresaUsuario;
  private int perfilParaviverbemPerfilEmpresaUsuario;
  private int perfilAdministradorPerfilEmpresaUsuario;
  private int perfilChefePerfilEmpresaUsuario;
  private int perfilPadraoPerfilEmpresaUsuario;

  public long getA01_codigo() {
    return codigoEmpresa;
  }

  public void setA01_codigo(long codigoEmpresa) {
    this.codigoEmpresa = codigoEmpresa;
  }

  public long getA02_codigo() {
    return codigoUsuario;
  }

  public void setA02_codigo(long codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  public Date getA03_dt_cadastro() {
    return dataCadastroPerfilEmpresaUsuario;
  }

  public void setA03_dt_cadastro(Date dataCadastroPerfilEmpresaUsuario) {
    this.dataCadastroPerfilEmpresaUsuario = dataCadastroPerfilEmpresaUsuario;
  }

  public int getA03_perfil_paraviverbem() {
    return perfilParaviverbemPerfilEmpresaUsuario;
  }

  public void setA03_perfil_paraviverbem(int perfilParaviverbemPerfilEmpresaUsuario) {
    this.perfilParaviverbemPerfilEmpresaUsuario = perfilParaviverbemPerfilEmpresaUsuario;
  }

  public int getA03_perfil_administrador() {
    return perfilAdministradorPerfilEmpresaUsuario;
  }

  public void setA03_perfil_administrador(int perfilAdministradorPerfilEmpresaUsuario) {
    this.perfilAdministradorPerfilEmpresaUsuario = perfilAdministradorPerfilEmpresaUsuario;
  }

  public int getA03_perfil_chefe() {
    return perfilChefePerfilEmpresaUsuario;
  }

  public void setA03_perfil_chefe(int perfilChefePerfilEmpresaUsuario) {
    this.perfilChefePerfilEmpresaUsuario = perfilChefePerfilEmpresaUsuario;
  }

  public int getA03_perfil_padrao() {
    return perfilPadraoPerfilEmpresaUsuario;
  }

  public void setA03_perfil_padrao(int perfilPadraoPerfilEmpresaUsuario) {
    this.perfilPadraoPerfilEmpresaUsuario = perfilPadraoPerfilEmpresaUsuario;
  }
}
