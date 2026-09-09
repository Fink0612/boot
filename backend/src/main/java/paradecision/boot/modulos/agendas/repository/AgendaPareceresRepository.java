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
public class AgendaPareceresRepository {
  public AgendaPareceresDados selectPareceresDaAgenda(AgendaPareceresDados v) { for(var r:banco.listar("vw_fatores_pareceres",campos("a04_codigo",v.getoAgendaModel().getA04_codigo()))) { v.getArrUsuarioModel().add(Mapeamento.Usuario(r)); v.getArrParecerFatorUsuarioModel().add(Mapeamento.ParecerFatorUsuario(r)); } return v; }
  private final BancoDados banco;
  public AgendaPareceresRepository(BancoDados banco) { this.banco = banco; }
  public ArrayList<ParecerFatorUsuario> getArrPareceresModel(Agenda v) { return new ArrayList<>(banco.listar("vw_fatores_pareceres",campos("a04_codigo",v.getA04_codigo())).stream().map(Mapeamento::ParecerFatorUsuario).toList()); }
}
