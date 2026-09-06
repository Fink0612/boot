package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaPareceresDados;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaPareceresRepository {

  public AgendaPareceresDados selectPareceresDaAgenda(AgendaPareceresDados oAgendaPareceresModel) {
    Usuario oUsuarioModel;
    ParecerFatorUsuario oParecerFatorUsuarioModel;
    ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
    ArrayList<ParecerFatorUsuario> arrParecerFatorUsuarioModel =
        new ArrayList<ParecerFatorUsuario>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM VW_FATORES_PARECERES WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaPareceresModel.getoAgendaModel().getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel = new Usuario();
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        arrUsuarioModel.add(oUsuarioModel);
        oParecerFatorUsuarioModel = new ParecerFatorUsuario();
        oParecerFatorUsuarioModel.setA07_codigo(rs.getLong("A07_CODIGO"));
        oParecerFatorUsuarioModel.setA06_codigo(rs.getLong("A06_CODIGO"));
        oParecerFatorUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oParecerFatorUsuarioModel.setA07_num_sequencia(rs.getInt("A07_NUM_SEQUENCIA"));
        oParecerFatorUsuarioModel.setA07_certeza(rs.getDouble("A07_CERTEZA"));
        oParecerFatorUsuarioModel.setA07_contradicao(rs.getDouble("A07_CONTRADICAO"));
        oParecerFatorUsuarioModel.setA07_dt_cadastro(rs.getDate("A07_DT_CADASTRO"));
        arrParecerFatorUsuarioModel.add(oParecerFatorUsuarioModel);
      }
      oAgendaPareceresModel.setArrUsuarioModel(arrUsuarioModel);
      oAgendaPareceresModel.setArrParecerFatorUsuarioModel(arrParecerFatorUsuarioModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP)");
    }
    fechaCon(con);
    return oAgendaPareceresModel;
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
