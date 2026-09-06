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

  public UsuarioEmpresasDados selectEmpresasDoUsuario(UsuarioEmpresasDados oUsuarioEmpresasModel) {
    Empresa oEmpresaModel;
    EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel;
    ArrayList<Empresa> arrEmpresaModel = new ArrayList<Empresa>();
    ArrayList<EmpresaUsuarioPerfil> arrEmpresaUsuarioPerfilModel =
        new ArrayList<EmpresaUsuarioPerfil>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM EMPRESA_01 AS E ";
    sql += "INNER JOIN EMPRESA_USUARIO_PERFIL_03 AS EU ON ";
    sql += "((E.A01_CODIGO = EU.A01_CODIGO) AND (EU.A02_CODIGO = ?));";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oUsuarioEmpresasModel.getoUsuarioModel().getA02_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oEmpresaModel = new Empresa();
        oEmpresaModel.setA01_codigo(rs.getLong("A01_CODIGO"));
        oEmpresaModel.setA01_dt_cadastro(rs.getDate("A01_DT_CADASTRO"));
        oEmpresaModel.setA01_dt_ultima_alteracao(rs.getDate("A01_DT_ULTIMA_ALTERACAO"));
        oEmpresaModel.setA01_descricao(rs.getString("A01_DESCRICAO"));
        oEmpresaModel.setA01_nome(rs.getString("A01_NOME"));
        oEmpresaModel.setA01_status(rs.getInt("A01_STATUS"));
        arrEmpresaModel.add(oEmpresaModel);
        oEmpresaUsuarioPerfilModel = new EmpresaUsuarioPerfil();
        oEmpresaUsuarioPerfilModel.setA01_codigo(rs.getLong("A01_CODIGO"));
        oEmpresaUsuarioPerfilModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oEmpresaUsuarioPerfilModel.setA03_dt_cadastro(rs.getDate("A03_DT_CADASTRO"));
        oEmpresaUsuarioPerfilModel.setA03_perfil_paraviverbem(rs.getInt("A03_PERFIL_PARAVIVERBEM"));
        oEmpresaUsuarioPerfilModel.setA03_perfil_administrador(
            rs.getInt("A03_PERFIL_ADMINISTRADOR"));
        oEmpresaUsuarioPerfilModel.setA03_perfil_chefe(rs.getInt("A03_PERFIL_CHEFE"));
        oEmpresaUsuarioPerfilModel.setA03_perfil_padrao(rs.getInt("A03_PERFIL_PADRAO"));
        arrEmpresaUsuarioPerfilModel.add(oEmpresaUsuarioPerfilModel);
      }
      oUsuarioEmpresasModel.setArrEmpresaModel(arrEmpresaModel);
      oUsuarioEmpresasModel.setArrEmpresaUsuarioPerfilModel(arrEmpresaUsuarioPerfilModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println("Este Erro");
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(UEP)");
    }
    fechaCon(con);
    return oUsuarioEmpresasModel;
  }

  // ......PARA LIDAR COM O BANCO DE DADOS..........

  private void fechaCon(Connection con) {
    if (con == null) return;
    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
