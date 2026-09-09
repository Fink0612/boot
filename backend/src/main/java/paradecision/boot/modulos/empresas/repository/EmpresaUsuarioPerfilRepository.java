package paradecision.boot.modulos.empresas.repository;
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
public class EmpresaUsuarioPerfilRepository {
  private final BancoDados banco;
  public EmpresaUsuarioPerfilRepository(BancoDados banco) { this.banco = banco; }
  public EmpresaUsuarioPerfil selectEmpresaUsuario(EmpresaUsuarioPerfil v) { return banco.listar("empresa_usuario_perfil_03", campos("a01_codigo",v.getA01_codigo(),"a02_codigo",v.getA02_codigo())).stream().findFirst().map(Mapeamento::EmpresaUsuarioPerfil).orElse(null); }

  public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfil v) { var d=dados(v); d.put("a01_codigo",v.getA01_codigo()); d.put("a02_codigo",v.getA02_codigo()); banco.inserir("empresa_usuario_perfil_03",d); return 1; }
  public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfil v) { banco.atualizar("empresa_usuario_perfil_03",campos("a01_codigo",v.getA01_codigo(),"a02_codigo",v.getA02_codigo()),campos("a03_perfil_chefe",v.getA03_perfil_chefe(),"a03_perfil_padrao",v.getA03_perfil_padrao())); return "OK"; }
  private Map<String,Object> dados(EmpresaUsuarioPerfil v) { return campos("a03_perfil_paraviverbem",v.getA03_perfil_paraviverbem(),"a03_perfil_administrador",v.getA03_perfil_administrador(),"a03_perfil_chefe",v.getA03_perfil_chefe(),"a03_perfil_padrao",v.getA03_perfil_padrao()); }

}
