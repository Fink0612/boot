package paradecision.boot.modulos.diagnostico.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class TestegeralPaginaService {
  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    double valor = -4.56;

    pagina.put("valor", String.valueOf(valor));

    pagina.put("Math_abs_valor", String.valueOf(Math.abs(valor)));

    return pagina;
  }
}
