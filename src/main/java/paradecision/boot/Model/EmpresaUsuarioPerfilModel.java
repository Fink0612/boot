package paradecision.boot.Model;

import java.sql.Date;

public class EmpresaUsuarioPerfilModel {

		private long 	a01_codigo;
		private long 	a02_codigo;
		private Date 	a03_dt_cadastro;
		private int 	a03_perfil_paraviverbem;
		private int 	a03_perfil_administrador;
		private int 	a03_perfil_chefe;
		private int 	a03_perfil_padrao;
		
		public long getA01_codigo() {
			return a01_codigo;
		}
		public void setA01_codigo(long a01_codigo) {
			this.a01_codigo = a01_codigo;
		}
		public long getA02_codigo() {
			return a02_codigo;
		}
		public void setA02_codigo(long a02_codigo) {
			this.a02_codigo = a02_codigo;
		}
		public Date getA03_dt_cadastro() {
			return a03_dt_cadastro;
		}
		public void setA03_dt_cadastro(Date a03_dt_cadastro) {
			this.a03_dt_cadastro = a03_dt_cadastro;
		}
		public int getA03_perfil_paraviverbem() {
			return a03_perfil_paraviverbem;
		}
		public void setA03_perfil_paraviverbem(int a03_perfil_paraviverbem) {
			this.a03_perfil_paraviverbem = a03_perfil_paraviverbem;
		}
		public int getA03_perfil_administrador() {
			return a03_perfil_administrador;
		}
		public void setA03_perfil_administrador(int a03_perfil_administrador) {
			this.a03_perfil_administrador = a03_perfil_administrador;
		}
		public int getA03_perfil_chefe() {
			return a03_perfil_chefe;
		}
		public void setA03_perfil_chefe(int a03_perfil_chefe) {
			this.a03_perfil_chefe = a03_perfil_chefe;
		}
		public int getA03_perfil_padrao() {
			return a03_perfil_padrao;
		}
		public void setA03_perfil_padrao(int a03_perfil_padrao) {
			this.a03_perfil_padrao = a03_perfil_padrao;
		}
		
}
