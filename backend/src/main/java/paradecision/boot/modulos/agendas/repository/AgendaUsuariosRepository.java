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
public class AgendaUsuariosRepository {
  public ArrayList<Usuario> getArrEspecialistasModel(Agenda v) { return new ArrayList<>(banco.listar("vw_agendas_usuarios",campos("a04_codigo",v.getA04_codigo(),"a05_perfil_agenda_usuario_especialista",1)).stream().map(Mapeamento::Usuario).toList()); }
  private final BancoDados banco;
  public AgendaUsuariosRepository(BancoDados banco) { this.banco = banco; }
  public AgendaUsuariosDados selectUsuariosDaAgenda(AgendaUsuariosDados v) { for(var r:banco.listar("vw_agendas_usuarios",campos("a04_codigo",v.getoAgendaModel().getA04_codigo()))) { v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); v.getArrAgendaUsuarioPerfilModel().add(Mapeamento.AgendaUsuarioPerfil(r)); } return v; }
  public ArrayList<Usuario> getArrUsuariosModel(Agenda v) { return new ArrayList<>(banco.listar("vw_agendas_usuarios",campos("a04_codigo",v.getA04_codigo())).stream().map(Mapeamento::Usuario).toList()); }
}
