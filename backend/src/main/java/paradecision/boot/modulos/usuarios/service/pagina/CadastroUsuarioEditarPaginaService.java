package paradecision.boot.modulos.usuarios.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.empresas.service.EmpresaUsuarioPerfilService;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.UsuarioService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class CadastroUsuarioEditarPaginaService {
  private final EmpresaUsuarioPerfilService empresaUsuarioPerfilService;
  private final UsuarioService usuarioService;

  public CadastroUsuarioEditarPaginaService(
      EmpresaUsuarioPerfilService empresaUsuarioPerfilService, UsuarioService usuarioService) {
    this.empresaUsuarioPerfilService = empresaUsuarioPerfilService;
    this.usuarioService = usuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String retornoValido = "NOK";
    String codigoUsuarioFormularioUsuario = formulario.valor("eu_A02_CODIGO");
    long codigoUsuarioFormularioUsuarioNumerico = MetodosUteis.retornaLong(codigoUsuarioFormularioUsuario);
    String codigoEmpresaFormularioUsuario = formulario.valor("ct_A01_CODIGO");
    long codigoEmpresaFormularioUsuarioNumerico = MetodosUteis.retornaLong(codigoEmpresaFormularioUsuario);
    Usuario dadosUsuario = new Usuario();

    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil = new EmpresaUsuarioPerfil();

    if (codigoUsuarioFormularioUsuarioNumerico > 0) {
      dadosUsuario.setA02_codigo(codigoUsuarioFormularioUsuarioNumerico);
      dadosUsuario = usuarioService.selectUserByCode(dadosUsuario);
      if (dadosUsuario.getA02_nome() == null) dadosUsuario.setA02_nome("");
      if (dadosUsuario.getA02_nome().length() > 0) {
        dadosEmpresaUsuarioPerfil.setA02_codigo(codigoUsuarioFormularioUsuarioNumerico);
        dadosEmpresaUsuarioPerfil.setA01_codigo(codigoEmpresaFormularioUsuarioNumerico);
        dadosEmpresaUsuarioPerfil =
            empresaUsuarioPerfilService.selectEmpresaUsuario(dadosEmpresaUsuarioPerfil);
        if (empresaUsuarioPerfilService != null) {
          retornoValido = "OK";
        }
      }
    }

    pagina.put("okRetorno", String.valueOf(retornoValido));

    pagina.put("u_A02_CODIGO", String.valueOf(codigoUsuarioFormularioUsuario));

    pagina.put("oUsuarioModel_A02_nome", String.valueOf(dadosUsuario.getA02_nome()));

    pagina.put("oUsuarioModel_A02_usuario", String.valueOf(dadosUsuario.getA02_usuario()));

    pagina.put("oUsuarioModel_A02_senha", String.valueOf(dadosUsuario.getA02_senha()));

    pagina.put("oUsuarioModel_A02_email", String.valueOf(dadosUsuario.getA02_email()));

    pagina.put("oUsuarioModel_A02_status", String.valueOf(dadosUsuario.getA02_status()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_chefe",
        String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_chefe()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_padrao",
        String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_padrao()));

    return pagina;
  }
}
