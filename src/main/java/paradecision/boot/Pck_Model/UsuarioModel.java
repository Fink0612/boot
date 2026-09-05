package Pck_Model;

import java.sql.Date;

public class UsuarioModel {

	private long a02_codigo;
	private Date a02_dt_cadastro;
	private Date a02_dt_ultima_alteracao;
	private String a02_nome;
	private String a02_usuario;
	private String a02_senha;
	private String a02_codigo_link;
	private String a02_email;
	private int a02_status;
	//--------------------------------------------------
	public long getA02_codigo() {
		return a02_codigo;
	}
	public void setA02_codigo(long a02_codigo) {
		this.a02_codigo = a02_codigo;
	}
	public Date getA02_dt_cadastro() {
		return a02_dt_cadastro;
	}
	public void setA02_dt_cadastro(Date a02_dt_cadastro) {
		this.a02_dt_cadastro = a02_dt_cadastro;
	}
	public Date getA02_dt_ultima_alteracao() {
		return a02_dt_ultima_alteracao;
	}
	public void setA02_dt_ultima_alteracao(Date a02_dt_ultima_alteracao) {
		this.a02_dt_ultima_alteracao = a02_dt_ultima_alteracao;
	}
	public String getA02_nome() {
		return a02_nome;
	}
	public void setA02_nome(String a02_nome) {
		this.a02_nome = a02_nome;
	}
	public String getA02_usuario() {
		return a02_usuario;
	}
	public void setA02_usuario(String a02_usuario) {
		this.a02_usuario = a02_usuario;
	}
	public String getA02_senha() {
		return a02_senha;
	}
	public void setA02_senha(String a02_senha) {
		this.a02_senha = a02_senha;
	}
	public String getA02_codigo_link() {
		return a02_codigo_link;
	}
	public void setA02_codigo_link(String a02_codigo_link) {
		this.a02_codigo_link = a02_codigo_link;
	}
	public String getA02_email() {
		return a02_email;
	}
	public void setA02_email(String a02_email) {
		this.a02_email = a02_email;
	}
	public int getA02_status() {
		return a02_status;
	}
	public void setA02_status(int a02_status) {
		this.a02_status = a02_status;
	}
	
}
