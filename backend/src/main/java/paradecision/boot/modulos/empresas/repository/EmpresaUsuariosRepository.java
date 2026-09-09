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
public class EmpresaUsuariosRepository {
  private final BancoDados banco;
  public EmpresaUsuariosRepository(BancoDados banco) { this.banco = banco; }
  public EmpresaUsuariosDados selectUsuariosDaEmpresa(EmpresaUsuariosDados v) { for(var r:banco.listar("vw_empresa_usuarios",campos("a01_codigo",v.getoEmpresaModel().getA01_codigo()))) { v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); v.getArrEmpresaUsuarioPerfilModel().add(Mapeamento.EmpresaUsuarioPerfil(r)); } return v; }
}
