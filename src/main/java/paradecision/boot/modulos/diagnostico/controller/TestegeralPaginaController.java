package paradecision.boot.modulos.diagnostico.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.diagnostico.service.pagina.TestegeralPaginaService;

/** Adaptador MVC: recebe HTTP, delega ao serviço e escolhe a view. */
@Controller
public class TestegeralPaginaController {
  private final TestegeralPaginaService service;

  public TestegeralPaginaController(TestegeralPaginaService service) {
    this.service = service;
  }

  @RequestMapping(
      value = "/diagnostico/testegeral",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String exibir(HttpServletRequest request, Model model) {
    var formulario = new DadosFormulario(request.getParameterMap(), request.getContextPath());
    model.addAttribute("pagina", service.preparar(formulario));
    return "diagnostico/testegeral";
  }
}
