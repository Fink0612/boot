package paradecision.boot.Model;

import java.util.ArrayList;

public class AgendaFatoresModel {

	private AgendaModel oAgendaModel = new AgendaModel();
	private ArrayList<FatorModel> arrFatorModel = new ArrayList<FatorModel>();
	private ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	private ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = new ArrayList<ParecerFatorUsuarioModel>();
	
	//--------------------------------------------------
	
	public ArrayList<UsuarioModel> getArrUsuarioModel() {
		return arrUsuarioModel;
	}
	public void setArrUsuarioModel(ArrayList<UsuarioModel> arrUsuarioModel) {
		this.arrUsuarioModel = arrUsuarioModel;
	}
	public AgendaModel getoAgendaModel() {
		return oAgendaModel;
	}
	public void setoAgendaModel(AgendaModel oAgendaModel) {
		this.oAgendaModel = oAgendaModel;
	}
	public ArrayList<FatorModel> getArrFatorModel() {
		return arrFatorModel;
	}
	public void setArrFatorModel(ArrayList<FatorModel> arrFatorModel) {
		this.arrFatorModel = arrFatorModel;
	}
	public ArrayList<ParecerFatorUsuarioModel> getArrParecerFatorUsuarioModel() {
		return arrParecerFatorUsuarioModel;
	}
	public void setArrParecerFatorUsuarioModel(ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel) {
		this.arrParecerFatorUsuarioModel = arrParecerFatorUsuarioModel;
	}
	
}
