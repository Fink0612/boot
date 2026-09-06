package paradecision.boot.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** Campos de navegação compartilhados pelos formulários, sem consultas ao banco. */
@ControllerAdvice
public class FragmentosAdvice {
  @ModelAttribute("controle")
  public Map<String, Object> controle(HttpServletRequest request) {
    return CamposControleViewModel.preparar(request);
  }

  @ModelAttribute("controleAgenda")
  public Map<String, Object> agenda(HttpServletRequest request) {
    return CamposCtrlAgendaViewModel.preparar(request);
  }

  @ModelAttribute("controleFator")
  public Map<String, Object> fator(HttpServletRequest request) {
    return CamposCtrlFatorViewModel.preparar(request);
  }
}
