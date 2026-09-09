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
public class InterCadastroFatorPaginaService {
  private final FatorService fatorService;

  public InterCadastroFatorPaginaService(FatorService fatorService) {
    this.fatorService = fatorService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String operacaoConcluida = "NOK";
    String tituloFatorFormularioFator = formulario.valor("f_a06_titulo");
    String descricaoFatorFormularioFator = formulario.valor("f_a06_descricao");
    String codigoUsuarioCadastro = formulario.valor("ct_A02_CODIGO");
    String codigoAgendaCadastro = formulario.valor("ct_A04_CODIGO");
    if (tituloFatorFormularioFator == null) tituloFatorFormularioFator = "";
    if (descricaoFatorFormularioFator == null) descricaoFatorFormularioFator = "";
    if (codigoUsuarioCadastro == null) codigoUsuarioCadastro = "0";
    if (codigoAgendaCadastro == null) codigoAgendaCadastro = "0";
    tituloFatorFormularioFator = MetodosUteis.padronizarEspacos(tituloFatorFormularioFator);
    descricaoFatorFormularioFator = MetodosUteis.padronizarEspacos(descricaoFatorFormularioFator);
    long codigoUsuarioNumerico = MetodosUteis.retornaLong(codigoUsuarioCadastro);
    long codigoAgendaNumerico = MetodosUteis.retornaLong(codigoAgendaCadastro);
    Fator dadosFator = new Fator();

    dadosFator.setA06_titulo(tituloFatorFormularioFator);
    dadosFator.setA06_descricao(descricaoFatorFormularioFator);
    dadosFator.setA02_codigo(codigoUsuarioNumerico);
    dadosFator.setA04_codigo(codigoAgendaNumerico);
    operacaoConcluida = fatorService.insertFator(dadosFator);

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    return pagina;
  }
}
