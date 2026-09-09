package paradecision.boot.modulos.usuarios.controller;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import paradecision.boot.modulos.usuarios.service.UsuarioAplicacaoService;
import paradecision.boot.modulos.usuarios.dto.UsuarioApi;
@RestController
@RequestMapping("/api")
@Tag(name="usuarios")
public class UsuarioController {
  private final UsuarioAplicacaoService service;
  public UsuarioController(UsuarioAplicacaoService service){this.service=service;}

  @PostMapping("/empresas/{empresa}/usuarios") @ResponseStatus(HttpStatus.CREATED)
  public UsuarioApi.Dados criar(@SessionAttribute("usuarioId") long ator,@PathVariable long empresa,@Valid @RequestBody UsuarioApi.Cadastro dados){return service.salvar(ator,empresa,0,dados);}
  @PutMapping("/empresas/{empresa}/usuarios/{id}")
  public UsuarioApi.Dados editar(@SessionAttribute("usuarioId") long ator,@PathVariable long empresa,@PathVariable long id,@Valid @RequestBody UsuarioApi.Cadastro dados){return service.salvar(ator,empresa,id,dados);}

}
