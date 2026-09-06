package paradecision.boot.modulos.usuarios.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.compartilhado.infra.ConnectionFactory;
import paradecision.boot.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class UsuarioRepository {

  public Usuario selectUserLogin(Usuario oUsuarioModel) {
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM USUARIO_02 WHERE A02_USUARIO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_usuario());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_dt_cadastro(rs.getDate("A02_DT_CADASTRO"));
        oUsuarioModel.setA02_dt_ultima_alteracao(rs.getDate("A02_DT_ULTIMA_ALTERACAO"));
        oUsuarioModel.setA02_status(rs.getInt("A02_STATUS"));
        oUsuarioModel.setA02_email(rs.getString("A02_EMAIL"));
        oUsuarioModel.setA02_codigo_link(rs.getString("A02_CODIGO_LINK"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        oUsuarioModel.setA02_senha(rs.getString("A02_SENHA"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S1)");
    }
    fechaCon(con);
    return oUsuarioModel;
  }

  public Usuario selectUserByCode(Usuario oUsuarioModel) {
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM USUARIO_02 WHERE A02_CODIGO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oUsuarioModel.getA02_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel.setA02_dt_cadastro(rs.getDate("A02_DT_CADASTRO"));
        oUsuarioModel.setA02_dt_ultima_alteracao(rs.getDate("A02_DT_ULTIMA_ALTERACAO"));
        oUsuarioModel.setA02_status(rs.getInt("A02_STATUS"));
        oUsuarioModel.setA02_email(rs.getString("A02_EMAIL"));
        oUsuarioModel.setA02_codigo_link(rs.getString("A02_CODIGO_LINK"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        oUsuarioModel.setA02_usuario(rs.getString("A02_USUARIO"));
        oUsuarioModel.setA02_senha(rs.getString("A02_SENHA"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S2)");
    }
    fechaCon(con);
    return oUsuarioModel;
  }

  public Usuario selectUserIni(Usuario oUsuarioModel) {
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM USUARIO_02 WHERE A02_CODIGO_LINK=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_codigo_link());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_dt_cadastro(rs.getDate("A02_DT_CADASTRO"));
        oUsuarioModel.setA02_dt_ultima_alteracao(rs.getDate("A02_DT_ULTIMA_ALTERACAO"));
        oUsuarioModel.setA02_status(rs.getInt("A02_STATUS"));
        oUsuarioModel.setA02_email(rs.getString("A02_EMAIL"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        oUsuarioModel.setA02_usuario(rs.getString("A02_USUARIO"));
        oUsuarioModel.setA02_senha(rs.getString("A02_SENHA"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S3)");
    }
    fechaCon(con);
    return oUsuarioModel;
  }

  public void updateSenhaUsuario(Usuario oUsuarioModel) {
    Connection con = new ConnectionFactory().getConnection();
    String sql = "UPDATE USUARIO_02 SET A02_SENHA=?, A02_CODIGO_LINK=? WHERE A02_USUARIO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_senha().trim());
      stmt.setString(2, oUsuarioModel.getA02_codigo_link());
      stmt.setString(3, oUsuarioModel.getA02_usuario());
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a atualiza��o de dados no BD...(UP-U1)");
    }
    fechaCon(con);
  }

  public String updateUsuario(Usuario oUsuarioModel) {
    String okMetodo = "OK";
    Connection con = new ConnectionFactory().getConnection();
    String sql = "UPDATE USUARIO_02 SET ";
    sql += "A02_NOME=?, A02_EMAIL=?, A02_USUARIO=?, ";
    sql += "A02_SENHA=?, A02_STATUS=? ";
    sql += "WHERE A02_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_nome());
      stmt.setString(2, oUsuarioModel.getA02_email());
      stmt.setString(3, oUsuarioModel.getA02_usuario());
      stmt.setString(4, oUsuarioModel.getA02_senha());
      stmt.setInt(5, oUsuarioModel.getA02_status());
      stmt.setLong(6, oUsuarioModel.getA02_codigo());
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      okMetodo = "NOK";
      System.out.println(":: ERRO :: Problemas com a atualiza��o de dados no BD...(UP-U2)");
    }
    fechaCon(con);
    return okMetodo;
  }

  public Usuario insertUsuario(Usuario oUsuarioModel) {
    System.out.println("cadastrando usu�rio");
    String nowBd = MetodosUteis.getDatNowBD();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "INSERT INTO USUARIO_02 (A02_NOME, A02_USUARIO, A02_SENHA, ";
    sql += "A02_CODIGO_LINK, A02_EMAIL, A02_STATUS, A02_DT_CADASTRO) ";
    sql += "VALUES (?, ?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_nome());
      stmt.setString(2, oUsuarioModel.getA02_usuario());
      stmt.setString(3, oUsuarioModel.getA02_senha());
      stmt.setString(4, oUsuarioModel.getA02_codigo_link());
      stmt.setString(5, oUsuarioModel.getA02_email());
      stmt.setInt(6, oUsuarioModel.getA02_status());
      System.out.println(stmt);
      stmt.execute();
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(UP)");
    }
    sql = "SELECT * FROM USUARIO_02 WHERE A02_USUARIO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_usuario());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S4)");
    }
    fechaCon(con);
    return oUsuarioModel;
  }

  public Usuario XinsertUsuario2(Usuario oUsuarioModel) {
    Connection con = new ConnectionFactory().getConnection();
    String sql = "CALL PROC_INS_USUARIO2(?,?,?,?,?,?,?)";
    try {
      CallableStatement oCall = con.prepareCall(sql);
      oCall.setString(1, oUsuarioModel.getA02_nome());
      oCall.setString(2, oUsuarioModel.getA02_usuario());
      oCall.setString(3, oUsuarioModel.getA02_senha());
      oCall.setString(4, oUsuarioModel.getA02_email());
      oCall.setString(5, oUsuarioModel.getA02_codigo_link());
      oCall.setInt(6, oUsuarioModel.getA02_status());
      oCall.setString(7, MetodosUteis.vChave);
      oCall.execute();
    } catch (Exception e) {
      System.out.println(":: ERRO :: UP05 :: Problemas com a inser��o de dados no BD...(UP-PROC)");
    }
    sql = "SELECT * FROM USUARIO_02 WHERE A02_USUARIO=?";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setString(1, oUsuarioModel.getA02_usuario());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: UP05 :: Problemas com a leitura de dados no BD...(UP-S5)");
    }
    fechaCon(con);
    return oUsuarioModel;
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
