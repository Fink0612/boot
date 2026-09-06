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

  public EmpresaUsuariosDados selectUsuariosDaEmpresa(EmpresaUsuariosDados oEmpresaUsuariosModel) {
    Usuario oUsuarioModel;
    EmpresaUsuarioPerfil oEmpresaUsuarioPerfilModel;
    ArrayList<Usuario> arrUsuarioModel = new ArrayList<Usuario>();
    ArrayList<EmpresaUsuarioPerfil> arrEmpresaUsuarioPerfilModel =
        new ArrayList<EmpresaUsuarioPerfil>();
    Connection con = new ConnectionFactory().getConnection();
    String sql = "SELECT * FROM USUARIO_02 AS U ";
    sql += "INNER JOIN EMPRESA_USUARIO_PERFIL_03 AS EU ON ";
    sql += "((U.A02_CODIGO = EU.A02_CODIGO) AND (EU.A01_CODIGO = ?));";
    try {
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.setLong(1, oEmpresaUsuariosModel.getoEmpresaModel().getA01_codigo());
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        oUsuarioModel = new Usuario();
        oUsuarioModel.setA02_codigo(rs.getLong("A02_CODIGO"));
        oUsuarioModel.setA02_dt_cadastro(rs.getDate("A02_DT_CADASTRO"));
        oUsuarioModel.setA02_dt_ultima_alteracao(rs.getDate("A02_DT_ULTIMA_ALTERACAO"));
        oUsuarioModel.setA02_nome(rs.getString("A02_NOME"));
        oUsuarioModel.setA02_usuario(rs.getString("A02_USUARIO"));
        oUsuarioModel.setA02_senha(rs.getString("A02_SENHA"));
        oUsuarioModel.setA02_codigo_link(rs.getString("A02_CODIGO_LINK"));
        oUsuarioModel.setA02_email(rs.getString("A02_EMAIL"));
        oUsuarioModel.setA02_status(rs.getInt("A02_STATUS"));
        arrUsuarioModel.add(oUsuarioModel);
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
      oEmpresaUsuariosModel.setArrUsuarioModel(arrUsuarioModel);
      oEmpresaUsuariosModel.setArrEmpresaUsuarioPerfilModel(arrEmpresaUsuarioPerfilModel);
      stmt.close();
    } catch (Exception e) {
      System.out.println(":: ERRO :: Problemas com a leitura de dados no BD...(EUP)");
    }
    fechaCon(con);
    return oEmpresaUsuariosModel;
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
