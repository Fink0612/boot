package paradecision.boot.Model;

import java.util.ArrayList;

public class EmpresaUsuariosModel {

	private EmpresaModel oEmpresaModel = new EmpresaModel();
	private ArrayList<UsuarioModel> arrUsuarioModel = new ArrayList<UsuarioModel>();
	private ArrayList<EmpresaUsuarioPerfilModel> arrEmpresaUsuarioPerfilModel = new ArrayList<EmpresaUsuarioPerfilModel>();
	//--------------------------------------------------
	public EmpresaModel getoEmpresaModel() {
		return oEmpresaModel;
	}
	public void setoEmpresaModel(EmpresaModel oEmpresaModel) {
		this.oEmpresaModel = oEmpresaModel;
	}
	public ArrayList<UsuarioModel> getArrUsuarioModel() {
		return arrUsuarioModel;
	}
	public void setArrUsuarioModel(ArrayList<UsuarioModel> arrUsuarioModel) {
		this.arrUsuarioModel = arrUsuarioModel;
	}
	public ArrayList<EmpresaUsuarioPerfilModel> getArrEmpresaUsuarioPerfilModel() {
		return arrEmpresaUsuarioPerfilModel;
	}
	public void setArrEmpresaUsuarioPerfilModel(ArrayList<EmpresaUsuarioPerfilModel> arrEmpresaUsuarioPerfilModel) {
		this.arrEmpresaUsuarioPerfilModel = arrEmpresaUsuarioPerfilModel;
	}
	
}
