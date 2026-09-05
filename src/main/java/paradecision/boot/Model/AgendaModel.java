package paradecision.boot.Model;

import java.sql.Date;

public class AgendaModel {

	private long 	a04_codigo;
	private String 	a04_titulo;
	private String 	a04_descricao;
	private int 	a04_status_dt_limite;
	private Date 	a04_data_limite;
	private String 	a04_resultado;
	private double 	a04_certeza_resultado;
	private double 	a04_contradicao_resultado;
	private Date 	a04_dt_cadastro;
	private Date 	a04_dt_ultima_alteracao;
	private long 	a01_codigo;
	private int 	a04_status;
	
	public long getA04_codigo() {
		return a04_codigo;
	}
	public void setA04_codigo(long a04_codigo) {
		this.a04_codigo = a04_codigo;
	}
	public String getA04_titulo() {
		return a04_titulo;
	}
	public void setA04_titulo(String a04_titulo) {
		this.a04_titulo = a04_titulo;
	}
	public String getA04_descricao() {
		return a04_descricao;
	}
	public void setA04_descricao(String a04_descricao) {
		this.a04_descricao = a04_descricao;
	}
	public int getA04_status_dt_limite() {
		return a04_status_dt_limite;
	}
	public void setA04_status_dt_limite(int a04_status_dt_limite) {
		this.a04_status_dt_limite = a04_status_dt_limite;
	}
	public Date getA04_data_limite() {
		return a04_data_limite;
	}
	public void setA04_data_limite(Date a04_data_limite) {
		this.a04_data_limite = a04_data_limite;
	}
	public String getA04_resultado() {
		return a04_resultado;
	}
	public void setA04_resultado(String a04_resultado) {
		this.a04_resultado = a04_resultado;
	}
	public double getA04_certeza_resultado() {
		return a04_certeza_resultado;
	}
	public void setA04_certeza_resultado(double a04_certeza_resultado) {
		this.a04_certeza_resultado = a04_certeza_resultado;
	}
	public double getA04_contradicao_resultado() {
		return a04_contradicao_resultado;
	}
	public void setA04_contradicao_resultado(double a04_contradicao_resultado) {
		this.a04_contradicao_resultado = a04_contradicao_resultado;
	}
	public Date getA04_dt_cadastro() {
		return a04_dt_cadastro;
	}
	public void setA04_dt_cadastro(Date a04_dt_cadastro) {
		this.a04_dt_cadastro = a04_dt_cadastro;
	}
	public Date getA04_dt_ultima_alteracao() {
		return a04_dt_ultima_alteracao;
	}
	public void setA04_dt_ultima_alteracao(Date a04_dt_ultima_alteracao) {
		this.a04_dt_ultima_alteracao = a04_dt_ultima_alteracao;
	}
	public long getA01_codigo() {
		return a01_codigo;
	}
	public void setA01_codigo(long a01_codigo) {
		this.a01_codigo = a01_codigo;
	}
	public int getA04_status() {
		return a04_status;
	}
	public void setA04_status(int a04_status) {
		this.a04_status = a04_status;
	}
	
}
