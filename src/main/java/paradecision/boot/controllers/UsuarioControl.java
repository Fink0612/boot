package paradecision.boot.controllers;


import Pck_Model.UsuarioModel;
import Pck_Persistencia.UsuarioPersistencia;

public class UsuarioControl {

	public UsuarioPersistencia oUsuarioPersistencia = new UsuarioPersistencia();

	public UsuarioModel selectUserLogin(UsuarioModel oUsuarioModel) {
		UsuarioModel auxUsuarioModel = new UsuarioModel();
		auxUsuarioModel.setA02_usuario(oUsuarioModel.getA02_usuario());
		auxUsuarioModel = this.oUsuarioPersistencia.selectUserLogin(auxUsuarioModel);
		if (auxUsuarioModel.getA02_codigo() > 0) {
			if (!(auxUsuarioModel.getA02_senha().equals(oUsuarioModel.getA02_senha()))) {
				auxUsuarioModel = new UsuarioModel();
			}
		}
		return auxUsuarioModel;
	}

	public UsuarioModel selectUserIni(UsuarioModel oUsuarioModel) {
		UsuarioModel auxUsuarioModel = new UsuarioModel();
		auxUsuarioModel.setA02_codigo_link(oUsuarioModel.getA02_codigo_link());
		auxUsuarioModel = this.oUsuarioPersistencia.selectUserIni(auxUsuarioModel);
		return auxUsuarioModel;
	}

	public UsuarioModel selectUserByUser(UsuarioModel oUsuarioModel) {
		UsuarioModel auxUsuarioModel = new UsuarioModel();
		auxUsuarioModel.setA02_usuario(oUsuarioModel.getA02_usuario());
		auxUsuarioModel = this.oUsuarioPersistencia.selectUserLogin(auxUsuarioModel);
		return auxUsuarioModel;
	}

	public UsuarioModel selectUserByCode(UsuarioModel oUsuarioModel) {
		oUsuarioModel = this.oUsuarioPersistencia.selectUserByCode(oUsuarioModel);
		return oUsuarioModel;
	}

	public UsuarioModel updateSenhaUsuario(UsuarioModel oUsuarioModel) {
		UsuarioModel auxUsuarioModel = new UsuarioModel();
		this.oUsuarioPersistencia.updateSenhaUsuario(oUsuarioModel);
		auxUsuarioModel.setA02_usuario(oUsuarioModel.getA02_usuario());
		auxUsuarioModel = this.selectUserByUser(auxUsuarioModel);
		return auxUsuarioModel;
	}

	public String updateUsuario(UsuarioModel oUsuarioModel) {
		String okMetodo = "";
		okMetodo = oUsuarioPersistencia.updateUsuario(oUsuarioModel);
		return okMetodo;
	}

	public UsuarioModel insertUsuario(UsuarioModel oUsuarioModel) {
		oUsuarioModel = this.oUsuarioPersistencia.insertUsuario(oUsuarioModel);
		return oUsuarioModel;
	}

}
