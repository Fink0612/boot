package paradecision.boot.modulos.empresas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.empresas.dto.EmpresaAgendasDados;

@Repository
public class EmpresaAgendasRepository {

  public EmpresaAgendasDados selectAgendasDaEmpresa(EmpresaAgendasDados dadosEmpresaAgendas) {
    Agenda dadosAgenda;
    ArrayList<Agenda> listaAgenda = new ArrayList<Agenda>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM AGENDA_04 AS A ";
    instrucaoSql += "WHERE A.A01_CODIGO=? ";
    instrucaoSql += "ORDER BY A.A01_CODIGO, A.A04_STATUS, A.A04_TITULO;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosEmpresaAgendas.getoEmpresaModel().getA01_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosAgenda = new Agenda();
        dadosAgenda.setA04_codigo(resultadoConsulta.getLong("A04_CODIGO"));
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
        listaAgenda.add(dadosAgenda);
      }
      dadosEmpresaAgendas.setArrAgendaModel(listaAgenda);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EAP-S1)");
    }
    fechaCon(conexaoBanco);
    return dadosEmpresaAgendas;
  }

  public EmpresaAgendasDados selectAgendasDaEmpresaUsuario(
      EmpresaAgendasDados dadosEmpresaAgendas) {
    Agenda dadosAgenda;
    ArrayList<Agenda> listaAgenda = new ArrayList<Agenda>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM VW_PARTICIPANTES_AGENDA AS PA ";
    instrucaoSql += "WHERE PA.A01_CODIGO=? AND PA.A02_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosEmpresaAgendas.getoEmpresaModel().getA01_codigo());
      comandoPreparado.setLong(2, dadosEmpresaAgendas.getoUsuarioModel().getA02_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosAgenda = new Agenda();
        dadosAgenda.setA04_codigo(resultadoConsulta.getLong("A04_CODIGO"));
        dadosAgenda.setA04_titulo(resultadoConsulta.getString("A04_TITULO"));
        dadosAgenda.setA04_descricao(resultadoConsulta.getString("A04_DESCRICAO"));
        dadosAgenda.setA01_codigo(resultadoConsulta.getLong("A01_CODIGO"));
        dadosAgenda.setA04_status(resultadoConsulta.getInt("A04_STATUS"));
        listaAgenda.add(dadosAgenda);
      }
      dadosEmpresaAgendas.setArrAgendaModel(listaAgenda);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EAP-S2)");
    }
    fechaCon(conexaoBanco);
    return dadosEmpresaAgendas;
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
