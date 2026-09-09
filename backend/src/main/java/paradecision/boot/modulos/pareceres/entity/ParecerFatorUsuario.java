package paradecision.boot.modulos.pareceres.entity;

import java.sql.Date;

public class ParecerFatorUsuario {

  private long codigoParecer;
  private long codigoFator;
  private long codigoUsuario;
  private int numeroSequenciaParecer;
  private double certezaParecer;
  private double contradicaoParecer;
  private String certezaParecerTexto;
  private String contradicaoParecerTexto;
  private Date dataCadastroParecer;
  private Date dataUltimaAlteracaoParecer;

  public long getA07_codigo() {
    return codigoParecer;
  }

  public void setA07_codigo(long codigoParecer) {
    this.codigoParecer = codigoParecer;
  }

  public long getA06_codigo() {
    return codigoFator;
  }

  public void setA06_codigo(long codigoFator) {
    this.codigoFator = codigoFator;
  }

  public long getA02_codigo() {
    return codigoUsuario;
  }

  public void setA02_codigo(long codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  public int getA07_num_sequencia() {
    return numeroSequenciaParecer;
  }

  public void setA07_num_sequencia(int numeroSequenciaParecer) {
    this.numeroSequenciaParecer = numeroSequenciaParecer;
  }

  public double getA07_certeza() {
    return certezaParecer;
  }

  public void setA07_certeza(double certezaParecer) {
    this.certezaParecer = certezaParecer;
  }

  public double getA07_contradicao() {
    return contradicaoParecer;
  }

  public void setA07_contradicao(double contradicaoParecer) {
    this.contradicaoParecer = contradicaoParecer;
  }

  public String getStr_a07_certeza() {
    return certezaParecerTexto;
  }

  public void setStr_a07_certeza(String certezaParecerTexto) {
    this.certezaParecerTexto = certezaParecerTexto;
  }

  public String getStr_a07_contradicao() {
    return contradicaoParecerTexto;
  }

  public void setStr_a07_contradicao(String contradicaoParecerTexto) {
    this.contradicaoParecerTexto = contradicaoParecerTexto;
  }

  public Date getA07_dt_cadastro() {
    return dataCadastroParecer;
  }

  public void setA07_dt_cadastro(Date dataCadastroParecer) {
    this.dataCadastroParecer = dataCadastroParecer;
  }

  public Date getA07_dt_ultima_alteracao() {
    return dataUltimaAlteracaoParecer;
  }

  public void setA07_dt_ultima_alteracao(Date dataUltimaAlteracaoParecer) {
    this.dataUltimaAlteracaoParecer = dataUltimaAlteracaoParecer;
  }
}
