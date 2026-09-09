package paradecision.boot.modulos.usuarios.dto;
import jakarta.validation.constraints.*;
public final class UsuarioApi {
  public record Dados(long id,String nome,String login,String email,int status) {}
  public record Cadastro(@NotBlank @Size(max=200) String nome,@NotBlank @Size(max=100) String login,@NotBlank @Email @Size(max=255) String email,@Size(max=200) String senha,@Min(0) @Max(1) int status,boolean chefe,boolean padrao) {}
}
