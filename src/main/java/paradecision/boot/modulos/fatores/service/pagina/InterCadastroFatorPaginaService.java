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
public class InterCadastroFatorPaginaService {
  private final FatorService fatorService;

  public InterCadastroFatorPaginaService(FatorService fatorService) {
    this.fatorService = fatorService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String okMetodo = "NOK";
    String f_a06_titulo = formulario.valor("f_a06_titulo");
    String f_a06_descricao = formulario.valor("f_a06_descricao");
    String ic_A02_CODIGO = formulario.valor("ct_A02_CODIGO");
    String ic_A04_CODIGO = formulario.valor("ct_A04_CODIGO");
    if (f_a06_titulo == null) f_a06_titulo = "";
    if (f_a06_descricao == null) f_a06_descricao = "";
    if (ic_A02_CODIGO == null) ic_A02_CODIGO = "0";
    if (ic_A04_CODIGO == null) ic_A04_CODIGO = "0";
    f_a06_titulo = MetodosUteis.padronizarEspacos(f_a06_titulo);
    f_a06_descricao = MetodosUteis.padronizarEspacos(f_a06_descricao);
    long num_a02_codigo = MetodosUteis.retornaLong(ic_A02_CODIGO);
    long num_a04_codigo = MetodosUteis.retornaLong(ic_A04_CODIGO);
    Fator oFatorModel = new Fator();

    oFatorModel.setA06_titulo(f_a06_titulo);
    oFatorModel.setA06_descricao(f_a06_descricao);
    oFatorModel.setA02_codigo(num_a02_codigo);
    oFatorModel.setA04_codigo(num_a04_codigo);
    okMetodo = fatorService.insertFator(oFatorModel);

    pagina.put("okMetodo", String.valueOf(okMetodo));

    return pagina;
  }
}
