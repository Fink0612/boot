package paradecision.boot.modulos.agendas.entity;

import java.sql.Date;

public class AgendaUsuarioPerfil {

  private long a05_codigo;
  private long a04_codigo;
  private long a02_codigo;
  private long a05_num_sequencia;
  private int a05_perfil_agenda_usuario_titular;
  private int a05_perfil_agenda_usuario_facilitador;
  private int a05_perfil_agenda_usuario_especialista;
  private int a05_perfil_agenda_usuario_analista;
  private Date a05_dt_cadastro;
  private Date a05_dt_ultima_alteracao;

  public long getA05_codigo() {
    return a05_codigo;
  }

  public void setA05_codigo(long a05_codigo) {
    this.a05_codigo = a05_codigo;
  }

  public long getA04_codigo() {
    return a04_codigo;
  }

  public void setA04_codigo(long a04_codigo) {
    this.a04_codigo = a04_codigo;
  }

  public long getA02_codigo() {
    return a02_codigo;
  }

  public void setA02_codigo(long a02_codigo) {
    this.a02_codigo = a02_codigo;
  }

  public long getA05_num_sequencia() {
    return a05_num_sequencia;
  }

  public void setA05_num_sequencia(long a05_num_sequencia) {
    this.a05_num_sequencia = a05_num_sequencia;
  }

  public int getA05_perfil_agenda_usuario_titular() {
    return a05_perfil_agenda_usuario_titular;
  }

  public void setA05_perfil_agenda_usuario_titular(int a05_perfil_agenda_usuario_titular) {
    this.a05_perfil_agenda_usuario_titular = a05_perfil_agenda_usuario_titular;
  }

  public int getA05_perfil_agenda_usuario_facilitador() {
    return a05_perfil_agenda_usuario_facilitador;
  }

  public void setA05_perfil_agenda_usuario_facilitador(int a05_perfil_agenda_usuario_facilitador) {
    this.a05_perfil_agenda_usuario_facilitador = a05_perfil_agenda_usuario_facilitador;
  }

  public int getA05_perfil_agenda_usuario_especialista() {
    return a05_perfil_agenda_usuario_especialista;
  }

  public void setA05_perfil_agenda_usuario_especialista(
      int a05_perfil_agenda_usuario_especialista) {
    this.a05_perfil_agenda_usuario_especialista = a05_perfil_agenda_usuario_especialista;
  }

  public int getA05_perfil_agenda_usuario_analista() {
    return a05_perfil_agenda_usuario_analista;
  }

  public void setA05_perfil_agenda_usuario_analista(int a05_perfil_agenda_usuario_analista) {
    this.a05_perfil_agenda_usuario_analista = a05_perfil_agenda_usuario_analista;
  }

  public Date getA05_dt_cadastro() {
    return a05_dt_cadastro;
  }

  public void setA05_dt_cadastro(Date a05_dt_cadastro) {
    this.a05_dt_cadastro = a05_dt_cadastro;
  }

  public Date getA05_dt_ultima_alteracao() {
    return a05_dt_ultima_alteracao;
  }

  public void setA05_dt_ultima_alteracao(Date a05_dt_ultima_alteracao) {
    this.a05_dt_ultima_alteracao = a05_dt_ultima_alteracao;
  }
}
