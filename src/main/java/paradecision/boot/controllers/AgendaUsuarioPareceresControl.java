package paradecision.boot.controllers;

import Pck_Model.AgendaUsuarioPareceresModel;
import Pck_Persistencia.AgendaUsuarioPareceresPersistencia;

public class AgendaUsuarioPareceresControl {

	AgendaUsuarioPareceresPersistencia oAgendaUsuarioPareceresPersistencia = new AgendaUsuarioPareceresPersistencia();

	public AgendaUsuarioPareceresModel selectPareceresAgUsu(AgendaUsuarioPareceresModel oAgendaUsuarioPareceresModel) {
		oAgendaUsuarioPareceresModel = oAgendaUsuarioPareceresPersistencia.selectPareceresAgUsu(oAgendaUsuarioPareceresModel);
		return oAgendaUsuarioPareceresModel;
	}
	
}
