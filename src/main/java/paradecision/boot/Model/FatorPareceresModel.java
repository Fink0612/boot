package paradecision.boot.Model;

import java.util.ArrayList;

public class FatorPareceresModel {

	private FatorModel oFatorModel = new FatorModel();
	private ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	private ArrayList<ParecerFatorUsuarioModel> arrParecerFatorUsuarioModel = new ArrayList<ParecerFatorUsuarioModel>();
	
	//--------------------------------------------------
	
	public FatorModel getoFatorModel() {
		return oFatorModel;
	}
	public void setoFatorModel(FatorModel oFatorModel) {
		this.oFatorModel = oFatorModel;
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
