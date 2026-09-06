package paradecision.boot.modulos.empresas.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.empresas.dto.EmpresaAgendasDados;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.service.EmpresaAgendasService;
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class EmpresaAgendasPaginaService {
  private final EmpresaAgendasService empresaAgendasService;

  public EmpresaAgendasPaginaService(EmpresaAgendasService empresaAgendasService) {
    this.empresaAgendasService = empresaAgendasService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Usuario oUsuarioModel = new Usuario();
    Empresa oEmpresaModel = new Empresa();
    Agenda oAgendaModel = new Agenda();
    EmpresaAgendasDados oEmpresaAgendasModel = new EmpresaAgendasDados();

    int achouAgenda = 0;
    String perfP = formulario.valor("ct_A03_PERFIL_PARAVIVERBEM");
    String perfA = formulario.valor("ct_A03_PERFIL_ADMINISTRADOR");
    String perfC = formulario.valor("ct_A03_PERFIL_CHEFE");
    long ea_ct_A01_CODIGO = Long.parseLong(formulario.valor("ct_A01_CODIGO"));
    String ea_ct_A01_NOME = formulario.valor("ct_A01_NOME");
    long ea_ct_A02_CODIGO = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    oUsuarioModel.setA02_codigo(ea_ct_A02_CODIGO);
    oEmpresaModel.setA01_codigo(ea_ct_A01_CODIGO);
    oEmpresaModel.setA01_nome(ea_ct_A01_NOME);
    oEmpresaAgendasModel.setoEmpresaModel(oEmpresaModel);
    oEmpresaAgendasModel.setoUsuarioModel(oUsuarioModel);
    if (perfP.equals("1") || perfA.equals("1") || perfC.equals("1")) {
      oEmpresaAgendasModel = empresaAgendasService.selectAgendasDaEmpresa(oEmpresaAgendasModel);
    } else {
      oEmpresaAgendasModel =
          empresaAgendasService.selectAgendasDaEmpresaUsuario(oEmpresaAgendasModel);
    }
    if (oEmpresaAgendasModel.getArrAgendaModel().size() > 0) {
      achouAgenda = 1;
    }

    if (achouAgenda == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Agenda> arrAgendaModel = new ArrayList<Agenda>();
      arrAgendaModel = oEmpresaAgendasModel.getArrAgendaModel();
      for (int i = 0; i < arrAgendaModel.size(); i++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        oAgendaModel = oEmpresaAgendasModel.getArrAgendaModel().get(i);
        int numStatus = oAgendaModel.getA04_status();
        String strStatus = Integer.toString(numStatus);
        String txtStatus = MetodosUteis.retornaTxtStatusAgenda(strStatus);

        linha2.put("oAgendaModel_A04_codigo", String.valueOf(oAgendaModel.getA04_codigo()));

        linha2.put("oAgendaModel_A04_titulo", String.valueOf(oAgendaModel.getA04_titulo()));

        linha2.put("oAgendaModel_A04_status", String.valueOf(oAgendaModel.getA04_status()));

        linha2.put("oAgendaModel_A04_titulo2", String.valueOf(oAgendaModel.getA04_titulo()));

        linha2.put("oAgendaModel_A04_descricao", String.valueOf(oAgendaModel.getA04_descricao()));

        linha2.put("txtStatus", String.valueOf(txtStatus));
      }
    }

    return pagina;
  }
}
