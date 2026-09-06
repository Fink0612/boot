package paradecision.boot.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** Camada web de ctrltargetpage: prepara os dados; o HTML fica no template. */
@Controller
public class CtrltargetpagePaginaController {
  @RequestMapping(
      value = "/compartilhado/ctrltargetpage",
      method = {RequestMethod.GET, RequestMethod.POST})
  public String exibir(HttpServletRequest request, Model model) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    model.addAttribute("pagina", pagina);
    return "compartilhado/ctrltargetpage";
  }
}
