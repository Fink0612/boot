package paradecision.boot.Persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pck_DAO.ConnectionFactory;
import Pck_Model.AgendaPareceresModel;
import Pck_Model.ParecerFatorUsuarioModel;
import Pck_Model.UsuarioModel;

public class AgendaPareceresPersistencia {

	private Connection con;
	
	public AgendaPareceresModel selectPareceresDaAgenda(AgendaPareceresModel oAgendaPareceresModel) {
		UsuarioModel oUsuarioModel;
		ParecerFatorUsuarioModel oParecerFatorUsuarioModel;
		ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
		ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = new ArrayList<ParecerFatorUsuarioModel>();
		this.abreCon();
		String sql = "SELECT * FROM VW_FATORES_PARECERES WHERE A04_CODIGO = ?;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oAgendaPareceresModel.getoAgendaModel().getA04_codigo());
			//System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oUsuarioModel = new UsuarioModel();
				oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
				arrUsuarioModel.add(oUsuarioModel);
				oParecerFatorUsuarioModel = new ParecerFatorUsuarioModel();
				oParecerFatorUsuarioModel.setA07_codigo(rs.getLong("A07_CODIGO"));
				oParecerFatorUsuarioModel.setA06_codigo(rs.getLong("A06_CODIGO"));
				oParecerFatorUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oParecerFatorUsuarioModel.setA07_num_sequencia(rs.getInt("A07_NUM_SEQUENCIA"));
				oParecerFatorUsuarioModel.setA07_certeza(rs.getDouble("A07_CERTEZA"));
				oParecerFatorUsuarioModel.setA07_contradicao(rs.getDouble("A07_CONTRADICAO"));
				oParecerFatorUsuarioModel.setA07_dt_cadastro(rs.getDate("A07_DT_CADASTRO"));
				arrParecerFatorUsuarioModel.add(oParecerFatorUsuarioModel);
				//--------------------------------------------
			}
			oAgendaPareceresModel.setArrUsuarioModel(arrUsuarioModel);
			oAgendaPareceresModel.setArrParecerFatorUsuarioModel(arrParecerFatorUsuarioModel);
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP)");
		}
		this.fechaCon();
		return oAgendaPareceresModel;
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
