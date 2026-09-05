package paradecision.boot.controllers;

import Pck_Model.AgendaUsuarioPerfilModel;
import Pck_Persistencia.AgendaUsuarioPerfilPersistencia;

public class AgendaUsuarioPerfilControl {

	AgendaUsuarioPerfilPersistencia oAgendaUsuarioPerfilPersistencia = new AgendaUsuarioPerfilPersistencia();
	
	public AgendaUsuarioPerfilModel selectAgendaUsuarioPerfil(AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel) {
		oAgendaUsuarioPerfilModel = oAgendaUsuarioPerfilPersistencia.selectAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
		return oAgendaUsuarioPerfilModel;
	}

	public String deleteAgendaUsuarioPerfil(AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel) {
		String msgAction = oAgendaUsuarioPerfilPersistencia.deleteAgendaUsuarioPerfil(oAgendaUsuarioPerfilModel);
		return msgAction;
	}

	public String updatePerfilUsuarioAgenda(AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel) {
		String msgAction = oAgendaUsuarioPerfilPersistencia.updatePerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
		return msgAction;
	}

	public String insertPerfilUsuarioAgenda(AgendaUsuarioPerfilModel oAgendaUsuarioPerfilModel) {
		String msgAction = oAgendaUsuarioPerfilPersistencia.insertPerfilUsuarioAgenda(oAgendaUsuarioPerfilModel);
		return msgAction;
	}

}
