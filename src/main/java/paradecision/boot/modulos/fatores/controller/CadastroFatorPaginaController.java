package paradecision.boot.modulos.fatores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** Exibe o formulário; o processamento do cadastro fica no service correspondente. */
@Controller
public class CadastroFatorPaginaController {
  @RequestMapping(
      value = "/fatores/CadastroFator",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String exibir() {
    return "fatores/CadastroFator";
  }
}
