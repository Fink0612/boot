package paradecision.boot.modulos.agendas.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.agendas.dto.AgendaUsuariosDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaService;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;
import paradecision.boot.modulos.agendas.service.AgendaUsuariosService;
import paradecision.boot.modulos.empresas.dto.EmpresaUsuariosDados;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.service.EmpresaUsuariosService;
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class AgendaUsuariosEditarPaginaService {
  private final AgendaService agendaService;
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;
  private final AgendaUsuariosService agendaUsuariosService;
  private final EmpresaUsuariosService empresaUsuariosService;

  public AgendaUsuariosEditarPaginaService(
      AgendaService agendaService,
      AgendaUsuarioPerfilService agendaUsuarioPerfilService,
      AgendaUsuariosService agendaUsuariosService,
      EmpresaUsuariosService empresaUsuariosService) {
    this.agendaService = agendaService;
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
    this.agendaUsuariosService = agendaUsuariosService;
    this.empresaUsuariosService = empresaUsuariosService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Empresa oEmpresaModel = new Empresa();
    Agenda oAgendaModel = new Agenda();
    // AgendaService oAgendaControl = agendaService;
    Usuario oEmpUsuarioModel = new Usuario();
    Usuario oAgeUsuarioModel = new Usuario();
    AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();
    // AgendaUsuarioPerfilService oAgendaUsuarioPerfilControl = agendaUsuarioPerfilService;
    EmpresaUsuariosDados oEmpresaUsuariosModel = new EmpresaUsuariosDados();

    AgendaUsuariosDados oAgendaUsuariosModel = new AgendaUsuariosDados();

    // ..Capturando dados da Agenda
    long au_ct_A04_CODIGO = Long.parseLong(formulario.valor("ct_A04_CODIGO"));
    // String au_A04_TITULO = "";
    // String au_A04_STATUS_DT_LIMITE = "";
    // String au_A04_DATA_LIMITE = "";
    // String au_A04_STATUS = "";
    oAgendaModel.setA04_codigo(au_ct_A04_CODIGO);
    // oAgendaModel = oAgendaControl.selectAgenda(oAgendaModel);
    // try {
    //	if (oAgendaModel.getA01_codigo() > 0) {
    //		au_A04_TITULO = oAgendaModel.getA04_titulo();
    //		au_A04_STATUS_DT_LIMITE = Long.toString(oAgendaModel.getA04_status_dt_limite());
    //		au_A04_DATA_LIMITE = oAgendaModel.getA04_data_limite().toString();
    //		au_A04_STATUS = Integer.toString(oAgendaModel.getA04_status());
    //	}
    // } catch (Exception e) {
    // }
    // ..Capturando dados do Usuário Atual em relação à Agenda
    long au_ct_A02_CODIGO = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    // int au_A05_PERFIL_TITULAR = 0;
    // int au_A05_PERFIL_FACILITADOR = 0;
    // int au_A05_PERFIL_ESPECIALISTA = 0;
    // int au_A05_PERFIL_ANALISTA = 0;
    oAgendaUsuarioPerfilModel.setA02_codigo(au_ct_A02_CODIGO);
    oAgendaUsuarioPerfilModel.setA04_codigo(au_ct_A04_CODIGO);
    // oAgendaUsuarioPerfilModel =
    // try {
    //	if (oAgendaUsuarioPerfilModel.getA05_codigo() > 0) {
    //		au_A05_PERFIL_TITULAR = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular();
    //		au_A05_PERFIL_FACILITADOR =
    //		au_A05_PERFIL_ESPECIALISTA =
    //		au_A05_PERFIL_ANALISTA = oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista();
    //	}
    // } catch (Exception e) {
    // }
    // ..Capturando os Usuários da Empresa (para permitir seleção)
    int achouUsuariosEmpresa = 0;
    long aue_ct_A01_CODIGO = Long.parseLong(formulario.valor("ct_A01_CODIGO"));
    oEmpresaModel.setA01_codigo(aue_ct_A01_CODIGO);
    oEmpresaUsuariosModel.setoEmpresaModel(oEmpresaModel);
    oEmpresaUsuariosModel = empresaUsuariosService.selectUsuariosDaEmpresa(oEmpresaUsuariosModel);
    if (oEmpresaUsuariosModel.getArrUsuarioModel().size() > 0) {
      achouUsuariosEmpresa = 1;
    }
    int achouUsuariosAgenda = 0;
    oAgendaUsuariosModel.setoAgendaModel(oAgendaModel);
    oAgendaUsuariosModel = agendaUsuariosService.selectUsuariosDaAgenda(oAgendaUsuariosModel);
    if (oAgendaUsuariosModel.getArrUsuarioModel().size() > 0) {
      achouUsuariosAgenda = 1;
    }

    int qtdUsuariosEmpresa = 0;
    if (achouUsuariosEmpresa == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Usuario> arrEmpUsuariosModel = new ArrayList<Usuario>();
      arrEmpUsuariosModel = oEmpresaUsuariosModel.getArrUsuarioModel();
      ArrayList<Usuario> arrAgeUsuariosModel = new ArrayList<Usuario>();
      arrAgeUsuariosModel = oAgendaUsuariosModel.getArrUsuarioModel();
      String checkUsu = "";
      String checkT = "";
      String checkF = "";
      String checkE = "";
      String checkA = "";
      String ncheckT = "";
      String ncheckF = "";
      String ncheckE = "";
      String ncheckA = "";
      qtdUsuariosEmpresa = arrEmpUsuariosModel.size();
      for (int emp = 0; emp < qtdUsuariosEmpresa; emp++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        int ageArrLoc = -1;
        long val_a02_codigo = -1;
        long val_a05_codigo = -1;
        int val_usu_marcado = 0;
        oEmpUsuarioModel = oEmpresaUsuariosModel.getArrUsuarioModel().get(emp);
        val_a02_codigo = oEmpUsuarioModel.getA02_codigo();
        for (int age = 0; age < arrAgeUsuariosModel.size(); age++) {
          oAgeUsuarioModel = oAgendaUsuariosModel.getArrUsuarioModel().get(age);
          if (oEmpUsuarioModel.getA02_codigo() == oAgeUsuarioModel.getA02_codigo()) {
            ageArrLoc = age;
          }
        }
        checkUsu = "";
        checkT = "";
        checkF = "";
        checkE = "";
        checkA = "";
        ncheckT = "0";
        ncheckF = "0";
        ncheckE = "0";
        ncheckA = "0";
        if (ageArrLoc != -1) {
          val_usu_marcado = 1;
          String txtChecked = "checked=\"checked\" ";
          checkUsu = txtChecked;
          oAgendaUsuarioPerfilModel =
              oAgendaUsuariosModel.getArrAgendaUsuarioPerfilModel().get(ageArrLoc);
          val_a05_codigo = oAgendaUsuarioPerfilModel.getA05_codigo();
          if (oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular() == 1) {
            checkT = txtChecked;
            ncheckT = "1";
          }
          if (oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador() == 1) {
            checkF = txtChecked;
            ncheckF = "1";
          }
          if (oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista() == 1) {
            checkE = txtChecked;
            ncheckE = "1";
          }
          if (oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista() == 1) {
            checkA = txtChecked;
            ncheckA = "1";
          }
        }

        linha2.put("emp", String.valueOf(emp));

        linha2.put("val_usu_marcado", String.valueOf(val_usu_marcado));

        linha2.put("emp2", String.valueOf(emp));

        linha2.put("val_a02_codigo", String.valueOf(val_a02_codigo));

        linha2.put("emp3", String.valueOf(emp));

        linha2.put("val_a05_codigo", String.valueOf(val_a05_codigo));

        linha2.put("emp4", String.valueOf(emp));

        linha2.put("checkUsu", String.valueOf(checkUsu));

        linha2.put("oEmpUsuarioModel_A02_nome", String.valueOf(oEmpUsuarioModel.getA02_nome()));

        linha2.put("emp5", String.valueOf(emp));

        linha2.put("ncheckT", String.valueOf(ncheckT));

        linha2.put("emp6", String.valueOf(emp));

        linha2.put("checkT", String.valueOf(checkT));

        linha2.put("ncheckT2", String.valueOf(ncheckT));

        linha2.put("emp7", String.valueOf(emp));

        linha2.put("ncheckF", String.valueOf(ncheckF));

        linha2.put("emp8", String.valueOf(emp));

        linha2.put("checkF", String.valueOf(checkF));

        linha2.put("emp9", String.valueOf(emp));

        linha2.put("ncheckE", String.valueOf(ncheckE));

        linha2.put("emp10", String.valueOf(emp));

        linha2.put("checkE", String.valueOf(checkE));

        linha2.put("emp11", String.valueOf(emp));

        linha2.put("ncheckA", String.valueOf(ncheckA));

        linha2.put("emp12", String.valueOf(emp));

        linha2.put("checkA", String.valueOf(checkA));
      }
    } // fechando o for e o if acima
    pagina.put("qtdUsuariosEmpresa", String.valueOf(qtdUsuariosEmpresa));

    return pagina;
  }
}
