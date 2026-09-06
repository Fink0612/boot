package paradecision.boot.modulos.agendas.service.pagina;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import paradecision.boot.modulos.compartilhado.dto.DadosFormulario;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.agendas.service.AgendaUsuarioPerfilService;

/** Coordena o caso de uso e prepara os dados da tela, sem dependência HTTP. */
@Service
public class InterAgendaUsuariosPaginaService {
  private final AgendaUsuarioPerfilService agendaUsuarioPerfilService;

  public InterAgendaUsuariosPaginaService(AgendaUsuarioPerfilService agendaUsuarioPerfilService) {
    this.agendaUsuarioPerfilService = agendaUsuarioPerfilService;
  }

  public Map<String, Object> preparar(DadosFormulario formulario) {
    Map<String, Object> pagina = new LinkedHashMap<>();

    int okMetodo = 1;
    String iau_str_qtd_usuarios = formulario.valor("qtdUsuariosEmpresa");
    int iau_int_qtd_usuarios = MetodosUteis.retornaInt(iau_str_qtd_usuarios);
    if (iau_int_qtd_usuarios > 0) {
      String str_a04_codigo = formulario.valor("ct_A04_CODIGO");
      long lng_a04_codigo = Long.parseLong(str_a04_codigo);
      for (int ii = 0; ii < iau_int_qtd_usuarios; ii++) {
        String resultBD = "";
        // Valor anterior (ao abrir a página)...
        String val_marc = formulario.valor("a02_marcado_" + ii);
        if (val_marc == null) val_marc = "0";
        if ("".equals(val_marc)) val_marc = "0";
        // Valor atual (após o envio)...
        String val_check = formulario.valor("usu_emp_" + ii);
        if (val_check == null) val_check = "0";
        if ("".equals(val_check)) val_check = "0";
        if (!(val_marc.equals(val_check))) {
          if (val_check.equals("0")) {
            String str_a05_codigo = formulario.valor("a05_codigo_" + ii);
            long lng_a05_codigo = Long.parseLong(str_a05_codigo);
            if (lng_a05_codigo > 0) {
              AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();

              oAgendaUsuarioPerfilModel.setA05_codigo(lng_a05_codigo);
              resultBD =
                  agendaUsuarioPerfilService.deleteAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
            }
          } else if (val_check.equals("1")) {
            Date iau_dateToday = MetodosUteis.retornaDataAgora();
            String str_a02_codigo = formulario.valor("a02_codigo_" + ii);
            long lng_a02_codigo = Long.parseLong(str_a02_codigo);
            AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();

            oAgendaUsuarioPerfilModel.setA02_codigo(lng_a02_codigo);
            oAgendaUsuarioPerfilModel.setA04_codigo(lng_a04_codigo);
            oAgendaUsuarioPerfilModel.setA05_num_sequencia(1);
            String usu_per_T = formulario.valor("usu_per_T_" + ii);
            String usu_per_F = formulario.valor("usu_per_F_" + ii);
            String usu_per_E = formulario.valor("usu_per_E_" + ii);
            String usu_per_A = formulario.valor("usu_per_A_" + ii);
            if (usu_per_T == null) usu_per_T = "0";
            if ("".equals(usu_per_T)) usu_per_T = "0";
            if (usu_per_F == null) usu_per_F = "0";
            if ("".equals(usu_per_F)) usu_per_F = "0";
            if (usu_per_E == null) usu_per_E = "0";
            if ("".equals(usu_per_E)) usu_per_E = "0";
            if (usu_per_A == null) usu_per_A = "0";
            if ("".equals(usu_per_A)) usu_per_A = "0";
            int num_usu_per_T = Integer.parseInt(usu_per_T);
            int num_usu_per_F = Integer.parseInt(usu_per_F);
            int num_usu_per_E = Integer.parseInt(usu_per_E);
            int num_usu_per_A = Integer.parseInt(usu_per_A);
            oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(num_usu_per_T);
            oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(num_usu_per_F);
            oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(num_usu_per_E);
            oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(num_usu_per_A);
            oAgendaUsuarioPerfilModel.setA05_dt_cadastro(iau_dateToday);
            resultBD =
                agendaUsuarioPerfilService.insertPerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
          }
          if (resultBD == "NOK") {
            okMetodo = 0;
          }
        } else {
          if (val_check.equals("1")) {
            String usu_per_T = formulario.valor("usu_per_T_" + ii);
            String usu_per_F = formulario.valor("usu_per_F_" + ii);
            String usu_per_E = formulario.valor("usu_per_E_" + ii);
            String usu_per_A = formulario.valor("usu_per_A_" + ii);
            String aux_per_T = formulario.valor("aux_per_T_" + ii);
            String aux_per_F = formulario.valor("aux_per_F_" + ii);
            String aux_per_E = formulario.valor("aux_per_E_" + ii);
            String aux_per_A = formulario.valor("aux_per_A_" + ii);
            if (usu_per_T == null) usu_per_T = "0";
            if ("".equals(usu_per_T)) usu_per_T = "0";
            if (usu_per_F == null) usu_per_F = "0";
            if ("".equals(usu_per_F)) usu_per_F = "0";
            if (usu_per_E == null) usu_per_E = "0";
            if ("".equals(usu_per_E)) usu_per_E = "0";
            if (usu_per_A == null) usu_per_A = "0";
            if ("".equals(usu_per_A)) usu_per_A = "0";
            if (aux_per_T == null) aux_per_T = "0";
            if ("".equals(aux_per_T)) aux_per_T = "0";
            if (aux_per_F == null) aux_per_F = "0";
            if ("".equals(aux_per_F)) aux_per_F = "0";
            if (aux_per_E == null) aux_per_E = "0";
            if ("".equals(aux_per_E)) aux_per_E = "0";
            if (aux_per_A == null) aux_per_A = "0";
            if ("".equals(aux_per_A)) aux_per_A = "0";
            if (!usu_per_T.equals(aux_per_T)
                || !usu_per_F.equals(aux_per_F)
                || !usu_per_E.equals(aux_per_E)
                || !usu_per_A.equals(aux_per_A)) {
              Date iau_dateToday = MetodosUteis.retornaDataAgora();
              String str_a05_codigo = formulario.valor("a05_codigo_" + ii);
              long lng_a05_codigo = Long.parseLong(str_a05_codigo);
              AgendaUsuarioPerfil oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();

              oAgendaUsuarioPerfilModel.setA05_codigo(lng_a05_codigo);
              int num_usu_per_T = Integer.parseInt(usu_per_T);
              int num_usu_per_F = Integer.parseInt(usu_per_F);
              int num_usu_per_E = Integer.parseInt(usu_per_E);
              int num_usu_per_A = Integer.parseInt(usu_per_A);
              oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(num_usu_per_T);
              oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(num_usu_per_F);
              oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(num_usu_per_E);
              oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(num_usu_per_A);
              oAgendaUsuarioPerfilModel.setA05_dt_ultima_alteracao(iau_dateToday);
              resultBD =
                  agendaUsuarioPerfilService.updatePerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
            }
          }
        }
      }
    }

    pagina.put("okMetodo", String.valueOf(okMetodo));

    return pagina;
  }
}
