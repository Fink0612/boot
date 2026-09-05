package paradecision.boot.controllers;

import Pck_Model.EmpresaUsuariosModel;
import Pck_Persistencia.EmpresaUsuariosPersistencia;

public class EmpresaUsuariosControl {

	EmpresaUsuariosPersistencia oEmpresaUsuariosPersistencia = new EmpresaUsuariosPersistencia();

	public EmpresaUsuariosModel selectUsuariosDaEmpresa(EmpresaUsuariosModel oEmpresaUsuariosModel) {
		oEmpresaUsuariosModel = oEmpresaUsuariosPersistencia.selectUsuariosDaEmpresa(oEmpresaUsuariosModel);
		return oEmpresaUsuariosModel;
	}
	
}
