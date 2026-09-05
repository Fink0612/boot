package paradecision.boot.controllers;

import Pck_Model.AgendaFatoresModel;
import Pck_Persistencia.AgendaFatoresPersistencia;

public class AgendaFatoresControl {

	AgendaFatoresPersistencia oAgendaFatoresPersistencia = new AgendaFatoresPersistencia();

	public AgendaFatoresModel selectFatoresDaAgenda(AgendaFatoresModel oAgendaFatoresModel) {
		oAgendaFatoresModel = oAgendaFatoresPersistencia.selectFatoresDaAgenda(oAgendaFatoresModel);
		return oAgendaFatoresModel;
	}
	
	public String updateGrausFatoresDaAgenda(AgendaFatoresModel oAgendaFatoresModel) {
		String msgAction = oAgendaFatoresPersistencia.updateGrausFatoresDaAgenda(oAgendaFatoresModel);
		return msgAction;
	}
	
}
