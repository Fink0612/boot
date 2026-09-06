package paradecision.boot.modulos.agendas.service.pagina;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterCadastroAgendaPaginaService {
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;

  public InterCadastroAgendaPaginaService(
      AgendaService agendaService, AgendaUsuarioPerfilService agendaUsuarioPerfilService) {
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    long novoCodigo = 0;
    String a_a04_titulo = formulario.valor("a_a04_titulo");
    String a_a04_descricao = formulario.valor("a_a04_descricao");
    String a_a04_status_dt_limite = formulario.valor("a_a04_status_dt_limite");
    String a_a04_data_limite = formulario.valor("a_a04_data_limite");
    String ic_A01_CODIGO = formulario.valor("ct_A01_CODIGO");
    if (a_a04_titulo == null) a_a04_titulo = "";
    if (a_a04_descricao == null) a_a04_descricao = "";
    if (a_a04_status_dt_limite == null) a_a04_status_dt_limite = "0";
    if (a_a04_data_limite == null) a_a04_data_limite = "";
    if ("".equals(a_a04_status_dt_limite)) a_a04_status_dt_limite = "0";
    if (ic_A01_CODIGO == null) ic_A01_CODIGO = "0";
    a_a04_titulo = MetodosUteis.padronizarEspacos(a_a04_titulo);
    a_a04_descricao = MetodosUteis.padronizarEspacos(a_a04_descricao);
    Agenda oAgendaModel = new Agenda();

    oAgendaModel.setA04_titulo(a_a04_titulo);
    oAgendaModel.setA04_descricao(a_a04_descricao);
    int a_num_a04_status_dt_limite = Integer.parseInt(a_a04_status_dt_limite);
    oAgendaModel.setA04_status_dt_limite(a_num_a04_status_dt_limite);
    Date a_dt_a04_data_limite = MetodosUteis.retornaDate(a_a04_data_limite, "yyyy-MM-dd");
    oAgendaModel.setA04_data_limite(a_dt_a04_data_limite);
    long ic_num_A01_CODIGO = Long.parseLong(ic_A01_CODIGO);
    oAgendaModel.setA01_codigo(ic_num_A01_CODIGO);
    novoCodigo = agendaService.insertAgenda(oAgendaModel);
    if (novoCodigo > 0) {
      String ic_A02_CODIGO = formulario.valor("ct_A02_CODIGO");
      if (ic_A02_CODIGO == null) ic_A02_CODIGO = "0";
      long ic_num_A02_CODIGO = Long.parseLong(ic_A02_CODIGO);
      AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();
      oAgendaUsuarioPerfilModel.setA02_codigo(ic_num_A02_CODIGO);
      oAgendaUsuarioPerfilModel.setA04_codigo(novoCodigo);
      oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(1);
      oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(0);
      oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(0);
      oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(0);

      agendaUsuarioPerfilService.insertPerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
    }

    pagina.put("novoCodigo", String.valueOf(novoCodigo));

    return pagina;
  }
}
