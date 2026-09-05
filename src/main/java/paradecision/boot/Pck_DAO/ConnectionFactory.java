package Pck_DAO;

import java.sql.*;

import Pck_Util.MetodosUteis;

public class ConnectionFactory {
	
	public String erro;
	public String driver, url, base_dados, login, senha;
	public String ipPrincipal, ipServer, ipAtual, portaServer, outro;
	private Connection con;
	
	public ConnectionFactory() {

		// #### DADOS DO SERVIDOR PRINCIPAL PARADECISION
		ipPrincipal = "177.70.27.122";

		System.out.println("");
		System.out.println("####### 01");
		System.out.println(ipAtual);
		System.out.println("");
		
		// #### DADOS DE LOCALIZAÇÃO DA BASE DE DADOS
		ipAtual = MetodosUteis.getIpAddress();
		// EM RELAÇÃO A TESTES LOCAIS, OU EM OUTROS SERVIDORES...
		// ...DESCOMENTAR A LINHA ABAIXO PARA **FORÇAR** SEMPRE O SERVIDOR DO Banco de Dados DA Paradecision
		//ipAtual = "177.70.27.122";
		if (ipAtual.equals(ipPrincipal)) {
			System.out.println("Conexão PD");
			ipServer = ipAtual;
			portaServer = "3306";
			base_dados = "SSDParaViverBem";
			login = "rveras";
			senha = "Mescl@do";
			// #### STRINGS DOS DRIVER E DA URL
			driver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://" + ipServer + ":" + portaServer + "/" + base_dados;
		} else {
			System.out.println("Conexão local");
			ipServer = "localhost";
			portaServer = "3306";
			base_dados = "ssdparaviverbem";
			login = "rveras";
			senha = "Mescl@do"; //coloquei, no meu Mysql local, a mesma senha do MySQL da Paradecision
			// #### STRINGS DOS DRIVER E DA URL
			driver = "com.mysql.cj.jdbc.Driver";
			url = "jdbc:mysql://" + ipServer + ":" + portaServer + "/" + base_dados;
			url += "?useTimezone=true&serverTimezone=UTC";
		}
		//System.out.println("IP: " + ipServer);
		con = null;
	}
	
	public Connection getConnection() {
		try{
			//System.out.println("1A");
			//System.out.println(driver);
			Class.forName(driver);
			//System.out.println(url);
			this.con = DriverManager.getConnection(url,login,senha);

			System.out.println("");
			System.out.println("####### ** 02");
			System.out.println("Sucesso na Conexão");
			System.out.println("");
			
			//System.out.println("2B");
		}catch(ClassNotFoundException ex){
			erro = ":: ERRO :: Driver JDBC não encontrado na aplicação!";
			System.out.println(erro);
		}catch(SQLException ex){
			erro = ":: ERRO :: Problemas na conexão com a fonte de dados";
			System.out.println(erro);
		}catch(Exception ex){
			erro = ":: ERRO :: Outros problemas na conexão...";
			System.out.println(erro);
		}
		return this.con;
	}

}