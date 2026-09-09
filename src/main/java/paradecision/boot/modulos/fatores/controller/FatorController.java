package paradecision.boot.modulos.fatores.controller;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import paradecision.boot.modulos.fatores.service.FatorAplicacaoService;
import paradecision.boot.modulos.fatores.dto.FatorApi;
@RestController
@RequestMapping("/api")
@Tag(name="fatores")
public class FatorController {
  private final FatorAplicacaoService service;
  public FatorController(FatorAplicacaoService service){this.service=service;}

  @GetMapping("/agendas/{agenda}/fatores") public List<FatorApi.Dados> listar(@SessionAttribute("usuarioId") long ator,@PathVariable long agenda){return service.listar(ator,agenda);}
  @PostMapping("/agendas/{agenda}/fatores") @ResponseStatus(HttpStatus.CREATED)
  public FatorApi.Dados criar(@SessionAttribute("usuarioId") long ator,@PathVariable long agenda,@Valid @RequestBody FatorApi.Cadastro dados){return service.criar(ator,agenda,dados);}
  @PutMapping("/fatores/{id}") public FatorApi.Dados editar(@SessionAttribute("usuarioId") long ator,@PathVariable long id,@Valid @RequestBody FatorApi.Cadastro dados){return service.editar(ator,id,dados);}

}
