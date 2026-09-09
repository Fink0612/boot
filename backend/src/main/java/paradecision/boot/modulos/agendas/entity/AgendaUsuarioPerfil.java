package paradecision.boot.modulos.agendas.entity;

import java.sql.Date;

public class AgendaUsuarioPerfil {

  private long codigoParticipacaoAgenda;
  private long codigoAgenda;
  private long codigoUsuario;
  private long numeroSequenciaParticipacaoAgenda;
  private int perfilAgendaUsuarioTitularParticipacaoAgenda;
  private int perfilAgendaUsuarioFacilitadorParticipacaoAgenda;
  private int perfilAgendaUsuarioEspecialistaParticipacaoAgenda;
  private int perfilAgendaUsuarioAnalistaParticipacaoAgenda;
  private Date dataCadastroParticipacaoAgenda;
  private Date dataUltimaAlteracaoParticipacaoAgenda;

  public long getA05_codigo() {
    return codigoParticipacaoAgenda;
  }

  public void setA05_codigo(long codigoParticipacaoAgenda) {
    this.codigoParticipacaoAgenda = codigoParticipacaoAgenda;
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

  public long getA05_num_sequencia() {
    return numeroSequenciaParticipacaoAgenda;
  }

  public void setA05_num_sequencia(long numeroSequenciaParticipacaoAgenda) {
    this.numeroSequenciaParticipacaoAgenda = numeroSequenciaParticipacaoAgenda;
  }

  public int getA05_perfil_agenda_usuario_titular() {
    return perfilAgendaUsuarioTitularParticipacaoAgenda;
  }

  public void setA05_perfil_agenda_usuario_titular(int perfilAgendaUsuarioTitularParticipacaoAgenda) {
    this.perfilAgendaUsuarioTitularParticipacaoAgenda = perfilAgendaUsuarioTitularParticipacaoAgenda;
  }

  public int getA05_perfil_agenda_usuario_facilitador() {
    return perfilAgendaUsuarioFacilitadorParticipacaoAgenda;
  }

  public void setA05_perfil_agenda_usuario_facilitador(int perfilAgendaUsuarioFacilitadorParticipacaoAgenda) {
    this.perfilAgendaUsuarioFacilitadorParticipacaoAgenda = perfilAgendaUsuarioFacilitadorParticipacaoAgenda;
  }

  public int getA05_perfil_agenda_usuario_especialista() {
    return perfilAgendaUsuarioEspecialistaParticipacaoAgenda;
  }

  public void setA05_perfil_agenda_usuario_especialista(
      int perfilAgendaUsuarioEspecialistaParticipacaoAgenda) {
    this.perfilAgendaUsuarioEspecialistaParticipacaoAgenda = perfilAgendaUsuarioEspecialistaParticipacaoAgenda;
  }

  public int getA05_perfil_agenda_usuario_analista() {
    return perfilAgendaUsuarioAnalistaParticipacaoAgenda;
  }

  public void setA05_perfil_agenda_usuario_analista(int perfilAgendaUsuarioAnalistaParticipacaoAgenda) {
    this.perfilAgendaUsuarioAnalistaParticipacaoAgenda = perfilAgendaUsuarioAnalistaParticipacaoAgenda;
  }

  public Date getA05_dt_cadastro() {
    return dataCadastroParticipacaoAgenda;
  }

  public void setA05_dt_cadastro(Date dataCadastroParticipacaoAgenda) {
    this.dataCadastroParticipacaoAgenda = dataCadastroParticipacaoAgenda;
  }

  public Date getA05_dt_ultima_alteracao() {
    return dataUltimaAlteracaoParticipacaoAgenda;
  }

  public void setA05_dt_ultima_alteracao(Date dataUltimaAlteracaoParticipacaoAgenda) {
    this.dataUltimaAlteracaoParticipacaoAgenda = dataUltimaAlteracaoParticipacaoAgenda;
  }
}
