package paradecision.boot;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/** Renderiza todas as telas com listas vazias e preenchidas, sem usar o banco. */
@SpringBootTest
class FrontendTemplatesTests {
  @Autowired SpringTemplateEngine engine;
  private static final Path TEMPLATES = Path.of("src/main/resources/templates");
  private static final String TEXTO = "Ação d'Água \"teste\" <script>alert(1)</script> & amigos";

  @TestFactory
  Stream<DynamicTest> renderizaTodosOsTemplates() throws Exception {
    try (var files = Files.walk(TEMPLATES)) {
      return files
          .filter(p -> p.toString().endsWith(".html"))
          .sorted()
          .flatMap(
              path ->
                  Stream.of(false, true)
                      .map(
                          preenchido ->
                              DynamicTest.dynamicTest(
                                  TEMPLATES.relativize(path) + " preenchido=" + preenchido,
                                  () -> renderizar(path, preenchido))))
          .toList()
          .stream();
    }
  }

  private void renderizar(Path path, boolean preenchido) throws Exception {
    String source = Files.readString(path);
    Map<String, Object> model = new HashMap<>();
    // Dados de cada escopo do template. Os fragmentos usam os mesmos campos do advice.
    String all;
    try (var files = Files.walk(TEMPLATES)) {
      all =
          String.join(
              "\n",
              files
                  .filter(p -> p.toString().endsWith(".html"))
                  .map(
                      p -> {
                        try {
                          return Files.readString(p);
                        } catch (Exception e) {
                          throw new RuntimeException(e);
                        }
                      })
                  .toList());
    }
    var fields =
        Pattern.compile(
                "\\b(pagina|controleAgenda|controleFator|controle|linha\\d+)\\.([A-Za-z0-9_]+)")
            .matcher(all);
    Map<String, Map<String, Object>> scopes = new HashMap<>();
    while (fields.find()) {
      String field = fields.group(2);
      scopes
          .computeIfAbsent(fields.group(1), k -> new HashMap<>())
          .put(field, field.startsWith("check") ? "checked" : TEXTO);
    }
    scopes.computeIfAbsent("pagina", k -> new HashMap<>());
    var loops =
        Pattern.compile("th:each=\"(linha\\d+) : \\$\\{(\\w+)\\['(linhas\\d+)'\\]}")
            .matcher(source);
    while (loops.find()) {
      var child = scopes.computeIfAbsent(loops.group(1), k -> new HashMap<>());
      scopes
          .computeIfAbsent(loops.group(2), k -> new HashMap<>())
          .put(loops.group(3), preenchido ? List.of(child, child) : List.of());
    }
    model.putAll(scopes);
    MockServletContext servlet = new MockServletContext();
    var request = new MockHttpServletRequest(servlet);
    request.setContextPath("/estudo");
    request.setRequestURI("/estudo/preview");
    var exchange =
        JakartaServletWebApplication.buildApplication(servlet)
            .buildExchange(request, new MockHttpServletResponse());
    String template = TEMPLATES.relativize(path).toString().replace('\\', '/').replace(".html", "");
    String rendered =
        engine.process(template, new WebContext(exchange, Locale.forLanguageTag("pt-BR"), model));
    assertFalse(rendered.contains("<%"));
    assertFalse(Pattern.compile("\\bth:[\\w-]+=").matcher(rendered).find(), template);
    assertFalse(rendered.contains("[[${"), template);
    assertFalse(rendered.contains("<script>alert(1)</script>"), "Dados devem ser escapados");
    Path output =
        Path.of("target/frontend-preview", preenchido ? "preenchido" : "vazio", template + ".html");
    Files.createDirectories(output.getParent());
    Files.writeString(output, rendered);
  }
}
