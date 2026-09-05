package paradecision.boot.controllers;

import Pck_Model.ParecerFatorUsuarioModel;
import Pck_Persistencia.ParecerFatorUsuarioPersistencia;

public class ParecerFatorUsuarioControl {

	ParecerFatorUsuarioPersistencia oParecerFatorUsuarioPersistencia = new ParecerFatorUsuarioPersistencia();
	
	public ParecerFatorUsuarioModel selectParecerFatorUsuario(ParecerFatorUsuarioModel oParecerFatorUsuarioModel) {
		oParecerFatorUsuarioModel = oParecerFatorUsuarioPersistencia.selectParecerFatorUsuario(oParecerFatorUsuarioModel);
		return oParecerFatorUsuarioModel;
	}

	public String insertParecerFatorUsuario(ParecerFatorUsuarioModel oParecerFatorUsuarioModel) {
		String okMetodo = "";
		okMetodo = oParecerFatorUsuarioPersistencia.insertParecerFatorUsuario(oParecerFatorUsuarioModel);
		return okMetodo;
	}

	public String updateParecerFatorUsuario(ParecerFatorUsuarioModel oParecerFatorUsuarioModel) {
		String okMetodo = "";
		okMetodo = oParecerFatorUsuarioPersistencia.updateParecerFatorUsuario(oParecerFatorUsuarioModel);
		return okMetodo;
	}
	
}
