package paradecision.boot.modulos.usuarios.service;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.usuarios.dto.UsuarioApi;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.empresas.service.EmpresaUsuarioPerfilService;
import paradecision.boot.modulos.autenticacao.service.AcessoService;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
@Service
public class UsuarioAplicacaoService {
  private final UsuarioService usuarios;
  private final EmpresaUsuarioPerfilService perfis;
  private final AcessoService acesso;
  public UsuarioAplicacaoService(UsuarioService usuarios,EmpresaUsuarioPerfilService perfis,AcessoService acesso) {this.usuarios=usuarios;this.perfis=perfis;this.acesso=acesso;}
  public static UsuarioApi.Dados dados(Usuario u) {return new UsuarioApi.Dados(u.getA02_codigo(),u.getA02_nome(),u.getA02_usuario(),u.getA02_email(),u.getA02_status());}
  public UsuarioApi.Dados autenticar(String login,String senha) {var u=new Usuario();u.setA02_usuario(login.trim().toLowerCase(java.util.Locale.ROOT));u.setA02_senha(senha);u=usuarios.selectUserLogin(u);if(u.getA02_codigo()==0||u.getA02_status()!=1)throw new RegraNegocioException(401,"Usuário ou senha inválidos.");return dados(u);}
  public UsuarioApi.Dados buscar(long id) {var u=new Usuario();u.setA02_codigo(id);u=usuarios.selectUserByCode(u);if(u.getA02_codigo()==0)throw new RegraNegocioException(404,"Usuário não encontrado.");return dados(u);}
  public UsuarioApi.Dados salvar(long ator,long empresa,long id,UsuarioApi.Cadastro c) {
    acesso.gerenciarEmpresa(ator,empresa);var u=new Usuario();
    if(id>0){acesso.empresa(id,empresa);u.setA02_codigo(id);u=usuarios.selectUserByCode(u);}
    if(id==0&&(c.senha()==null||c.senha().length()<8))throw new RegraNegocioException(400,"A senha deve ter pelo menos 8 caracteres.");
    var existente=new Usuario();existente.setA02_usuario(c.login().trim().toLowerCase(java.util.Locale.ROOT));existente=usuarios.selectUserByUser(existente);
    if(existente.getA02_codigo()>0&&existente.getA02_codigo()!=id)throw new RegraNegocioException(409,"Este login já está cadastrado.");
    u.setA02_nome(MetodosUteis.padronizarMaiusculoCE(c.nome()));u.setA02_usuario(c.login().trim().toLowerCase(java.util.Locale.ROOT));u.setA02_email(c.email().trim().toLowerCase(java.util.Locale.ROOT));u.setA02_status(c.status());
    if(c.senha()!=null&&!c.senha().isBlank()){if(c.senha().length()<8)throw new RegraNegocioException(400,"A senha deve ter pelo menos 8 caracteres.");u.setA02_senha(Senhas.codificar(c.senha()));}
    if(id==0){u.setA02_codigo_link(MetodosUteis.gerarCodigo(25));u=usuarios.insertUsuario(u);}else usuarios.updateUsuario(u);
    var p=new EmpresaUsuarioPerfil();p.setA01_codigo(empresa);p.setA02_codigo(u.getA02_codigo());p.setA03_perfil_chefe(c.chefe()?1:0);p.setA03_perfil_padrao(c.padrao()?1:0);
    if(id==0)perfis.insertEmpresaUsuarioPerfil(p);else perfis.updateEmpresaUsuarioPerfil(p);
    return dados(u);
  }
}
