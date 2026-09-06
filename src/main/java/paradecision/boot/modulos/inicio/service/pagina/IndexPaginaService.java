package paradecision.boot.modulos.inicio.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.UsuarioService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class IndexPaginaService {
  private final UsuarioService usuarioService;

  public IndexPaginaService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String indCodini = "";
    String indIFrame = "";
    String indUsuSessao = "";
    String indNomeUsuSessao = "";

    indCodini = formulario.valor("codini");
    if (indCodini == null) indCodini = "";
    Usuario indUsuarioModel = new Usuario();

    if (!"".equals(indCodini.trim())) {
      indUsuarioModel.setA02_codigo_link(indCodini);
      indUsuarioModel = usuarioService.selectUserIni(indUsuarioModel);
    }
    indIFrame = formulario.contextPath() + "/autenticacao/login";
    if (indUsuarioModel.getA02_codigo() != 0) {
      indIFrame = formulario.contextPath() + "/autenticacao/loginIni";
      indUsuSessao = indUsuarioModel.getA02_usuario().trim();
      indNomeUsuSessao = indUsuarioModel.getA02_nome().trim();
    }

    pagina.put("indCodini", String.valueOf(indCodini));

    pagina.put("indIFrame", String.valueOf(indIFrame));

    pagina.put("indUsuSessao", String.valueOf(indUsuSessao));

    pagina.put("indNomeUsuSessao", String.valueOf(indNomeUsuSessao));

    return pagina;
  }
}
