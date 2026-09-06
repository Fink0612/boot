package paradecision.boot.modulos.empresas.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import paradecision.boot.modulos.compartilhado.infra.ConnectionFactory;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;

@Repository
public class EmpresaUsuarioPerfilRepository {

  public EmpresaUsuarioPerfil selectEmpresaUsuario(
      EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfilAuxiliares = null;
    int achouCadastro = 0;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "SELECT * FROM EMPRESA_USUARIO_PERFIL_03 WHERE A01_CODIGO=? AND A02_CODIGO=?";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosEmpresaUsuarioPerfil.getA01_codigo());
      comandoPreparado.setLong(2, dadosEmpresaUsuarioPerfil.getA02_codigo());
      ResultSet resultadoConsulta = comandoPreparado.executeQuery();
      while (resultadoConsulta.next()) {
        achouCadastro = 1;
        dadosEmpresaUsuarioPerfil.setA03_dt_cadastro(resultadoConsulta.getDate("A03_DT_CADASTRO"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_paraviverbem(resultadoConsulta.getInt("A03_PERFIL_PARAVIVERBEM"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_administrador(
            resultadoConsulta.getInt("A03_PERFIL_ADMINISTRADOR"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_chefe(resultadoConsulta.getInt("A03_PERFIL_CHEFE"));
        dadosEmpresaUsuarioPerfil.setA03_perfil_padrao(resultadoConsulta.getInt("A03_PERFIL_PADRAO"));
      }
      comandoPreparado.close();
    } catch (Exception excecao) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EUPP)");
    }
    fechaCon(conexaoBanco);
    if (achouCadastro == 1) dadosEmpresaUsuarioPerfilAuxiliares = dadosEmpresaUsuarioPerfil;
    return dadosEmpresaUsuarioPerfilAuxiliares;
  }

  public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    int operacaoConcluida = 1;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "INSERT INTO EMPRESA_USUARIO_PERFIL_03 (A01_CODIGO, A02_CODIGO, ";
    instrucaoSql += "A03_PERFIL_PARAVIVERBEM, A03_PERFIL_ADMINISTRADOR, ";
    instrucaoSql += "A03_PERFIL_CHEFE, A03_PERFIL_PADRAO, A03_DT_CADASTRO) ";
    instrucaoSql += "VALUES (?, ?, ?, ?, ?, ?, sysdate())";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setLong(1, dadosEmpresaUsuarioPerfil.getA01_codigo());
      comandoPreparado.setLong(2, dadosEmpresaUsuarioPerfil.getA02_codigo());
      comandoPreparado.setInt(3, dadosEmpresaUsuarioPerfil.getA03_perfil_paraviverbem());
      comandoPreparado.setInt(4, dadosEmpresaUsuarioPerfil.getA03_perfil_administrador());
      comandoPreparado.setInt(5, dadosEmpresaUsuarioPerfil.getA03_perfil_chefe());
      comandoPreparado.setInt(6, dadosEmpresaUsuarioPerfil.getA03_perfil_padrao());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      operacaoConcluida = 0;
      System.out.println(":: ERRO :: Problemas com a inser��o de dados no BD...(EUPP)");
    }
    fechaCon(conexaoBanco);
    return operacaoConcluida;
  }

  public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    String operacaoConcluida = "OK";
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "UPDATE EMPRESA_USUARIO_PERFIL_03 SET ";
    instrucaoSql += "A03_PERFIL_CHEFE=?, A03_PERFIL_PADRAO=? ";
    instrucaoSql += "WHERE A01_CODIGO=? AND A02_CODIGO=?;";
    try {
      PreparedStatement comandoPreparado = conexaoBanco.prepareStatement(instrucaoSql);
      comandoPreparado.setInt(1, dadosEmpresaUsuarioPerfil.getA03_perfil_chefe());
      comandoPreparado.setInt(2, dadosEmpresaUsuarioPerfil.getA03_perfil_padrao());
      comandoPreparado.setLong(3, dadosEmpresaUsuarioPerfil.getA01_codigo());
      comandoPreparado.setLong(4, dadosEmpresaUsuarioPerfil.getA02_codigo());
      comandoPreparado.execute();
      comandoPreparado.close();
    } catch (Exception excecao) {
      operacaoConcluida = "NOK";
      System.out.println(":: ERRO :: Problemas com a altera��o de dados no BD...(EUPP)");
    }
    fechaCon(conexaoBanco);
    return operacaoConcluida;
  }

  public int XinsertEmpresaUsuarioPerfil2(EmpresaUsuarioPerfil dadosEmpresaUsuarioPerfil) {
    int operacaoConcluida = 1;
    Connection conexaoBanco = new ConnectionFactory().getConnection();
    String instrucaoSql = "CALL PROC_INS_EMPRESA_USUARIO_PERFIL2(?,?,?,?,?,?)";
    try {
      CallableStatement oCall = conexaoBanco.prepareCall(instrucaoSql);
      oCall.setLong(1, dadosEmpresaUsuarioPerfil.getA01_codigo());
      oCall.setLong(2, dadosEmpresaUsuarioPerfil.getA02_codigo());
      oCall.setInt(3, dadosEmpresaUsuarioPerfil.getA03_perfil_paraviverbem());
      oCall.setInt(4, dadosEmpresaUsuarioPerfil.getA03_perfil_administrador());
      oCall.setInt(5, dadosEmpresaUsuarioPerfil.getA03_perfil_chefe());
      oCall.setInt(6, dadosEmpresaUsuarioPerfil.getA03_perfil_padrao());
      oCall.execute();
    } catch (Exception excecao) {
      operacaoConcluida = 0;
      System.out.println(":: ERRO :: Problemas com a inser��o de dados no BD...(EUPP-PROC)");
    }
    fechaCon(conexaoBanco);
    return operacaoConcluida;
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
