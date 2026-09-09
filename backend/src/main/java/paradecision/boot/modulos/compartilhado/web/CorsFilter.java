package paradecision.boot.modulos.compartilhado.web;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Filtro CORS: agrega os cabeçalhos de acesso quando a origem está permitida. */
public class CorsFilter implements Filter {

  private final List<String> origensPermitidas;

  public CorsFilter(List<String> origensPermitidas) {
    this.origensPermitidas = origensPermitidas;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;

    String origem = request.getHeader("Origin");
    if (origem != null && origensPermitidas.contains(origem)) {
      response.setHeader("Access-Control-Allow-Origin", origem);
      response.setHeader("Access-Control-Allow-Credentials", "true");
      response.setHeader("Vary", "Origin");
    }

    response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE,OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
    response.setHeader("Access-Control-Max-Age", "3600");

    if ("OPTIONS".equals(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return;
    }

    chain.doFilter(req, resp);
  }
}