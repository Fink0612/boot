package paradecision.boot.compartilhado.infra;

import java.sql.*;
import paradecision.boot.compartilhado.util.MetodosUteis;

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

    // #### DADOS DE LOCALIZA��O DA BASE DE DADOS
    ipAtual = MetodosUteis.getIpAddress();
    // EM RELA��O A TESTES LOCAIS, OU EM OUTROS SERVIDORES...
    // ...DESCOMENTAR A LINHA ABAIXO PARA **FOR�AR** SEMPRE O SERVIDOR DO Banco de Dados DA
    // Paradecision
    // ipAtual = "177.70.27.122";
    if (ipAtual.equals(ipPrincipal)) {
      System.out.println("Conex�o PD");
      ipServer = ipAtual;
      portaServer = "3306";
      base_dados = "SSDParaViverBem";
      login = "rveras";
      senha = "Mescl@do";
      // #### STRINGS DOS DRIVER E DA URL
      driver = "com.mysql.jdbc.Driver";
      url = "jdbc:mysql://" + ipServer + ":" + portaServer + "/" + base_dados;
    } else {
      System.out.println("Conex�o local");
      ipServer = "localhost";
      portaServer = "3306";
      base_dados = "ssdparaviverbem";
      login = "rveras";
      senha = "Mescl@do"; // coloquei, no meu Mysql local, a mesma senha do MySQL da Paradecision
      // #### STRINGS DOS DRIVER E DA URL
      driver = "com.mysql.cj.jdbc.Driver";
      url = "jdbc:mysql://" + ipServer + ":" + portaServer + "/" + base_dados;
      url += "?useTimezone=true&serverTimezone=UTC";
    }
    con = null;
  }

  public Connection getConnection() {
    try {
      Class.forName(driver);
      this.con = DriverManager.getConnection(url, login, senha);

      System.out.println("");
      System.out.println("####### ** 02");
      System.out.println("Sucesso na Conex�o");
      System.out.println("");

    } catch (ClassNotFoundException ex) {
      erro = ":: ERRO :: Driver JDBC n�o encontrado na aplica��o!";
      System.out.println(erro);
    } catch (SQLException ex) {
      erro = ":: ERRO :: Problemas na conex�o com a fonte de dados";
      System.out.println(erro);
    } catch (Exception ex) {
      erro = ":: ERRO :: Outros problemas na conex�o...";
      System.out.println(erro);
    }
    return this.con;
  }
}
