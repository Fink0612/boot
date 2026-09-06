package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.entity.Agenda;

@Repository
public class AgendaRepository {

  public long insertAgenda(Agenda dadosAgenda) {
    long resultadoProcessamento = 0;
    Connection conexaoBanco = new ConnectionFactory().getConnection();

    String instrucaoSql = "INSERT INTO AGENDA_04 (A04_TITULO, A04_DESCRICAO, ";
    instrucaoSql += "A04_STATUS_DT_LIMITE, A04_DATA_LIMITE, A04_RESULTADO, ";
    instrucaoSql +=
        "A04_CERTEZA_RESULTADO, A04_CONTRADICAO_RESULTADO, A01_CODIGO, A04_STATUS, A04_DT_CADASTRO)"
            + " ";
    instrucaoSql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql, PreparedStatement.RETURN_GENERATED_KEYS);
      comandoPreparado.setString(1, dadosAgenda.getA04_titulo());
      comandoPreparado.setString(2, dadosAgenda.getA04_descricao());
      comandoPreparado.setInt(3, dadosAgenda.getA04_status_dt_limite());
      comandoPreparado.setDate(4, dadosAgenda.getA04_data_limite());
      comandoPreparado.setString(5, dadosAgenda.getA04_resultado());
      comandoPreparado.setDouble(6, dadosAgenda.getA04_certeza_resultado());
      comandoPreparado.setDouble(7, dadosAgenda.getA04_contradicao_resultado());
      comandoPreparado.setLong(8, dadosAgenda.getA01_codigo());
      comandoPreparado.setInt(9, dadosAgenda.getA04_status());
      comandoPreparado.executeUpdate();
      ResultSet resultadoConsulta = comandoPreparado.getGeneratedKeys();
      if (resultadoConsulta.first()) {
        resultadoProcessamento = resultadoConsulta.getLong(1);
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(AP)");
    }
    fechaCon(conexaoBanco);
    return resultadoProcessamento;
  }

  public String updateAgenda(Agenda dadosAgenda) {
    String resultadoProcessamento = "OK";
    Connection conexaoBanco = new ConnectionFactory().getConnection();

    String instrucaoSql = "UPDATE AGENDA_04 SET ";
    instrucaoSql += "A04_TITULO=?, A04_DESCRICAO=?, A04_STATUS_DT_LIMITE=?, ";
    instrucaoSql += "A04_DATA_LIMITE=?, A04_DT_ULTIMA_ALTERACAO=sysdate() ";
    instrucaoSql += "WHERE A04_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setString(1, dadosAgenda.getA04_titulo());
      comandoPreparado.setString(2, dadosAgenda.getA04_descricao());
      comandoPreparado.setInt(3, dadosAgenda.getA04_status_dt_limite());
      comandoPreparado.setDate(4, dadosAgenda.getA04_data_limite());
      comandoPreparado.setLong(5, dadosAgenda.getA04_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      resultadoProcessamento = "NOK";
      System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(AP-U1)");
    }
    fechaCon(conexaoBanco);
    return resultadoProcessamento;
  }

  public Agenda selectAgenda(Agenda dadosAgenda) {
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM AGENDA_04 WHERE A04_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgenda.getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosAgenda.setA04_titulo(resultadoConsulta.getString("A04_TITULO"));
        dadosAgenda.setA04_descricao(resultadoConsulta.getString("A04_DESCRICAO"));
        dadosAgenda.setA04_status_dt_limite(resultadoConsulta.getInt("A04_STATUS_DT_LIMITE"));
        dadosAgenda.setA04_data_limite(resultadoConsulta.getDate("A04_DATA_LIMITE"));
        dadosAgenda.setA04_resultado(resultadoConsulta.getString("A04_RESULTADO"));
        dadosAgenda.setA04_certeza_resultado(resultadoConsulta.getDouble("A04_CERTEZA_RESULTADO"));
        dadosAgenda.setA04_contradicao_resultado(resultadoConsulta.getDouble("A04_CONTRADICAO_RESULTADO"));
        dadosAgenda.setA04_dt_cadastro(resultadoConsulta.getDate("A04_DT_CADASTRO"));
        dadosAgenda.setA04_dt_ultima_alteracao(resultadoConsulta.getDate("A04_DT_ULTIMA_ALTERACAO"));
        dadosAgenda.setA01_codigo(resultadoConsulta.getLong("A01_CODIGO"));
        dadosAgenda.setA04_status(resultadoConsulta.getInt("A04_STATUS"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AP)");
    }
    fechaCon(conexaoBanco);
    return dadosAgenda;
  }

  public String updateStatusAgenda(Agenda dadosAgenda) {
    String mensagemAcao = "";
    Connection conexaoBanco = new ConnectionFactory().getConnection();

    String instrucaoSql = "UPDATE AGENDA_04 ";
    instrucaoSql += "SET A04_STATUS=? ";
    instrucaoSql += "WHERE A04_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setInt(1, dadosAgenda.getA04_status());
      comandoPreparado.setLong(2, dadosAgenda.getA04_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
      mensagemAcao = "OK";
    } catch (Exception excecao) {
      mensagemAcao = "NOK";
      System.out.println(":: ERRO :: Problemas com a atualiza��o de dados no BD...(AP-U2)");
    }
    fechaCon(conexaoBanco);
    return mensagemAcao;
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
