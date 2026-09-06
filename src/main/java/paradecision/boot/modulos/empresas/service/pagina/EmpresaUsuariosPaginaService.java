package paradecision.boot.modulos.empresas.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.empresas.dto.EmpresaUsuariosDados;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.empresas.service.EmpresaUsuariosService;
import paradecision.boot.modulos.usuarios.entity.Usuario;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class EmpresaUsuariosPaginaService {
  private final EmpresaUsuariosService empresaUsuariosService;

  public EmpresaUsuariosPaginaService(EmpresaUsuariosService empresaUsuariosService) {
    this.empresaUsuariosService = empresaUsuariosService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Empresa oEmpresaModel = new Empresa();
    Usuario oUsuarioModel = new Usuario();
    EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfil();
    EmpresaUsuariosDados oEmpresaUsuariosModel = new EmpresaUsuariosDados();

    int achouUsuario = 0;
    long eu_ct_A01_CODIGO = Long.parseLong(formulario.valor("ct_A01_CODIGO"));
    String eu_ct_A01_NOME = formulario.valor("ct_A01_NOME");
    oEmpresaModel.setA01_codigo(eu_ct_A01_CODIGO);
    oEmpresaModel.setA01_nome(eu_ct_A01_NOME);
    oEmpresaUsuariosModel.setoEmpresaModel(oEmpresaModel);
    oEmpresaUsuariosModel = empresaUsuariosService.selectUsuariosDaEmpresa(oEmpresaUsuariosModel);
    if (oEmpresaUsuariosModel.getArrUsuarioModel().size() > 0) {
      achouUsuario = 1;
    }

    String eu_ct_A03_PERFIL_PARAVIVERBEM = formulario.valor("ct_A03_PERFIL_PARAVIVERBEM");
    String eu_ct_A03_PERFIL_ADMINISTRADOR = formulario.valor("ct_A03_PERFIL_ADMINISTRADOR");
    String displayLink = "none";
    String displaySoTexto = "inline";
    if (eu_ct_A03_PERFIL_PARAVIVERBEM.equals("1") || eu_ct_A03_PERFIL_ADMINISTRADOR.equals("1")) {
      displayLink = "inline";
      displaySoTexto = "none";
    }
    if (achouUsuario == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
      arrUsuarioModel = oEmpresaUsuariosModel.getArrUsuarioModel();
      for (int i = 0; i < arrUsuarioModel.size(); i++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        oUsuarioModel = oEmpresaUsuariosModel.getArrUsuarioModel().get(i);
        oEmpresaUsuarioPerfilModel = oEmpresaUsuariosModel.getArrEmpresaUsuarioPerfilModel().get(i);

        linha2.put("displayLink", String.valueOf(displayLink));

        linha2.put("oUsuarioModel_A02_codigo", String.valueOf(oUsuarioModel.getA02_codigo()));

        linha2.put("oUsuarioModel_A02_nome", String.valueOf(oUsuarioModel.getA02_nome()));

        linha2.put("displaySoTexto", String.valueOf(displaySoTexto));

        linha2.put("oUsuarioModel_A02_nome2", String.valueOf(oUsuarioModel.getA02_nome()));

        linha2.put(
            "oEmpresaUsuarioPerfilModel_A03_perfil_chefe",
            String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_chefe()));

        linha2.put(
            "oEmpresaUsuarioPerfilModel_A03_perfil_padrao",
            String.valueOf(oEmpresaUsuarioPerfilModel.getA03_perfil_padrao()));
      }
    }

    return pagina;
  }
}
