package paradecision.boot.modulos.diagnostico.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.diagnostico.dto.DiagnosticoBanco;
import paradecision.boot.modulos.diagnostico.service.AcessoBancoService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class TestebdPaginaService {
  private final AcessoBancoService acessoBancoService;

  public TestebdPaginaService(AcessoBancoService acessoBancoService) {
    this.acessoBancoService = acessoBancoService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String tbd = "";

    DiagnosticoBanco abd = acessoBancoService.verificar();
    tbd = abd.mensagem();

    pagina.put("tbd", String.valueOf(tbd));

    pagina.put("abd_conFac_ipAtual", String.valueOf(abd.ipAtual()));

    pagina.put("abd_conFac_ipPrincipal", String.valueOf(abd.ipPrincipal()));

    pagina.put("abd_conFac_ipServer", String.valueOf(abd.ipServer()));

    pagina.put("abd_conFac_url", String.valueOf(abd.url()));

    return pagina;
  }
}
