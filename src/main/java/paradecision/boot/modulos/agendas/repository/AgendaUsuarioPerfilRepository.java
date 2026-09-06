package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;

@Repository
public class AgendaUsuarioPerfilRepository {

  public AgendaUsuarioPerfil selectAgendaUsuarioPerfil(
      AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    AgendaUsuarioPerfil dadosAgendaUsuarioPerfilAuxiliares = null;
    int achouCadastro = 0;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM USUARIO_AGENDA_05 WHERE A02_CODIGO=? AND A04_CODIGO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgendaUsuarioPerfil.getA02_codigo());
      comandoPreparado.setLong(2, dadosAgendaUsuarioPerfil.getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        achouCadastro = 1;
        dadosAgendaUsuarioPerfil.setA05_codigo(resultadoConsulta.getLong("A05_CODIGO"));
        dadosAgendaUsuarioPerfil.setA05_num_sequencia(resultadoConsulta.getInt("A05_NUM_SEQUENCIA"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_titular(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_TITULAR"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_facilitador(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_FACILITADOR"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_especialista(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA"));
        dadosAgendaUsuarioPerfil.setA05_perfil_agenda_usuario_analista(
            resultadoConsulta.getInt("A05_PERFIL_AGENDA_USUARIO_ANALISTA"));
        dadosAgendaUsuarioPerfil.setA05_dt_cadastro(resultadoConsulta.getDate("A05_DT_CADASTRO"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(conexaoBanco);
    if (achouCadastro == 1) dadosAgendaUsuarioPerfilAuxiliares = dadosAgendaUsuarioPerfil;
    return dadosAgendaUsuarioPerfilAuxiliares;
  }

  public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    String mensagemAcao = "";
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "DELETE FROM USUARIO_AGENDA_05 WHERE A05_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgendaUsuarioPerfil.getA05_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
      mensagemAcao = "OK";
    } catch (Exception excecao) {
      mensagemAcao = "NOK";
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(conexaoBanco);
    return mensagemAcao;
  }

  public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    String mensagemAcao = "";
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "UPDATE USUARIO_AGENDA_05 ";
    instrucaoSql += "SET A05_PERFIL_AGENDA_USUARIO_TITULAR=?, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_FACILITADOR=?, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA=?, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_ANALISTA=?, ";
    instrucaoSql += "A05_DT_ULTIMA_ALTERACAO=? ";
    instrucaoSql += "WHERE A05_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setInt(1, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_titular());
      comandoPreparado.setInt(2, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_facilitador());
      comandoPreparado.setInt(3, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista());
      comandoPreparado.setInt(4, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_analista());
      comandoPreparado.setDate(5, dadosAgendaUsuarioPerfil.getA05_dt_ultima_alteracao());
      comandoPreparado.setLong(6, dadosAgendaUsuarioPerfil.getA05_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
      mensagemAcao = "OK";
    } catch (Exception excecao) {
      mensagemAcao = "NOK";
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
    }
    fechaCon(conexaoBanco);
    return mensagemAcao;
  }

  public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfil dadosAgendaUsuarioPerfil) {
    String mensagemAcao = "";
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "INSERT INTO USUARIO_AGENDA_05 (";
    instrucaoSql += "A02_CODIGO, ";
    instrucaoSql += "A04_CODIGO, ";
    instrucaoSql += "A05_NUM_SEQUENCIA, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_TITULAR, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_FACILITADOR, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA, ";
    instrucaoSql += "A05_PERFIL_AGENDA_USUARIO_ANALISTA, ";
    instrucaoSql += "A05_DT_CADASTRO) ";
    instrucaoSql += "VALUES (?, ?, ?, ?, ?, ?, ?, sysdate());";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgendaUsuarioPerfil.getA02_codigo());
      comandoPreparado.setLong(2, dadosAgendaUsuarioPerfil.getA04_codigo());
      comandoPreparado.setLong(3, dadosAgendaUsuarioPerfil.getA05_num_sequencia());
      comandoPreparado.setInt(4, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_titular());
      comandoPreparado.setInt(5, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_facilitador());
      comandoPreparado.setInt(6, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_especialista());
      comandoPreparado.setInt(7, dadosAgendaUsuarioPerfil.getA05_perfil_agenda_usuario_analista());
      comandoPreparado.execute();
      comandoPreparado.close();
      mensagemAcao = "OK";
    } catch (Exception excecao) {
      mensagemAcao = "NOK";
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP2)");
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
