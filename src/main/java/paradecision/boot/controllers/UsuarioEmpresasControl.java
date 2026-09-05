package paradecision.boot.controllers;

import Pck_Model.UsuarioEmpresasModel;
import Pck_Persistencia.UsuarioEmpresasPersistencia;

public class UsuarioEmpresasControl {

	public UsuarioEmpresasModel selectEmpresasDoUsuario(UsuarioEmpresasModel oUsuarioEmpresasModel) {
		UsuarioEmpresasPersistencia oUsuarioEmpresasPersistencia = new UsuarioEmpresasPersistencia();
		oUsuarioEmpresasModel = oUsuarioEmpresasPersistencia.selectEmpresasDoUsuario(oUsuarioEmpresasModel);
		return oUsuarioEmpresasModel;
	}
	
}
