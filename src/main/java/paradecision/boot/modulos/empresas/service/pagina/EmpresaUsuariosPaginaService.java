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

    Empresa dadosEmpresa = new Empresa();
    Usuario dadosUsuario = new Usuario();
    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil = new EmpresaUsuarioPerfil();
    EmpresaUsuariosDados dadosEmpresaUsuarios = new EmpresaUsuariosDados();

    int achouUsuario = 0;
    long codigoEmpresaUsuariosEmpresaControle = Long.parseLong(formulario.valor("ct_A01_CODIGO"));
    String nomeEmpresaUsuariosEmpresaControle = formulario.valor("ct_A01_NOME");
    dadosEmpresa.setA01_codigo(codigoEmpresaUsuariosEmpresaControle);
    dadosEmpresa.setA01_nome(nomeEmpresaUsuariosEmpresaControle);
    dadosEmpresaUsuarios.setoEmpresaModel(dadosEmpresa);
    dadosEmpresaUsuarios = empresaUsuariosService.selectUsuariosDaEmpresa(dadosEmpresaUsuarios);
    if (dadosEmpresaUsuarios.getArrUsuarioModel().size() > 0) {
      achouUsuario = 1;
    }

    String perfilParaviverbemPerfilEmpresaUsuarioUsuariosEmpresaControle = formulario.valor("ct_A03_PERFIL_PARAVIVERBEM");
    String perfilAdministradorPerfilEmpresaUsuarioUsuariosEmpresaControle = formulario.valor("ct_A03_PERFIL_ADMINISTRADOR");
    String displayLink = "none";
    String displaySoTexto = "inline";
    if (perfilParaviverbemPerfilEmpresaUsuarioUsuariosEmpresaControle.equals("1") || perfilAdministradorPerfilEmpresaUsuarioUsuariosEmpresaControle.equals("1")) {
      displayLink = "inline";
      displaySoTexto = "none";
    }
    if (achouUsuario == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
      listaUsuario = dadosEmpresaUsuarios.getArrUsuarioModel();
      for (int indiceElemento = 0; indiceElemento < listaUsuario.size(); indiceElemento++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        dadosUsuario = dadosEmpresaUsuarios.getArrUsuarioModel().get(indiceElemento);
        dadosEmpresaUsuarioPerfil = dadosEmpresaUsuarios.getArrEmpresaUsuarioPerfilModel().get(indiceElemento);

        linha2.put("displayLink", String.valueOf(displayLink));

        linha2.put("oUsuarioModel_A02_codigo", String.valueOf(dadosUsuario.getA02_codigo()));

        linha2.put("oUsuarioModel_A02_nome", String.valueOf(dadosUsuario.getA02_nome()));

        linha2.put("displaySoTexto", String.valueOf(displaySoTexto));

        linha2.put("oUsuarioModel_A02_nome2", String.valueOf(dadosUsuario.getA02_nome()));

        linha2.put(
            "oEmpresaUsuarioPerfilModel_A03_perfil_chefe",
            String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_chefe()));

        linha2.put(
            "oEmpresaUsuarioPerfilModel_A03_perfil_padrao",
            String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_padrao()));
      }
    }

    return pagina;
  }
}
