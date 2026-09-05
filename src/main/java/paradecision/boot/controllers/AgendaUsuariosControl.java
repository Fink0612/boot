package paradecision.boot.controllers;

import Pck_Model.AgendaUsuariosModel;
import Pck_Persistencia.AgendaUsuariosPersistencia;

public class AgendaUsuariosControl {

	AgendaUsuariosPersistencia oAgendaUsuariosPersistencia = new AgendaUsuariosPersistencia();

	public AgendaUsuariosModel selectUsuariosDaAgenda(AgendaUsuariosModel oAgendaUsuariosModel) {
		oAgendaUsuariosModel = oAgendaUsuariosPersistencia.selectUsuariosDaAgenda(oAgendaUsuariosModel);
		return oAgendaUsuariosModel;
	}
	
}
