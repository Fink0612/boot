package paradecision.boot.modulos.usuarios.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.util.MetodosUteis;
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

    String okRetorno = "NOK";
    String u_A02_CODIGO = formulario.valor("eu_A02_CODIGO");
    long u_num_A02_CODIGO = MetodosUteis.retornaLong(u_A02_CODIGO);
    String u_A01_CODIGO = formulario.valor("ct_A01_CODIGO");
    long u_num_A01_CODIGO = MetodosUteis.retornaLong(u_A01_CODIGO);
    Usuario oUsuarioModel = new Usuario();

    EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfil();

    if (u_num_A02_CODIGO > 0) {
      oUsuarioModel.setA02_codigo(u_num_A02_CODIGO);
      oUsuarioModel = usuarioService.selectUserByCode(oUsuarioModel);
      if (oUsuarioModel.getA02_nome() == null) oUsuarioModel.setA02_nome("");
      if (oUsuarioModel.getA02_nome().length() > 0) {
        oEmpresaUsuarioPerfilModel.setA02_codigo(u_num_A02_CODIGO);
        oEmpresaUsuarioPerfilModel.setA01_codigo(u_num_A01_CODIGO);
        oEmpresaUsuarioPerfilModel =
            empresaUsuarioPerfilService.selectEmpresaUsuario(oEmpresaUsuarioPerfilModel);
        if (empresaUsuarioPerfilService != null) {
          okRetorno = "OK";
        }
      }
    }

    pagina.put("okRetorno", String.valueOf(okRetorno));

    pagina.put("u_A02_CODIGO", String.valueOf(u_A02_CODIGO));

    pagina.put("oUsuarioModel_A02_nome", String.valueOf(oUsuarioModel.getA02_nome()));

    pagina.put("oUsuarioModel_A02_usuario", String.valueOf(oUsuarioModel.getA02_usuario()));

    pagina.put("oUsuarioModel_A02_senha", String.valueOf(oUsuarioModel.getA02_senha()));

    pagina.put("oUsuarioModel_A02_email", String.valueOf(oUsuarioModel.getA02_email()));

    pagina.put("oUsuarioModel_A02_status", String.valueOf(oUsuarioModel.getA02_status()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_chefe",
        String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_chefe()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_padrao",
        String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_padrao()));

    return pagina;
  }
}
