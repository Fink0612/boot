package paradecision.boot.modulos.empresas.entity;

import java.sql.Date;

public class Empresa {

  private long codigoEmpresa;
  private Date dataCadastroEmpresa;
  private Date dataUltimaAlteracaoEmpresa;
  private String nomeEmpresa;
  private String descricaoEmpresa;
  private int statusEmpresa;

  public long getA01_codigo() {
    return codigoEmpresa;
  }

  public void setA01_codigo(long codigoEmpresa) {
    this.codigoEmpresa = codigoEmpresa;
  }

  public Date getA01_dt_cadastro() {
    return dataCadastroEmpresa;
  }

  public void setA01_dt_cadastro(Date dataCadastroEmpresa) {
    this.dataCadastroEmpresa = dataCadastroEmpresa;
  }

  public Date getA01_dt_ultima_alteracao() {
    return dataUltimaAlteracaoEmpresa;
  }

  public void setA01_dt_ultima_alteracao(Date dataUltimaAlteracaoEmpresa) {
    this.dataUltimaAlteracaoEmpresa = dataUltimaAlteracaoEmpresa;
  }

  public String getA01_nome() {
    return nomeEmpresa;
  }

  public void setA01_nome(String nomeEmpresa) {
    this.nomeEmpresa = nomeEmpresa;
  }

  public String getA01_descricao() {
    return descricaoEmpresa;
  }

  public void setA01_descricao(String descricaoEmpresa) {
    this.descricaoEmpresa = descricaoEmpresa;
  }

  public int getA01_status() {
    return statusEmpresa;
  }

  public void setA01_status(int statusEmpresa) {
    this.statusEmpresa = statusEmpresa;
  }
}
