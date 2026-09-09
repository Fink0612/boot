package paradecision.boot.modulos.pareceres.dto;
import jakarta.validation.constraints.*;
public final class ParecerApi {
  public record Dados(long fatorId,Double certeza,Double contradicao) {}
  public record Cadastro(@DecimalMin("0") @DecimalMax("1") Double certeza,@DecimalMin("0") @DecimalMax("1") Double contradicao) {}
}
