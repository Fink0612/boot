package paradecision.boot.compartilhado.web;

import jakarta.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/** Prepara os dados do fragmento compartilhado camposControle. */
public final class CamposControleViewModel {
  public static Map<String, Object> preparar(HttpServletRequest request) {
    Map<String, Object> controle = new LinkedHashMap<>();

    String ct_A02_CODIGO = request.getParameter("ct_A02_CODIGO");
    String ct_A02_USUARIO = request.getParameter("ct_A02_USUARIO");
    String ct_QTD_EMPRESAS = request.getParameter("ct_QTD_EMPRESAS");
    String ct_A01_CODIGO = request.getParameter("ct_A01_CODIGO");
    String ct_A01_NOME = request.getParameter("ct_A01_NOME");
    String ct_A03_PERFIL_PARAVIVERBEM = request.getParameter("ct_A03_PERFIL_PARAVIVERBEM");
    String ct_A03_PERFIL_ADMINISTRADOR = request.getParameter("ct_A03_PERFIL_ADMINISTRADOR");
    String ct_A03_PERFIL_CHEFE = request.getParameter("ct_A03_PERFIL_CHEFE");
    String ct_A03_PERFIL_PADRAO = request.getParameter("ct_A03_PERFIL_PADRAO");
    String ct_pdAcao = request.getParameter("pdAcao");
    if (ct_A02_CODIGO == null) ct_A02_CODIGO = "";
    if (ct_A02_USUARIO == null) ct_A02_USUARIO = "";
    if (ct_QTD_EMPRESAS == null) ct_QTD_EMPRESAS = "";
    if (ct_A01_CODIGO == null) ct_A01_CODIGO = "";
    if (ct_A01_NOME == null) ct_A01_NOME = "";
    if (ct_A03_PERFIL_PARAVIVERBEM == null) ct_A03_PERFIL_PARAVIVERBEM = "";
    if (ct_A03_PERFIL_ADMINISTRADOR == null) ct_A03_PERFIL_ADMINISTRADOR = "";
    if (ct_A03_PERFIL_CHEFE == null) ct_A03_PERFIL_CHEFE = "";
    if (ct_A03_PERFIL_PADRAO == null) ct_A03_PERFIL_PADRAO = "";
    if (ct_pdAcao == null) ct_pdAcao = "";

    controle.put("ct_A02_CODIGO", String.valueOf(ct_A02_CODIGO));

    controle.put("ct_A02_USUARIO", String.valueOf(ct_A02_USUARIO));

    controle.put("ct_QTD_EMPRESAS", String.valueOf(ct_QTD_EMPRESAS));

    controle.put("ct_A01_CODIGO", String.valueOf(ct_A01_CODIGO));

    controle.put("ct_A01_NOME", String.valueOf(ct_A01_NOME));

    controle.put("ct_A03_PERFIL_PARAVIVERBEM", String.valueOf(ct_A03_PERFIL_PARAVIVERBEM));

    controle.put("ct_A03_PERFIL_ADMINISTRADOR", String.valueOf(ct_A03_PERFIL_ADMINISTRADOR));

    controle.put("ct_A03_PERFIL_CHEFE", String.valueOf(ct_A03_PERFIL_CHEFE));

    controle.put("ct_A03_PERFIL_PADRAO", String.valueOf(ct_A03_PERFIL_PADRAO));

    controle.put("ct_pdAcao", String.valueOf(ct_pdAcao));

    return controle;
  }
}
