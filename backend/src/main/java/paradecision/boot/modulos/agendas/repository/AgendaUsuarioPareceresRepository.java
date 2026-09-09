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
public class AgendaUsuarioPareceresRepository {
  private final BancoDados banco;
  public AgendaUsuarioPareceresRepository(BancoDados banco) { this.banco = banco; }
  public AgendaUsuarioPareceresDados selectPareceresAgUsu(AgendaUsuarioPareceresDados v) { v.setArrParecerFatorUsuarioModel(new ArrayList<>(banco.listar("vw_fatores_pareceres",campos("a04_codigo",v.getoAgendaModel().getA04_codigo(),"a02_codigo",v.getoUsuarioModel().getA02_codigo())).stream().map(Mapeamento::ParecerFatorUsuario).toList())); return v; }
}
