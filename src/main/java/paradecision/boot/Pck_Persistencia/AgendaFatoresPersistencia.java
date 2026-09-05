package Pck_Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pck_DAO.ConnectionFactory;
import Pck_Model.AgendaFatoresModel;
import Pck_Model.AgendaModel;
import Pck_Model.FatorModel;
import Pck_Model.UsuarioModel;

public class AgendaFatoresPersistencia {

	private Connection con;

	public AgendaFatoresModel selectFatoresDaAgenda(AgendaFatoresModel oAgendaFatoresModel) {
		FatorModel oFatorModel;
		ArrayList<FatorModel> arrFatorModel = new ArrayList<FatorModel>();
		UsuarioModel oUsuarioModel;
		ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
		this.abreCon();
		String sql = "SELECT * FROM VW_AGENDAS_FATORES WHERE A04_CODIGO = ?;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oAgendaFatoresModel.getoAgendaModel().getA04_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oFatorModel = new FatorModel();
				oFatorModel.setA06_codigo(rs.getLong("A06_CODIGO"));
				oFatorModel.setA06_titulo(rs.getString("A06_TITULO"));
				oFatorModel.setA06_descricao(rs.getString("A06_DESCRICAO"));
				oFatorModel.setA06_num_sequencia(rs.getInt("A06_NUM_SEQUENCIA"));
				// oFatorModel.setA04_codigo(rs.getLong("A04_CODIGO"));
				oFatorModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oFatorModel.setA06_certeza_resultante_fator(rs.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
				oFatorModel.setA06_contradicao_resultante_fator(rs.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
				oFatorModel.setA06_resultado_fator(rs.getString("A06_RESULTADO_FATOR"));
				oFatorModel.setA06_dt_cadastro(rs.getDate("A06_DT_CADASTRO"));
				oFatorModel.setA06_dt_ultima_alteracao(rs.getDate("A06_DT_ULTIMA_ALTERACAO"));
				arrFatorModel.add(oFatorModel);
				oUsuarioModel = new UsuarioModel();
				oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
				arrUsuarioModel.add(oUsuarioModel);
				// --------------------------------------------
			}
			oAgendaFatoresModel.setArrFatorModel(arrFatorModel);
			oAgendaFatoresModel.setArrUsuarioModel(arrUsuarioModel);
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP-S1)");
		}
		this.fechaCon();
		return oAgendaFatoresModel;
	}

	public ArrayList<FatorModel> getArrFatoresModel(AgendaModel oAgendaModel) {
		FatorModel oFatorModel;
		ArrayList<FatorModel> arrFatorModel = new ArrayList<FatorModel>();
		this.abreCon();
		String sql = "SELECT * FROM FATOR_06 WHERE A04_CODIGO = ?;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oAgendaModel.getA04_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oFatorModel = new FatorModel();
				oFatorModel.setA06_codigo(rs.getLong("A06_CODIGO"));
				oFatorModel.setA06_titulo(rs.getString("A06_TITULO"));
				oFatorModel.setA06_descricao(rs.getString("A06_DESCRICAO"));
				oFatorModel.setA06_num_sequencia(rs.getInt("A06_NUM_SEQUENCIA"));
				// oFatorModel.setA04_codigo(rs.getLong("A04_CODIGO"));
				oFatorModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oFatorModel.setA06_certeza_resultante_fator(rs.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
				oFatorModel.setA06_contradicao_resultante_fator(rs.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
				oFatorModel.setA06_resultado_fator(rs.getString("A06_RESULTADO_FATOR"));
				oFatorModel.setA06_dt_cadastro(rs.getDate("A06_DT_CADASTRO"));
				oFatorModel.setA06_dt_ultima_alteracao(rs.getDate("A06_DT_ULTIMA_ALTERACAO"));
				arrFatorModel.add(oFatorModel);
				// --------------------------------------------
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP-S2)");
		}
		this.fechaCon();
		return arrFatorModel;
	}

	public String updateGrausFatoresDaAgenda(AgendaFatoresModel oAgendaFatoresModel) {
		String msgAction = "NOK";
		FatorModel oFatorModel;
		AgendaModel oAgendaModel;
		ArrayList<FatorModel> arrFatorModel = oAgendaFatoresModel.getArrFatorModel();
		this.abreCon();
		String sql = "";
		try {
			PreparedStatement stmt;
			int qtdFatores = arrFatorModel.size();
			oAgendaModel = oAgendaFatoresModel.getoAgendaModel();
			if (oAgendaModel.getA04_codigo() > 0 && qtdFatores > 0) {
				//---- ATUALIZANDO A AGENDA -------
				sql = "UPDATE AGENDA_04 SET ";
				sql += "A04_CERTEZA_RESULTADO=?, ";
				sql += "A04_CONTRADICAO_RESULTADO=?, ";
				sql += "A04_RESULTADO=? ";
				sql += "WHERE A04_CODIGO=?;";
				stmt = con.prepareStatement(sql);
				stmt.setDouble(1, oAgendaModel.getA04_certeza_resultado());
				stmt.setDouble(2, oAgendaModel.getA04_contradicao_resultado());
				stmt.setString(3, oAgendaModel.getA04_resultado());
				//System.out.println(oAgendaModel.getA04_resultado());
				stmt.setLong(4, oAgendaModel.getA04_codigo());
				//System.out.println(stmt);
				stmt.execute();
				stmt.close();
				//---- ATUALIZANDO OS FATORES -------
				sql = "UPDATE FATOR_06 SET ";
				sql += "A06_CERTEZA_RESULTANTE_FATOR=?, ";
				sql += "A06_CONTRADICAO_RESULTANTE_FATOR=?, ";
				sql += "A06_RESULTADO_FATOR=? ";
				sql += "WHERE A06_CODIGO=?;";
				for (int ii = 0; ii < qtdFatores; ii++) {
					oFatorModel = arrFatorModel.get(ii);
					if (oFatorModel.getA06_codigo() > 0) {
						stmt = con.prepareStatement(sql);
						stmt.setDouble(1, oFatorModel.getA06_certeza_resultante_fator());
						stmt.setDouble(2, oFatorModel.getA06_contradicao_resultante_fator());
						stmt.setString(3, oFatorModel.getA06_resultado_fator());
						stmt.setLong(4, oFatorModel.getA06_codigo());
						//System.out.println(stmt);
						stmt.execute();
						stmt.close();
					}
				}
				msgAction = "OK";
			}
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a Alteração de dados no BD...(AFP)");
		}
		this.fechaCon();
		return msgAction;
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
