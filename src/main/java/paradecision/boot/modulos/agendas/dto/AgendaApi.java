package paradecision.boot.modulos.agendas.dto;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import paradecision.boot.modulos.usuarios.dto.UsuarioApi;
public final class AgendaApi {
  public record Dados(long id,long empresaId,String titulo,String descricao,int status,LocalDate dataLimite,String resultado,double certeza,double contradicao) {}
  public record Cadastro(@NotBlank @Size(max=255) String titulo,@Size(max=10000) String descricao,LocalDate dataLimite) {}
  public record Fluxo(@NotBlank String acao) {}
  public record Perfil(boolean titular,boolean facilitador,boolean especialista,boolean analista) {}
  public record Participante(UsuarioApi.Dados usuario,Perfil perfil) {}
}
