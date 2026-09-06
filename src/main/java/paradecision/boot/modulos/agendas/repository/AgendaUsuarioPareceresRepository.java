package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaUsuarioPareceresDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaUsuarioPareceresRepository {

  public AgendaUsuarioPareceresDados selectPareceresAgUsu(
      AgendaUsuarioPareceresDados oAgendaUsuarioPareceresModel) {
    Agenda oAgendaModel = oAgendaUsuarioPareceresModel.getoAgendaModel();
    Usuario oUsuarioModel = oAgendaUsuarioPareceresModel.getoUsuarioModel();
    ArrayList<ParecerFatorUsuario> arrParecerFatorUsuarioModel =
        new ArrayList<ParecerFatorUsuario>();
    ParecerFatorUsuario oParecerFatorUsuarioModel;
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM VW_FATORES_PARECERES WHERE (A04_CODIGO=? AND A02_CODIGO=?);";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaModel.getA04_codigo());
      stmt.setLong(2, oUsuarioModel.getA02_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oParecerFatorUsuarioModel = new ParecerFatorUsuario();
        oParecerFatorUsuarioModel.setA07_codigo(rs.getLong("A07_CODIGO"));
        oParecerFatorUsuarioModel.setA06_codigo(rs.getLong("A06_CODIGO"));
        oParecerFatorUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oParecerFatorUsuarioModel.setA07_num_sequencia(rs.getInt("A07_NUM_SEQUENCIA"));
        oParecerFatorUsuarioModel.setA07_certeza(rs.getDouble("A07_CERTEZA"));
        oParecerFatorUsuarioModel.setA07_contradicao(rs.getDouble("A07_CONTRADICAO"));
        oParecerFatorUsuarioModel.setStr_a07_certeza(rs.getString("A07_CERTEZA"));
        oParecerFatorUsuarioModel.setStr_a07_contradicao(rs.getString("A07_CONTRADICAO"));
        oParecerFatorUsuarioModel.setA07_dt_cadastro(rs.getDate("A07_DT_CADASTRO"));
        oParecerFatorUsuarioModel.setA07_dt_ultima_alteracao(rs.getDate("A07_DT_ULTIMA_ALTERACAO"));
        arrParecerFatorUsuarioModel.add(oParecerFatorUsuarioModel);
      }
      oAgendaUsuarioPareceresModel.setArrParecerFatorUsuarioModel(arrParecerFatorUsuarioModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP1)");
    }
    fechaCon(con);
    return oAgendaUsuarioPareceresModel;
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
