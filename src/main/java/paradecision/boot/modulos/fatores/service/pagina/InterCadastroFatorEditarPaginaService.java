package paradecision.boot.modulos.fatores.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.util.MetodosUteis;
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

    String okMetodo = "NOK";
    String ic_A06_CODIGO = formulario.valor("ct_A06_CODIGO");
    String f_a06_titulo = formulario.valor("f_a06_titulo");
    String f_a06_descricao = formulario.valor("f_a06_descricao");
    if (ic_A06_CODIGO == null) ic_A06_CODIGO = "0";
    if (f_a06_titulo == null) f_a06_titulo = "";
    if (f_a06_descricao == null) f_a06_descricao = "";
    long num_a06_codigo = MetodosUteis.retornaLong(ic_A06_CODIGO);
    f_a06_titulo = MetodosUteis.padronizarEspacos(f_a06_titulo);
    f_a06_descricao = MetodosUteis.padronizarEspacos(f_a06_descricao);
    Fator oFatorModel = new Fator();

    oFatorModel.setA06_codigo(num_a06_codigo);
    oFatorModel.setA06_titulo(f_a06_titulo);
    oFatorModel.setA06_descricao(f_a06_descricao);
    okMetodo = fatorService.updateFator(oFatorModel);

    pagina.put("okMetodo", String.valueOf(okMetodo));

    return pagina;
  }
}
