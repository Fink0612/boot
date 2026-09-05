package Pck_Model;

import java.util.ArrayList;

public class EmpresaAgendasModel {

	private EmpresaModel oEmpresaModel = new EmpresaModel();
	private UsuarioModel oUsuarioModel = new UsuarioModel();
	private ArrayList<AgendaModel> arrAgendaModel = new ArrayList<AgendaModel>();
	//--------------------------------------------------
	public EmpresaModel getoEmpresaModel() {
		return oEmpresaModel;
	}
	public void setoEmpresaModel(EmpresaModel oEmpresaModel) {
		this.oEmpresaModel = oEmpresaModel;
	}
	public UsuarioModel getoUsuarioModel() {
		return oUsuarioModel;
	}
	public void setoUsuarioModel(UsuarioModel oUsuarioModel) {
		this.oUsuarioModel = oUsuarioModel;
	}
	public ArrayList<AgendaModel> getArrAgendaModel() {
		return arrAgendaModel;
	}
	public void setArrAgendaModel(ArrayList<AgendaModel> arrAgendaModel) {
		this.arrAgendaModel = arrAgendaModel;
	}
	
}
