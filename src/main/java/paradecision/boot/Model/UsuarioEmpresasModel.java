package paradecision.boot.Model;

import java.util.ArrayList;

public class UsuarioEmpresasModel {

	private UsuarioModel oUsuarioModel = new UsuarioModel();
	private ArrayList<EmpresaModel> arrEmpresaModel = new ArrayList<EmpresaModel>();
	private ArrayList<EmpresaUsuarioPerfilModel> arrEmpresaUsuarioPerfilModel = new ArrayList<EmpresaUsuarioPerfilModel>();
	
	public UsuarioModel getoUsuarioModel() {
		return oUsuarioModel;
	}
	public void setoUsuarioModel(UsuarioModel oUsuarioModel) {
		this.oUsuarioModel = oUsuarioModel;
	}
	public ArrayList<EmpresaModel> getArrEmpresaModel() {
		return arrEmpresaModel;
	}
	public void setArrEmpresaModel(ArrayList<EmpresaModel> arrEmpresaModel) {
		this.arrEmpresaModel = arrEmpresaModel;
	}
	public ArrayList<EmpresaUsuarioPerfilModel> getArrEmpresaUsuarioPerfilModel() {
		return arrEmpresaUsuarioPerfilModel;
	}
	public void setArrEmpresaUsuarioPerfilModel(ArrayList<EmpresaUsuarioPerfilModel> arrEmpresaUsuarioPerfilModel) {
		this.arrEmpresaUsuarioPerfilModel = arrEmpresaUsuarioPerfilModel;
	}
	
}
