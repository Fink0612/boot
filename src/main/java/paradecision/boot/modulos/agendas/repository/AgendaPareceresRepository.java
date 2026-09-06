package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaPareceresDados;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaPareceresRepository {

  public AgendaPareceresDados selectPareceresDaAgenda(AgendaPareceresDados dadosAgendaPareceres) {
    Usuario dadosUsuario;
    ParecerFatorUsuario dadosParecerFatorUsuario;
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario =
        new ArrayList<ParecerFatorUsuario>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM VW_FATORES_PARECERES WHERE A04_CODIGO = ?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgendaPareceres.getoAgendaModel().getA04_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario = new Usuario();
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        listaUsuario.add(dadosUsuario);
        dadosParecerFatorUsuario = new ParecerFatorUsuario();
        dadosParecerFatorUsuario.setA07_codigo(resultadoConsulta.getLong("A07_CODIGO"));
        dadosParecerFatorUsuario.setA06_codigo(resultadoConsulta.getLong("A06_CODIGO"));
        dadosParecerFatorUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosParecerFatorUsuario.setA07_num_sequencia(resultadoConsulta.getInt("A07_NUM_SEQUENCIA"));
        dadosParecerFatorUsuario.setA07_certeza(resultadoConsulta.getDouble("A07_CERTEZA"));
        dadosParecerFatorUsuario.setA07_contradicao(resultadoConsulta.getDouble("A07_CONTRADICAO"));
        dadosParecerFatorUsuario.setA07_dt_cadastro(resultadoConsulta.getDate("A07_DT_CADASTRO"));
        listaParecerFatorUsuario.add(dadosParecerFatorUsuario);
      }
      dadosAgendaPareceres.setArrUsuarioModel(listaUsuario);
      dadosAgendaPareceres.setArrParecerFatorUsuarioModel(listaParecerFatorUsuario);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AFP)");
    }
    fechaCon(conexaoBanco);
    return dadosAgendaPareceres;
  }

  // ......PARA LIDAR COM O BANCO DE DADOS..........

  private void fechaCon(Connection conexaoBanco) {
    if (conexaoBanco == null) return;
    try {
      conexaoBanco.close();
    } catch (SQLException excecao) {
      excecao.printStackTrace();
    }
  }
}
