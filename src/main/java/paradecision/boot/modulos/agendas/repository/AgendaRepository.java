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
public class AgendaRepository {
  private final BancoDados banco;
  public AgendaRepository(BancoDados banco) { this.banco = banco; }
  public Agenda selectAgenda(Agenda v) { return banco.listar("agenda_04", campos("a04_codigo",v.getA04_codigo())).stream().findFirst().map(Mapeamento::Agenda).orElseGet(Agenda::new); }

  public long insertAgenda(Agenda v) { var d=dados(v); d.put("a01_codigo",v.getA01_codigo()); d.put("a04_status",v.getA04_status()); return banco.inserir("agenda_04",d).numero("a04_codigo"); }
  public String updateAgenda(Agenda v) { banco.atualizar("agenda_04",campos("a04_codigo",v.getA04_codigo()),dados(v)); return "OK"; }
  public String updateStatusAgenda(Agenda v) { banco.atualizar("agenda_04",campos("a04_codigo",v.getA04_codigo()),campos("a04_status",v.getA04_status())); return "OK"; }
  private Map<String,Object> dados(Agenda v) { return campos("a04_titulo",v.getA04_titulo(),"a04_descricao",v.getA04_descricao(),"a04_status_dt_limite",v.getA04_status_dt_limite(),"a04_data_limite",v.getA04_data_limite()); }

}
