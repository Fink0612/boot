package paradecision.boot.modulos.inicio.controller;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Profile("!legado")
public class InicioController {
  @GetMapping("/") public String inicio(){return "redirect:/app/index.html";}
}
