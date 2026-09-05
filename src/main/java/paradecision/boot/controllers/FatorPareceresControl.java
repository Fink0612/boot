package paradecision.boot.controllers;

import Pck_Model.FatorPareceresModel;
import Pck_Persistencia.FatorPareceresPersistencia;

public class FatorPareceresControl {

	FatorPareceresPersistencia oFatorPareceresPersistencia = new FatorPareceresPersistencia();

	public FatorPareceresModel selectPareceresDoFator(FatorPareceresModel oFatorPareceresModel) {
		oFatorPareceresModel = oFatorPareceresPersistencia.selectPareceresDoFator(oFatorPareceresModel);
		return oFatorPareceresModel;
	}
	
}
