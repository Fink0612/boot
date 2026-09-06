package paradecision.boot.modulos.usuarios.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.empresas.service.EmpresaUsuarioPerfilService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterUsuarioEmpresasPaginaService {
  private final EmpresaUsuarioPerfilService empresaUsuarioPerfilService;

  public InterUsuarioEmpresasPaginaService(
      EmpresaUsuarioPerfilService empresaUsuarioPerfilService) {
    this.empresaUsuarioPerfilService = empresaUsuarioPerfilService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil = new EmpresaUsuarioPerfil();

    int achouEmpresa = 0;
    long codigoEmpresaVinculoEmpresaUsuarioControle = Long.parseLong(formulario.valor("ct_A01_CODIGO"));
    long codigoUsuarioVinculoEmpresaUsuarioControle = Long.parseLong(formulario.valor("ct_A02_CODIGO"));
    dadosEmpresaUsuarioPerfil.setA01_codigo(codigoEmpresaVinculoEmpresaUsuarioControle);
    dadosEmpresaUsuarioPerfil.setA02_codigo(codigoUsuarioVinculoEmpresaUsuarioControle);
    dadosEmpresaUsuarioPerfil =
        empresaUsuarioPerfilService.selectEmpresaUsuario(dadosEmpresaUsuarioPerfil);
    if (dadosEmpresaUsuarioPerfil != null) {
      achouEmpresa = 1;
    } else {
      dadosEmpresaUsuarioPerfil = new EmpresaUsuarioPerfil();
    }

    pagina.put("achouEmpresa", String.valueOf(achouEmpresa));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_paraviverbem",
        String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_paraviverbem()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_administrador",
        String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_administrador()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_chefe",
        String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_chefe()));

    pagina.put(
        "oEmpresaUsuarioPerfilModel_A03_perfil_padrao",
        String.valueOf(dadosEmpresaUsuarioPerfil.getA03_perfil_padrao()));

    return pagina;
  }
}
