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
public class InterCadastroFatorEditarPaginaService {
  private final FatorService fatorService;

  public InterCadastroFatorEditarPaginaService(FatorService fatorService) {
    this.fatorService = fatorService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String operacaoConcluida = "NOK";
    String codigoFatorCadastro = formulario.valor("ct_A06_CODIGO");
    String tituloFatorFormularioFator = formulario.valor("f_a06_titulo");
    String descricaoFatorFormularioFator = formulario.valor("f_a06_descricao");
    if (codigoFatorCadastro == null) codigoFatorCadastro = "0";
    if (tituloFatorFormularioFator == null) tituloFatorFormularioFator = "";
    if (descricaoFatorFormularioFator == null) descricaoFatorFormularioFator = "";
    long codigoFatorNumerico = MetodosUteis.retornaLong(codigoFatorCadastro);
    tituloFatorFormularioFator = MetodosUteis.padronizarEspacos(tituloFatorFormularioFator);
    descricaoFatorFormularioFator = MetodosUteis.padronizarEspacos(descricaoFatorFormularioFator);
    Fator dadosFator = new Fator();

    dadosFator.setA06_codigo(codigoFatorNumerico);
    dadosFator.setA06_titulo(tituloFatorFormularioFator);
    dadosFator.setA06_descricao(descricaoFatorFormularioFator);
    operacaoConcluida = fatorService.updateFator(dadosFator);

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    return pagina;
  }
}
