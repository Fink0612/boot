package paradecision.boot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.repository.UsuarioRepository;
import paradecision.boot.modulos.usuarios.service.UsuarioService;

class UsuarioServiceTests {
  @Test
  void senhaIncorretaNaoRetornaUsuarioAutenticado() {
    var repo = mock(UsuarioRepository.class);
    var salvo = new Usuario();
    salvo.setA02_codigo(10);
    salvo.setA02_senha("correta");
    when(repo.selectUserLogin(any())).thenReturn(salvo);
    var entrada = new Usuario();
    entrada.setA02_usuario("amigo");
    entrada.setA02_senha("errada");
    assertEquals(0, new UsuarioService(repo).selectUserLogin(entrada).getA02_codigo());
    assertEquals(10, salvo.getA02_codigo());
  }
}
