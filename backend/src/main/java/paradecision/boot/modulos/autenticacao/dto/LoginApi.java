package paradecision.boot.modulos.autenticacao.dto;
import jakarta.validation.constraints.*;
public record LoginApi(@NotBlank String login,@NotBlank String senha) {}
