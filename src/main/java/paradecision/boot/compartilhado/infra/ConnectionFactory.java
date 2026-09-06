package paradecision.boot.compartilhado.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
  public String erro;
  public String driver, url, base_dados, login, senha;
  public String ipPrincipal, ipServer, ipAtual, portaServer, outro;

  public ConnectionFactory() {
    Properties config = new Properties();
    Path arquivo = Path.of(System.getProperty("db.config", "config/banco-local.properties"));
    if (Files.exists(arquivo)) {
      try (var leitor = Files.newBufferedReader(arquivo, StandardCharsets.UTF_8)) {
        config.load(leitor);
      } catch (IOException e) {
        throw new IllegalStateException("Não foi possível ler a configuração do banco", e);
      }
    }
    driver = "com.mysql.cj.jdbc.Driver";
    ipPrincipal = ipServer = ipAtual = "127.0.0.1";
    portaServer = "3306";
    base_dados = "ssdparaviverbem";
    url = valor(config, "db.url", "DB_URL", "jdbc:mysql://127.0.0.1:3306/ssdparaviverbem");
    login = valor(config, "db.user", "DB_USER", "zeen_app");
    senha = valor(config, "db.password", "DB_PASSWORD", "");
  }

  private static String valor(Properties config, String chave, String ambiente, String padrao) {
    String valor = System.getenv(ambiente);
    return valor != null ? valor : config.getProperty(chave, padrao);
  }

  public Connection getConnection() {
    try {
      return DriverManager.getConnection(url, login, senha);
    } catch (SQLException e) {
      erro = "Problemas na conexão com a fonte de dados (SQLState: " + e.getSQLState() + ")";
      System.err.println(erro);
      return null;
    }
  }
}
