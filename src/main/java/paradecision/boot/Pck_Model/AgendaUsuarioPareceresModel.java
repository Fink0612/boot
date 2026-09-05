package Pck_Model;

import java.util.ArrayList;

public class AgendaUsuarioPareceresModel {

	private AgendaModel oAgendaModel = new AgendaModel();
	private UsuarioModel oUsuarioModel = new UsuarioModel();
	private ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = new ArrayList<ParecerFatorUsuarioModel>();

	//--------------------------------------------------
	
	public AgendaModel getoAgendaModel() {
		return oAgendaModel;
	}
	public void setoAgendaModel(AgendaModel oAgendaModel) {
		this.oAgendaModel = oAgendaModel;
	}
	public UsuarioModel getoUsuarioModel() {
		return oUsuarioModel;
	}
	public void setoUsuarioModel(UsuarioModel oUsuarioModel) {
		this.oUsuarioModel = oUsuarioModel;
	}
	public ArrayList<ParecerFatorUsuarioModel> getArrParecerFatorUsuarioModel() {
		return arrParecerFatorUsuarioModel;
	}
	public void setArrParecerFatorUsuarioModel(ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel) {
		this.arrParecerFatorUsuarioModel = arrParecerFatorUsuarioModel;
	}
	
}
