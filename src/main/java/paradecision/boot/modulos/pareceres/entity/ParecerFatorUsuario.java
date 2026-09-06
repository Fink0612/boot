package paradecision.boot.modulos.pareceres.entity;

import java.sql.Date;

public class ParecerFatorUsuario {

  private long a07_codigo;
  private long a06_codigo;
  private long a02_codigo;
  private int a07_num_sequencia;
  private double a07_certeza;
  private double a07_contradicao;
  private String str_a07_certeza;
  private String str_a07_contradicao;
  private Date a07_dt_cadastro;
  private Date a07_dt_ultima_alteracao;

  public long getA07_codigo() {
    return a07_codigo;
  }

  public void setA07_codigo(long a07_codigo) {
    this.a07_codigo = a07_codigo;
  }

  public long getA06_codigo() {
    return a06_codigo;
  }

  public void setA06_codigo(long a06_codigo) {
    this.a06_codigo = a06_codigo;
  }

  public long getA02_codigo() {
    return a02_codigo;
  }

  public void setA02_codigo(long a02_codigo) {
    this.a02_codigo = a02_codigo;
  }

  public int getA07_num_sequencia() {
    return a07_num_sequencia;
  }

  public void setA07_num_sequencia(int a07_num_sequencia) {
    this.a07_num_sequencia = a07_num_sequencia;
  }

  public double getA07_certeza() {
    return a07_certeza;
  }

  public void setA07_certeza(double a07_certeza) {
    this.a07_certeza = a07_certeza;
  }

  public double getA07_contradicao() {
    return a07_contradicao;
  }

  public void setA07_contradicao(double a07_contradicao) {
    this.a07_contradicao = a07_contradicao;
  }

  public String getStr_a07_certeza() {
    return str_a07_certeza;
  }

  public void setStr_a07_certeza(String str_a07_certeza) {
    this.str_a07_certeza = str_a07_certeza;
  }

  public String getStr_a07_contradicao() {
    return str_a07_contradicao;
  }

  public void setStr_a07_contradicao(String str_a07_contradicao) {
    this.str_a07_contradicao = str_a07_contradicao;
  }

  public Date getA07_dt_cadastro() {
    return a07_dt_cadastro;
  }

  public void setA07_dt_cadastro(Date a07_dt_cadastro) {
    this.a07_dt_cadastro = a07_dt_cadastro;
  }

  public Date getA07_dt_ultima_alteracao() {
    return a07_dt_ultima_alteracao;
  }

  public void setA07_dt_ultima_alteracao(Date a07_dt_ultima_alteracao) {
    this.a07_dt_ultima_alteracao = a07_dt_ultima_alteracao;
  }
}
