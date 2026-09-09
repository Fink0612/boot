package paradecision.boot.modulos.empresas.dto;
import paradecision.boot.modulos.usuarios.dto.UsuarioApi;
public final class EmpresaApi {
  public record Dados(long id,String nome,String descricao,boolean podeGerenciar) {}
  public record Participante(UsuarioApi.Dados usuario,boolean chefe,boolean padrao) {}
}
