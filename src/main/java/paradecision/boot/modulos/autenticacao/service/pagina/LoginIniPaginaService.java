package paradecision.boot.modulos.autenticacao.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.UsuarioService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class LoginIniPaginaService {
  private final UsuarioService usuarioService;

  public LoginIniPaginaService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Usuario oUsuarioModel = new Usuario();

    String txt_codini = formulario.valor("liCodini");
    String txt_pdUsuario = formulario.valor("pdUsuario");
    String txt_pdNovaSenha = formulario.valor("pdNovaSenha");
    String txt_pdConfNovaSenha = formulario.valor("pdConfNovaSenha");
    String txt_pdAcao = formulario.valor("pdAcao");
    String txt_pdMensagens = "";
    String txt_pdMsgDisplay = "none";
    if (txt_codini == null) txt_codini = "";
    if (txt_pdUsuario == null) txt_pdUsuario = "";
    if (txt_pdNovaSenha == null) txt_pdNovaSenha = "";
    if (txt_pdConfNovaSenha == null) txt_pdConfNovaSenha = "";
    if (txt_pdAcao == null) txt_pdAcao = "";
    try {
      if (txt_pdAcao.equals("envLoginIni")) {
        // ... inserindo um "x" na penúltima posição do código inicial
        int tam = txt_codini.length();
        txt_codini = txt_codini.substring(0, tam - 1) + "x" + txt_codini.substring(tam - 1);
        oUsuarioModel.setA02_usuario(txt_pdUsuario);
        oUsuarioModel.setA02_senha(txt_pdNovaSenha);
        oUsuarioModel.setA02_codigo_link(txt_codini);
        oUsuarioModel = usuarioService.updateSenhaUsuario(oUsuarioModel);
      }
    } catch (Exception e) {
    }

    pagina.put("txt_pdNovaSenha", String.valueOf(txt_pdNovaSenha));

    pagina.put("txt_pdConfNovaSenha", String.valueOf(txt_pdConfNovaSenha));

    pagina.put("txt_pdMsgDisplay", String.valueOf(txt_pdMsgDisplay));

    pagina.put("txt_pdMensagens", String.valueOf(txt_pdMensagens));

    pagina.put("txt_pdAcao", String.valueOf(txt_pdAcao));

    pagina.put("oUsuarioModel_A02_usuario", String.valueOf(oUsuarioModel.getA02_usuario()));

    pagina.put("oUsuarioModel_A02_codigo", String.valueOf(oUsuarioModel.getA02_codigo()));

    return pagina;
  }
}
