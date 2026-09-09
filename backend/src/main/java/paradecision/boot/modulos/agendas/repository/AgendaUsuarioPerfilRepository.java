package paradecision.boot.modulos.agendas.repository;
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
public class AgendaUsuarioPerfilRepository {
  private final BancoDados banco;
  public AgendaUsuarioPerfilRepository(BancoDados banco) { this.banco = banco; }
  public AgendaUsuarioPerfil selectAgendaUsuarioPerfil(AgendaUsuarioPerfil v) { return banco.listar("usuario_agenda_05", campos("a04_codigo",v.getA04_codigo(),"a02_codigo",v.getA02_codigo())).stream().findFirst().map(Mapeamento::AgendaUsuarioPerfil).orElse(null); }

  public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfil v) { var d=dados(v); d.put("a04_codigo",v.getA04_codigo()); d.put("a02_codigo",v.getA02_codigo()); d.put("a05_num_sequencia",v.getA05_num_sequencia()); banco.inserir("usuario_agenda_05",d); return "OK"; }
  public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfil v) { banco.atualizar("usuario_agenda_05",campos("a05_codigo",v.getA05_codigo()),dados(v)); return "OK"; }
  public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfil v) { banco.excluir("usuario_agenda_05",campos("a05_codigo",v.getA05_codigo())); return "OK"; }
  private Map<String,Object> dados(AgendaUsuarioPerfil v) { return campos("a05_perfil_agenda_usuario_titular",v.getA05_perfil_agenda_usuario_titular(),"a05_perfil_agenda_usuario_facilitador",v.getA05_perfil_agenda_usuario_facilitador(),"a05_perfil_agenda_usuario_especialista",v.getA05_perfil_agenda_usuario_especialista(),"a05_perfil_agenda_usuario_analista",v.getA05_perfil_agenda_usuario_analista()); }

}
