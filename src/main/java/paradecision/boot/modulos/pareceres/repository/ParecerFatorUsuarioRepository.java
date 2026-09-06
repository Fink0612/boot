package paradecision.boot.modulos.pareceres.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;

@Repository
public class ParecerFatorUsuarioRepository {

  public ParecerFatorUsuario selectParecerFatorUsuario(
      ParecerFatorUsuario oParecerFatorUsuarioModel) {
    ParecerFatorUsuario auxParecerFatorUsuarioModel = oParecerFatorUsuarioModel;
    int achouCadastro = 0;
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM PARECER_FATOR_USUARIO_07 WHERE A06_CODIGO=? AND A02_CODIGO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oParecerFatorUsuarioModel.getA06_codigo());
      stmt.setLong(2, oParecerFatorUsuarioModel.getA02_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        achouCadastro = 1;
        oParecerFatorUsuarioModel.setA07_codigo(rs.getLong("A07_CODIGO"));
        oParecerFatorUsuarioModel.setA07_num_sequencia(rs.getInt("A07_NUM_SEQUENCIA"));
        oParecerFatorUsuarioModel.setA07_certeza(rs.getDouble("A07_CERTEZA"));
        oParecerFatorUsuarioModel.setA07_contradicao(rs.getDouble("A07_CONTRADICAO"));
        oParecerFatorUsuarioModel.setStr_a07_certeza(rs.getString("A07_CERTEZA"));
        oParecerFatorUsuarioModel.setStr_a07_contradicao(rs.getString("A07_CONTRADICAO"));
        oParecerFatorUsuarioModel.setA07_dt_cadastro(rs.getDate("A07_DT_CADASTRO"));
        oParecerFatorUsuarioModel.setA07_dt_ultima_alteracao(rs.getDate("A07_DT_ULTIMA_ALTERACAO"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(PFUP)");
    }
    fechaCon(con);
    if (achouCadastro == 1) auxParecerFatorUsuarioModel = oParecerFatorUsuarioModel;
    return auxParecerFatorUsuarioModel;
  }

  public String insertParecerFatorUsuario(ParecerFatorUsuario oParecerFatorUsuarioModel) {
    String okMetodo = "OK";
    double valParecer = 0;
    Connection con = new ConnectionFactory().getConnection();
    String sql = "INSERT INTO PARECER_FATOR_USUARIO_07 (";
    sql += "A06_CODIGO, A02_CODIGO, ";
    sql += "A07_NUM_SEQUENCIA, A07_CERTEZA, ";
    sql += "A07_CONTRADICAO, A07_DT_CADASTRO) ";
    sql += "VALUES (?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oParecerFatorUsuarioModel.getA06_codigo());
      stmt.setLong(2, oParecerFatorUsuarioModel.getA02_codigo());
      stmt.setInt(3, oParecerFatorUsuarioModel.getA07_num_sequencia());
      valParecer = oParecerFatorUsuarioModel.getA07_certeza();
      if (valParecer < 0) stmt.setNull(4, Types.DOUBLE);
      else stmt.setDouble(4, valParecer);
      valParecer = oParecerFatorUsuarioModel.getA07_contradicao();
      if (valParecer < 0) stmt.setNull(5, Types.DOUBLE);
      else stmt.setDouble(5, valParecer);
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      okMetodo = "NOK";
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(PFUP)");
    }
    fechaCon(con);
    return okMetodo;
  }

  public String updateParecerFatorUsuario(ParecerFatorUsuario oParecerFatorUsuarioModel) {
    String okMetodo = "OK";
    double valParecer = 0;
    Connection con = new ConnectionFactory().getConnection();
    String sql = "UPDATE PARECER_FATOR_USUARIO_07 ";
    sql += "SET A07_CERTEZA=?, ";
    sql += "A07_CONTRADICAO=? ";
    sql += "WHERE (A06_CODIGO=? AND A02_CODIGO=?); ";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      valParecer = oParecerFatorUsuarioModel.getA07_certeza();
      if (valParecer < 0) stmt.setNull(1, Types.DOUBLE);
      else stmt.setDouble(1, valParecer);
      valParecer = oParecerFatorUsuarioModel.getA07_contradicao();
      if (valParecer < 0) stmt.setNull(2, Types.DOUBLE);
      else stmt.setDouble(2, valParecer);
      stmt.setLong(3, oParecerFatorUsuarioModel.getA06_codigo());
      stmt.setLong(4, oParecerFatorUsuarioModel.getA02_codigo());
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      okMetodo = "NOK";
      System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(PFUP)");
    }
    fechaCon(con);
    return okMetodo;
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
