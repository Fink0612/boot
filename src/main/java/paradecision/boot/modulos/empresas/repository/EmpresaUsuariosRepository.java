package paradecision.boot.modulos.empresas.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.empresas.dto.EmpresaUsuariosDados;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.entity.Usuario;

@Repository
public class EmpresaUsuariosRepository {

  public EmpresaUsuariosDados selectUsuariosDaEmpresa(EmpresaUsuariosDados dadosEmpresaUsuarios) {
    Usuario dadosUsuario;
    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil;
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    ArrayList<EmpresaUsuarioPerfil> listaEmpresaUsuarioPerfil =
        new ArrayList<EmpresaUsuarioPerfil>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM USUARIO_02 AS U ";
    instrucaoSql += "INNER JOIN EMPRESA_USUARIO_PERFIL_03 AS EU ON ";
    instrucaoSql += "((U.A02_CODIGO = EU.A02_CODIGO) AND (EU.A01_CODIGO = ?));";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosEmpresaUsuarios.getoEmpresaModel().getA01_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosUsuario = new Usuario();
        dadosUsuario.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosUsuario.setA02_dt_cadastro(resultadoConsulta.getDate("A02_DT_CADASTRO"));
        dadosUsuario.setA02_dt_ultima_alteracao(resultadoConsulta.getDate("A02_DT_ULTIMA_ALTERACAO"));
        dadosUsuario.setA02_nome(resultadoConsulta.getString("A02_NOME"));
        dadosUsuario.setA02_usuario(resultadoConsulta.getString("A02_USUARIO"));
        dadosUsuario.setA02_senha(resultadoConsulta.getString("A02_SENHA"));
        dadosUsuario.setA02_codigo_link(resultadoConsulta.getString("A02_CODIGO_LINK"));
        dadosUsuario.setA02_email(resultadoConsulta.getString("A02_EMAIL"));
        dadosUsuario.setA02_status(resultadoConsulta.getInt("A02_STATUS"));
        listaUsuario.add(dadosUsuario);
        dadosEmpresaUsuarioPerfil = new EmpresaUsuarioPerfil();
        dadosEmpresaUsuarioPerfil.setA01_codigo(resultadoConsulta.getLong("A01_CODIGO"));
        dadosEmpresaUsuarioPerfil.setA02_codigo(resultadoConsulta.getLong("A02_CODIGO"));
        dadosEmpresaUsuarioPerfil.setA03_dt_cadastro(resultadoConsulta.getDate("A03_DT_CADASTRO"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_paraviverbem(resultadoConsulta.getInt("A03_PERFIL_PARAVIVERBEM"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_administrador(
            resultadoConsulta.getInt("A03_PERFIL_ADMINISTRADOR"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_chefe(resultadoConsulta.getInt("A03_PERFIL_CHEFE"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_padrao(resultadoConsulta.getInt("A03_PERFIL_PADRAO"));
        listaEmpresaUsuarioPerfil.add(dadosEmpresaUsuarioPerfil);
      }
      dadosEmpresaUsuarios.setArrUsuarioModel(listaUsuario);
      dadosEmpresaUsuarios.setArrEmpresaUsuarioPerfilModel(listaEmpresaUsuarioPerfil);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EUP)");
    }
    fechaCon(conexaoBanco);
    return dadosEmpresaUsuarios;
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
