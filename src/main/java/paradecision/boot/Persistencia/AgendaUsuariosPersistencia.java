package paradecision.boot.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pck_DAO.ConnectionFactory;
import Pck_Model.AgendaModel;
import Pck_Model.AgendaUsuarioPerfilModel;
import Pck_Model.AgendaUsuariosModel;
import Pck_Model.UsuarioModel;

public class AgendaUsuariosPersistencia {

	private Connection con;
	
	public AgendaUsuariosModel selectUsuariosDaAgenda(AgendaUsuariosModel oAgendaUsuariosModel) {
		UsuarioModel oUsuarioModel;
		AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel;
		ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
		ArrayList<AgendaUsuarioPerfilModel> arrAgendaUsuarioPerfilModel = new ArrayList<AgendaUsuarioPerfilModel>();
		this.abreCon();
		String sql = "SELECT * FROM VW_AGENDAS_USUARIOS WHERE A04_CODIGO = ?;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oAgendaUsuariosModel.getoAgendaModel().getA04_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oUsuarioModel = new UsuarioModel();
				oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
				arrUsuarioModel.add(oUsuarioModel);
				oAgendaUsuarioPerfilModel = new AgendaUsuarioPerfilModel();
				oAgendaUsuarioPerfilModel.setA05_codigo(rs.getLong("A05_CODIGO"));
				oAgendaUsuarioPerfilModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oAgendaUsuarioPerfilModel.setA05_num_sequencia(rs.getLong("A05_NUM_SEQUENCIA"));
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_titular(rs.getInt("A05_PERFIL_AGENDA_USUARIO_TITULAR"));
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_facilitador(rs.getInt("A05_PERFIL_AGENDA_USUARIO_FACILITADOR"));
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_especialista(rs.getInt("A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA"));
				oAgendaUsuarioPerfilModel.setA05_perfil_agenda_usuario_analista(rs.getInt("A05_PERFIL_AGENDA_USUARIO_ANALISTA"));
				oAgendaUsuarioPerfilModel.setA05_dt_cadastro(rs.getDate("A05_DT_CADASTRO"));
				arrAgendaUsuarioPerfilModel.add(oAgendaUsuarioPerfilModel);
				//--------------------------------------------
			}
			oAgendaUsuariosModel.setArrUsuarioModel(arrUsuarioModel);
			oAgendaUsuariosModel.setArrAgendaUsuarioPerfilModel(arrAgendaUsuarioPerfilModel);
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUP-S1)");
		}
		this.fechaCon();
		return oAgendaUsuariosModel;
	}

	public ArrayList<UsuarioModel> getArrEspecialistasModel(AgendaModel oAgendaModel) {
		UsuarioModel oUsuarioModel;
		ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
		this.abreCon();
		String sql = "SELECT * FROM VW_AGENDAS_USUARIOS WHERE (A04_CODIGO=? ";
		sql += "AND A05_PERFIL_AGENDA_USUARIO_ESPECIALISTA=1);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oAgendaModel.getA04_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oUsuarioModel = new UsuarioModel();
				oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
				arrUsuarioModel.add(oUsuarioModel);
				//--------------------------------------------
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUP-S2)");
		}
		this.fechaCon();
		return arrUsuarioModel;
	}

	// ......PARA LIDAR COM O BANCO DE DADOS..........
	// ...............................................

	private void abreCon() {
		this.con = new ConnectionFactory().getConnection();
	}

	private void fechaCon() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
