package paradecision.boot.modulos.fatores.entity;

import java.sql.Date;

public class Fator {

  private long codigoFator;
  private String tituloFator;
  private String descricaoFator;
  private int numeroSequenciaFator;
  private long codigoAgenda;
  private long codigoUsuario;
  private double certezaResultanteFatorFator;
  private double contradicaoResultanteFatorFator;
  private String resultadoFatorFator;
  private Date dataCadastroFator;
  private Date dataUltimaAlteracaoFator;

  public long getA06_codigo() {
    return codigoFator;
  }

  public void setA06_codigo(long codigoFator) {
    this.codigoFator = codigoFator;
  }

  public String getA06_titulo() {
    return tituloFator;
  }

  public void setA06_titulo(String tituloFator) {
    this.tituloFator = tituloFator;
  }

  public String getA06_descricao() {
    return descricaoFator;
  }

  public void setA06_descricao(String descricaoFator) {
    this.descricaoFator = descricaoFator;
  }

  public int getA06_num_sequencia() {
    return numeroSequenciaFator;
  }

  public void setA06_num_sequencia(int numeroSequenciaFator) {
    this.numeroSequenciaFator = numeroSequenciaFator;
  }

  public long getA04_codigo() {
    return codigoAgenda;
  }

  public void setA04_codigo(long codigoAgenda) {
    this.codigoAgenda = codigoAgenda;
  }

  public long getA02_codigo() {
    return codigoUsuario;
  }

  public void setA02_codigo(long codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  public double getA06_certeza_resultante_fator() {
    return certezaResultanteFatorFator;
  }

  public void setA06_certeza_resultante_fator(double certezaResultanteFatorFator) {
    this.certezaResultanteFatorFator = certezaResultanteFatorFator;
  }

  public double getA06_contradicao_resultante_fator() {
    return contradicaoResultanteFatorFator;
  }

  public void setA06_contradicao_resultante_fator(double contradicaoResultanteFatorFator) {
    this.contradicaoResultanteFatorFator = contradicaoResultanteFatorFator;
  }

  public String getA06_resultado_fator() {
    return resultadoFatorFator;
  }

  public void setA06_resultado_fator(String resultadoFatorFator) {
    this.resultadoFatorFator = resultadoFatorFator;
  }

  public Date getA06_dt_cadastro() {
    return dataCadastroFator;
  }

  public void setA06_dt_cadastro(Date dataCadastroFator) {
    this.dataCadastroFator = dataCadastroFator;
  }

  public Date getA06_dt_ultima_alteracao() {
    return dataUltimaAlteracaoFator;
  }

  public void setA06_dt_ultima_alteracao(Date dataUltimaAlteracaoFator) {
    this.dataUltimaAlteracaoFator = dataUltimaAlteracaoFator;
  }
}
