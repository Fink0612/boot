package paradecision.boot.modulos.autenticacao.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.dto.UsuarioEmpresasDados;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.UsuarioEmpresasService;
import paradecision.boot.modulos.usuarios.service.UsuarioService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class LoginPaginaService {
  private final UsuarioEmpresasService usuarioEmpresasService;
  private final UsuarioService usuarioService;

  public LoginPaginaService(
      UsuarioEmpresasService usuarioEmpresasService, UsuarioService usuarioService) {
    this.usuarioEmpresasService = usuarioEmpresasService;
    this.usuarioService = usuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Usuario oUsuarioModel = new Usuario();
    Empresa oEmpresaModel = new Empresa();
    EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfil();

    UsuarioEmpresasDados oUsuarioEmpresasModel = new UsuarioEmpresasDados();

    int qtdEmpresas = 0;
    String txt_pdUsuario = formulario.valor("pdUsuario");
    String txt_pdSenha = formulario.valor("pdSenha");
    String txt_pdAcao = formulario.valor("pdAcao");
    String txt_pdMensagens = "";
    String txt_pdMsgDisplay = "none";
    if (txt_pdUsuario == null) txt_pdUsuario = "";
    if (txt_pdSenha == null) txt_pdSenha = "";
    if (txt_pdAcao == null) txt_pdAcao = "";
    if (txt_pdAcao.equals("envLogin")) {
      oUsuarioModel.setA02_usuario(txt_pdUsuario);
      oUsuarioModel.setA02_senha(txt_pdSenha);
      oUsuarioModel = usuarioService.selectUserLogin(oUsuarioModel);
      if (oUsuarioModel.getA02_codigo() == 0) {
        txt_pdMensagens = "Usuario e/ou Senha Invalidos";
        txt_pdMsgDisplay = "";
      } else {
        oUsuarioEmpresasModel.setoUsuarioModel(oUsuarioModel);
        oUsuarioEmpresasModel =
            usuarioEmpresasService.selectEmpresasDoUsuario(oUsuarioEmpresasModel);
        qtdEmpresas = oUsuarioEmpresasModel.getArrEmpresaModel().size();
        if (qtdEmpresas == 1) {
          oEmpresaModel = oUsuarioEmpresasModel.getArrEmpresaModel().get(0);
          oEmpresaUsuarioPerfilModel =
              oUsuarioEmpresasModel.getArrEmpresaUsuarioPerfilModel().get(0);
        }
      }
    }

    pagina.put("txt_pdUsuario", String.valueOf(txt_pdUsuario));

    pagina.put("txt_pdMsgDisplay", String.valueOf(txt_pdMsgDisplay));

    pagina.put("txt_pdMensagens", String.valueOf(txt_pdMensagens));

    pagina.put("oUsuarioModel_A02_nome", String.valueOf(oUsuarioModel.getA02_nome()));

    pagina.put("oUsuarioModel_A02_usuario", String.valueOf(oUsuarioModel.getA02_usuario()));

    pagina.put("oUsuarioModel_A02_nome2", String.valueOf(oUsuarioModel.getA02_nome()));

    pagina.put("qtdEmpresas", String.valueOf(qtdEmpresas));

    pagina.put("oUsuarioModel_A02_codigo", String.valueOf(oUsuarioModel.getA02_codigo()));

    pagina.put("oUsuarioModel_A02_usuario2", String.valueOf(oUsuarioModel.getA02_usuario()));

    pagina.put("oEmpresaModel_A01_codigo", String.valueOf(oEmpresaModel.getA01_codigo()));

    pagina.put("oEmpresaModel_A01_nome", String.valueOf(oEmpresaModel.getA01_nome()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_paraviverbem",
        String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_paraviverbem()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_administrador",
        String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_administrador()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_chefe",
        String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_chefe()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_padrao",
        String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_padrao()));

    return pagina;
  }
}
