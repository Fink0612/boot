package paradecision.boot.modulos.compartilhado.infra;
public class FalhaPersistencia extends RuntimeException {
  public FalhaPersistencia(Throwable causa) { super("Não foi possível acessar o banco de dados.",causa); }
  public FalhaPersistencia(String mensagem) { super(mensagem); }
}
