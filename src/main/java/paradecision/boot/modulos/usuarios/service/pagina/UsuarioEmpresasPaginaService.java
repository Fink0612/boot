package paradecision.boot.modulos.usuarios.service.pagina;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.dto.DadosPagina;
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

    Usuario oUsuarioModel = new Usuario();
    Empresa oEmpresaModel = new Empresa();

    UsuarioEmpresasDados oUsuarioEmpresasModel = new UsuarioEmpresasDados();

    int achouEmpresa = 0;
    long ue_ct_A02_CODIGO = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    oUsuarioModel.setA02_codigo(ue_ct_A02_CODIGO);
    oUsuarioEmpresasModel.setoUsuarioModel(oUsuarioModel);
    oUsuarioEmpresasModel = usuarioEmpresasService.selectEmpresasDoUsuario(oUsuarioEmpresasModel);
    if (oUsuarioEmpresasModel.getArrEmpresaModel().size() > 0) {
      achouEmpresa = 1;
    }

    if (achouEmpresa == 1) {
      Map<String, Object> linha1 = DadosPagina.novaLinha(pagina, "linhas1");

      ArrayList<Empresa> arrEmpresaModel = new ArrayList<Empresa>();
      arrEmpresaModel = oUsuarioEmpresasModel.getArrEmpresaModel();
      for (int i = 0; i < arrEmpresaModel.size(); i++) {
        Map<String, Object> linha2 = DadosPagina.novaLinha(linha1, "linhas2");

        oEmpresaModel = oUsuarioEmpresasModel.getArrEmpresaModel().get(i);

        linha2.put("oEmpresaModel_A01_codigo", String.valueOf(oEmpresaModel.getA01_codigo()));

        linha2.put("oEmpresaModel_A01_nome", String.valueOf(oEmpresaModel.getA01_nome()));

        linha2.put("ue_ct_A02_CODIGO", String.valueOf(ue_ct_A02_CODIGO));

        linha2.put("oEmpresaModel_A01_nome2", String.valueOf(oEmpresaModel.getA01_nome()));
      }
    }

    return pagina;
  }
}
