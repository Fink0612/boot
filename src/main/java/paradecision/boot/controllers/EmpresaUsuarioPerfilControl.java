package paradecision.boot.controllers;

import Pck_Model.EmpresaUsuarioPerfilModel;
import Pck_Persistencia.EmpresaUsuarioPerfilPersistencia;

public class EmpresaUsuarioPerfilControl {

	EmpresaUsuarioPerfilPersistencia oEmpresaUsuarioPerfilPersistencia = new EmpresaUsuarioPerfilPersistencia();
	
	public EmpresaUsuarioPerfilModel selectEmpresaUsuario(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		oEmpresaUsuarioPerfilModel = oEmpresaUsuarioPerfilPersistencia.selectEmpresaUsuario(oEmpresaUsuarioPerfilModel);
		return oEmpresaUsuarioPerfilModel;
	}

	public int insertEmpresaUsuarioPerfil(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		int okMetodo = 1;
		okMetodo = oEmpresaUsuarioPerfilPersistencia.insertEmpresaUsuarioPerfil(oEmpresaUsuarioPerfilModel);
		return okMetodo;
	}
	
	public String updateEmpresaUsuarioPerfil(EmpresaUsuarioPerfilModel oEmpresaUsuarioPerfilModel) {
		String okMetodo = "";
		okMetodo = oEmpresaUsuarioPerfilPersistencia.updateEmpresaUsuarioPerfil(oEmpresaUsuarioPerfilModel);
		return okMetodo;
	}

}
