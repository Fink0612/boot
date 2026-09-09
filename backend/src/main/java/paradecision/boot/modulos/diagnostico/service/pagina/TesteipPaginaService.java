package paradecision.boot.modulos.diagnostico.service.pagina;

import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class TesteipPaginaService {
  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String endereco = "";
    String hostname = "";
    try {
      InetAddress addr = InetAddress.getLocalHost();
      endereco = addr.getHostAddress();
      hostname = addr.getHostName();
    } catch (Exception excecao) {
      endereco = "OPS!!";
    }

    pagina.put("endereco", String.valueOf(endereco));

    pagina.put("hostname", String.valueOf(hostname));

    return pagina;
  }
}
