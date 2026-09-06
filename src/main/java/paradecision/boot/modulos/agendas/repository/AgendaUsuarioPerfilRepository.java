package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;

@Repository
public class AgendaUsuarioPerfilRepository {

  public AgendaUsuarioPerfil selectAgendaUsuarioPerfil(
      AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    AgendaUsuarioPerfil auxAgendaUsuarioPerfilModel = null;
    int achouCadastro = 0;
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM USUARIO_AGENDA_05 WHERE A02_CODIGO=? AND A04_CODIGO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaUsuarioPerfilModel.getA02_codigo());
      stmt.setLong(2, oAgendaUsuarioPerfilModel.getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        achouCadastro = 1;
        oAgendaUsuarioPerfilModel.setA05_codigo(rs.getLong("A05_CODIGO"));
        oAgendaUsuarioPerfilModel.setA05_num_sequencia(rs.getInt("A05_NUM_SEQUENCIA"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_TITULAR"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_FACILITADOR"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_ANALISTA"));
        oAgendaUsuarioPerfilModel.setA05_dt_cadastro(rs.getDate("A05_DT_CADASTRO"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(con);
    if (achouCadastro == 1) auxAgendaUsuarioPerfilModel = oAgendaUsuarioPerfilModel;
    return auxAgendaUsuarioPerfilModel;
  }

  public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    String msgAction = "";
    Connection con = new ConnectionFactory().getConnection();
    String sql = "DELETE FROM USUARIO_AGENDA_05 WHERE A05_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaUsuarioPerfilModel.getA05_codigo());
      stmt.execute();
      stmt.close();
      msgAction = "OK";
    } catch (Exception e) {
      msgAction = "NOK";
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(con);
    return msgAction;
  }

  public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    String msgAction = "";
    Connection con = new ConnectionFactory().getConnection();
    String sql = "UPDATE USUARIO_AGENDA_05 ";
    sql += "SET A05_PERFIL_AGENDA_USUARIO_TITULAR=?, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_FACILITADOR=?, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA=?, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_ANALISTA=?, ";
    sql += "A05_DT_ULTIMA_ALTERACAO=? ";
    sql += "WHERE A05_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular());
      stmt.setInt(2, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador());
      stmt.setInt(3, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista());
      stmt.setInt(4, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista());
      stmt.setDate(5, oAgendaUsuarioPerfilModel.getA05_dt_ultima_alteracao());
      stmt.setLong(6, oAgendaUsuarioPerfilModel.getA05_codigo());
      stmt.execute();
      stmt.close();
      msgAction = "OK";
    } catch (Exception e) {
      msgAction = "NOK";
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(con);
    return msgAction;
  }

  public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfil oAgendaUsuarioPerfilModel) {
    String msgAction = "";
    Connection con = new ConnectionFactory().getConnection();
    String sql = "INSERT INTO USUARIO_AGENDA_05 (";
    sql += "A02_CODIGO, ";
    sql += "A04_CODIGO, ";
    sql += "A05_NUM_SEQUENCIA, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_TITULAR, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_FACILITADOR, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA, ";
    sql += "A05_PERFIL_AGENDA_USUARIO_ANALISTA, ";
    sql += "A05_DT_CADASTRO) ";
    sql += "VALUES (?, ?, ?, ?, ?, ?, ?, sysdate());";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaUsuarioPerfilModel.getA02_codigo());
      stmt.setLong(2, oAgendaUsuarioPerfilModel.getA04_codigo());
      stmt.setLong(3, oAgendaUsuarioPerfilModel.getA05_num_sequencia());
      stmt.setInt(4, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_titular());
      stmt.setInt(5, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_facilitador());
      stmt.setInt(6, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_especialista());
      stmt.setInt(7, oAgendaUsuarioPerfilModel.getA05_perfil_agenda_usuario_analista());
      stmt.execute();
      stmt.close();
      msgAction = "OK";
    } catch (Exception e) {
      msgAction = "NOK";
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(con);
    return msgAction;
  }

  // ......PARA LIDAR COM O BANCO DE DADOS..........

  private void fechaCon(Connection con) {
    if (con == null) return;
    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
