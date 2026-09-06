package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaUsuariosDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.entity.AgendaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaUsuariosRepository {

  public AgendaUsuariosDados selectUsuariosDaAgenda(AgendaUsuariosDados oAgendaUsuariosModel) {
    Usuario oUsuarioModel;
    AgendaUsuarioPerfil oAgendaUsuarioPerfilModel;
    ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
    ArrayList<AgendaUsuarioPerfil> arrAgendaUsuarioPerfilModel =
        new ArrayList<AgendaUsuarioPerfil>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM VW_AGENDAS_USUARIOS WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaUsuariosModel.getoAgendaModel().getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel = new Usuario();
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        arrUsuarioModel.add(oUsuarioModel);
        oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfil();
        oAgendaUsuarioPerfilModel.setA05_codigo(rs.getLong("A05_CODIGO"));
        oAgendaUsuarioPerfilModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oAgendaUsuarioPerfilModel.setA05_num_sequencia(rs.getLong("A05_NUM_SEQUENCIA"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_TITULAR"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_FACILITADOR"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA"));
        oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(
            rs.getInt("A05_PERFIL_AGENDA_USUARIO_ANALISTA"));
        oAgendaUsuarioPerfilModel.setA05_dt_cadastro(rs.getDate("A05_DT_CADASTRO"));
        arrAgendaUsuarioPerfilModel.add(oAgendaUsuarioPerfilModel);
      }
      oAgendaUsuariosModel.setArrUsuarioModel(arrUsuarioModel);
      oAgendaUsuariosModel.setArrAgendaUsuarioPerfilModel(arrAgendaUsuarioPerfilModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUP-S1)");
    }
    fechaCon(con);
    return oAgendaUsuariosModel;
  }

  public ArrayList<Usuario> getArrEspecialistasModel(Agenda oAgendaModel) {
    Usuario oUsuarioModel;
    ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM VW_AGENDAS_USUARIOS WHERE (A04_CODIGO=? ";
    sql += "AND A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA=1);";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oAgendaModel.getA04_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel = new Usuario();
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        arrUsuarioModel.add(oUsuarioModel);
      }
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUP-S2)");
    }
    fechaCon(con);
    return arrUsuarioModel;
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
