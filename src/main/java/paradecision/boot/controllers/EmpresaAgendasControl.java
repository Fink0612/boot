package paradecision.boot.controllers;

import Pck_Model.EmpresaAgendasModel;
import Pck_Persistencia.EmpresaAgendasPersistencia;

public class EmpresaAgendasControl {

	EmpresaAgendasPersistencia oEmpresaAgendasPersistencia = new EmpresaAgendasPersistencia();

	public EmpresaAgendasModel selectAgendasDaEmpresa(EmpresaAgendasModel oEmpresaAgendasModel) {
		oEmpresaAgendasModel = oEmpresaAgendasPersistencia.selectAgendasDaEmpresa(oEmpresaAgendasModel);
		return oEmpresaAgendasModel;
	}

	public EmpresaAgendasModel selectAgendasDaEmpresaUsuario(EmpresaAgendasModel oEmpresaAgendasModel) {
		oEmpresaAgendasModel = oEmpresaAgendasPersistencia.selectAgendasDaEmpresaUsuario(oEmpresaAgendasModel);
		return oEmpresaAgendasModel;
	}
	
}
