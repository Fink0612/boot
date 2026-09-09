package paradecision.boot.modulos.compartilhado.web;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import jakarta.servlet.Filter;

/** Libera o frontend publicado em outra origem (CORS). Sem CORS_ORIGINS, nada é liberado. */
@Configuration
public class CorsConfig {

  private final List<String> origensPermitidas;

  public CorsConfig(@Value("${app.cors-origins:}") String origens) {
    this.origensPermitidas = parsear(origens);
  }

  @Bean
  FilterRegistrationBean<Filter> filtroCors() {
    FilterRegistrationBean<Filter> registro = new FilterRegistrationBean<Filter>(new CorsFilter(origensPermitidas));
    registro.addUrlPatterns("/api/*");
    registro.setOrder(Ordered.HIGHEST_PRECEDENCE);
    return registro;
  }

  private static List<String> parsear(String origens) {
    if (origens == null || origens.isBlank()) {
      return List.of();
    }
    return Arrays.stream(origens.split(","))
        .map(String::trim)
        .filter(origem -> !origem.isBlank())
        .toList();
  }
}