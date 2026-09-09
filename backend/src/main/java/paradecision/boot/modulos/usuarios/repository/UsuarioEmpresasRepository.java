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
public class UsuarioEmpresasRepository {
  private final BancoDados banco;
  public UsuarioEmpresasRepository(BancoDados banco) { this.banco = banco; }
  public UsuarioEmpresasDados selectEmpresasDoUsuario(UsuarioEmpresasDados v) { for(var r:banco.listar("vw_usuario_empresas",campos("a02_codigo",v.getoUsuarioModel().getA02_codigo()))) { v.getArrEmpresaModel().add(Mapeamento.Empresa(r)); v.getArrEmpresaUsuarioPerfilModel().add(Mapeamento.EmpresaUsuarioPerfil(r)); } return v; }
}
