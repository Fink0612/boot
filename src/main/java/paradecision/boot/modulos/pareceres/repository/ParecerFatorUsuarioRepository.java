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
      ParecerFatorUsuario dadosParecerFatorUsuario) {
    ParecerFatorUsuario dadosParecerFatorUsuarioAuxiliares = dadosParecerFatorUsuario;
    int achouCadastro = 0;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM PARECER_FATOR_USUARIO_07 WHERE A06_CODIGO=? AND A02_CODIGO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosParecerFatorUsuario.getA06_codigo());
      comandoPreparado.setLong(2, dadosParecerFatorUsuario.getA02_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        achouCadastro = 1;
        dadosParecerFatorUsuario.setA07_codigo(resultadoConsulta.getLong("A07_CODIGO"));
        dadosParecerFatorUsuario.setA07_num_sequencia(resultadoConsulta.getInt("A07_NUM_SEQUENCIA"));
        dadosParecerFatorUsuario.setA07_certeza(resultadoConsulta.getDouble("A07_CERTEZA"));
        dadosParecerFatorUsuario.setA07_contradicao(resultadoConsulta.getDouble("A07_CONTRADICAO"));
        dadosParecerFatorUsuario.setStr_a07_certeza(resultadoConsulta.getString("A07_CERTEZA"));
        dadosParecerFatorUsuario.setStr_a07_contradicao(resultadoConsulta.getString("A07_CONTRADICAO"));
        dadosParecerFatorUsuario.setA07_dt_cadastro(resultadoConsulta.getDate("A07_DT_CADASTRO"));
        dadosParecerFatorUsuario.setA07_dt_ultima_alteracao(resultadoConsulta.getDate("A07_DT_ULTIMA_ALTERACAO"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(PFUP)");
    }
    fechaCon(conexaoBanco);
    if (achouCadastro == 1) dadosParecerFatorUsuarioAuxiliares = dadosParecerFatorUsuario;
    return dadosParecerFatorUsuarioAuxiliares;
  }

  public String insertParecerFatorUsuario(ParecerFatorUsuario dadosParecerFatorUsuario) {
    String operacaoConcluida = "OK";
    double valorParecer = 0;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "INSERT INTO PARECER_FATOR_USUARIO_07 (";
    instrucaoSql += "A06_CODIGO, A02_CODIGO, ";
    instrucaoSql += "A07_NUM_SEQUENCIA, A07_CERTEZA, ";
    instrucaoSql += "A07_CONTRADICAO, A07_DT_CADASTRO) ";
    instrucaoSql += "VALUES (?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosParecerFatorUsuario.getA06_codigo());
      comandoPreparado.setLong(2, dadosParecerFatorUsuario.getA02_codigo());
      comandoPreparado.setInt(3, dadosParecerFatorUsuario.getA07_num_sequencia());
      valorParecer = dadosParecerFatorUsuario.getA07_certeza();
      if (valorParecer < 0) comandoPreparado.setNull(4, Types.DOUBLE);
      else comandoPreparado.setDouble(4, valorParecer);
      valorParecer = dadosParecerFatorUsuario.getA07_contradicao();
      if (valorParecer < 0) comandoPreparado.setNull(5, Types.DOUBLE);
      else comandoPreparado.setDouble(5, valorParecer);
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      operacaoConcluida = "NOK";
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(PFUP)");
    }
    fechaCon(conexaoBanco);
    return operacaoConcluida;
  }

  public String updateParecerFatorUsuario(ParecerFatorUsuario dadosParecerFatorUsuario) {
    String operacaoConcluida = "OK";
    double valorParecer = 0;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "UPDATE PARECER_FATOR_USUARIO_07 ";
    instrucaoSql += "SET A07_CERTEZA=?, ";
    instrucaoSql += "A07_CONTRADICAO=? ";
    instrucaoSql += "WHERE (A06_CODIGO=? AND A02_CODIGO=?); ";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      valorParecer = dadosParecerFatorUsuario.getA07_certeza();
      if (valorParecer < 0) comandoPreparado.setNull(1, Types.DOUBLE);
      else comandoPreparado.setDouble(1, valorParecer);
      valorParecer = dadosParecerFatorUsuario.getA07_contradicao();
      if (valorParecer < 0) comandoPreparado.setNull(2, Types.DOUBLE);
      else comandoPreparado.setDouble(2, valorParecer);
      comandoPreparado.setLong(3, dadosParecerFatorUsuario.getA06_codigo());
      comandoPreparado.setLong(4, dadosParecerFatorUsuario.getA02_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      operacaoConcluida = "NOK";
      System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(PFUP)");
    }
    fechaCon(conexaoBanco);
    return operacaoConcluida;
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
