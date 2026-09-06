package paradecision.boot.modulos.usuarios.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.empresas.entity.Empresa;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.usuarios.dto.UsuarioEmpresasDados;

@Repository
public class UsuarioEmpresasRepository {

  public UsuarioEmpresasDados selectEmpresasDoUsuario(UsuarioEmpresasDados dadosUsuarioEmpresas) {
    Empresa dadosEmpresa;
    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil;
    ArrayList<Empresa> listaEmpresa = new ArrayList<Empresa>();
    ArrayList<EmpresaUsuarioPerfil> listaEmpresaUsuarioPerfil =
        new ArrayList<EmpresaUsuarioPerfil>();
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM EMPRESA_01 AS E ";
    instrucaoSql += "INNER JOIN EMPRESA_USUARIO_PERFIL_03 AS EU ON ";
    instrucaoSql += "((E.A01_CODIGO = EU.A01_CODIGO) AND (EU.A02_CODIGO = ?));";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosUsuarioEmpresas.getoUsuarioModel().getA02_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        dadosEmpresa = new Empresa();
        dadosEmpresa.setA01_codigo(resultadoConsulta.getLong("A01_CODIGO"));
        dadosEmpresa.setA01_dt_cadastro(resultadoConsulta.getDate("A01_DT_CADASTRO"));
        dadosEmpresa.setA01_dt_ultima_alteracao(resultadoConsulta.getDate("A01_DT_ULTIMA_ALTERACAO"));
        dadosEmpresa.setA01_descricao(resultadoConsulta.getString("A01_DESCRICAO"));
        dadosEmpresa.setA01_nome(resultadoConsulta.getString("A01_NOME"));
        dadosEmpresa.setA01_status(resultadoConsulta.getInt("A01_STATUS"));
        listaEmpresa.add(dadosEmpresa);
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
      dadosUsuarioEmpresas.setArrEmpresaModel(listaEmpresa);
      dadosUsuarioEmpresas.setArrEmpresaUsuarioPerfilModel(listaEmpresaUsuarioPerfil);
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println("Este Erro");
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UEP)");
    }
    fechaCon(conexaoBanco);
    return dadosUsuarioEmpresas;
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
