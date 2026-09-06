package paradecision.boot.modulos.agendas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.agendas.dto.AgendaUsuarioPareceresDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class AgendaUsuarioPareceresRepository {

  public AgendaUsuarioPareceresDados selectPareceresAgUsu(
      AgendaUsuarioPareceresDados dadosAgendaUsuarioPareceres) {
    Agenda dadosAgenda = dadosAgendaUsuarioPareceres.getoAgendaModel();
    Usuario dadosUsuario = dadosAgendaUsuarioPareceres.getoUsuarioModel();
    ArrayList<ParecerFatorUsuario> listaParecerFatorUsuario =
        new ArrayList<ParecerFatorUsuario>();
    ParecerFatorUsuario dadosParecerFatorUsuario;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM VW_FATORES_PARECERES WHERE (A04_CODIGO=? AND A02_CODIGO=?);";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosAgenda.getA04_codigo());
      comandoPreparado.setLong(2, dadosUsuario.getA02_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosParecerFatorUsuario = new ParecerFatorUsuario();
        dadosParecerFatorUsuario.setA07_codigo(resultadoConsulta.getLong("A07_CODIGO"));
        dadosParecerFatorUsuario.setA06_codigo(resultadoConsulta.getLong("A06_CODIGO"));
        dadosParecerFatorUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosParecerFatorUsuario.setA07_num_sequencia(resultadoConsulta.getInt("A07_NUM_SEQUENCIA"));
        dadosParecerFatorUsuario.setA07_certeza(resultadoConsulta.getDouble("A07_CERTEZA"));
        dadosParecerFatorUsuario.setA07_contradicao(resultadoConsulta.getDouble("A07_CONTRADICAO"));
        dadosParecerFatorUsuario.setStr_a07_certeza(resultadoConsulta.getString("A07_CERTEZA"));
        dadosParecerFatorUsuario.setStr_a07_contradicao(resultadoConsulta.getString("A07_CONTRADICAO"));
        dadosParecerFatorUsuario.setA07_dt_cadastro(resultadoConsulta.getDate("A07_DT_CADASTRO"));
        dadosParecerFatorUsuario.setA07_dt_ultima_alteracao(resultadoConsulta.getDate("A07_DT_ULTIMA_ALTERACAO"));
        listaParecerFatorUsuario.add(dadosParecerFatorUsuario);
      }
      dadosAgendaUsuarioPareceres.setArrParecerFatorUsuarioModel(listaParecerFatorUsuario);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(AUPP1)");
    }
    fechaCon(conexaoBanco);
    return dadosAgendaUsuarioPareceres;
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
