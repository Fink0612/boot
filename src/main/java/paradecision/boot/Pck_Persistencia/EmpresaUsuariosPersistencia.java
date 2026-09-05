package Pck_Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pck_DAO.ConnectionFactory;
import Pck_Model.EmpresaUsuarioPerfilModel;
import Pck_Model.EmpresaUsuariosModel;
import Pck_Model.UsuarioModel;

public class EmpresaUsuariosPersistencia {

	private Connection con;
	
	public EmpresaUsuariosModel selectUsuariosDaEmpresa(EmpresaUsuariosModel oEmpresaUsuariosModel) {
		UsuarioModel oUsuarioModel;
		EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel;
		ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
		ArrayList<EmpresaUsuarioPerfilModel> arrEmpresaUsuarioPerfilModel = new ArrayList<EmpresaUsuarioPerfilModel>();
		this.abreCon();
		String sql = "SELECT * FROM USUARIO_02 AS U ";
		sql += "INNER JOIN EMPRESA_USUARIO_PERFIL_03 AS EU ON ";
		sql += "((U.A02_CODIGO = EU.A02_CODIGO) AND (EU.A01_CODIGO = ?));";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oEmpresaUsuariosModel.getoEmpresaModel().getA01_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oUsuarioModel = new UsuarioModel();
				oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oUsuarioModel.setA02_dt_cadastro(rs.getDate("A02_DT_CADASTRO"));
				oUsuarioModel.setA02_dt_ultima_alteracao(rs.getDate("A02_DT_ULTIMA_ALTERACAO"));
				oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
				oUsuarioModel.setA02_usuario(rs.getString("A02_USUARIO"));
				oUsuarioModel.setA02_senha(rs.getString("A02_SENHA"));
				oUsuarioModel.setA02_codigo_link(rs.getString("A02_CODIGO_LINK"));
				oUsuarioModel.setA02_email(rs.getString("A02_EMAIL"));
				oUsuarioModel.setA02_status(rs.getInt("A02_STATUS"));
				arrUsuarioModel.add(oUsuarioModel);
				oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfilModel();
				oEmpresaUsuarioPerfilModel.setA01_codigo(rs.getLong("A01_CODIGO"));
				oEmpresaUsuarioPerfilModel.setA02_codigo(rs.getLong("A02_CODIGO"));
				oEmpresaUsuarioPerfilModel.setA03_dt_cadastro(rs.getDate("A03_DT_CADASTRO"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_paraviverbem(rs.getInt("A03_PERFIL_PARAVIVERBEM"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_administrador(rs.getInt("A03_PERFIL_ADMINISTRADOR"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_chefe(rs.getInt("A03_PERFIL_CHEFE"));
				oEmpresaUsuarioPerfilModel.setA03_perfil_padrao(rs.getInt("A03_PERFIL_PADRAO"));
				arrEmpresaUsuarioPerfilModel.add(oEmpresaUsuarioPerfilModel);
				//--------------------------------------------
			}
			oEmpresaUsuariosModel.setArrUsuarioModel(arrUsuarioModel);
			oEmpresaUsuariosModel.setArrEmpresaUsuarioPerfilModel(arrEmpresaUsuarioPerfilModel);
			stmt.close();
		} catch (Exception e) {
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EUP)");
		}
		this.fechaCon();
		return oEmpresaUsuariosModel;
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
