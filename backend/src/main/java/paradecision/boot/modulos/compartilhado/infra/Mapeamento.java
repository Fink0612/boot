package paradecision.boot.modulos.compartilhado.infra;
public final class Mapeamento {
  private Mapeamento() {}
  public static paradecision.boot.modulos.agendas.entity.Agenda Agenda(Registro r) {
    var v = new paradecision.boot.modulos.agendas.entity.Agenda();
    v.setA04_codigo(r.numero("a04_codigo"));
    v.setA04_titulo(r.texto("a04_titulo"));
    v.setA04_descricao(r.texto("a04_descricao"));
    v.setA04_status_dt_limite(r.inteiro("a04_status_dt_limite"));
    v.setA04_data_limite(r.data("a04_data_limite"));
    v.setA04_resultado(r.texto("a04_resultado"));
    v.setA04_certeza_resultado(r.decimal("a04_certeza_resultado"));
    v.setA04_contradicao_resultado(r.decimal("a04_contradicao_resultado"));
    v.setA04_dt_cadastro(r.data("a04_dt_cadastro"));
    v.setA04_dt_ultima_alteracao(r.data("a04_dt_ultima_alteracao"));
    v.setA01_codigo(r.numero("a01_codigo"));
    v.setA04_status(r.inteiro("a04_status"));
    return v;
  }
  public static paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil AgendaUsuarioPerfil(Registro r) {
    var v = new paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil();
    v.setA05_codigo(r.numero("a05_codigo"));
    v.setA04_codigo(r.numero("a04_codigo"));
    v.setA02_codigo(r.numero("a02_codigo"));
    v.setA05_num_sequencia(r.numero("a05_num_sequencia"));
    v.setA05_perfil_agenda_usuario_titular(r.inteiro("a05_perfil_agenda_usuario_titular"));
    v.setA05_perfil_agenda_usuario_facilitador(r.inteiro("a05_perfil_agenda_usuario_facilitador"));
    v.setA05_perfil_agenda_usuario_analista(r.inteiro("a05_perfil_agenda_usuario_analista"));
    v.setA05_dt_cadastro(r.data("a05_dt_cadastro"));
    v.setA05_dt_ultima_alteracao(r.data("a05_dt_ultima_alteracao"));
    return v;
  }
  public static paradecision.boot.modulos.empresas.entity.Empresa Empresa(Registro r) {
    var v = new paradecision.boot.modulos.empresas.entity.Empresa();
    v.setA01_codigo(r.numero("a01_codigo"));
    v.setA01_dt_cadastro(r.data("a01_dt_cadastro"));
    v.setA01_dt_ultima_alteracao(r.data("a01_dt_ultima_alteracao"));
    v.setA01_nome(r.texto("a01_nome"));
    v.setA01_descricao(r.texto("a01_descricao"));
    v.setA01_status(r.inteiro("a01_status"));
    return v;
  }
  public static paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil EmpresaUsuarioPerfil(Registro r) {
    var v = new paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil();
    v.setA01_codigo(r.numero("a01_codigo"));
    v.setA02_codigo(r.numero("a02_codigo"));
    v.setA03_dt_cadastro(r.data("a03_dt_cadastro"));
    v.setA03_perfil_paraviverbem(r.inteiro("a03_perfil_paraviverbem"));
    v.setA03_perfil_administrador(r.inteiro("a03_perfil_administrador"));
    v.setA03_perfil_chefe(r.inteiro("a03_perfil_chefe"));
    v.setA03_perfil_padrao(r.inteiro("a03_perfil_padrao"));
    return v;
  }
  public static paradecision.boot.modulos.fatores.entity.Fator Fator(Registro r) {
    var v = new paradecision.boot.modulos.fatores.entity.Fator();
    v.setA06_codigo(r.numero("a06_codigo"));
    v.setA06_titulo(r.texto("a06_titulo"));
    v.setA06_descricao(r.texto("a06_descricao"));
    v.setA06_num_sequencia(r.inteiro("a06_num_sequencia"));
    v.setA04_codigo(r.numero("a04_codigo"));
    v.setA02_codigo(r.numero("a02_codigo"));
    v.setA06_certeza_resultante_fator(r.decimal("a06_certeza_resultante_fator"));
    v.setA06_contradicao_resultante_fator(r.decimal("a06_contradicao_resultante_fator"));
    v.setA06_resultado_fator(r.texto("a06_resultado_fator"));
    v.setA06_dt_cadastro(r.data("a06_dt_cadastro"));
    v.setA06_dt_ultima_alteracao(r.data("a06_dt_ultima_alteracao"));
    return v;
  }
  public static paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario ParecerFatorUsuario(Registro r) {
    var v = new paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario();
    v.setA07_codigo(r.numero("a07_codigo"));
    v.setA06_codigo(r.numero("a06_codigo"));
    v.setA02_codigo(r.numero("a02_codigo"));
    v.setA07_num_sequencia(r.inteiro("a07_num_sequencia"));
    v.setA07_certeza(r.decimal("a07_certeza"));
    v.setA07_contradicao(r.decimal("a07_contradicao"));
    v.setA07_dt_cadastro(r.data("a07_dt_cadastro"));
    v.setA07_dt_ultima_alteracao(r.data("a07_dt_ultima_alteracao"));
    v.setStr_a07_certeza(r.texto("a07_certeza"));
    v.setStr_a07_contradicao(r.texto("a07_contradicao"));
    return v;
  }
  public static paradecision.boot.modulos.usuarios.entity.Usuario Usuario(Registro r) {
    var v = new paradecision.boot.modulos.usuarios.entity.Usuario();
    v.setA02_codigo(r.numero("a02_codigo"));
    v.setA02_dt_cadastro(r.data("a02_dt_cadastro"));
    v.setA02_dt_ultima_alteracao(r.data("a02_dt_ultima_alteracao"));
    v.setA02_nome(r.texto("a02_nome"));
    v.setA02_usuario(r.texto("a02_usuario"));
    v.setA02_senha(r.texto("a02_senha"));
    v.setA02_codigo_link(r.texto("a02_codigo_link"));
    v.setA02_email(r.texto("a02_email"));
    v.setA02_status(r.inteiro("a02_status"));
    return v;
  }
}
