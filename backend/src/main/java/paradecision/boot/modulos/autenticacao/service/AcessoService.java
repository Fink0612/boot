package paradecision.boot.modulos.autenticacao.service;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.empresas.service.EmpresaUsuarioPerfilService;
import paradecision.boot.modulos.agendas.entity.*;
import paradecision.boot.modulos.agendas.service.*;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
@Service
public class AcessoService {
  private final EmpresaUsuarioPerfilService empresas;
  private final AgendaService agendas;
  private final AgendaUsuarioPerfilService participantes;
  public AcessoService(EmpresaUsuarioPerfilService empresas,AgendaService agendas,AgendaUsuarioPerfilService participantes) {this.empresas=empresas;this.agendas=agendas;this.participantes=participantes;}
  public EmpresaUsuarioPerfil empresa(long usuario,long empresa) {
    var p=new EmpresaUsuarioPerfil();p.setA01_codigo(empresa);p.setA02_codigo(usuario);p=empresas.selectEmpresaUsuario(p);
    if(p==null)throw new RegraNegocioException(403,"Você não participa desta empresa.");return p;
  }
  public boolean gestor(EmpresaUsuarioPerfil p) {return p.getA03_perfil_paraviverbem()==1||p.getA03_perfil_administrador()==1||p.getA03_perfil_chefe()==1;}
  public void gerenciarEmpresa(long usuario,long empresa) {if(!gestor(empresa(usuario,empresa)))throw new RegraNegocioException(403,"Seu perfil não permite gerenciar a empresa.");}
  public Agenda agenda(long usuario,long id,boolean escrita) {
    var a=new Agenda();a.setA04_codigo(id);a=agendas.selectAgenda(a);
    if(a.getA04_codigo()==0)throw new RegraNegocioException(404,"Agenda não encontrada.");
    var e=empresa(usuario,a.getA01_codigo());var p=perfil(usuario,id);
    if(!gestor(e)&&(p==null||(escrita&&p.getA05_perfil_agenda_usuario_titular()!=1&&p.getA05_perfil_agenda_usuario_facilitador()!=1)))throw new RegraNegocioException(403,"Seu perfil não permite esta operação na agenda.");
    return a;
  }
  public AgendaUsuarioPerfil perfil(long usuario,long agenda) {var p=new AgendaUsuarioPerfil();p.setA02_codigo(usuario);p.setA04_codigo(agenda);return participantes.selectAgendaUsuarioPerfil(p);}
}
