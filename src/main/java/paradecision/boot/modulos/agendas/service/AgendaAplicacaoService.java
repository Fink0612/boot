package paradecision.boot.modulos.agendas.service;
import java.util.*;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.agendas.entity.*;
import paradecision.boot.modulos.agendas.dto.*;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.dto.EmpresaAgendasDados;
import paradecision.boot.modulos.empresas.service.EmpresaAgendasService;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.UsuarioAplicacaoService;
import paradecision.boot.modulos.autenticacao.service.AcessoService;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
@Service
public class AgendaAplicacaoService {
  private final AgendaService agendas;
  private final EmpresaAgendasService empresas;
  private final AgendaUsuarioPerfilService perfis;
  private final AgendaUsuariosService participantes;
  private final CalculoResultadoAgendaService calculo;
  private final AcessoService acesso;
  public AgendaAplicacaoService(AgendaService agendas,EmpresaAgendasService empresas,AgendaUsuarioPerfilService perfis,AgendaUsuariosService participantes,CalculoResultadoAgendaService calculo,AcessoService acesso) {this.agendas=agendas;this.empresas=empresas;this.perfis=perfis;this.participantes=participantes;this.calculo=calculo;this.acesso=acesso;}
  public static AgendaApi.Dados dados(Agenda a) {return new AgendaApi.Dados(a.getA04_codigo(),a.getA01_codigo(),a.getA04_titulo(),a.getA04_descricao(),a.getA04_status(),a.getA04_data_limite()==null?null:a.getA04_data_limite().toLocalDate(),a.getA04_resultado(),a.getA04_certeza_resultado(),a.getA04_contradicao_resultado());}
  public List<AgendaApi.Dados> listar(long ator,long empresa) {var p=acesso.empresa(ator,empresa);var d=new EmpresaAgendasDados();var e=new Empresa();e.setA01_codigo(empresa);d.setoEmpresaModel(e);var u=new Usuario();u.setA02_codigo(ator);d.setoUsuarioModel(u);d=acesso.gestor(p)?empresas.selectAgendasDaEmpresa(d):empresas.selectAgendasDaEmpresaUsuario(d);return d.getArrAgendaModel().stream().map(AgendaAplicacaoService::dados).sorted(Comparator.comparingInt(AgendaApi.Dados::status).thenComparing(AgendaApi.Dados::titulo)).toList();}
  public AgendaApi.Dados buscar(long ator,long id) {return dados(acesso.agenda(ator,id,false));}
  public AgendaApi.Dados criar(long ator,long empresa,AgendaApi.Cadastro c) {acesso.gerenciarEmpresa(ator,empresa);var a=new Agenda();a.setA01_codigo(empresa);preencher(a,c);a.setA04_codigo(agendas.insertAgenda(a));var p=new AgendaUsuarioPerfil();p.setA02_codigo(ator);p.setA04_codigo(a.getA04_codigo());p.setA05_perfil_agenda_usuario_titular(1);perfis.insertPerfilUsuarioAgenda(p);return dados(a);}
  public AgendaApi.Dados editar(long ator,long id,AgendaApi.Cadastro c) {var a=acesso.agenda(ator,id,true);aberta(a);preencher(a,c);agendas.updateAgenda(a);return dados(a);}
  private void preencher(Agenda a,AgendaApi.Cadastro c) {a.setA04_titulo(c.titulo().trim());a.setA04_descricao(c.descricao());a.setA04_status_dt_limite(c.dataLimite()==null?0:1);a.setA04_data_limite(c.dataLimite()==null?null:java.sql.Date.valueOf(c.dataLimite()));}
  public AgendaApi.Dados fluxo(long ator,long id,String acao) {var a=acesso.agenda(ator,id,true);int proximo=switch(acao){case "encaminhar"->1;case "liberar"->2;case "encerrar"->9;default->throw new RegraNegocioException(400,"Ação de fluxo inválida.");};if(!((a.getA04_status()==0&&proximo==1)||(a.getA04_status()==1&&proximo==2)||(a.getA04_status()==2&&proximo==9)))throw new RegraNegocioException(409,"Transição incompatível com o status atual.");a.setA04_status(proximo);agendas.updateStatusAgenda(a);return dados(a);}
  public AgendaApi.Dados calcular(long ator,long id) {var a=acesso.agenda(ator,id,true);if(!"OK".equals(calculo.geraResultados(a,0)))throw new RegraNegocioException(409,"Não foi possível calcular: confira fatores, especialistas e pareceres.");return buscar(ator,id);}
  public List<AgendaApi.Participante> participantes(long ator,long id) {acesso.agenda(ator,id,false);var d=new AgendaUsuariosDados();var a=new Agenda();a.setA04_codigo(id);d.setoAgendaModel(a);d=participantes.selectUsuariosDaAgenda(d);List<AgendaApi.Participante> lista=new ArrayList<>();for(int i=0;i<d.getArrUsuarioModel().size();i++){var p=d.getArrAgendaUsuarioPerfilModel().get(i);lista.add(new AgendaApi.Participante(UsuarioAplicacaoService.dados(d.getArrUsuarioModel().get(i)),new AgendaApi.Perfil(p.getA05_perfil_agenda_usuario_titular()==1,p.getA05_perfil_agenda_usuario_facilitador()==1,p.getA05_perfil_agenda_usuario_especialista()==1,p.getA05_perfil_agenda_usuario_analista()==1)));}return lista;}
  public void vincular(long ator,long id,long usuario,AgendaApi.Perfil c) {var a=acesso.agenda(ator,id,true);aberta(a);acesso.empresa(usuario,a.getA01_codigo());var p=acesso.perfil(usuario,id);boolean novo=p==null;if(novo)p=new AgendaUsuarioPerfil();p.setA04_codigo(id);p.setA02_codigo(usuario);p.setA05_perfil_agenda_usuario_titular(c.titular()?1:0);p.setA05_perfil_agenda_usuario_facilitador(c.facilitador()?1:0);p.setA05_perfil_agenda_usuario_especialista(c.especialista()?1:0);p.setA05_perfil_agenda_usuario_analista(c.analista()?1:0);if(novo)perfis.insertPerfilUsuarioAgenda(p);else perfis.updatePerfilUsuarioAgenda(p);}
  public void remover(long ator,long id,long usuario) {aberta(acesso.agenda(ator,id,true));var p=acesso.perfil(usuario,id);if(p==null)throw new RegraNegocioException(404,"Participante não encontrado.");perfis.deleteAgendaUsuarioPerfil(p);}
  public static void aberta(Agenda a) {if(a.getA04_status()==9)throw new RegraNegocioException(409,"A agenda está encerrada.");}
}
