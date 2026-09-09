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
public class AgendaFatoresRepository {
  private final BancoDados banco;
  public AgendaFatoresRepository(BancoDados banco) { this.banco = banco; }
  public AgendaFatoresDados selectFatoresDaAgenda(AgendaFatoresDados v) { for(var r:banco.listar("vw_agendas_fatores",campos("a04_codigo",v.getoAgendaModel().getA04_codigo()))) { v.getArrFatorModel().add(Mapeamento.Fator(r)); v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); } return v; }
  public ArrayList<Fator> getArrFatoresModel(Agenda v) { return new ArrayList<>(banco.listar("fator_06",campos("a04_codigo",v.getA04_codigo())).stream().map(Mapeamento::Fator).toList()); }
  public String updateGrausFatoresDaAgenda(AgendaFatoresDados v) { var a=v.getoAgendaModel(); banco.atualizar("agenda_04",campos("a04_codigo",a.getA04_codigo()),campos("a04_certeza_resultado",a.getA04_certeza_resultado(),"a04_contradicao_resultado",a.getA04_contradicao_resultado(),"a04_resultado",a.getA04_resultado())); for(var f:v.getArrFatorModel()) banco.atualizar("fator_06",campos("a06_codigo",f.getA06_codigo()),campos("a06_certeza_resultante_fator",f.getA06_certeza_resultante_fator(),"a06_contradicao_resultante_fator",f.getA06_contradicao_resultante_fator(),"a06_resultado_fator",f.getA06_resultado_fator())); return "OK"; }
}
