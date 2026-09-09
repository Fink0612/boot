package paradecision.boot.modulos.fatores.dto;
import jakarta.validation.constraints.*;
public final class FatorApi {
  public record Dados(long id,long agendaId,String titulo,String descricao,int sequencia,String resultado,double certeza,double contradicao) {}
  public record Cadastro(@NotBlank @Size(max=255) String titulo,@Size(max=10000) String descricao,@Min(0) int sequencia) {}
}
