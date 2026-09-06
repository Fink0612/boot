package paradecision.boot.modulos.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/** Prepara os dados do fragmento compartilhado camposControle. */
public final class CamposControleViewModel {
  public static Map<String, Object> preparar(HttpServletRequest request) {
    Map<String, Object> controle = new LinkedHashMap<>();

    String codigoUsuarioControle = request.getParameter("ct_A02_CODIGO");
    String usuarioUsuarioControle = request.getParameter("ct_A02_USUARIO");
    String ct_QTD_EMPRESAS = request.getParameter("ct_QTD_EMPRESAS");
    String codigoEmpresaControle = request.getParameter("ct_A01_CODIGO");
    String nomeEmpresaControle = request.getParameter("ct_A01_NOME");
    String perfilParaviverbemPerfilEmpresaUsuarioControle = request.getParameter("ct_A03_PERFIL_PARAVIVERBEM");
    String perfilAdministradorPerfilEmpresaUsuarioControle = request.getParameter("ct_A03_PERFIL_ADMINISTRADOR");
    String perfilChefePerfilEmpresaUsuarioControle = request.getParameter("ct_A03_PERFIL_CHEFE");
    String perfilPadraoPerfilEmpresaUsuarioControle = request.getParameter("ct_A03_PERFIL_PADRAO");
    String ct_pdAcao = request.getParameter("pdAcao");
    if (codigoUsuarioControle == null) codigoUsuarioControle = "";
    if (usuarioUsuarioControle == null) usuarioUsuarioControle = "";
    if (ct_QTD_EMPRESAS == null) ct_QTD_EMPRESAS = "";
    if (codigoEmpresaControle == null) codigoEmpresaControle = "";
    if (nomeEmpresaControle == null) nomeEmpresaControle = "";
    if (perfilParaviverbemPerfilEmpresaUsuarioControle == null) perfilParaviverbemPerfilEmpresaUsuarioControle = "";
    if (perfilAdministradorPerfilEmpresaUsuarioControle == null) perfilAdministradorPerfilEmpresaUsuarioControle = "";
    if (perfilChefePerfilEmpresaUsuarioControle == null) perfilChefePerfilEmpresaUsuarioControle = "";
    if (perfilPadraoPerfilEmpresaUsuarioControle == null) perfilPadraoPerfilEmpresaUsuarioControle = "";
    if (ct_pdAcao == null) ct_pdAcao = "";

    controle.put("ct_A02_CODIGO", String.valueOf(codigoUsuarioControle));

    controle.put("ct_A02_USUARIO", String.valueOf(usuarioUsuarioControle));

    controle.put("ct_QTD_EMPRESAS", String.valueOf(ct_QTD_EMPRESAS));

    controle.put("ct_A01_CODIGO", String.valueOf(codigoEmpresaControle));

    controle.put("ct_A01_NOME", String.valueOf(nomeEmpresaControle));

    controle.put("ct_A03_PERFIL_PARAVIVERBEM", String.valueOf(perfilParaviverbemPerfilEmpresaUsuarioControle));

    controle.put("ct_A03_PERFIL_ADMINISTRADOR", String.valueOf(perfilAdministradorPerfilEmpresaUsuarioControle));

    controle.put("ct_A03_PERFIL_CHEFE", String.valueOf(perfilChefePerfilEmpresaUsuarioControle));

    controle.put("ct_A03_PERFIL_PADRAO", String.valueOf(perfilPadraoPerfilEmpresaUsuarioControle));

    controle.put("ct_pdAcao", String.valueOf(ct_pdAcao));

    return controle;
  }
}
