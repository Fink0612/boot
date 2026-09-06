package paradecision.boot.modulos.usuarios.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.compartilhado.util.MetodosUteis;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class UsuarioRepository {

  public Usuario selectUserLogin(Usuario dadosUsuario) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM USUARIO_02 WHERE A02_USUARIO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_usuario());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_dt_cadastro(resultadoConsulta.getDate("A02_DT_CADASTRO"));
        dadosUsuario.setA02_dt_ultima_alteracao(resultadoConsulta.getDate("A02_DT_ULTIMA_ALTERACAO"));
        dadosUsuario.setA02_status(resultadoConsulta.getInt("A02_STATUS"));
        dadosUsuario.setA02_email(resultadoConsulta.getString("A02_EMAIL"));
        dadosUsuario.setA02_codigo_link(resultadoConsulta.getString("A02_CODIGO_LINK"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        dadosUsuario.setA02_senha(resultadoConsulta.getString("A02_SENHA"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S1)");
    }
    fechaCon(conexaoBanco);
    return dadosUsuario;
  }

  public Usuario selectUserByCode(Usuario dadosUsuario) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM USUARIO_02 WHERE A02_CODIGO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosUsuario.getA02_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario.setA02_dt_cadastro(resultadoConsulta.getDate("A02_DT_CADASTRO"));
        dadosUsuario.setA02_dt_ultima_alteracao(resultadoConsulta.getDate("A02_DT_ULTIMA_ALTERACAO"));
        dadosUsuario.setA02_status(resultadoConsulta.getInt("A02_STATUS"));
        dadosUsuario.setA02_email(resultadoConsulta.getString("A02_EMAIL"));
        dadosUsuario.setA02_codigo_link(resultadoConsulta.getString("A02_CODIGO_LINK"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        dadosUsuario.setA02_usuario(resultadoConsulta.getString("A02_USUARIO"));
        dadosUsuario.setA02_senha(resultadoConsulta.getString("A02_SENHA"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S2)");
    }
    fechaCon(conexaoBanco);
    return dadosUsuario;
  }

  public Usuario selectUserIni(Usuario dadosUsuario) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM USUARIO_02 WHERE A02_CODIGO_LINK=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_codigo_link());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_dt_cadastro(resultadoConsulta.getDate("A02_DT_CADASTRO"));
        dadosUsuario.setA02_dt_ultima_alteracao(resultadoConsulta.getDate("A02_DT_ULTIMA_ALTERACAO"));
        dadosUsuario.setA02_status(resultadoConsulta.getInt("A02_STATUS"));
        dadosUsuario.setA02_email(resultadoConsulta.getString("A02_EMAIL"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        dadosUsuario.setA02_usuario(resultadoConsulta.getString("A02_USUARIO"));
        dadosUsuario.setA02_senha(resultadoConsulta.getString("A02_SENHA"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S3)");
    }
    fechaCon(conexaoBanco);
    return dadosUsuario;
  }

  public void updateSenhaUsuario(Usuario dadosUsuario) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "UPDATE USUARIO_02 SET A02_SENHA=?, A02_CODIGO_LINK=? WHERE A02_USUARIO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_senha().trim());
      comandoPreparado.setString(2, dadosUsuario.getA02_codigo_link());
      comandoPreparado.setString(3, dadosUsuario.getA02_usuario());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a atualiza��o de dados no BD...(UP-U1)");
    }
    fechaCon(conexaoBanco);
  }

  public String updateUsuario(Usuario dadosUsuario) {
    String operacaoConcluida = "OK";
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "UPDATE USUARIO_02 SET ";
    instrucaoSql += "A02_NOME=?, A02_EMAIL=?, A02_USUARIO=?, ";
    instrucaoSql += "A02_SENHA=?, A02_STATUS=? ";
    instrucaoSql += "WHERE A02_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_nome());
      comandoPreparado.setString(2, dadosUsuario.getA02_email());
      comandoPreparado.setString(3, dadosUsuario.getA02_usuario());
      comandoPreparado.setString(4, dadosUsuario.getA02_senha());
      comandoPreparado.setInt(5, dadosUsuario.getA02_status());
      comandoPreparado.setLong(6, dadosUsuario.getA02_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      operacaoConcluida = "NOK";
      System.out.println(":: ERRO :: Problemas com a atualiza��o de dados no BD...(UP-U2)");
    }
    fechaCon(conexaoBanco);
    return operacaoConcluida;
  }

  public Usuario insertUsuario(Usuario dadosUsuario) {
    System.out.println("cadastrando usu�rio");
    String nowBd = MetodosUteis.getDatNowBD();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "INSERT INTO USUARIO_02 (A02_NOME, A02_USUARIO, A02_SENHA, ";
    instrucaoSql += "A02_CODIGO_LINK, A02_EMAIL, A02_STATUS, A02_DT_CADASTRO) ";
    instrucaoSql += "VALUES (?, ?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_nome());
      comandoPreparado.setString(2, dadosUsuario.getA02_usuario());
      comandoPreparado.setString(3, dadosUsuario.getA02_senha());
      comandoPreparado.setString(4, dadosUsuario.getA02_codigo_link());
      comandoPreparado.setString(5, dadosUsuario.getA02_email());
      comandoPreparado.setInt(6, dadosUsuario.getA02_status());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(UP)");
    }
    instrucaoSql = "SELECT * FROM USUARIO_02 WHERE A02_USUARIO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_usuario());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UP-S4)");
    }
    fechaCon(conexaoBanco);
    return dadosUsuario;
  }

  public Usuario XinsertUsuario2(Usuario dadosUsuario) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "CALL PROC_INS_USUARIO2(?,?,?,?,?,?,?)";
    try {
      CallableStatement oCall = conexaoBanco.prepareCall(instrucaoSql);
      oCall.setString(1, dadosUsuario.getA02_nome());
      oCall.setString(2, dadosUsuario.getA02_usuario());
      oCall.setString(3, dadosUsuario.getA02_senha());
      oCall.setString(4, dadosUsuario.getA02_email());
      oCall.setString(5, dadosUsuario.getA02_codigo_link());
      oCall.setInt(6, dadosUsuario.getA02_status());
      oCall.setString(7, MetodosUteis.chaveCodificacaoLegada);
      oCall.execute();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: UP05 :: Problemas com a inser��o de dados no BD...(UP-PROC)");
    }
    instrucaoSql = "SELECT * FROM USUARIO_02 WHERE A02_USUARIO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosUsuario.getA02_usuario());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: UP05 :: Problemas com a leitura de dados no BD...(UP-S5)");
    }
    fechaCon(conexaoBanco);
    return dadosUsuario;
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
