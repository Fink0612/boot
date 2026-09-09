package paradecision.boot.modulos.pareceres.controller;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import paradecision.boot.modulos.pareceres.service.ParecerAplicacaoService;
import paradecision.boot.modulos.pareceres.dto.ParecerApi;
@RestController
@RequestMapping("/api")
@Tag(name="pareceres")
public class ParecerController {
  private final ParecerAplicacaoService service;
  public ParecerController(ParecerAplicacaoService service){this.service=service;}

  @GetMapping("/agendas/{agenda}/pareceres/meus") public List<ParecerApi.Dados> listar(@SessionAttribute("usuarioId") long ator,@PathVariable long agenda){return service.listar(ator,agenda);}
  @PutMapping("/fatores/{fator}/pareceres/meu") public ParecerApi.Dados salvar(@SessionAttribute("usuarioId") long ator,@PathVariable long fator,@Valid @RequestBody ParecerApi.Cadastro dados){return service.salvar(ator,fator,dados);}

}
