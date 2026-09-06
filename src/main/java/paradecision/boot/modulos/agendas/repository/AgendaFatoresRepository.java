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

  public AgendaFatoresDados selectFatoresDaAgenda(AgendaFatoresDados dadosAgendaFatores) {
    Fator dadosFator;
    ArrayList<Fator> listaFator = new ArrayList<Fator>();
    Usuario dadosUsuario;
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM VW_AGENDAS_FATORES WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgendaFatores.getoAgendaModel().getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosFator = new Fator();
        dadosFator.setA06_codigo(resultadoConsulta.getLong("A06_CODIGO"));
        dadosFator.setA06_titulo(resultadoConsulta.getString("A06_TITULO"));
        dadosFator.setA06_descricao(resultadoConsulta.getString("A06_DESCRICAO"));
        dadosFator.setA06_num_sequencia(resultadoConsulta.getInt("A06_NUM_SEQUENCIA"));
        dadosFator.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosFator.setA06_certeza_resultante_fator(resultadoConsulta.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
        dadosFator.setA06_contradicao_resultante_fator(
            resultadoConsulta.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
        dadosFator.setA06_resultado_fator(resultadoConsulta.getString("A06_RESULTADO_FATOR"));
        dadosFator.setA06_dt_cadastro(resultadoConsulta.getDate("A06_DT_CADASTRO"));
        dadosFator.setA06_dt_ultima_alteracao(resultadoConsulta.getDate("A06_DT_ULTIMA_ALTERACAO"));
        listaFator.add(dadosFator);
        dadosUsuario = new Usuario();
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        listaUsuario.add(dadosUsuario);
      }
      dadosAgendaFatores.setArrFatorModel(listaFator);
      dadosAgendaFatores.setArrUsuarioModel(listaUsuario);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP-S1)");
    }
    fechaCon(conexaoBanco);
    return dadosAgendaFatores;
  }

  public ArrayList<Fator> getArrFatoresModel(Agenda dadosAgenda) {
    Fator dadosFator;
    ArrayList<Fator> listaFator = new ArrayList<Fator>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM FATOR_06 WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgenda.getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosFator = new Fator();
        dadosFator.setA06_codigo(resultadoConsulta.getLong("A06_CODIGO"));
        dadosFator.setA06_titulo(resultadoConsulta.getString("A06_TITULO"));
        dadosFator.setA06_descricao(resultadoConsulta.getString("A06_DESCRICAO"));
        dadosFator.setA06_num_sequencia(resultadoConsulta.getInt("A06_NUM_SEQUENCIA"));
        dadosFator.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosFator.setA06_certeza_resultante_fator(resultadoConsulta.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
        dadosFator.setA06_contradicao_resultante_fator(
            resultadoConsulta.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
        dadosFator.setA06_resultado_fator(resultadoConsulta.getString("A06_RESULTADO_FATOR"));
        dadosFator.setA06_dt_cadastro(resultadoConsulta.getDate("A06_DT_CADASTRO"));
        dadosFator.setA06_dt_ultima_alteracao(resultadoConsulta.getDate("A06_DT_ULTIMA_ALTERACAO"));
        listaFator.add(dadosFator);
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP-S2)");
    }
    fechaCon(conexaoBanco);
    return listaFator;
  }

  public String updateGrausFatoresDaAgenda(AgendaFatoresDados dadosAgendaFatores) {
    String mensagemAcao = "NOK";
    Fator dadosFator;
    Agenda dadosAgenda;
    ArrayList<Fator> listaFator = dadosAgendaFatores.getArrFatorModel();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "";
    try {
      PreparedStatement comandoPreparado;
      int quantidadeFatores = listaFator.size();
      dadosAgenda = dadosAgendaFatores.getoAgendaModel();
      if (dadosAgenda.getA04_codigo() > 0 && quantidadeFatores > 0) {
        // ---- ATUALIZANDO A AGENDA -------
        instrucaoSql = "UPDATE AGENDA_04 SET ";
        instrucaoSql += "A04_CERTEZA_RESULTADO=?, ";
        instrucaoSql += "A04_CONTRADICAO_RESULTADO=?, ";
        instrucaoSql += "A04_RESULTADO=? ";
        instrucaoSql += "WHERE A04_CODIGO=?;";
        comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
        comandoPreparado.setDouble(1, dadosAgenda.getA04_certeza_resultado());
        comandoPreparado.setDouble(2, dadosAgenda.getA04_contradicao_resultado());
        comandoPreparado.setString(3, dadosAgenda.getA04_resultado());
        comandoPreparado.setLong(4, dadosAgenda.getA04_codigo());
        comandoPreparado.execute();
        comandoPreparado.close();
        // ---- ATUALIZANDO OS FATORES -------
        instrucaoSql = "UPDATE FATOR_06 SET ";
        instrucaoSql += "A06_CERTEZA_RESULTANTE_FATOR=?, ";
        instrucaoSql += "A06_CONTRADICAO_RESULTANTE_FATOR=?, ";
        instrucaoSql += "A06_RESULTADO_FATOR=? ";
        instrucaoSql += "WHERE A06_CODIGO=?;";
        for (int indiceRegistro = 0; indiceRegistro < quantidadeFatores; indiceRegistro++) {
          dadosFator = listaFator.get(indiceRegistro);
          if (dadosFator.getA06_codigo() > 0) {
            comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
            comandoPreparado.setDouble(1, dadosFator.getA06_certeza_resultante_fator());
            comandoPreparado.setDouble(2, dadosFator.getA06_contradicao_resultante_fator());
            comandoPreparado.setString(3, dadosFator.getA06_resultado_fator());
            comandoPreparado.setLong(4, dadosFator.getA06_codigo());
            comandoPreparado.execute();
            comandoPreparado.close();
          }
        }
        mensagemAcao = "OK";
      }
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a Altera��o de dados no BD...(AFP)");
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
