package paradecision.boot.modulos.autenticacao.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import paradecision.boot.modulos.autenticacao.dto.LoginApi;
import paradecision.boot.modulos.usuarios.dto.UsuarioApi;
import paradecision.boot.modulos.usuarios.service.UsuarioAplicacaoService;
@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {
  private final UsuarioAplicacaoService usuarios;
  public AutenticacaoController(UsuarioAplicacaoService usuarios){this.usuarios=usuarios;}
  @PostMapping("/login") public UsuarioApi.Dados login(@Valid @RequestBody LoginApi dados,HttpServletRequest request) {var usuario=usuarios.autenticar(dados.login(),dados.senha());var anterior=request.getSession(false);if(anterior!=null)anterior.invalidate();request.getSession(true).setAttribute("usuarioId",usuario.id());return usuario;}
  @GetMapping("/me") public UsuarioApi.Dados me(@SessionAttribute("usuarioId") long id){return usuarios.buscar(id);}
  @PostMapping("/logout") @ResponseStatus(HttpStatus.NO_CONTENT) public void logout(HttpServletRequest request){var session=request.getSession(false);if(session!=null)session.invalidate();}
}
