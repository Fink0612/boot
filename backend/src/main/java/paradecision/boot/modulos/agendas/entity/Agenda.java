package paradecision.boot.modulos.agendas.entity;

import java.sql.Date;

public class Agenda {

  private long codigoAgenda;
  private String tituloAgenda;
  private String descricaoAgenda;
  private int statusDataLimiteAgenda;
  private Date dataLimiteAgenda;
  private String resultadoAgenda;
  private double certezaResultadoAgenda;
  private double contradicaoResultadoAgenda;
  private Date dataCadastroAgenda;
  private Date dataUltimaAlteracaoAgenda;
  private long codigoEmpresa;
  private int statusAgenda;

  public long getA04_codigo() {
    return codigoAgenda;
  }

  public void setA04_codigo(long codigoAgenda) {
    this.codigoAgenda = codigoAgenda;
  }

  public String getA04_titulo() {
    return tituloAgenda;
  }

  public void setA04_titulo(String tituloAgenda) {
    this.tituloAgenda = tituloAgenda;
  }

  public String getA04_descricao() {
    return descricaoAgenda;
  }

  public void setA04_descricao(String descricaoAgenda) {
    this.descricaoAgenda = descricaoAgenda;
  }

  public int getA04_status_dt_limite() {
    return statusDataLimiteAgenda;
  }

  public void setA04_status_dt_limite(int statusDataLimiteAgenda) {
    this.statusDataLimiteAgenda = statusDataLimiteAgenda;
  }

  public Date getA04_data_limite() {
    return dataLimiteAgenda;
  }

  public void setA04_data_limite(Date dataLimiteAgenda) {
    this.dataLimiteAgenda = dataLimiteAgenda;
  }

  public String getA04_resultado() {
    return resultadoAgenda;
  }

  public void setA04_resultado(String resultadoAgenda) {
    this.resultadoAgenda = resultadoAgenda;
  }

  public double getA04_certeza_resultado() {
    return certezaResultadoAgenda;
  }

  public void setA04_certeza_resultado(double certezaResultadoAgenda) {
    this.certezaResultadoAgenda = certezaResultadoAgenda;
  }

  public double getA04_contradicao_resultado() {
    return contradicaoResultadoAgenda;
  }

  public void setA04_contradicao_resultado(double contradicaoResultadoAgenda) {
    this.contradicaoResultadoAgenda = contradicaoResultadoAgenda;
  }

  public Date getA04_dt_cadastro() {
    return dataCadastroAgenda;
  }

  public void setA04_dt_cadastro(Date dataCadastroAgenda) {
    this.dataCadastroAgenda = dataCadastroAgenda;
  }

  public Date getA04_dt_ultima_alteracao() {
    return dataUltimaAlteracaoAgenda;
  }

  public void setA04_dt_ultima_alteracao(Date dataUltimaAlteracaoAgenda) {
    this.dataUltimaAlteracaoAgenda = dataUltimaAlteracaoAgenda;
  }

  public long getA01_codigo() {
    return codigoEmpresa;
  }

  public void setA01_codigo(long codigoEmpresa) {
    this.codigoEmpresa = codigoEmpresa;
  }

  public int getA04_status() {
    return statusAgenda;
  }

  public void setA04_status(int statusAgenda) {
    this.statusAgenda = statusAgenda;
  }
}
