package paradecision.boot.modulos.pareceres.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.compartilhado.dto.DadosFormulario;
import paradecision.boot.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.pareceres.service.ParecerFatorUsuarioService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterPareceresFatoresPaginaService {
  private final ParecerFatorUsuarioService parecerFatorUsuarioService;

  public InterPareceresFatoresPaginaService(ParecerFatorUsuarioService parecerFatorUsuarioService) {
    this.parecerFatorUsuarioService = parecerFatorUsuarioService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    String okIteracao = "";
    String okMetodo = "OK";
    String txt_ct_QTD_FATORES_AGENDA = formulario.valor("ct_QTD_FATORES_AGENDA");
    int num_ct_QTD_FATORES_AGENDA = MetodosUteis.retornaInt(txt_ct_QTD_FATORES_AGENDA);
    if (num_ct_QTD_FATORES_AGENDA > 0) {
      for (int ii = 0; ii < num_ct_QTD_FATORES_AGENDA; ii++) {
        okIteracao = "";
        String txt_afp_A02_CODIGO = formulario.valor("ct_A02_CODIGO");
        long num_afp_A02_CODIGO = MetodosUteis.retornaLong(txt_afp_A02_CODIGO);
        String txt_afp_A06_CODIGO = formulario.valor("afp_A06_CODIGO_" + ii);
        long num_afp_A06_CODIGO = MetodosUteis.retornaLong(txt_afp_A06_CODIGO);
        String txt_afp_A07_CERTEZA = formulario.valor("afp_A07_CERTEZA_" + ii);
        double num_afp_A07_CERTEZA = MetodosUteis.retornaDouble(txt_afp_A07_CERTEZA);
        String txt_afp_A07_CONTRADICAO = formulario.valor("afp_A07_CONTRADICAO_" + ii);
        double num_afp_A07_CONTRADICAO = MetodosUteis.retornaDouble(txt_afp_A07_CONTRADICAO);
        //		if (!(num_afp_A07_CERTEZA < 0 && num_afp_A07_CONTRADICAO < 0)) {
        long codParecer = 0;
        ParecerFatorUsuario oParecerFatorUsuarioModel = new ParecerFatorUsuario();

        oParecerFatorUsuarioModel.setA02_codigo(num_afp_A02_CODIGO);
        oParecerFatorUsuarioModel.setA06_codigo(num_afp_A06_CODIGO);
        oParecerFatorUsuarioModel =
            parecerFatorUsuarioService.selectParecerFatorUsuario(oParecerFatorUsuarioModel);
        oParecerFatorUsuarioModel.setA07_certeza(num_afp_A07_CERTEZA);
        oParecerFatorUsuarioModel.setA07_contradicao(num_afp_A07_CONTRADICAO);
        codParecer = oParecerFatorUsuarioModel.getA07_codigo();
        if (codParecer > 0) {
          okIteracao =
              parecerFatorUsuarioService.updateParecerFatorUsuario(oParecerFatorUsuarioModel);
        } else {
          okIteracao =
              parecerFatorUsuarioService.insertParecerFatorUsuario(oParecerFatorUsuarioModel);
        }
        if (okIteracao.equals("NOK")) {
          okMetodo = "NOK";
        }
        //		}
      }
    }

    pagina.put("okMetodo", String.valueOf(okMetodo));

    return pagina;
  }
}
