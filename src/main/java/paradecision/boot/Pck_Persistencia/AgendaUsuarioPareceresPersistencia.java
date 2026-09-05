package Pck_Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pck_DAO.ConnectionFactory;
import Pck_Model.AgendaModel;
import Pck_Model.AgendaUsuarioPareceresModel;
import Pck_Model.ParecerFatorUsuarioModel;
import Pck_Model.UsuarioModel;

public class AgendaUsuarioPareceresPersistencia {

	private Connection con;
	
	public AgendaUsuarioPareceresModel selectPareceresAgUsu(AgendaUsuarioPareceresModel oAgendaUsuarioPareceresModel) {
		AgendaModel oAgendaModel = oAgendaUsuarioPareceresModel.getoAgendaModel();
		UsuarioModel oUsuarioModel = oAgendaUsuarioPareceresModel.getoUsuarioModel();
		ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = new ArrayList<ParecerFatorUsuarioModel>();
		ParecerFatorUsuarioModel oParecerFatorUsuarioModel;
		this.abreCon();
		String sql = "SELECT * FROM VW_FATORES_PARECERES WHERE (A04_CODIGO=? AND A02_CODIGO=?);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oAgendaModel.getA04_codigo());
			stmt.setLong(2, oUsuarioModel.getA02_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oParecerFatorUsuarioModel = new ParecerFatorUsuarioModel();
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
				//--------------------------------------------
			}
			oAgendaUsuarioPareceresModel.setArrParecerFatorUsuarioModel(arrParecerFatorUsuarioModel);
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP1)");
		}
		this.fechaCon();
		return oAgendaUsuarioPareceresModel;
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
