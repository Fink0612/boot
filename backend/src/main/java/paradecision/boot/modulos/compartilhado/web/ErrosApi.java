package paradecision.boot.modulos.compartilhado.web;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import paradecision.boot.modulos.compartilhado.service.RegraNegocioException;
import paradecision.boot.modulos.compartilhado.infra.FalhaPersistencia;
@RestControllerAdvice
public class ErrosApi {
  @ExceptionHandler(RegraNegocioException.class)
  ResponseEntity<ProblemDetail> regra(RegraNegocioException e) { return erro(e.status(),e.getMessage()); }
  @ExceptionHandler(FalhaPersistencia.class)
  ResponseEntity<ProblemDetail> banco(FalhaPersistencia e) { return erro(503,e.getMessage()); }
  @ExceptionHandler({MethodArgumentNotValidException.class,IllegalArgumentException.class,org.springframework.http.converter.HttpMessageNotReadableException.class})
  ResponseEntity<ProblemDetail> entrada(Exception e) { return erro(400,"Dados inválidos. Confira os campos obrigatórios, datas e limites."); }
  private ResponseEntity<ProblemDetail> erro(int status,String mensagem) { return ResponseEntity.status(status).body(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(status),mensagem)); }
}
