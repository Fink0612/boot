package paradecision.boot.modulos.usuarios.repository;
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
public class UsuarioRepository {
  private final BancoDados banco;
  public UsuarioRepository(BancoDados banco) { this.banco = banco; }
  public Usuario selectUserLogin(Usuario v) { return banco.listar("usuario_02", campos("a02_usuario", v.getA02_usuario())).stream().findFirst().map(Mapeamento::Usuario).orElseGet(Usuario::new); }
  public Usuario selectUserByCode(Usuario v) { return banco.listar("usuario_02", campos("a02_codigo", v.getA02_codigo())).stream().findFirst().map(Mapeamento::Usuario).orElseGet(Usuario::new); }
  public Usuario selectUserIni(Usuario v) { return banco.listar("usuario_02", campos("a02_codigo_link", v.getA02_codigo_link())).stream().findFirst().map(Mapeamento::Usuario).orElseGet(Usuario::new); }

  public Usuario insertUsuario(Usuario v) { return Mapeamento.Usuario(banco.inserir("usuario_02", dados(v))); }
  public String updateUsuario(Usuario v) { banco.atualizar("usuario_02", campos("a02_codigo",v.getA02_codigo()),dados(v)); return "OK"; }
  public void updateSenhaUsuario(Usuario v) { banco.atualizar("usuario_02",campos("a02_usuario",v.getA02_usuario()),campos("a02_senha",v.getA02_senha(),"a02_codigo_link",v.getA02_codigo_link())); }
  private Map<String,Object> dados(Usuario v) { return campos("a02_nome",v.getA02_nome(),"a02_usuario",v.getA02_usuario(),"a02_senha",v.getA02_senha(),"a02_email",v.getA02_email(),"a02_status",v.getA02_status(),"a02_codigo_link",v.getA02_codigo_link()); }

}
