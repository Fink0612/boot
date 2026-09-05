package paradecision.boot.Model;

import java.util.ArrayList;

public class AgendaUsuariosModel {

	private AgendaModel oAgendaModel = new AgendaModel();
	private ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	private ArrayList<AgendaUsuarioPerfilModel> arrAgendaUsuarioPerfilModel = new ArrayList<AgendaUsuarioPerfilModel>();
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
	public ArrayList<AgendaUsuarioPerfilModel> getArrAgendaUsuarioPerfilModel() {
		return arrAgendaUsuarioPerfilModel;
	}
	public void setArrAgendaUsuarioPerfilModel(ArrayList<AgendaUsuarioPerfilModel> arrAgendaUsuarioPerfilModel) {
		this.arrAgendaUsuarioPerfilModel = arrAgendaUsuarioPerfilModel;
	}
	
}
