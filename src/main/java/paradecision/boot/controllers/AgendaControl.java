package paradecision.boot.controllers;

import Pck_Model.AgendaModel;
import Pck_Persistencia.AgendaPersistencia;
import org.springframework.beans.factory.annotation.Autowired;

public class AgendaControl {

	@Autowired
	public AgendaPersistencia oAgendaPersistencia = new AgendaPersistencia();

	public long insertAgenda(AgendaModel oAgendaModel) {
		long res = this.oAgendaPersistencia.insertAgenda(oAgendaModel);
		return res;
	}

	public AgendaModel selectAgenda(AgendaModel oAgendaModel) {
		oAgendaModel = this.oAgendaPersistencia.selectAgenda(oAgendaModel);
		return oAgendaModel;
	}

	public String updateStatusAgenda(AgendaModel oAgendaModel) {
		String msgAction = this.oAgendaPersistencia.updateStatusAgenda(oAgendaModel);
		return msgAction;
	}

	public String updateAgenda(AgendaModel oAgendaModel) {
		String msgAction = this.oAgendaPersistencia.updateAgenda(oAgendaModel);
		return msgAction;
	}




}
