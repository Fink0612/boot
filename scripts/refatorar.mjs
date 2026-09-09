import fs from 'node:fs';
import path from 'node:path';
const root='src/main/java/paradecision/boot/modulos';
const write=(p,s)=>{fs.mkdirSync(path.dirname(p),{recursive:true});fs.writeFileSync(p,s)};
const walk=p=>fs.readdirSync(p,{withFileTypes:true}).flatMap(x=>x.isDirectory()?walk(path.join(p,x.name)):[path.join(p,x.name)]);
// O MVC anterior fica disponível somente no perfil legado.
for(const p of walk(root).filter(p=>p.endsWith('.java'))){let s=fs.readFileSync(p,'utf8');if(s.includes('@Controller')||s.includes('@ControllerAdvice')) {s=s.replace(/(@Controller(?:Advice)?)/,'@org.springframework.context.annotation.Profile("legado")\n$1');write(p,s)}}
for(const p of ['FrontendFluxosTests.java','FrontendTemplatesTests.java']) {const f='src/test/java/paradecision/boot/'+p;let s=fs.readFileSync(f,'utf8').replace('@SpringBootTest','@org.springframework.test.context.ActiveProfiles("legado")\n@SpringBootTest');write(f,s)}
// Mapeamento explícito dos nomes de coluna legados, compartilhado pelos adaptadores.
let mappings='';
const entities={};
for(const p of walk(root).filter(p=>p.includes(path.sep+'entity'+path.sep)&&p.endsWith('.java'))){const s=fs.readFileSync(p,'utf8'),name=path.basename(p,'.java'),pkg=s.match(/package ([^;]+)/)[1];entities[name]=pkg+'.'+name;const setters=[...s.matchAll(/public void (set(A\d\d_\w+))\((\w+(?:\.\w+)*) \w+\)/g)];if(!setters.length)continue;mappings+=`  public static ${pkg}.${name} ${name}(Registro r) {\n    var v = new ${pkg}.${name}();\n`+setters.map(([,method,col,type])=>`    v.${method}(r.${({long:'numero',int:'inteiro',double:'decimal',String:'texto',Date:'data'})[type]}("${col.toLowerCase()}"));`).join('\n')+'\n    return v;\n  }\n';}
write(root+'/compartilhado/infra/Mapeamento.java',`package paradecision.boot.modulos.compartilhado.infra;\npublic final class Mapeamento {\n  private Mapeamento() {}\n${mappings}}\n`);
const repo=(mod,name,body)=>write(`${root}/${mod}/repository/${name}.java`,`package paradecision.boot.modulos.${mod}.repository;
import java.util.*;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.*;
import paradecision.boot.modulos.agendas.entity.*;
import paradecision.boot.modulos.agendas.dto.*;
import paradecision.boot.modulos.empresas.entity.*;
import paradecision.boot.modulos.empresas.dto.*;
import paradecision.boot.modulos.usuarios.entity.*;
import paradecision.boot.modulos.usuarios.dto.*;
import paradecision.boot.modulos.fatores.entity.*;
import paradecision.boot.modulos.pareceres.entity.*;
import static paradecision.boot.modulos.compartilhado.infra.Registro.campos;
@Repository
public class ${name} {
  private final BancoDados banco;
  public ${name}(BancoDados banco) { this.banco = banco; }
${body}
}
`);
const single=(method,entity,table,filters)=>`  public ${entity} ${method}(${entity} v) { return banco.listar("${table}", ${filters}).stream().findFirst().map(Mapeamento::${entity}).orElseGet(${entity}::new); }\n`;
repo('usuarios','UsuarioRepository',
single('selectUserLogin','Usuario','usuario_02','campos("a02_usuario", v.getA02_usuario())')+
single('selectUserByCode','Usuario','usuario_02','campos("a02_codigo", v.getA02_codigo())')+
single('selectUserIni','Usuario','usuario_02','campos("a02_codigo_link", v.getA02_codigo_link())')+`
  public Usuario insertUsuario(Usuario v) { return Mapeamento.Usuario(banco.inserir("usuario_02", dados(v))); }
  public String updateUsuario(Usuario v) { banco.atualizar("usuario_02", campos("a02_codigo",v.getA02_codigo()),dados(v)); return "OK"; }
  public void updateSenhaUsuario(Usuario v) { banco.atualizar("usuario_02",campos("a02_usuario",v.getA02_usuario()),campos("a02_senha",v.getA02_senha(),"a02_codigo_link",v.getA02_codigo_link())); }
  private Map<String,Object> dados(Usuario v) { return campos("a02_nome",v.getA02_nome(),"a02_usuario",v.getA02_usuario(),"a02_senha",v.getA02_senha(),"a02_email",v.getA02_email(),"a02_status",v.getA02_status(),"a02_codigo_link",v.getA02_codigo_link()); }
`);
repo('agendas','AgendaRepository',single('selectAgenda','Agenda','agenda_04','campos("a04_codigo",v.getA04_codigo())')+`
  public long insertAgenda(Agenda v) { var d=dados(v); d.put("a01_codigo",v.getA01_codigo()); d.put("a04_status",v.getA04_status()); return banco.inserir("agenda_04",d).numero("a04_codigo"); }
  public String updateAgenda(Agenda v) { banco.atualizar("agenda_04",campos("a04_codigo",v.getA04_codigo()),dados(v)); return "OK"; }
  public String updateStatusAgenda(Agenda v) { banco.atualizar("agenda_04",campos("a04_codigo",v.getA04_codigo()),campos("a04_status",v.getA04_status())); return "OK"; }
  private Map<String,Object> dados(Agenda v) { return campos("a04_titulo",v.getA04_titulo(),"a04_descricao",v.getA04_descricao(),"a04_status_dt_limite",v.getA04_status_dt_limite(),"a04_data_limite",v.getA04_data_limite()); }
`);
repo('fatores','FatorRepository',single('selectFator','Fator','fator_06','campos("a06_codigo",v.getA06_codigo())')+`
  public String insertFator(Fator v) { var d=campos("a06_titulo",v.getA06_titulo(),"a06_descricao",v.getA06_descricao(),"a06_num_sequencia",v.getA06_num_sequencia(),"a04_codigo",v.getA04_codigo(),"a02_codigo",v.getA02_codigo()); v.setA06_codigo(banco.inserir("fator_06",d).numero("a06_codigo")); return "OK"; }
  public String updateFator(Fator v) { banco.atualizar("fator_06",campos("a06_codigo",v.getA06_codigo()),campos("a06_titulo",v.getA06_titulo(),"a06_descricao",v.getA06_descricao())); return "OK"; }
`);
repo('empresas','EmpresaUsuarioPerfilRepository',single('selectEmpresaUsuario','EmpresaUsuarioPerfil','empresa_usuario_perfil_03','campos("a01_codigo",v.getA01_codigo(),"a02_codigo",v.getA02_codigo())').replace('orElseGet(EmpresaUsuarioPerfil::new)','orElse(null)')+`
  public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfil v) { var d=dados(v); d.put("a01_codigo",v.getA01_codigo()); d.put("a02_codigo",v.getA02_codigo()); banco.inserir("empresa_usuario_perfil_03",d); return 1; }
  public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfil v) { banco.atualizar("empresa_usuario_perfil_03",campos("a01_codigo",v.getA01_codigo(),"a02_codigo",v.getA02_codigo()),campos("a03_perfil_chefe",v.getA03_perfil_chefe(),"a03_perfil_padrao",v.getA03_perfil_padrao())); return "OK"; }
  private Map<String,Object> dados(EmpresaUsuarioPerfil v) { return campos("a03_perfil_paraviverbem",v.getA03_perfil_paraviverbem(),"a03_perfil_administrador",v.getA03_perfil_administrador(),"a03_perfil_chefe",v.getA03_perfil_chefe(),"a03_perfil_padrao",v.getA03_perfil_padrao()); }
`);
repo('agendas','AgendaUsuarioPerfilRepository',single('selectAgendaUsuarioPerfil','AgendaUsuarioPerfil','usuario_agenda_05','campos("a04_codigo",v.getA04_codigo(),"a02_codigo",v.getA02_codigo())').replace('orElseGet(AgendaUsuarioPerfil::new)','orElse(null)')+`
  public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfil v) { var d=dados(v); d.put("a04_codigo",v.getA04_codigo()); d.put("a02_codigo",v.getA02_codigo()); d.put("a05_num_sequencia",v.getA05_num_sequencia()); banco.inserir("usuario_agenda_05",d); return "OK"; }
  public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfil v) { banco.atualizar("usuario_agenda_05",campos("a05_codigo",v.getA05_codigo()),dados(v)); return "OK"; }
  public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfil v) { banco.excluir("usuario_agenda_05",campos("a05_codigo",v.getA05_codigo())); return "OK"; }
  private Map<String,Object> dados(AgendaUsuarioPerfil v) { return campos("a05_perfil_agenda_usuario_titular",v.getA05_perfil_agenda_usuario_titular(),"a05_perfil_agenda_usuario_facilitador",v.getA05_perfil_agenda_usuario_facilitador(),"a05_perfil_agenda_usuario_especialista",v.getA05_perfil_agenda_usuario_especialista(),"a05_perfil_agenda_usuario_analista",v.getA05_perfil_agenda_usuario_analista()); }
`);
repo('pareceres','ParecerFatorUsuarioRepository',single('selectParecerFatorUsuario','ParecerFatorUsuario','parecer_fator_usuario_07','campos("a06_codigo",v.getA06_codigo(),"a02_codigo",v.getA02_codigo())').replace('orElseGet(ParecerFatorUsuario::new)','orElse(v)')+`
  public String insertParecerFatorUsuario(ParecerFatorUsuario v) { var d=dados(v); d.put("a06_codigo",v.getA06_codigo()); d.put("a02_codigo",v.getA02_codigo()); d.put("a07_num_sequencia",v.getA07_num_sequencia()); banco.inserir("parecer_fator_usuario_07",d); return "OK"; }
  public String updateParecerFatorUsuario(ParecerFatorUsuario v) { banco.atualizar("parecer_fator_usuario_07",campos("a07_codigo",v.getA07_codigo()),dados(v)); return "OK"; }
  private Map<String,Object> dados(ParecerFatorUsuario v) { return campos("a07_certeza",v.getA07_certeza()<0?null:v.getA07_certeza(),"a07_contradicao",v.getA07_contradicao()<0?null:v.getA07_contradicao()); }
`);
repo('usuarios','UsuarioEmpresasRepository',`  public UsuarioEmpresasDados selectEmpresasDoUsuario(UsuarioEmpresasDados v) { for(var r:banco.listar("vw_usuario_empresas",campos("a02_codigo",v.getoUsuarioModel().getA02_codigo()))) { v.getArrEmpresaModel().add(Mapeamento.Empresa(r)); v.getArrEmpresaUsuarioPerfilModel().add(Mapeamento.EmpresaUsuarioPerfil(r)); } return v; }`);
repo('empresas','EmpresaUsuariosRepository',`  public EmpresaUsuariosDados selectUsuariosDaEmpresa(EmpresaUsuariosDados v) { for(var r:banco.listar("vw_empresa_usuarios",campos("a01_codigo",v.getoEmpresaModel().getA01_codigo()))) { v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); v.getArrEmpresaUsuarioPerfilModel().add(Mapeamento.EmpresaUsuarioPerfil(r)); } return v; }`);
repo('empresas','EmpresaAgendasRepository',`  public EmpresaAgendasDados selectAgendasDaEmpresa(EmpresaAgendasDados v) { v.setArrAgendaModel(new ArrayList<>(banco.listar("agenda_04",campos("a01_codigo",v.getoEmpresaModel().getA01_codigo())).stream().map(Mapeamento::Agenda).toList())); return v; }
  public EmpresaAgendasDados selectAgendasDaEmpresaUsuario(EmpresaAgendasDados v) { v.setArrAgendaModel(new ArrayList<>(banco.listar("vw_participantes_agenda",campos("a01_codigo",v.getoEmpresaModel().getA01_codigo(),"a02_codigo",v.getoUsuarioModel().getA02_codigo())).stream().map(Mapeamento::Agenda).toList())); return v; }`);
repo('agendas','AgendaUsuariosRepository',`  public AgendaUsuariosDados selectUsuariosDaAgenda(AgendaUsuariosDados v) { for(var r:banco.listar("vw_agendas_usuarios",campos("a04_codigo",v.getoAgendaModel().getA04_codigo()))) { v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); v.getArrAgendaUsuarioPerfilModel().add(Mapeamento.AgendaUsuarioPerfil(r)); } return v; }
  public ArrayList<Usuario> getArrUsuariosModel(Agenda v) { return new ArrayList<>(banco.listar("vw_agendas_usuarios",campos("a04_codigo",v.getA04_codigo())).stream().map(Mapeamento::Usuario).toList()); }`);
repo('agendas','AgendaPareceresRepository',`  public ArrayList<ParecerFatorUsuario> getArrPareceresModel(Agenda v) { return new ArrayList<>(banco.listar("vw_fatores_pareceres",campos("a04_codigo",v.getA04_codigo())).stream().map(Mapeamento::ParecerFatorUsuario).toList()); }`);
repo('agendas','AgendaUsuarioPareceresRepository',`  public AgendaUsuarioPareceresDados selectPareceresAgUsu(AgendaUsuarioPareceresDados v) { v.setArrParecerFatorUsuarioModel(new ArrayList<>(banco.listar("vw_fatores_pareceres",campos("a04_codigo",v.getoAgendaModel().getA04_codigo(),"a02_codigo",v.getoUsuarioModel().getA02_codigo())).stream().map(Mapeamento::ParecerFatorUsuario).toList())); return v; }`);
repo('agendas','AgendaFatoresRepository',`  public AgendaFatoresDados selectFatoresDaAgenda(AgendaFatoresDados v) { for(var r:banco.listar("vw_agendas_fatores",campos("a04_codigo",v.getoAgendaModel().getA04_codigo()))) { v.getArrFatorModel().add(Mapeamento.Fator(r)); v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); } return v; }
  public ArrayList<Fator> getArrFatoresModel(Agenda v) { return new ArrayList<>(banco.listar("fator_06",campos("a04_codigo",v.getA04_codigo())).stream().map(Mapeamento::Fator).toList()); }
  public String updateGrausFatoresDaAgenda(AgendaFatoresDados v) { var a=v.getoAgendaModel(); banco.atualizar("agenda_04",campos("a04_codigo",a.getA04_codigo()),campos("a04_certeza_resultado",a.getA04_certeza_resultado(),"a04_contradicao_resultado",a.getA04_contradicao_resultado(),"a04_resultado",a.getA04_resultado())); for(var f:v.getArrFatorModel()) banco.atualizar("fator_06",campos("a06_codigo",f.getA06_codigo()),campos("a06_certeza_resultante_fator",f.getA06_certeza_resultante_fator(),"a06_contradicao_resultante_fator",f.getA06_contradicao_resultante_fator(),"a06_resultado_fator",f.getA06_resultado_fator())); return "OK"; }`);
