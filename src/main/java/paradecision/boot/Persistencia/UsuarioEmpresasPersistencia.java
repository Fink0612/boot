package paradecision.boot.Persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Pck_DAO.ConnectionFactory;
import Pck_Model.EmpresaModel;
import Pck_Model.EmpresaUsuarioPerfilModel;
import Pck_Model.UsuarioEmpresasModel;

public class UsuarioEmpresasPersistencia {

	private Connection con;
	
	public UsuarioEmpresasModel selectEmpresasDoUsuario(UsuarioEmpresasModel oUsuarioEmpresasModel) {
		EmpresaModel oEmpresaModel;
		EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel;
		ArrayList<EmpresaModel> arrEmpresaModel = new ArrayList<EmpresaModel>();
		ArrayList<EmpresaUsuarioPerfilModel> arrEmpresaUsuarioPerfilModel = new ArrayList<EmpresaUsuarioPerfilModel>();
		this.abreCon();
		String sql = "SELECT * FROM EMPRESA_01 AS E ";
		sql += "INNER JOIN EMPRESA_USUARIO_PERFIL_03 AS EU ON ";
		sql += "((E.A01_CODIGO = EU.A01_CODIGO) AND (EU.A02_CODIGO = ?));";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, oUsuarioEmpresasModel.getoUsuarioModel().getA02_codigo());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				oEmpresaModel = new EmpresaModel();
				oEmpresaModel.setA01_codigo(rs.getLong("A01_CODIGO"));
				oEmpresaModel.setA01_dt_cadastro(rs.getDate("A01_DT_CADASTRO"));
				oEmpresaModel.setA01_dt_ultima_alteracao(rs.getDate("A01_DT_ULTIMA_ALTERACAO"));
				oEmpresaModel.setA01_descricao(rs.getString("A01_DESCRICAO"));
				oEmpresaModel.setA01_nome(rs.getString("A01_NOME"));
				oEmpresaModel.setA01_status(rs.getInt("A01_STATUS"));
				arrEmpresaModel.add(oEmpresaModel);
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
			oUsuarioEmpresasModel.setArrEmpresaModel(arrEmpresaModel);
			oUsuarioEmpresasModel.setArrEmpresaUsuarioPerfilModel(arrEmpresaUsuarioPerfilModel);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Este Erro");
			System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UEP)");
		}
		this.fechaCon();
		return oUsuarioEmpresasModel;
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
