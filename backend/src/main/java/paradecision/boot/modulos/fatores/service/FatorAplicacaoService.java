package paradecision.boot.modulos.fatores.service;
import java.util.List;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.fatores.dto.FatorApi;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.service.*;
import paradecision.boot.modulos.autenticacao.service.AcessoService;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
@Service
public class FatorAplicacaoService {
  private final FatorService fatores;
  private final AgendaFatoresService listas;
  private final AcessoService acesso;
  public FatorAplicacaoService(FatorService fatores,AgendaFatoresService listas,AcessoService acesso){this.fatores=fatores;this.listas=listas;this.acesso=acesso;}
  public static FatorApi.Dados dados(Fator f){return new FatorApi.Dados(f.getA06_codigo(),f.getA04_codigo(),f.getA06_titulo(),f.getA06_descricao(),f.getA06_num_sequencia(),f.getA06_resultado_fator(),f.getA06_certeza_resultante_fator(),f.getA06_contradicao_resultante_fator());}
  public List<FatorApi.Dados> listar(long ator,long agenda) {var d=new AgendaFatoresDados();d.setoAgendaModel(acesso.agenda(ator,agenda,false));return listas.selectFatoresDaAgenda(d).getArrFatorModel().stream().map(FatorAplicacaoService::dados).toList();}
  public Fator obter(long id) {var f=new Fator();f.setA06_codigo(id);f=fatores.selectFator(f);if(f.getA06_codigo()==0)throw new RegraNegocioException(404,"Fator não encontrado.");return f;}
  public FatorApi.Dados criar(long ator,long agenda,FatorApi.Cadastro c) {AgendaAplicacaoService.aberta(acesso.agenda(ator,agenda,true));var f=new Fator();f.setA04_codigo(agenda);f.setA02_codigo(ator);f.setA06_num_sequencia(c.sequencia());preencher(f,c);fatores.insertFator(f);return dados(f);}
  public FatorApi.Dados editar(long ator,long id,FatorApi.Cadastro c) {var f=obter(id);AgendaAplicacaoService.aberta(acesso.agenda(ator,f.getA04_codigo(),true));preencher(f,c);fatores.updateFator(f);return dados(f);}
  private void preencher(Fator f,FatorApi.Cadastro c){f.setA06_titulo(c.titulo().trim());f.setA06_descricao(c.descricao());}
}
