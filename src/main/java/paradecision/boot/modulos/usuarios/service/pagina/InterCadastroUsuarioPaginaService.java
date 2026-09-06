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
public class InterCadastroUsuarioPaginaService {
  private final EmpresaUsuarioPerfilService empresaUsuarioPerfilService;
  private final UsuarioService usuarioService;

  public InterCadastroUsuarioPaginaService(
      EmpresaUsuarioPerfilService empresaUsuarioPerfilService, UsuarioService usuarioService) {
    this.empresaUsuarioPerfilService = empresaUsuarioPerfilService;
    this.usuarioService = usuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    int okMetodo = 0;
    String u_a02_nome = formulario.valor("u_a02_nome");
    String u_a02_email = formulario.valor("u_a02_email");
    String u_a02_usuario = formulario.valor("u_a02_usuario");
    String u_a02_senha = formulario.valor("u_a02_senha");
    String u_a02_status = formulario.valor("u_a02_status");
    String eup_a03_perfil_chefe = formulario.valor("eup_a03_perfil_chefe");
    String eup_a03_perfil_padrao = formulario.valor("eup_a03_perfil_padrao");
    String ic_A01_CODIGO = formulario.valor("ct_A01_CODIGO");
    if (u_a02_nome == null) u_a02_nome = "";
    if (u_a02_email == null) u_a02_email = "";
    if (u_a02_usuario == null) u_a02_usuario = "";
    if (u_a02_senha == null) u_a02_senha = "";
    if (u_a02_status == null) u_a02_status = "0";
    if (eup_a03_perfil_chefe == null) eup_a03_perfil_chefe = "0";
    if (eup_a03_perfil_padrao == null) eup_a03_perfil_padrao = "0";
    if ("".equals(u_a02_status)) u_a02_status = "0";
    if ("".equals(eup_a03_perfil_chefe)) eup_a03_perfil_chefe = "0";
    if ("".equals(eup_a03_perfil_padrao)) eup_a03_perfil_padrao = "0";
    if (ic_A01_CODIGO == null) ic_A01_CODIGO = "0";
    u_a02_nome = MetodosUteis.padronizarMaiusculoCE(u_a02_nome);
    u_a02_email = MetodosUteis.padronizarMinusculoSE(u_a02_email);
    u_a02_usuario = MetodosUteis.padronizarMinusculoSE(u_a02_usuario);
    Usuario oUsuarioModel = new Usuario();

    EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfil();

    oUsuarioModel.setA02_nome(u_a02_nome);
    oUsuarioModel.setA02_email(u_a02_email);
    oUsuarioModel.setA02_usuario(u_a02_usuario);
    oUsuarioModel.setA02_senha(u_a02_senha);
    oUsuarioModel.setA02_codigo_link(MetodosUteis.gerarCodigo(25));
    oUsuarioModel.setA02_status(Integer.parseInt(u_a02_status));
    oUsuarioModel = usuarioService.insertUsuario(oUsuarioModel);
    if (oUsuarioModel.getA02_codigo() != 0) {
      oEmpresaUsuarioPerfilModel.setA01_codigo(Long.parseLong(ic_A01_CODIGO));
      oEmpresaUsuarioPerfilModel.setA02_codigo(oUsuarioModel.getA02_codigo());
      oEmpresaUsuarioPerfilModel.setA03_perfil_paraviverbem(0);
      oEmpresaUsuarioPerfilModel.setA03_perfil_chefe(Integer.parseInt(eup_a03_perfil_chefe));
      oEmpresaUsuarioPerfilModel.setA03_perfil_padrao(Integer.parseInt(eup_a03_perfil_padrao));
      // ... continuar pegando o control deste ultimo objeto
      okMetodo = empresaUsuarioPerfilService.insertEmpresaUsuarioPerfil(oEmpresaUsuarioPerfilModel);
    }

    pagina.put("okMetodo", String.valueOf(okMetodo));

    return pagina;
  }
}
