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

  public EmpresaAgendasDados selectAgendasDaEmpresa(EmpresaAgendasDados oEmpresaAgendasModel) {
    Agenda oAgendaModel;
    ArrayList<Agenda> arrAgendaModel = new ArrayList<Agenda>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM AGENDA_04 AS A ";
    sql += "WHERE A.A01_CODIGO=? ";
    sql += "ORDER BY A.A01_CODIGO, A.A04_STATUS, A.A04_TITULO;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oEmpresaAgendasModel.getoEmpresaModel().getA01_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oAgendaModel = new Agenda();
        oAgendaModel.setA04_codigo(rs.getLong("A04_CODIGO"));
        oAgendaModel.setA04_titulo(rs.getString("A04_TITULO"));
        oAgendaModel.setA04_descricao(rs.getString("A04_DESCRICAO"));
        oAgendaModel.setA04_status_dt_limite(rs.getInt("A04_STATUS_DT_LIMITE"));
        oAgendaModel.setA04_data_limite(rs.getDate("A04_DATA_LIMITE"));
        oAgendaModel.setA04_resultado(rs.getString("A04_RESULTADO"));
        oAgendaModel.setA04_certeza_resultado(rs.getDouble("A04_CERTEZA_RESULTADO"));
        oAgendaModel.setA04_contradicao_resultado(rs.getDouble("A04_CONTRADICAO_RESULTADO"));
        oAgendaModel.setA04_dt_cadastro(rs.getDate("A04_DT_CADASTRO"));
        oAgendaModel.setA04_dt_ultima_alteracao(rs.getDate("A04_DT_ULTIMA_ALTERACAO"));
        oAgendaModel.setA01_codigo(rs.getLong("A01_CODIGO"));
        oAgendaModel.setA04_status(rs.getInt("A04_STATUS"));
        arrAgendaModel.add(oAgendaModel);
      }
      oEmpresaAgendasModel.setArrAgendaModel(arrAgendaModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EAP-S1)");
    }
    fechaCon(con);
    return oEmpresaAgendasModel;
  }

  public EmpresaAgendasDados selectAgendasDaEmpresaUsuario(
      EmpresaAgendasDados oEmpresaAgendasModel) {
    Agenda oAgendaModel;
    ArrayList<Agenda> arrAgendaModel = new ArrayList<Agenda>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM VW_PARTICIPANTES_AGENDA AS PA ";
    sql += "WHERE PA.A01_CODIGO=? AND PA.A02_CODIGO=?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oEmpresaAgendasModel.getoEmpresaModel().getA01_codigo());
      stmt.setLong(2, oEmpresaAgendasModel.getoUsuarioModel().getA02_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oAgendaModel = new Agenda();
        oAgendaModel.setA04_codigo(rs.getLong("A04_CODIGO"));
        oAgendaModel.setA04_titulo(rs.getString("A04_TITULO"));
        oAgendaModel.setA04_descricao(rs.getString("A04_DESCRICAO"));
        oAgendaModel.setA01_codigo(rs.getLong("A01_CODIGO"));
        oAgendaModel.setA04_status(rs.getInt("A04_STATUS"));
        arrAgendaModel.add(oAgendaModel);
      }
      oEmpresaAgendasModel.setArrAgendaModel(arrAgendaModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EAP-S2)");
    }
    fechaCon(con);
    return oEmpresaAgendasModel;
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
