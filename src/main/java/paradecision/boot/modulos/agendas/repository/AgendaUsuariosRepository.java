package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaUsuariosDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaUsuariosRepository {

  public AgendaUsuariosDados selectUsuariosDaAgenda(AgendaUsuariosDados dadosAgendaUsuarios) {
    Usuario dadosUsuario;
    AgendaUsuarioPerfil dadosAgendaUsuarioPerfil;
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    ArrayList<AgendaUsuarioPerfil> listaAgendaUsuarioPerfil =
        new ArrayList<AgendaUsuarioPerfil>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM VW_AGENDAS_USUARIOS WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgendaUsuarios.getoAgendaModel().getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario = new Usuario();
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        listaUsuario.add(dadosUsuario);
        dadosAgendaUsuarioPerfil = new AgendaUsuarioPerfil();
        dadosAgendaUsuarioPerfil.setA05_codigo(resultadoConsulta.getLong("A05_CODIGO"));
        dadosAgendaUsuarioPerfil.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosAgendaUsuarioPerfil.setA05_num_sequencia(resultadoConsulta.getLong("A05_NUM_SEQUENCIA"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_titular(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_TITULAR"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_facilitador(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_FACILITADOR"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_especialista(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_analista(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_ANALISTA"));
        dadosAgendaUsuarioPerfil.setA05_dt_cadastro(resultadoConsulta.getDate("A05_DT_CADASTRO"));
        listaAgendaUsuarioPerfil.add(dadosAgendaUsuarioPerfil);
      }
      dadosAgendaUsuarios.setArrUsuarioModel(listaUsuario);
      dadosAgendaUsuarios.setArrAgendaUsuarioPerfilModel(listaAgendaUsuarioPerfil);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUP-S1)");
    }
    fechaCon(conexaoBanco);
    return dadosAgendaUsuarios;
  }

  public ArrayList<Usuario> getArrEspecialistasModel(Agenda dadosAgenda) {
    Usuario dadosUsuario;
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM VW_AGENDAS_USUARIOS WHERE (A04_CODIGO=? ";
    instrucaoSql += "AND A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA=1);";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgenda.getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario = new Usuario();
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        listaUsuario.add(dadosUsuario);
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUP-S2)");
    }
    fechaCon(conexaoBanco);
    return listaUsuario;
  }

  // ......PARA LIDAR COM O BANCO DE DADOS..........

  private void fechaCon(Connection conexaoBanco) {
    if (conexaoBanco == null) return;
    try {
      conexaoBanco.close();
    } catch (SQLException excecao) {
      excecao.printStackTrace();
    }
  }
}
