package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.entity.Agenda;

@Repository
public class AgendaRepository {

  public long insertAgenda(Agenda oAgendaModel) {
    long res = 0;
    Connection con = new ConnectionFactory().getConnection();

    String sql = "INSERT INTO AGENDA_04 (A04_TITULO, A04_DESCRICAO, ";
    sql += "A04_STATUS_DT_LIMITE, A04_DATA_LIMITE, A04_RESULTADO, ";
    sql +=
        "A04_CERTEZA_RESULTADO, A04_CONTRADICAO_RESULTADO, A01_CODIGO, A04_STATUS, A04_DT_CADASTRO)"
            + " ";
    sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      stmt.setString(1, oAgendaModel.getA04_titulo());
      stmt.setString(2, oAgendaModel.getA04_descricao());
      stmt.setInt(3, oAgendaModel.getA04_status_dt_limite());
      stmt.setDate(4, oAgendaModel.getA04_data_limite());
      stmt.setString(5, oAgendaModel.getA04_resultado());
      stmt.setDouble(6, oAgendaModel.getA04_certeza_resultado());
      stmt.setDouble(7, oAgendaModel.getA04_contradicao_resultado());
      stmt.setLong(8, oAgendaModel.getA01_codigo());
      stmt.setInt(9, oAgendaModel.getA04_status());
      stmt.executeUpdate();
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.first()) {
        res = rs.getLong(1);
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(AP)");
    }
    fechaCon(con);
    return res;
  }

  public String updateAgenda(Agenda oAgendaModel) {
    String res = "OK";
    Connection con = new ConnectionFactory().getConnection();

    String sql = "UPDATE AGENDA_04 SET ";
    sql += "A04_TITULO=?, A04_DESCRICAO=?, A04_STATUS_DT_LIMITE=?, ";
    sql += "A04_DATA_LIMITE=?, A04_DT_ULTIMA_ALTERACAO=sysdate() ";
    sql += "WHERE A04_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oAgendaModel.getA04_titulo());
      stmt.setString(2, oAgendaModel.getA04_descricao());
      stmt.setInt(3, oAgendaModel.getA04_status_dt_limite());
      stmt.setDate(4, oAgendaModel.getA04_data_limite());
      stmt.setLong(5, oAgendaModel.getA04_codigo());
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      res = "NOK";
      System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(AP-U1)");
    }
    fechaCon(con);
    return res;
  }

  public Agenda selectAgenda(Agenda oAgendaModel) {
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM AGENDA_04 WHERE A04_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaModel.getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oAgendaModel.setA04_titulo(rs.getString("A04_TITULO"));
        oAgendaModel.setA04_descricao(rs.getString("A04_DESCRICAO"));
        oAgendaModel.setA04_status_dt_limite(rs.getInt("A04_STATUS_DT_LIMITE"));
        oAgendaModel.setA04_data_limite(rs.getDate("A04_DATA_LIMITE"));
        oAgendaModel.setA04_resultado(rs.getString("A04_RESULTADO"));
        oAgendaModel.setA04_certeza_resultado(rs.getDouble("A04_CERTEZA_RESULTADO"));
        oAgendaModel.setA04_contradicao_resultado(rs.getDouble("A04_CONTRADICAO_RESULTADO"));
        oAgendaModel.setA04_dt_cadastro(rs.getDate("A04_DT_CADASTRO"));
        oAgendaModel.setA04_dt_ultima_alteracao(rs.getDate("A04_DT_ULTIMA_ALTERACAO"));
        oAgendaModel.setA01_codigo(rs.getLong("A01_CODIGO"));
        oAgendaModel.setA04_status(rs.getInt("A04_STATUS"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AP)");
    }
    fechaCon(con);
    return oAgendaModel;
  }

  public String updateStatusAgenda(Agenda oAgendaModel) {
    String msgAction = "";
    Connection con = new ConnectionFactory().getConnection();

    String sql = "UPDATE AGENDA_04 ";
    sql += "SET A04_STATUS=? ";
    sql += "WHERE A04_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setInt(1, oAgendaModel.getA04_status());
      stmt.setLong(2, oAgendaModel.getA04_codigo());
      stmt.execute();
      stmt.close();
      msgAction = "OK";
    } catch (Exception e) {
      msgAction = "NOK";
      System.out.println(":: ERRO :: Problemas com a atualiza��o de dados no BD...(AP-U2)");
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
