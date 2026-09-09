package paradecision.boot.modulos.agendas.controller;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import paradecision.boot.modulos.agendas.service.AgendaAplicacaoService;
import paradecision.boot.modulos.agendas.dto.AgendaApi;
@RestController
@RequestMapping("/api")
@Tag(name="agendas")
public class AgendaController {
  private final AgendaAplicacaoService service;
  public AgendaController(AgendaAplicacaoService service){this.service=service;}

  @GetMapping("/empresas/{empresa}/agendas") public List<AgendaApi.Dados> listar(@SessionAttribute("usuarioId") long ator,@PathVariable long empresa){return service.listar(ator,empresa);}
  @GetMapping("/agendas/{id}") public AgendaApi.Dados buscar(@SessionAttribute("usuarioId") long ator,@PathVariable long id){return service.buscar(ator,id);}
  @PostMapping("/empresas/{empresa}/agendas") @ResponseStatus(HttpStatus.CREATED)
  public AgendaApi.Dados criar(@SessionAttribute("usuarioId") long ator,@PathVariable long empresa,@Valid @RequestBody AgendaApi.Cadastro dados){return service.criar(ator,empresa,dados);}
  @PutMapping("/agendas/{id}") public AgendaApi.Dados editar(@SessionAttribute("usuarioId") long ator,@PathVariable long id,@Valid @RequestBody AgendaApi.Cadastro dados){return service.editar(ator,id,dados);}
  @PatchMapping("/agendas/{id}/status") public AgendaApi.Dados fluxo(@SessionAttribute("usuarioId") long ator,@PathVariable long id,@Valid @RequestBody AgendaApi.Fluxo dados){return service.fluxo(ator,id,dados.acao());}
  @PostMapping("/agendas/{id}/calculos") public AgendaApi.Dados calcular(@SessionAttribute("usuarioId") long ator,@PathVariable long id){return service.calcular(ator,id);}
  @GetMapping("/agendas/{id}/usuarios") public List<AgendaApi.Participante> participantes(@SessionAttribute("usuarioId") long ator,@PathVariable long id){return service.participantes(ator,id);}
  @PutMapping("/agendas/{id}/usuarios/{usuario}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void vincular(@SessionAttribute("usuarioId") long ator,@PathVariable long id,@PathVariable long usuario,@Valid @RequestBody AgendaApi.Perfil dados){service.vincular(ator,id,usuario,dados);}
  @DeleteMapping("/agendas/{id}/usuarios/{usuario}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@SessionAttribute("usuarioId") long ator,@PathVariable long id,@PathVariable long usuario){service.remover(ator,id,usuario);}

}
