package Pck_Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Pck_DAO.ConnectionFactory;

public class TestePersistencia {

	public long a00_codigo; 
	public String a00_nome; 
	
	private Connection con;

	public String insertTeste() {
		String res = "OK";
		this.abreCon();

		String sql = "INSERT INTO TESTE_00 (A00_NOME) VALUES (?);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.a00_nome);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.first()) {
                System.out.printf("Oi : %d\n", rs.getLong(1));
            }
			stmt.close();
		} catch (Exception e) {
			res = "ERRO";
			System.out.println(":: ERRO :: Problemas com a criação de dados no BD...");
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
