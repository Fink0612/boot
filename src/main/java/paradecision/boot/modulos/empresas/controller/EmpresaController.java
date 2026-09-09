package paradecision.boot.modulos.empresas.controller;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import paradecision.boot.modulos.empresas.service.EmpresaAplicacaoService;
import paradecision.boot.modulos.empresas.dto.EmpresaApi;
@RestController
@RequestMapping("/api")
@Tag(name="empresas")
public class EmpresaController {
  private final EmpresaAplicacaoService service;
  public EmpresaController(EmpresaAplicacaoService service){this.service=service;}

  @GetMapping("/empresas") public List<EmpresaApi.Dados> listar(@SessionAttribute("usuarioId") long ator) {return service.listar(ator);}
  @GetMapping("/empresas/{id}/usuarios") public List<EmpresaApi.Participante> usuarios(@SessionAttribute("usuarioId") long ator,@PathVariable long id) {return service.usuarios(ator,id);}

}
