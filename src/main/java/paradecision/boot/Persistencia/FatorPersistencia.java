package paradecision.boot.Persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Pck_DAO.ConnectionFactory;
import Pck_Model.FatorModel;

public class FatorPersistencia {

	private Connection con;

	public String insertFator(FatorModel oFatorModel) {
		String res = "OK";
		this.abreCon();

		String sql = "INSERT INTO FATOR_06 (";
		sql += "A06_TITULO, A06_DESCRICAO, A06_NUM_SEQUENCIA, ";
		sql += "A04_CODIGO, A02_CODIGO, A06_CERTEZA_RESULTANTE_FATOR, ";
		sql += "A06_CONTRADICAO_RESULTANTE_FATOR, A06_RESULTADO_FATOR, A06_DT_CADASTRO) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, oFatorModel.getA06_titulo());
			stmt.setString(2, oFatorModel.getA06_descricao());
			stmt.setInt(3, oFatorModel.getA06_num_sequencia());
			stmt.setLong(4, oFatorModel.getA04_codigo());
			stmt.setLong(5, oFatorModel.getA02_codigo());
			stmt.setDouble(6, oFatorModel.getA06_certeza_resultante_fator());
			stmt.setDouble(7, oFatorModel.getA06_contradicao_resultante_fator());
			stmt.setString(8, oFatorModel.getA06_resultado_fator());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			res = "NOK";
			System.out.println(":: ERRO :: Problemas com a cria��o de dados no BD...(FP)");
		}
		this.fechaCon();
		return res;
	}

	public FatorModel selectFator(FatorModel oFatorModel) {
		this.abreCon();
		String sql = "SELECT * FROM FATOR_06 WHERE A06_CODIGO=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oFatorModel.getA06_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oFatorModel.setA06_titulo(rs.getString("A06_TITULO"));
				oFatorModel.setA06_descricao(rs.getString("A06_DESCRICAO"));
				oFatorModel.setA06_num_sequencia(rs.getInt("A06_NUM_SEQUENCIA"));
				oFatorModel.setA04_codigo(rs.getLong("A04_CODIGO"));
				oFatorModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oFatorModel.setA06_certeza_resultante_fator(rs.getLong("A06_CERTEZA_RESULTANTE_FATOR"));
				oFatorModel.setA06_contradicao_resultante_fator(rs.getLong("A06_CONTRADICAO_RESULTANTE_FATOR"));
				oFatorModel.setA06_resultado_fator(rs.getString("A06_RESULTADO_FATOR"));
				oFatorModel.setA06_dt_cadastro(rs.getDate("A06_DT_CADASTRO"));
				oFatorModel.setA06_dt_ultima_alteracao(rs.getDate("A06_DT_ULTIMA_ALTERACAO"));
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(FP)");
		}
		this.fechaCon();
		return oFatorModel;
	}

	public String updateFator(FatorModel oFatorModel) {
		String res = "OK";
		this.abreCon();

		String sql = "UPDATE FATOR_06 ";
		sql += "SET A06_TITULO=?, ";
		sql += "A06_DESCRICAO=? ";
		sql += "WHERE A06_CODIGO=?;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, oFatorModel.getA06_titulo());
			stmt.setString(2, oFatorModel.getA06_descricao());
			stmt.setLong(3, oFatorModel.getA06_codigo());
			//System.out.println("Executando: " + stmt);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			res = "NOK";
			System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(FP)");
		}
		this.fechaCon();
		return res;
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
