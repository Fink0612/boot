package paradecision.boot.modulos.pareceres.service;
import java.util.List;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.pareceres.dto.ParecerApi;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.fatores.service.FatorAplicacaoService;
import paradecision.boot.modulos.agendas.dto.AgendaUsuarioPareceresDados;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPareceresService;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.autenticacao.service.AcessoService;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
@Service
public class ParecerAplicacaoService {
  private final ParecerFatorUsuarioService pareceres;
  private final AgendaUsuarioPareceresService listas;
  private final FatorAplicacaoService fatores;
  private final AcessoService acesso;
  public ParecerAplicacaoService(ParecerFatorUsuarioService pareceres,AgendaUsuarioPareceresService listas,FatorAplicacaoService fatores,AcessoService acesso){this.pareceres=pareceres;this.listas=listas;this.fatores=fatores;this.acesso=acesso;}
  public List<ParecerApi.Dados> listar(long ator,long agenda) {var d=new AgendaUsuarioPareceresDados();d.setoAgendaModel(acesso.agenda(ator,agenda,false));var u=new Usuario();u.setA02_codigo(ator);d.setoUsuarioModel(u);return listas.selectPareceresAgUsu(d).getArrParecerFatorUsuarioModel().stream().map(p->new ParecerApi.Dados(p.getA06_codigo(),p.getStr_a07_certeza()==null?null:p.getA07_certeza(),p.getStr_a07_contradicao()==null?null:p.getA07_contradicao())).toList();}
  public ParecerApi.Dados salvar(long ator,long fator,ParecerApi.Cadastro c) {var f=fatores.obter(fator);var a=acesso.agenda(ator,f.getA04_codigo(),false);var perfil=acesso.perfil(ator,f.getA04_codigo());if(perfil==null||perfil.getA05_perfil_agenda_usuario_especialista()!=1)throw new RegraNegocioException(403,"Apenas especialistas da agenda podem registrar pareceres.");if(a.getA04_status()!=2)throw new RegraNegocioException(409,"A agenda não está recebendo pareceres.");if(a.getA04_status_dt_limite()==1&&a.getA04_data_limite()!=null&&a.getA04_data_limite().toLocalDate().isBefore(java.time.LocalDate.now()))throw new RegraNegocioException(409,"O prazo desta agenda terminou.");var p=new ParecerFatorUsuario();p.setA06_codigo(fator);p.setA02_codigo(ator);p=pareceres.selectParecerFatorUsuario(p);p.setA07_certeza(c.certeza()==null?-1:c.certeza());p.setA07_contradicao(c.contradicao()==null?-1:c.contradicao());if(p.getA07_codigo()==0)pareceres.insertParecerFatorUsuario(p);else pareceres.updateParecerFatorUsuario(p);return new ParecerApi.Dados(fator,c.certeza(),c.contradicao());}
}
