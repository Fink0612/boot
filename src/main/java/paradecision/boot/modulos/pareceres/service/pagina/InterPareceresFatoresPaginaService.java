package paradecision.boot.modulos.pareceres.service.pagina;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
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
    String operacaoConcluida = "OK";
    String txt_ct_QTD_FATORES_AGENDA = formulario.valor("ct_QTD_FATORES_AGENDA");
    int num_ct_QTD_FATORES_AGENDA = MetodosUteis.retornaInt(txt_ct_QTD_FATORES_AGENDA);
    if (num_ct_QTD_FATORES_AGENDA > 0) {
      for (int indiceRegistro = 0; indiceRegistro < num_ct_QTD_FATORES_AGENDA; indiceRegistro++) {
        okIteracao = "";
        String codigoUsuarioTextoPareceresAgenda = formulario.valor("ct_A02_CODIGO");
        long codigoUsuarioNumericoPareceresAgenda = MetodosUteis.retornaLong(codigoUsuarioTextoPareceresAgenda);
        String codigoFatorTextoPareceresAgenda = formulario.valor("afp_A06_CODIGO_" + indiceRegistro);
        long codigoFatorNumericoPareceresAgenda = MetodosUteis.retornaLong(codigoFatorTextoPareceresAgenda);
        String certezaParecerTextoPareceresAgenda = formulario.valor("afp_A07_CERTEZA_" + indiceRegistro);
        double certezaParecerNumericoPareceresAgenda = MetodosUteis.retornaDouble(certezaParecerTextoPareceresAgenda);
        String contradicaoParecerTextoPareceresAgenda = formulario.valor("afp_A07_CONTRADICAO_" + indiceRegistro);
        double contradicaoParecerNumericoPareceresAgenda = MetodosUteis.retornaDouble(contradicaoParecerTextoPareceresAgenda);
        //		if (!(num_afp_A07_CERTEZA < 0 && num_afp_A07_CONTRADICAO < 0)) {
        long codigoParecer = 0;
        ParecerFatorUsuario dadosParecerFatorUsuario = new ParecerFatorUsuario();

        dadosParecerFatorUsuario.setA02_codigo(codigoUsuarioNumericoPareceresAgenda);
        dadosParecerFatorUsuario.setA06_codigo(codigoFatorNumericoPareceresAgenda);
        dadosParecerFatorUsuario =
            parecerFatorUsuarioService.selectParecerFatorUsuario(dadosParecerFatorUsuario);
        dadosParecerFatorUsuario.setA07_certeza(certezaParecerNumericoPareceresAgenda);
        dadosParecerFatorUsuario.setA07_contradicao(contradicaoParecerNumericoPareceresAgenda);
        codigoParecer = dadosParecerFatorUsuario.getA07_codigo();
        if (codigoParecer > 0) {
          okIteracao =
              parecerFatorUsuarioService.updateParecerFatorUsuario(dadosParecerFatorUsuario);
        } else {
          okIteracao =
              parecerFatorUsuarioService.insertParecerFatorUsuario(dadosParecerFatorUsuario);
        }
        if (okIteracao.equals("NOK")) {
          operacaoConcluida = "NOK";
        }
        //		}
      }
    }

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    return pagina;
  }
}
