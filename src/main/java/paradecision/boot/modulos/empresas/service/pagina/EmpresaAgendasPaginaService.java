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

    Usuario dadosUsuario = new Usuario();
    Empresa dadosEmpresa = new Empresa();
    Agenda dadosAgenda = new Agenda();
    EmpresaAgendasDados dadosEmpresaAgendas = new EmpresaAgendasDados();

    int achouAgenda = 0;
    String perfP = formulario.valor("ct_A03_PERFIL_PARAVIVERBEM");
    String perfA = formulario.valor("ct_A03_PERFIL_ADMINISTRADOR");
    String perfC = formulario.valor("ct_A03_PERFIL_CHEFE");
    long codigoEmpresaAgendasEmpresaControle = Long.parseLong(formulario.valor("ct_A01_CODIGO"));
    String nomeEmpresaAgendasEmpresaControle = formulario.valor("ct_A01_NOME");
    long codigoUsuarioAgendasEmpresaControle = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    dadosUsuario.setA02_codigo(codigoUsuarioAgendasEmpresaControle);
    dadosEmpresa.setA01_codigo(codigoEmpresaAgendasEmpresaControle);
    dadosEmpresa.setA01_nome(nomeEmpresaAgendasEmpresaControle);
    dadosEmpresaAgendas.setoEmpresaModel(dadosEmpresa);
    dadosEmpresaAgendas.setoUsuarioModel(dadosUsuario);
    if (perfP.equals("1") || perfA.equals("1") || perfC.equals("1")) {
      dadosEmpresaAgendas = empresaAgendasService.selectAgendasDaEmpresa(dadosEmpresaAgendas);
    } else {
      dadosEmpresaAgendas =
          empresaAgendasService.selectAgendasDaEmpresaUsuario(dadosEmpresaAgendas);
    }
    if (dadosEmpresaAgendas.getArrAgendaModel().size() > 0) {
      achouAgenda = 1;
    }

    if (achouAgenda == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Agenda> listaAgenda = new ArrayList<Agenda>();
      listaAgenda = dadosEmpresaAgendas.getArrAgendaModel();
      for (int indiceElemento = 0; indiceElemento < listaAgenda.size(); indiceElemento++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        dadosAgenda = dadosEmpresaAgendas.getArrAgendaModel().get(indiceElemento);
        int numStatus = dadosAgenda.getA04_status();
        String strStatus = Integer.toString(numStatus);
        String txtStatus = MetodosUteis.retornaTxtStatusAgenda(strStatus);

        linha2.put("oAgendaModel_A04_codigo", String.valueOf(dadosAgenda.getA04_codigo()));

        linha2.put("oAgendaModel_A04_titulo", String.valueOf(dadosAgenda.getA04_titulo()));

        linha2.put("oAgendaModel_A04_status", String.valueOf(dadosAgenda.getA04_status()));

        linha2.put("oAgendaModel_A04_titulo2", String.valueOf(dadosAgenda.getA04_titulo()));

        linha2.put("oAgendaModel_A04_descricao", String.valueOf(dadosAgenda.getA04_descricao()));

        linha2.put("txtStatus", String.valueOf(txtStatus));
      }
    }

    return pagina;
  }
}
