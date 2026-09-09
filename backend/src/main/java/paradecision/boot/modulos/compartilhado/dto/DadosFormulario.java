package paradecision.boot.modulos.compartilhado.dto;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/** Cópia dos dados de entrada. Serviços não recebem HttpServletRequest nem objetos MVC. */
public final class DadosFormulario {
  private final Map<String, String> valores;
  private final String contextPath;

  public DadosFormulario(Map<String, String[]> parametros, String contextPath) {
    Map<String, String> copia = new LinkedHashMap<>();
    parametros.forEach(
        (nome, lista) -> {
          if (lista != null && lista.length > 0) copia.put(nome, lista[0]);
        });
    this.valores = Collections.unmodifiableMap(copia);
    this.contextPath = contextPath == null ? "" : contextPath;
  }

  public String valor(String nome) {
    return valores.get(nome);
  }

  public String contextPath() {
    return contextPath;
  }
}
