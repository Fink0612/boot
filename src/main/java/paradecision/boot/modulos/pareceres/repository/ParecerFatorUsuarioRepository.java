package paradecision.boot.modulos.pareceres.repository;
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
public class ParecerFatorUsuarioRepository {
  private final BancoDados banco;
  public ParecerFatorUsuarioRepository(BancoDados banco) { this.banco = banco; }
  public ParecerFatorUsuario selectParecerFatorUsuario(ParecerFatorUsuario v) { return banco.listar("parecer_fator_usuario_07", campos("a06_codigo",v.getA06_codigo(),"a02_codigo",v.getA02_codigo())).stream().findFirst().map(Mapeamento::ParecerFatorUsuario).orElse(v); }

  public String insertParecerFatorUsuario(ParecerFatorUsuario v) { var d=dados(v); d.put("a06_codigo",v.getA06_codigo()); d.put("a02_codigo",v.getA02_codigo()); d.put("a07_num_sequencia",v.getA07_num_sequencia()); banco.inserir("parecer_fator_usuario_07",d); return "OK"; }
  public String updateParecerFatorUsuario(ParecerFatorUsuario v) { banco.atualizar("parecer_fator_usuario_07",campos("a07_codigo",v.getA07_codigo()),dados(v)); return "OK"; }
  private Map<String,Object> dados(ParecerFatorUsuario v) { return campos("a07_certeza",v.getA07_certeza()<0?null:v.getA07_certeza(),"a07_contradicao",v.getA07_contradicao()<0?null:v.getA07_contradicao()); }

}
