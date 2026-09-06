package paradecision.boot.modulos.agendas.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.agendas.service.pagina.AgendaUsuariosEditarPaginaService;

/** Adaptador MVC: recebe HTTP, delega ao serviço e escolhe a view. */
@Controller
public class AgendaUsuariosEditarPaginaController {
  private final AgendaUsuariosEditarPaginaService service;

  public AgendaUsuariosEditarPaginaController(AgendaUsuariosEditarPaginaService service) {
    this.service = service;
  }

  @RequestMapping(
      value = "/agendas/AgendaUsuariosEditar",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String exibir(HttpServletRequest request, Model model) {
    var formulario = new DadosFormulario(request.getParameterMap(), request.getContextPath());
    model.addAttribute("pagina", service.preparar(formulario));
    return "agendas/AgendaUsuariosEditar";
  }
}
