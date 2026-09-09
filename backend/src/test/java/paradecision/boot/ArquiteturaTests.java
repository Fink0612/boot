package paradecision.boot;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import java.nio.file.*;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

@SpringBootTest
class ArquiteturaTests {
  @Autowired ApplicationContext context;

  @Test
  void camadasRespeitamDirecaoDasDependencias() throws Exception {
    try (var files = Files.walk(Path.of("src/main/java/paradecision/boot/modulos"))) {
      for (var file : files.filter(f -> f.toString().endsWith(".java")).toList()) {
        String path = file.toString().replace('\\', '/'), source = Files.readString(file);
        if (path.contains("/entity/") || path.contains("/dto/")) {
          assertFalse(
              Pattern.compile("import .*\\.(service|repository|controller)\\.")
                  .matcher(source)
                  .find(),
              path);
          assertFalse(source.contains("org.springframework"), path);
          assertFalse(source.contains("java.sql.Connection"), path);
        }
        if (path.contains("/service/")) {
          assertFalse(source.contains("jakarta.servlet"), path);
          assertFalse(source.contains("ConnectionFactory"), path);
          assertFalse(source.contains("org.springframework.ui"), path);
          assertFalse(source.contains(".controller."), path);
        }
        if (path.contains("/controller/")) {
          assertFalse(source.contains(".repository."), path);
          assertFalse(source.contains(".entity."), path);
          assertFalse(source.contains("java.sql"), path);
        }
        assertFalse(
            Pattern.compile("new \\w+(Service|Repository)\\s*\\(").matcher(source).find(), path);
      }
    }
  }

  @Test
  void beansCompartilhadosNaoGuardamDadosDeRequisicao() {
    var beans = new java.util.HashMap<>(context.getBeansWithAnnotation(Service.class));
    beans.putAll(context.getBeansWithAnnotation(Repository.class));
    beans.forEach(
        (name, bean) -> {
          for (var field : ClassUtils.getUserClass(bean).getDeclaredFields()) {
            if (field.isSynthetic() || Modifier.isStatic(field.getModifiers())) continue;
            assertTrue(
                Modifier.isFinal(field.getModifiers()),
                name + " guarda estado mutável em " + field.getName());
            assertNotEquals(java.sql.Connection.class, field.getType(), name);
          }
        });
  }
}
