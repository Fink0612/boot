package paradecision.boot.modulos.usuarios.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.dto.DadosPagina;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.usuarios.dto.UsuarioEmpresasDados;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.UsuarioEmpresasService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class UsuarioEmpresasPaginaService {
  private final UsuarioEmpresasService usuarioEmpresasService;

  public UsuarioEmpresasPaginaService(UsuarioEmpresasService usuarioEmpresasService) {
    this.usuarioEmpresasService = usuarioEmpresasService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    Usuario dadosUsuario = new Usuario();
    Empresa dadosEmpresa = new Empresa();

    UsuarioEmpresasDados dadosUsuarioEmpresas = new UsuarioEmpresasDados();

    int achouEmpresa = 0;
    long codigoUsuarioEmpresasUsuarioControle = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    dadosUsuario.setA02_codigo(codigoUsuarioEmpresasUsuarioControle);
    dadosUsuarioEmpresas.setoUsuarioModel(dadosUsuario);
    dadosUsuarioEmpresas = usuarioEmpresasService.selectEmpresasDoUsuario(dadosUsuarioEmpresas);
    if (dadosUsuarioEmpresas.getArrEmpresaModel().size() > 0) {
      achouEmpresa = 1;
    }

    if (achouEmpresa == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Empresa> listaEmpresa = new ArrayList<Empresa>();
      listaEmpresa = dadosUsuarioEmpresas.getArrEmpresaModel();
      for (int indiceElemento = 0; indiceElemento < listaEmpresa.size(); indiceElemento++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        dadosEmpresa = dadosUsuarioEmpresas.getArrEmpresaModel().get(indiceElemento);

        linha2.put("oEmpresaModel_A01_codigo", String.valueOf(dadosEmpresa.getA01_codigo()));

        linha2.put("oEmpresaModel_A01_nome", String.valueOf(dadosEmpresa.getA01_nome()));

        linha2.put("ue_ct_A02_CODIGO", String.valueOf(codigoUsuarioEmpresasUsuarioControle));

        linha2.put("oEmpresaModel_A01_nome2", String.valueOf(dadosEmpresa.getA01_nome()));
      }
    }

    return pagina;
  }
}
