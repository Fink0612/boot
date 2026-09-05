package paradecision.boot.controllers;

import Pck_Model.FatorModel;
import Pck_Persistencia.FatorPersistencia;

public class FatorControl {

	public FatorPersistencia oFatorPersistencia = new FatorPersistencia();

	public String insertFator(FatorModel oFatorModel) {
		String res = this.oFatorPersistencia.insertFator(oFatorModel);
		return res;
	}

	public FatorModel selectFator(FatorModel oFatorModel) {
		oFatorModel = this.oFatorPersistencia.selectFator(oFatorModel);
		return oFatorModel;
	}

	public String updateFator(FatorModel oFatorModel) {
		String res = this.oFatorPersistencia.updateFator(oFatorModel);
		return res;
	}

}
