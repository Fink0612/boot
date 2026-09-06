package paradecision.boot.modulos.agendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** Exibe o formulário; o processamento do cadastro fica no service correspondente. */
@Controller
public class CadastroAgendaPaginaController {
  @RequestMapping(
      value = "/agendas/CadastroAgenda",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String exibir() {
    return "agendas/CadastroAgenda";
  }
}
