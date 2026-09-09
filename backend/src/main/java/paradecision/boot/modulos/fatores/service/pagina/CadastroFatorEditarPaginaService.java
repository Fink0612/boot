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

    String retornoValido = "NOK";
    String codigoFatorEdicaoFator = formulario.valor("ct_A06_CODIGO");
    long codigoFatorNumerico = MetodosUteis.retornaLong(codigoFatorEdicaoFator);
    Fator dadosFator = new Fator();

    if (codigoFatorNumerico > 0) {
      dadosFator.setA06_codigo(codigoFatorNumerico);
      dadosFator = fatorService.selectFator(dadosFator);
      if (dadosFator.getA06_titulo() == null) dadosFator.setA06_titulo("");
      if (dadosFator.getA06_titulo().length() > 0) {
        retornoValido = "OK";
      }
    }

    pagina.put("okRetorno", String.valueOf(retornoValido));

    pagina.put("oFatorModel_A06_titulo", String.valueOf(dadosFator.getA06_titulo()));

    pagina.put("oFatorModel_A06_descricao", String.valueOf(dadosFator.getA06_descricao()));

    return pagina;
  }
}
