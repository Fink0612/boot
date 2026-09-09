package paradecision.boot.modulos.compartilhado.service;
public class RegraNegocioException extends RuntimeException {
  private final int status;
  public RegraNegocioException(int status,String mensagem) { super(mensagem);this.status=status; }
  public int status() { return status; }
}
