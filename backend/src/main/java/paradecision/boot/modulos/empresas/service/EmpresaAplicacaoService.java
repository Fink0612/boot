package paradecision.boot.modulos.empresas.service;
import java.util.*;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.empresas.dto.*;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.dto.UsuarioEmpresasDados;
import paradecision.boot.modulos.usuarios.service.*;
import paradecision.boot.modulos.autenticacao.service.AcessoService;
@Service
public class EmpresaAplicacaoService {
  private final UsuarioEmpresasService empresas;
  private final EmpresaUsuariosService usuarios;
  private final AcessoService acesso;
  public EmpresaAplicacaoService(UsuarioEmpresasService empresas,EmpresaUsuariosService usuarios,AcessoService acesso) {this.empresas=empresas;this.usuarios=usuarios;this.acesso=acesso;}
  public List<EmpresaApi.Dados> listar(long usuario) {var d=new UsuarioEmpresasDados();var u=new Usuario();u.setA02_codigo(usuario);d.setoUsuarioModel(u);d=empresas.selectEmpresasDoUsuario(d);List<EmpresaApi.Dados> lista=new ArrayList<>();for(int i=0;i<d.getArrEmpresaModel().size();i++){var e=d.getArrEmpresaModel().get(i);lista.add(new EmpresaApi.Dados(e.getA01_codigo(),e.getA01_nome(),e.getA01_descricao(),acesso.gestor(d.getArrEmpresaUsuarioPerfilModel().get(i))));}return lista;}
  public List<EmpresaApi.Participante> usuarios(long ator,long id) {acesso.gerenciarEmpresa(ator,id);var d=new EmpresaUsuariosDados();var e=new Empresa();e.setA01_codigo(id);d.setoEmpresaModel(e);d=usuarios.selectUsuariosDaEmpresa(d);List<EmpresaApi.Participante> lista=new ArrayList<>();for(int i=0;i<d.getArrUsuarioModel().size();i++){var p=d.getArrEmpresaUsuarioPerfilModel().get(i);lista.add(new EmpresaApi.Participante(UsuarioAplicacaoService.dados(d.getArrUsuarioModel().get(i)),p.getA03_perfil_chefe()==1,p.getA03_perfil_padrao()==1));}return lista;}
}
