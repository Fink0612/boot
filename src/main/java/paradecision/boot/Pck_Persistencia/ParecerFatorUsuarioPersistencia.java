package Pck_Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import Pck_DAO.ConnectionFactory;
import Pck_Model.ParecerFatorUsuarioModel;

public class ParecerFatorUsuarioPersistencia {

	private Connection con;
	
	public ParecerFatorUsuarioModel selectParecerFatorUsuario(ParecerFatorUsuarioModel oParecerFatorUsuarioModel) {
		ParecerFatorUsuarioModel auxParecerFatorUsuarioModel = oParecerFatorUsuarioModel;
		int achouCadastro = 0;
		this.abreCon();
		String sql = "SELECT * FROM PARECER_FATOR_USUARIO_07 WHERE A06_CODIGO=? AND A02_CODIGO=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oParecerFatorUsuarioModel.getA06_codigo());
			stmt.setLong(2, oParecerFatorUsuarioModel.getA02_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				achouCadastro = 1;
				oParecerFatorUsuarioModel.setA07_codigo(rs.getLong("A07_CODIGO"));
				//oParecerFatorUsuarioModel.setA06_codigo(rs.getLong("A06_CODIGO"));
				//oParecerFatorUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oParecerFatorUsuarioModel.setA07_num_sequencia(rs.getInt("A07_NUM_SEQUENCIA"));
				oParecerFatorUsuarioModel.setA07_certeza(rs.getDouble("A07_CERTEZA"));
				oParecerFatorUsuarioModel.setA07_contradicao(rs.getDouble("A07_CONTRADICAO"));
				oParecerFatorUsuarioModel.setStr_a07_certeza(rs.getString("A07_CERTEZA"));
				oParecerFatorUsuarioModel.setStr_a07_contradicao(rs.getString("A07_CONTRADICAO"));
				oParecerFatorUsuarioModel.setA07_dt_cadastro(rs.getDate("A07_DT_CADASTRO"));
				oParecerFatorUsuarioModel.setA07_dt_ultima_alteracao(rs.getDate("A07_DT_ULTIMA_ALTERACAO"));
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(PFUP)");
		}
		this.fechaCon();
		if (achouCadastro == 1) auxParecerFatorUsuarioModel = oParecerFatorUsuarioModel;
		return auxParecerFatorUsuarioModel;
	}

	public String insertParecerFatorUsuario(ParecerFatorUsuarioModel oParecerFatorUsuarioModel) {
		String okMetodo = "OK";
		double valParecer = 0;
		this.abreCon();
		String sql = "INSERT INTO PARECER_FATOR_USUARIO_07 (";
		sql += "A06_CODIGO, A02_CODIGO, ";
		sql += "A07_NUM_SEQUENCIA, A07_CERTEZA, ";
		sql += "A07_CONTRADICAO, A07_DT_CADASTRO) ";
		sql += "VALUES (?, ?, ?, ?, ?, sysdate())";
		try {
			//System.out.println("Passou aqui 1");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oParecerFatorUsuarioModel.getA06_codigo());
			stmt.setLong(2, oParecerFatorUsuarioModel.getA02_codigo());
			stmt.setInt(3, oParecerFatorUsuarioModel.getA07_num_sequencia());
			valParecer = oParecerFatorUsuarioModel.getA07_certeza();
			if (valParecer < 0) stmt.setNull(4, Types.DOUBLE);
			else stmt.setDouble(4, valParecer);
			valParecer = oParecerFatorUsuarioModel.getA07_contradicao();
			if (valParecer < 0) stmt.setNull(5, Types.DOUBLE);
			else stmt.setDouble(5, valParecer);
			//System.out.println("Executando: " + stmt);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			okMetodo = "NOK";
			System.out.println(":: ERRO :: Problemas com a criação de dados no BD...(PFUP)");
		}
		this.fechaCon();
		return okMetodo;
	}

	public String updateParecerFatorUsuario(ParecerFatorUsuarioModel oParecerFatorUsuarioModel) {
		String okMetodo = "OK";
		double valParecer = 0;
		this.abreCon();
		String sql = "UPDATE PARECER_FATOR_USUARIO_07 ";
		sql += "SET A07_CERTEZA=?, ";
		sql += "A07_CONTRADICAO=? ";
		sql += "WHERE (A06_CODIGO=? AND A02_CODIGO=?); ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			valParecer = oParecerFatorUsuarioModel.getA07_certeza();
			if (valParecer < 0) stmt.setNull(1, Types.DOUBLE);
			else stmt.setDouble(1, valParecer);
			valParecer = oParecerFatorUsuarioModel.getA07_contradicao();
			if (valParecer < 0) stmt.setNull(2, Types.DOUBLE);
			else stmt.setDouble(2, valParecer);
			stmt.setLong(3, oParecerFatorUsuarioModel.getA06_codigo());
			stmt.setLong(4, oParecerFatorUsuarioModel.getA02_codigo());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			okMetodo = "NOK";
			System.out.println(":: ERRO :: Problemas com a alteração de dados no BD...(PFUP)");
		}
		this.fechaCon();
		return okMetodo;
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
