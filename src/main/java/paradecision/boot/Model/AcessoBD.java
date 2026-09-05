package paradecision.boot.Model;
import java.sql.Connection;
import java.sql.SQLException;
import Pck_DAO.ConnectionFactory;

// ### UTILIZAR APENAS PARA TESTE DE ACESSO A BANCO DE DADOS ###

public class AcessoBD {

	public Connection con;
	public ConnectionFactory conFac = new ConnectionFactory();
	
	public String acessarBD() {
		String ret = "";
		this.abreCon();
		if(con != null) {
			ret = "Sucesso!! Banco de Dados Conectado!";
		} else {
			ret = "OPS!! Problemas com acesso ao Banco de Dados!";
		}
		return ret;
	}
	
	private void abreCon() {
		this.con = conFac.getConnection();
	}

	private void fechaCon() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
