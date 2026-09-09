package paradecision.boot.modulos.usuarios.entity;

import java.sql.Date;

public class Usuario {

  private long codigoUsuario;
  private Date dataCadastroUsuario;
  private Date dataUltimaAlteracaoUsuario;
  private String nomeUsuario;
  private String loginUsuario;
  private String senhaUsuario;
  private String codigoLinkUsuario;
  private String emailUsuario;
  private int statusUsuario;

  public long getA02_codigo() {
    return codigoUsuario;
  }

  public void setA02_codigo(long codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  public Date getA02_dt_cadastro() {
    return dataCadastroUsuario;
  }

  public void setA02_dt_cadastro(Date dataCadastroUsuario) {
    this.dataCadastroUsuario = dataCadastroUsuario;
  }

  public Date getA02_dt_ultima_alteracao() {
    return dataUltimaAlteracaoUsuario;
  }

  public void setA02_dt_ultima_alteracao(Date dataUltimaAlteracaoUsuario) {
    this.dataUltimaAlteracaoUsuario = dataUltimaAlteracaoUsuario;
  }

  public String getA02_nome() {
    return nomeUsuario;
  }

  public void setA02_nome(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
  }

  public String getA02_usuario() {
    return loginUsuario;
  }

  public void setA02_usuario(String loginUsuario) {
    this.loginUsuario = loginUsuario;
  }

  public String getA02_senha() {
    return senhaUsuario;
  }

  public void setA02_senha(String senhaUsuario) {
    this.senhaUsuario = senhaUsuario;
  }

  public String getA02_codigo_link() {
    return codigoLinkUsuario;
  }

  public void setA02_codigo_link(String codigoLinkUsuario) {
    this.codigoLinkUsuario = codigoLinkUsuario;
  }

  public String getA02_email() {
    return emailUsuario;
  }

  public void setA02_email(String emailUsuario) {
    this.emailUsuario = emailUsuario;
  }

  public int getA02_status() {
    return statusUsuario;
  }

  public void setA02_status(int statusUsuario) {
    this.statusUsuario = statusUsuario;
  }
}
