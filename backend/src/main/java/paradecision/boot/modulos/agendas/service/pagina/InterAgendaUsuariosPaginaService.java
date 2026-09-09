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

    int operacaoConcluida = 1;
    String iau_str_qtd_usuarios = formulario.valor("qtdUsuariosEmpresa");
    int iau_int_qtd_usuarios = MetodosUteis.retornaInt(iau_str_qtd_usuarios);
    if (iau_int_qtd_usuarios > 0) {
      String codigoAgendaTexto = formulario.valor("ct_A04_CODIGO");
      long codigoAgendaNumerico = Long.parseLong(codigoAgendaTexto);
      for (int indiceRegistro = 0; indiceRegistro < iau_int_qtd_usuarios; indiceRegistro++) {
        String resultadoBanco = "";
        // Valor anterior (ao abrir a página)...
        String val_marc = formulario.valor("a02_marcado_" + indiceRegistro);
        if (val_marc == null) val_marc = "0";
        if ("".equals(val_marc)) val_marc = "0";
        // Valor atual (após o envio)...
        String val_check = formulario.valor("usu_emp_" + indiceRegistro);
        if (val_check == null) val_check = "0";
        if ("".equals(val_check)) val_check = "0";
        if (!(val_marc.equals(val_check))) {
          if (val_check.equals("0")) {
            String codigoParticipacaoAgendaTexto = formulario.valor("a05_codigo_" + indiceRegistro);
            long codigoParticipacaoAgendaNumerico = Long.parseLong(codigoParticipacaoAgendaTexto);
            if (codigoParticipacaoAgendaNumerico > 0) {
              AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();

              dadosAgendaUsuarioPerfil.setA05_codigo(codigoParticipacaoAgendaNumerico);
              resultadoBanco =
                  agendaUsuarioPerfilService.deleteAgendaUsuarioPerfil(dadosAgendaUsuarioPerfil);
            }
          } else if (val_check.equals("1")) {
            Date iau_dateToday = MetodosUteis.retornaDataAgora();
            String codigoUsuarioTexto = formulario.valor("a02_codigo_" + indiceRegistro);
            long codigoUsuarioNumerico = Long.parseLong(codigoUsuarioTexto);
            AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();

            dadosAgendaUsuarioPerfil.setA02_codigo(codigoUsuarioNumerico);
            dadosAgendaUsuarioPerfil.setA04_codigo(codigoAgendaNumerico);
            dadosAgendaUsuarioPerfil.setA05_num_sequencia(1);
            String perfilTitularInformado = formulario.valor("usu_per_T_" + indiceRegistro);
            String perfilFacilitadorInformado = formulario.valor("usu_per_F_" + indiceRegistro);
            String perfilEspecialistaInformado = formulario.valor("usu_per_E_" + indiceRegistro);
            String perfilAnalistaInformado = formulario.valor("usu_per_A_" + indiceRegistro);
            if (perfilTitularInformado == null) perfilTitularInformado = "0";
            if ("".equals(perfilTitularInformado)) perfilTitularInformado = "0";
            if (perfilFacilitadorInformado == null) perfilFacilitadorInformado = "0";
            if ("".equals(perfilFacilitadorInformado)) perfilFacilitadorInformado = "0";
            if (perfilEspecialistaInformado == null) perfilEspecialistaInformado = "0";
            if ("".equals(perfilEspecialistaInformado)) perfilEspecialistaInformado = "0";
            if (perfilAnalistaInformado == null) perfilAnalistaInformado = "0";
            if ("".equals(perfilAnalistaInformado)) perfilAnalistaInformado = "0";
            int perfilTitularNumerico = Integer.parseInt(perfilTitularInformado);
            int perfilFacilitadorNumerico = Integer.parseInt(perfilFacilitadorInformado);
            int perfilEspecialistaNumerico = Integer.parseInt(perfilEspecialistaInformado);
            int perfilAnalistaNumerico = Integer.parseInt(perfilAnalistaInformado);
            dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_titular(perfilTitularNumerico);
            dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_facilitador(perfilFacilitadorNumerico);
            dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_especialista(perfilEspecialistaNumerico);
            dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_analista(perfilAnalistaNumerico);
            dadosAgendaUsuarioPerfil.setA05_dt_cadastro(iau_dateToday);
            resultadoBanco =
                agendaUsuarioPerfilService.insertPerfilUsuarioAgenda(dadosAgendaUsuarioPerfil);
          }
          if (resultadoBanco == "NOK") {
            operacaoConcluida = 0;
          }
        } else {
          if (val_check.equals("1")) {
            String perfilTitularInformado = formulario.valor("usu_per_T_" + indiceRegistro);
            String perfilFacilitadorInformado = formulario.valor("usu_per_F_" + indiceRegistro);
            String perfilEspecialistaInformado = formulario.valor("usu_per_E_" + indiceRegistro);
            String perfilAnalistaInformado = formulario.valor("usu_per_A_" + indiceRegistro);
            String aux_per_T = formulario.valor("aux_per_T_" + indiceRegistro);
            String aux_per_F = formulario.valor("aux_per_F_" + indiceRegistro);
            String aux_per_E = formulario.valor("aux_per_E_" + indiceRegistro);
            String aux_per_A = formulario.valor("aux_per_A_" + indiceRegistro);
            if (perfilTitularInformado == null) perfilTitularInformado = "0";
            if ("".equals(perfilTitularInformado)) perfilTitularInformado = "0";
            if (perfilFacilitadorInformado == null) perfilFacilitadorInformado = "0";
            if ("".equals(perfilFacilitadorInformado)) perfilFacilitadorInformado = "0";
            if (perfilEspecialistaInformado == null) perfilEspecialistaInformado = "0";
            if ("".equals(perfilEspecialistaInformado)) perfilEspecialistaInformado = "0";
            if (perfilAnalistaInformado == null) perfilAnalistaInformado = "0";
            if ("".equals(perfilAnalistaInformado)) perfilAnalistaInformado = "0";
            if (aux_per_T == null) aux_per_T = "0";
            if ("".equals(aux_per_T)) aux_per_T = "0";
            if (aux_per_F == null) aux_per_F = "0";
            if ("".equals(aux_per_F)) aux_per_F = "0";
            if (aux_per_E == null) aux_per_E = "0";
            if ("".equals(aux_per_E)) aux_per_E = "0";
            if (aux_per_A == null) aux_per_A = "0";
            if ("".equals(aux_per_A)) aux_per_A = "0";
            if (!perfilTitularInformado.equals(aux_per_T)
                || !perfilFacilitadorInformado.equals(aux_per_F)
                || !perfilEspecialistaInformado.equals(aux_per_E)
                || !perfilAnalistaInformado.equals(aux_per_A)) {
              Date iau_dateToday = MetodosUteis.retornaDataAgora();
              String codigoParticipacaoAgendaTexto = formulario.valor("a05_codigo_" + indiceRegistro);
              long codigoParticipacaoAgendaNumerico = Long.parseLong(codigoParticipacaoAgendaTexto);
              AgendaUsuarioPerfil dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();

              dadosAgendaUsuarioPerfil.setA05_codigo(codigoParticipacaoAgendaNumerico);
              int perfilTitularNumerico = Integer.parseInt(perfilTitularInformado);
              int perfilFacilitadorNumerico = Integer.parseInt(perfilFacilitadorInformado);
              int perfilEspecialistaNumerico = Integer.parseInt(perfilEspecialistaInformado);
              int perfilAnalistaNumerico = Integer.parseInt(perfilAnalistaInformado);
              dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_titular(perfilTitularNumerico);
              dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_facilitador(perfilFacilitadorNumerico);
              dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_especialista(perfilEspecialistaNumerico);
              dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_analista(perfilAnalistaNumerico);
              dadosAgendaUsuarioPerfil.setA05_dt_ultima_alteracao(iau_dateToday);
              resultadoBanco =
                  agendaUsuarioPerfilService.updatePerfilUsuarioAgenda(dadosAgendaUsuarioPerfil);
            }
          }
        }
      }
    }

    pagina.put("okMetodo", String.valueOf(operacaoConcluida));

    return pagina;
  }
}
