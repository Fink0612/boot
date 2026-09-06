package paradecision.boot.modulos.compartilhado.infra;

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

    Path arquivo = Path.of(
            System.getProperty(
                    "db.config",
                    "config/banco-local.properties"
            )
    );

    if (Files.exists(arquivo)) {
      try (var leitor = Files.newBufferedReader(
              arquivo,
              StandardCharsets.UTF_8
      )) {
        config.load(leitor);
      } catch (IOException excecao) {
        throw new IllegalStateException(
                "Não foi possível ler a configuração do banco",
                excecao
        );
      }
    }

    driver = "com.mysql.cj.jdbc.Driver";

    ipPrincipal =
            ipServer =
                    ipAtual =
                            "mysql-1b84e46-jonathanfink195-5c75.b.aivencloud.com";

    portaServer = "16979";

    base_dados = "defaultdb";

    url = valor(
            config,
            "db.url",
            "DB_URL",
            "jdbc:mysql://mysql-1b84e46-jonathanfink195-5c75.b.aivencloud.com:16979/defaultdb?sslMode=REQUIRED"
    );

    login = valor(
            config,
            "db.user",
            "DB_USER",
            "avnadmin"
    );

    senha = valor(
            config,
            "db.password",
            "DB_PASSWORD",
            "COLOQUE_A_SENHA_DA_AIVEN_AQUI"
    );
  }

  private static String valor(
          Properties config,
          String chave,
          String ambiente,
          String padrao
  ) {

    String valor = System.getenv(ambiente);

    return valor != null
            ? valor
            : config.getProperty(chave, padrao);
  }

  public Connection getConnection() {

    try {

      return DriverManager.getConnection(
              url,
              login,
              senha
      );

    } catch (SQLException excecao) {

      erro =
              "Problemas na conexão com a fonte de dados "
                      + "(SQLState: "
                      + excecao.getSQLState()
                      + "): "
                      + excecao.getMessage();

      System.err.println(erro);

      return null;
    }
  }
}