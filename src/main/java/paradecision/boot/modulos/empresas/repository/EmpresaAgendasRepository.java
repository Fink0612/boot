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
public class EmpresaAgendasRepository {
  private final BancoDados banco;
  public EmpresaAgendasRepository(BancoDados banco) { this.banco = banco; }
  public EmpresaAgendasDados selectAgendasDaEmpresa(EmpresaAgendasDados v) { v.setArrAgendaModel(new ArrayList<>(banco.listar("agenda_04",campos("a01_codigo",v.getoEmpresaModel().getA01_codigo())).stream().map(Mapeamento::Agenda).toList())); return v; }
  public EmpresaAgendasDados selectAgendasDaEmpresaUsuario(EmpresaAgendasDados v) { v.setArrAgendaModel(new ArrayList<>(banco.listar("vw_participantes_agenda",campos("a01_codigo",v.getoEmpresaModel().getA01_codigo(),"a02_codigo",v.getoUsuarioModel().getA02_codigo())).stream().map(Mapeamento::Agenda).toList())); return v; }
}
