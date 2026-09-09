package paradecision.boot.modulos.fatores.repository;
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
public class FatorRepository {
  private final BancoDados banco;
  public FatorRepository(BancoDados banco) { this.banco = banco; }
  public Fator selectFator(Fator v) { return banco.listar("fator_06", campos("a06_codigo",v.getA06_codigo())).stream().findFirst().map(Mapeamento::Fator).orElseGet(Fator::new); }

  public String insertFator(Fator v) { var d=campos("a06_titulo",v.getA06_titulo(),"a06_descricao",v.getA06_descricao(),"a06_num_sequencia",v.getA06_num_sequencia(),"a04_codigo",v.getA04_codigo(),"a02_codigo",v.getA02_codigo()); v.setA06_codigo(banco.inserir("fator_06",d).numero("a06_codigo")); return "OK"; }
  public String updateFator(Fator v) { banco.atualizar("fator_06",campos("a06_codigo",v.getA06_codigo()),campos("a06_titulo",v.getA06_titulo(),"a06_descricao",v.getA06_descricao())); return "OK"; }

}
