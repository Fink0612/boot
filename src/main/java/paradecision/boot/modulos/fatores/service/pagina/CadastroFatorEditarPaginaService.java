package paradecision.boot.modulos.fatores.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.fatores.service.FatorService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class CadastroFatorEditarPaginaService {
  private final FatorService fatorService;

  public CadastroFatorEditarPaginaService(FatorService fatorService) {
    this.fatorService = fatorService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String okRetorno = "NOK";
    String cfe_A06_CODIGO = formulario.valor("ct_A06_CODIGO");
    long num_A06_CODIGO = MetodosUteis.retornaLong(cfe_A06_CODIGO);
    Fator oFatorModel = new Fator();

    if (num_A06_CODIGO > 0) {
      oFatorModel.setA06_codigo(num_A06_CODIGO);
      oFatorModel = fatorService.selectFator(oFatorModel);
      if (oFatorModel.getA06_titulo() == null) oFatorModel.setA06_titulo("");
      if (oFatorModel.getA06_titulo().length() > 0) {
        okRetorno = "OK";
      }
    }

    pagina.put("okRetorno", String.valueOf(okRetorno));

    pagina.put("oFatorModel_A06_titulo", String.valueOf(oFatorModel.getA06_titulo()));

    pagina.put("oFatorModel_A06_descricao", String.valueOf(oFatorModel.getA06_descricao()));

    return pagina;
  }
}
