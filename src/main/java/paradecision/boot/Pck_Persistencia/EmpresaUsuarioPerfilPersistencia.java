package Pck_Persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Pck_DAO.ConnectionFactory;
import Pck_Model.EmpresaUsuarioPerfilModel;

public class EmpresaUsuarioPerfilPersistencia {

	private Connection con;
	
	public EmpresaUsuarioPerfilModel selectEmpresaUsuario(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		EmpresaUsuarioPerfilModel auxEmpresaUsuarioPerfilModel = null;
		int achouCadastro = 0;
		this.abreCon();
		String sql = "SELECT * FROM EMPRESA_USUARIO_PERFIL_03 WHERE A01_CODIGO=? AND A02_CODIGO=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oEmpresaUsuarioPerfilModel.getA01_codigo());
			stmt.setLong(2, oEmpresaUsuarioPerfilModel.getA02_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				achouCadastro = 1;
				//oEmpresaUsuarioPerfilModel.setA02_codigo(rs.getLong("A01_CODIGO"));
				//oEmpresaUsuarioPerfilModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oEmpresaUsuarioPerfilModel.setA03_dt_cadastro(rs.getDate("A03_DT_CADASTRO"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_paraviverbem(rs.getInt("A03_PERFIL_PARAVIVERBEM"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_administrador(rs.getInt("A03_PERFIL_ADMINISTRADOR"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_chefe(rs.getInt("A03_PERFIL_CHEFE"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_padrao(rs.getInt("A03_PERFIL_PADRAO"));
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EUPP)");
		}
		this.fechaCon();
		if (achouCadastro == 1) auxEmpresaUsuarioPerfilModel = oEmpresaUsuarioPerfilModel;
		return auxEmpresaUsuarioPerfilModel;
	}

	public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		int okMetodo = 1;
		this.abreCon();
		String sql = "INSERT INTO EMPRESA_USUARIO_PERFIL_03 (A01_CODIGO, A02_CODIGO, ";
		sql += "A03_PERFIL_PARAVIVERBEM, A03_PERFIL_ADMINISTRADOR, ";
		sql += "A03_PERFIL_CHEFE, A03_PERFIL_PADRAO, A03_DT_CADASTRO) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, sysdate())";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oEmpresaUsuarioPerfilModel.getA01_codigo());
			stmt.setLong(2, oEmpresaUsuarioPerfilModel.getA02_codigo());
			stmt.setInt(3, oEmpresaUsuarioPerfilModel.getA03_perfil_paraviverbem());
			stmt.setInt(4, oEmpresaUsuarioPerfilModel.getA03_perfil_administrador());
			stmt.setInt(5, oEmpresaUsuarioPerfilModel.getA03_perfil_chefe());
			stmt.setInt(6, oEmpresaUsuarioPerfilModel.getA03_perfil_padrao());
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			okMetodo = 0;
			System.out.println(":: ERRO :: Problemas com a inserção de dados no BD...(EUPP)");
		}
		this.fechaCon();
		return okMetodo;
	}

	public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		String okMetodo = "OK";
		this.abreCon();
		String sql = "UPDATE EMPRESA_USUARIO_PERFIL_03 SET ";
		sql += "A03_PERFIL_CHEFE=?, A03_PERFIL_PADRAO=? ";
		sql += "WHERE A01_CODIGO=? AND A02_CODIGO=?;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, oEmpresaUsuarioPerfilModel.getA03_perfil_chefe());
			stmt.setInt(2, oEmpresaUsuarioPerfilModel.getA03_perfil_padrao());
			stmt.setLong(3, oEmpresaUsuarioPerfilModel.getA01_codigo());
			stmt.setLong(4, oEmpresaUsuarioPerfilModel.getA02_codigo());
			//System.out.println(stmt);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			okMetodo = "NOK";
			System.out.println(":: ERRO :: Problemas com a alteração de dados no BD...(EUPP)");
		}
		this.fechaCon();
		return okMetodo;
	}

	public int XinsertEmpresaUsuarioPerfil2(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		int okMetodo = 1;
		this.abreCon();
		String sql = "CALL PROC_INS_EMPRESA_USUARIO_PERFIL2(?,?,?,?,?,?)";
		try {
			CallableStatement oCall = con.prepareCall(sql);
			oCall.setLong(1, oEmpresaUsuarioPerfilModel.getA01_codigo());
			oCall.setLong(2, oEmpresaUsuarioPerfilModel.getA02_codigo());
			oCall.setInt(3, oEmpresaUsuarioPerfilModel.getA03_perfil_paraviverbem());
			oCall.setInt(4, oEmpresaUsuarioPerfilModel.getA03_perfil_administrador());
			oCall.setInt(5, oEmpresaUsuarioPerfilModel.getA03_perfil_chefe());
			oCall.setInt(6, oEmpresaUsuarioPerfilModel.getA03_perfil_padrao());
			oCall.execute();
			//oCall.close();
		} catch (Exception e) {
			okMetodo = 0;
			System.out.println(":: ERRO :: Problemas com a inserção de dados no BD...(EUPP-PROC)");
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
