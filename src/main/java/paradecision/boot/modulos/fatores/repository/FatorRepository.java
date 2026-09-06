package paradecision.boot.modulos.fatores.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.fatores.entity.Fator;

@Repository
public class FatorRepository {

  public String insertFator(Fator dadosFator) {
    String resultadoProcessamento = "OK";
    Connection conexaoBanco = new ConnectionFactory().getConnection();

    String instrucaoSql = "INSERT INTO FATOR_06 (";
    instrucaoSql += "A06_TITULO, A06_DESCRICAO, A06_NUM_SEQUENCIA, ";
    instrucaoSql += "A04_CODIGO, A02_CODIGO, A06_CERTEZA_RESULTANTE_FATOR, ";
    instrucaoSql += "A06_CONTRADICAO_RESULTANTE_FATOR, A06_RESULTADO_FATOR, A06_DT_CADASTRO) ";
    instrucaoSql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosFator.getA06_titulo());
      comandoPreparado.setString(2, dadosFator.getA06_descricao());
      comandoPreparado.setInt(3, dadosFator.getA06_num_sequencia());
      comandoPreparado.setLong(4, dadosFator.getA04_codigo());
      comandoPreparado.setLong(5, dadosFator.getA02_codigo());
      comandoPreparado.setDouble(6, dadosFator.getA06_certeza_resultante_fator());
      comandoPreparado.setDouble(7, dadosFator.getA06_contradicao_resultante_fator());
      comandoPreparado.setString(8, dadosFator.getA06_resultado_fator());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      resultadoProcessamento = "NOK";
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(FP)");
    }
    fechaCon(conexaoBanco);
    return resultadoProcessamento;
  }

  public Fator selectFator(Fator dadosFator) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM FATOR_06 WHERE A06_CODIGO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosFator.getA06_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosFator.setA06_titulo(resultadoConsulta.getString("A06_TITULO"));
        dadosFator.setA06_descricao(resultadoConsulta.getString("A06_DESCRICAO"));
        dadosFator.setA06_num_sequencia(resultadoConsulta.getInt("A06_NUM_SEQUENCIA"));
        dadosFator.setA04_codigo(resultadoConsulta.getLong("A04_CODIGO"));
        dadosFator.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosFator.setA06_certeza_resultante_fator(resultadoConsulta.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
        dadosFator.setA06_contradicao_resultante_fator(
            resultadoConsulta.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
        dadosFator.setA06_resultado_fator(resultadoConsulta.getString("A06_RESULTADO_FATOR"));
        dadosFator.setA06_dt_cadastro(resultadoConsulta.getDate("A06_DT_CADASTRO"));
        dadosFator.setA06_dt_ultima_alteracao(resultadoConsulta.getDate("A06_DT_ULTIMA_ALTERACAO"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(FP)");
    }
    fechaCon(conexaoBanco);
    return dadosFator;
  }

  public String updateFator(Fator dadosFator) {
    String resultadoProcessamento = "OK";
    Connection conexaoBanco = new ConnectionFactory().getConnection();

    String instrucaoSql = "UPDATE FATOR_06 ";
    instrucaoSql += "SET A06_TITULO=?, ";
    instrucaoSql += "A06_DESCRICAO=? ";
    instrucaoSql += "WHERE A06_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosFator.getA06_titulo());
      comandoPreparado.setString(2, dadosFator.getA06_descricao());
      comandoPreparado.setLong(3, dadosFator.getA06_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      resultadoProcessamento = "NOK";
      System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(FP)");
    }
    fechaCon(conexaoBanco);
    return resultadoProcessamento;
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
