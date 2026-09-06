package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaFatoresRepository {

  public AgendaFatoresDados selectFatoresDaAgenda(AgendaFatoresDados oAgendaFatoresModel) {
    Fator oFatorModel;
    ArrayList<Fator> arrFatorModel = new ArrayList<Fator>();
    Usuario oUsuarioModel;
    ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM VW_AGENDAS_FATORES WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaFatoresModel.getoAgendaModel().getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oFatorModel = new Fator();
        oFatorModel.setA06_codigo(rs.getLong("A06_CODIGO"));
        oFatorModel.setA06_titulo(rs.getString("A06_TITULO"));
        oFatorModel.setA06_descricao(rs.getString("A06_DESCRICAO"));
        oFatorModel.setA06_num_sequencia(rs.getInt("A06_NUM_SEQUENCIA"));
        oFatorModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oFatorModel.setA06_certeza_resultante_fator(rs.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
        oFatorModel.setA06_contradicao_resultante_fator(
            rs.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
        oFatorModel.setA06_resultado_fator(rs.getString("A06_RESULTADO_FATOR"));
        oFatorModel.setA06_dt_cadastro(rs.getDate("A06_DT_CADASTRO"));
        oFatorModel.setA06_dt_ultima_alteracao(rs.getDate("A06_DT_ULTIMA_ALTERACAO"));
        arrFatorModel.add(oFatorModel);
        oUsuarioModel = new Usuario();
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        arrUsuarioModel.add(oUsuarioModel);
      }
      oAgendaFatoresModel.setArrFatorModel(arrFatorModel);
      oAgendaFatoresModel.setArrUsuarioModel(arrUsuarioModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP-S1)");
    }
    fechaCon(con);
    return oAgendaFatoresModel;
  }

  public ArrayList<Fator> getArrFatoresModel(Agenda oAgendaModel) {
    Fator oFatorModel;
    ArrayList<Fator> arrFatorModel = new ArrayList<Fator>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM FATOR_06 WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaModel.getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oFatorModel = new Fator();
        oFatorModel.setA06_codigo(rs.getLong("A06_CODIGO"));
        oFatorModel.setA06_titulo(rs.getString("A06_TITULO"));
        oFatorModel.setA06_descricao(rs.getString("A06_DESCRICAO"));
        oFatorModel.setA06_num_sequencia(rs.getInt("A06_NUM_SEQUENCIA"));
        oFatorModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oFatorModel.setA06_certeza_resultante_fator(rs.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
        oFatorModel.setA06_contradicao_resultante_fator(
            rs.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
        oFatorModel.setA06_resultado_fator(rs.getString("A06_RESULTADO_FATOR"));
        oFatorModel.setA06_dt_cadastro(rs.getDate("A06_DT_CADASTRO"));
        oFatorModel.setA06_dt_ultima_alteracao(rs.getDate("A06_DT_ULTIMA_ALTERACAO"));
        arrFatorModel.add(oFatorModel);
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP-S2)");
    }
    fechaCon(con);
    return arrFatorModel;
  }

  public String updateGrausFatoresDaAgenda(AgendaFatoresDados oAgendaFatoresModel) {
    String msgAction = "NOK";
    Fator oFatorModel;
    Agenda oAgendaModel;
    ArrayList<Fator> arrFatorModel = oAgendaFatoresModel.getArrFatorModel();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "";
    try {
      PreparedStatement stmt;
      int qtdFatores = arrFatorModel.size();
      oAgendaModel = oAgendaFatoresModel.getoAgendaModel();
      if (oAgendaModel.getA04_codigo() > 0 && qtdFatores > 0) {
        // ---- ATUALIZANDO A AGENDA -------
        sql = "UPDATE AGENDA_04 SET ";
        sql += "A04_CERTEZA_RESULTADO=?, ";
        sql += "A04_CONTRADICAO_RESULTADO=?, ";
        sql += "A04_RESULTADO=? ";
        sql += "WHERE A04_CODIGO=?;";
        stmt = con.prepareStatement(sql);
        stmt.setDouble(1, oAgendaModel.getA04_certeza_resultado());
        stmt.setDouble(2, oAgendaModel.getA04_contradicao_resultado());
        stmt.setString(3, oAgendaModel.getA04_resultado());
        stmt.setLong(4, oAgendaModel.getA04_codigo());
        stmt.execute();
        stmt.close();
        // ---- ATUALIZANDO OS FATORES -------
        sql = "UPDATE FATOR_06 SET ";
        sql += "A06_CERTEZA_RESULTANTE_FATOR=?, ";
        sql += "A06_CONTRADICAO_RESULTANTE_FATOR=?, ";
        sql += "A06_RESULTADO_FATOR=? ";
        sql += "WHERE A06_CODIGO=?;";
        for (int ii = 0; ii < qtdFatores; ii++) {
          oFatorModel = arrFatorModel.get(ii);
          if (oFatorModel.getA06_codigo() > 0) {
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, oFatorModel.getA06_certeza_resultante_fator());
            stmt.setDouble(2, oFatorModel.getA06_contradicao_resultante_fator());
            stmt.setString(3, oFatorModel.getA06_resultado_fator());
            stmt.setLong(4, oFatorModel.getA06_codigo());
            stmt.execute();
            stmt.close();
          }
        }
        msgAction = "OK";
      }
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a Altera��o de dados no BD...(AFP)");
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
