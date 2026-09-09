import fs from 'node:fs';
const root='src/main/java/paradecision/boot/modulos';
const create=(mod,name,service,imports,methods)=>{const dir=`${root}/${mod}/controller`;fs.mkdirSync(dir,{recursive:true});fs.writeFileSync(`${dir}/${name}.java`,`package paradecision.boot.modulos.${mod}.controller;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import paradecision.boot.modulos.${mod}.service.${service};
${imports}
@RestController
@RequestMapping("/api")
@Tag(name="${mod}")
public class ${name} {
  private final ${service} service;
  public ${name}(${service} service){this.service=service;}
${methods}
}
`)};
const session='@SessionAttribute("usuarioId") long ator';
create('empresas','EmpresaController','EmpresaAplicacaoService','import paradecision.boot.modulos.empresas.dto.EmpresaApi;',`
  @GetMapping("/empresas") public List<EmpresaApi.Dados> listar(${session}) {return service.listar(ator);}
  @GetMapping("/empresas/{id}/usuarios") public List<EmpresaApi.Participante> usuarios(${session},@PathVariable long id) {return service.usuarios(ator,id);}
`);
create('usuarios','UsuarioController','UsuarioAplicacaoService','import paradecision.boot.modulos.usuarios.dto.UsuarioApi;',`
  @PostMapping("/empresas/{empresa}/usuarios") @ResponseStatus(HttpStatus.CREATED)
  public UsuarioApi.Dados criar(${session},@PathVariable long empresa,@Valid @RequestBody UsuarioApi.Cadastro dados){return service.salvar(ator,empresa,0,dados);}
  @PutMapping("/empresas/{empresa}/usuarios/{id}")
  public UsuarioApi.Dados editar(${session},@PathVariable long empresa,@PathVariable long id,@Valid @RequestBody UsuarioApi.Cadastro dados){return service.salvar(ator,empresa,id,dados);}
`);
create('agendas','AgendaController','AgendaAplicacaoService','import paradecision.boot.modulos.agendas.dto.AgendaApi;',`
  @GetMapping("/empresas/{empresa}/agendas") public List<AgendaApi.Dados> listar(${session},@PathVariable long empresa){return service.listar(ator,empresa);}
  @GetMapping("/agendas/{id}") public AgendaApi.Dados buscar(${session},@PathVariable long id){return service.buscar(ator,id);}
  @PostMapping("/empresas/{empresa}/agendas") @ResponseStatus(HttpStatus.CREATED)
  public AgendaApi.Dados criar(${session},@PathVariable long empresa,@Valid @RequestBody AgendaApi.Cadastro dados){return service.criar(ator,empresa,dados);}
  @PutMapping("/agendas/{id}") public AgendaApi.Dados editar(${session},@PathVariable long id,@Valid @RequestBody AgendaApi.Cadastro dados){return service.editar(ator,id,dados);}
  @PatchMapping("/agendas/{id}/status") public AgendaApi.Dados fluxo(${session},@PathVariable long id,@Valid @RequestBody AgendaApi.Fluxo dados){return service.fluxo(ator,id,dados.acao());}
  @PostMapping("/agendas/{id}/calculos") public AgendaApi.Dados calcular(${session},@PathVariable long id){return service.calcular(ator,id);}
  @GetMapping("/agendas/{id}/usuarios") public List<AgendaApi.Participante> participantes(${session},@PathVariable long id){return service.participantes(ator,id);}
  @PutMapping("/agendas/{id}/usuarios/{usuario}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void vincular(${session},@PathVariable long id,@PathVariable long usuario,@Valid @RequestBody AgendaApi.Perfil dados){service.vincular(ator,id,usuario,dados);}
  @DeleteMapping("/agendas/{id}/usuarios/{usuario}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(${session},@PathVariable long id,@PathVariable long usuario){service.remover(ator,id,usuario);}
`);
create('fatores','FatorController','FatorAplicacaoService','import paradecision.boot.modulos.fatores.dto.FatorApi;',`
  @GetMapping("/agendas/{agenda}/fatores") public List<FatorApi.Dados> listar(${session},@PathVariable long agenda){return service.listar(ator,agenda);}
  @PostMapping("/agendas/{agenda}/fatores") @ResponseStatus(HttpStatus.CREATED)
  public FatorApi.Dados criar(${session},@PathVariable long agenda,@Valid @RequestBody FatorApi.Cadastro dados){return service.criar(ator,agenda,dados);}
  @PutMapping("/fatores/{id}") public FatorApi.Dados editar(${session},@PathVariable long id,@Valid @RequestBody FatorApi.Cadastro dados){return service.editar(ator,id,dados);}
`);
create('pareceres','ParecerController','ParecerAplicacaoService','import paradecision.boot.modulos.pareceres.dto.ParecerApi;',`
  @GetMapping("/agendas/{agenda}/pareceres/meus") public List<ParecerApi.Dados> listar(${session},@PathVariable long agenda){return service.listar(ator,agenda);}
  @PutMapping("/fatores/{fator}/pareceres/meu") public ParecerApi.Dados salvar(${session},@PathVariable long fator,@Valid @RequestBody ParecerApi.Cadastro dados){return service.salvar(ator,fator,dados);}
`);
// Completa os campos textuais do parecer, inclusive a distinção entre nulo e zero.
const f=root+'/compartilhado/infra/Mapeamento.java';let s=fs.readFileSync(f,'utf8');const start=s.indexOf('public static paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario');const pos=s.indexOf('    return v;',start);s=s.slice(0,pos)+'    v.setStr_a07_certeza(r.texto("a07_certeza"));\n    v.setStr_a07_contradicao(r.texto("a07_contradicao"));\n'+s.slice(pos);fs.writeFileSync(f,s);
