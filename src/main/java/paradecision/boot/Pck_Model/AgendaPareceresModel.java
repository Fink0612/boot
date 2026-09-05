package Pck_Model;

import java.util.ArrayList;

public class AgendaPareceresModel {

	private AgendaModel oAgendaModel = new AgendaModel();
	private ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	private ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = new ArrayList<ParecerFatorUsuarioModel>();

	//--------------------------------------------------
	
	public AgendaModel getoAgendaModel() {
		return oAgendaModel;
	}
	public void setoAgendaModel(AgendaModel oAgendaModel) {
		this.oAgendaModel = oAgendaModel;
	}
	public ArrayList<UsuarioModel> getArrUsuarioModel() {
		return arrUsuarioModel;
	}
	public void setArrUsuarioModel(ArrayList<UsuarioModel> arrUsuarioModel) {
		this.arrUsuarioModel = arrUsuarioModel;
	}
	public ArrayList<ParecerFatorUsuarioModel> getArrParecerFatorUsuarioModel() {
		return arrParecerFatorUsuarioModel;
	}
	public void setArrParecerFatorUsuarioModel(ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel) {
		this.arrParecerFatorUsuarioModel = arrParecerFatorUsuarioModel;
	}
	
}
