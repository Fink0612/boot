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
public class InterCadastroUsuarioEditarPaginaService {
  private final EmpresaUsuarioPerfilService empresaUsuarioPerfilService;
  private final UsuarioService usuarioService;

  public InterCadastroUsuarioEditarPaginaService(
      EmpresaUsuarioPerfilService empresaUsuarioPerfilService, UsuarioService usuarioService) {
    this.empresaUsuarioPerfilService = empresaUsuarioPerfilService;
    this.usuarioService = usuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String operacaoConcluida = "";
    String codigoUsuarioFormularioUsuario = formulario.valor("u_A02_CODIGO");
    String nomeUsuarioFormularioUsuario = formulario.valor("u_a02_nome");
    String emailUsuarioFormularioUsuario = formulario.valor("u_a02_email");
    String usuarioUsuarioFormularioUsuario = formulario.valor("u_a02_usuario");
    String senhaUsuarioFormularioUsuario = formulario.valor("u_a02_senha");
    String statusUsuarioFormularioUsuario = formulario.valor("u_a02_status");
    String perfilChefePerfilEmpresaUsuarioPerfilEmpresaUsuario = formulario.valor("eup_a03_perfil_chefe");
    String perfilPadraoPerfilEmpresaUsuarioPerfilEmpresaUsuario = formulario.valor("eup_a03_perfil_padrao");
    String codigoEmpresaCadastro = formulario.valor("ct_A01_CODIGO");
    if (codigoUsuarioFormularioUsuario == null) codigoUsuarioFormularioUsuario = "";
    if (nomeUsuarioFormularioUsuario == null) nomeUsuarioFormularioUsuario = "";
    if (emailUsuarioFormularioUsuario == null) emailUsuarioFormularioUsuario = "";
    if (usuarioUsuarioFormularioUsuario == null) usuarioUsuarioFormularioUsuario = "";
    if (senhaUsuarioFormularioUsuario == null) senhaUsuarioFormularioUsuario = "";
    if (statusUsuarioFormularioUsuario == null) statusUsuarioFormularioUsuario = "0";
    if (perfilChefePerfilEmpresaUsuarioPerfilEmpresaUsuario == null) perfilChefePerfilEmpresaUsuarioPerfilEmpresaUsuario = "0";
    if (perfilPadraoPerfilEmpresaUsuarioPerfilEmpresaUsuario == null) perfilPadraoPerfilEmpresaUsuarioPerfilEmpresaUsuario = "0";
    if ("".equals(statusUsuarioFormularioUsuario)) statusUsuarioFormularioUsuario = "0";
    if ("".equals(perfilChefePerfilEmpresaUsuarioPerfilEmpresaUsuario)) perfilChefePerfilEmpresaUsuarioPerfilEmpresaUsuario = "0";
    if ("".equals(perfilPadraoPerfilEmpresaUsuarioPerfilEmpresaUsuario)) perfilPadraoPerfilEmpresaUsuarioPerfilEmpresaUsuario = "0";
    if (codigoEmpresaCadastro == null) codigoEmpresaCadastro = "0";
    nomeUsuarioFormularioUsuario = MetodosUteis.padronizarMaiusculoCE(nomeUsuarioFormularioUsuario);
    emailUsuarioFormularioUsuario = MetodosUteis.padronizarMinusculoSE(emailUsuarioFormularioUsuario);
    usuarioUsuarioFormularioUsuario = MetodosUteis.padronizarMinusculoSE(usuarioUsuarioFormularioUsuario);
    Usuario dadosUsuario = new Usuario();

    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil = new EmpresaUsuarioPerfil();

    long codigoUsuarioNumerico = MetodosUteis.retornaLong(codigoUsuarioFormularioUsuario);
    dadosUsuario.setA02_codigo(codigoUsuarioNumerico);
    dadosUsuario.setA02_nome(nomeUsuarioFormularioUsuario);
    dadosUsuario.setA02_email(emailUsuarioFormularioUsuario);
    dadosUsuario.setA02_usuario(usuarioUsuarioFormularioUsuario);
    dadosUsuario.setA02_senha(senhaUsuarioFormularioUsuario);
    dadosUsuario.setA02_status(Integer.parseInt(statusUsuarioFormularioUsuario));
    operacaoConcluida = usuarioService.updateUsuario(dadosUsuario);
    if (dadosUsuario.getA02_codigo() != 0) {
      dadosEmpresaUsuarioPerfil.setA01_codigo(Long.parseLong(codigoEmpresaCadastro));
      dadosEmpresaUsuarioPerfil.setA02_codigo(dadosUsuario.getA02_codigo());
      dadosEmpresaUsuarioPerfil.setA03_perfil_chefe(Integer.parseInt(perfilChefePerfilEmpresaUsuarioPerfilEmpresaUsuario));
      dadosEmpresaUsuarioPerfil.setA03_perfil_padrao(Integer.parseInt(perfilPadraoPerfilEmpresaUsuarioPerfilEmpresaUsuario));
      // ... continuar pegando o control deste ultimo objeto
      operacaoConcluida = empresaUsuarioPerfilService.updateEmpresaUsuarioPerfil(dadosEmpresaUsuarioPerfil);
    }

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    return pagina;
  }
}
