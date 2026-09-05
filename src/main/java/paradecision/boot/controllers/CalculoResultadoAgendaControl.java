package paradecision.boot.controllers;

import java.util.ArrayList;

import Pck_Model.AgendaFatoresModel;
import Pck_Model.AgendaModel;
import Pck_Model.AgendaPareceresModel;
import Pck_Model.FatorModel;
import Pck_Model.ParecerFatorUsuarioModel;
import Pck_Model.UsuarioModel;
import Pck_Persistencia.AgendaFatoresPersistencia;
import Pck_Persistencia.AgendaPareceresPersistencia;
import Pck_Persistencia.AgendaUsuariosPersistencia;

public class CalculoResultadoAgendaControl {

	// ###### Atributos Privados ######
	private int qtdUsuarios = 0;
	private int qtdFatores = 0;
	private int qtdGrupos = 0;
	private int qtdPareceres = 0;
	
	private double valLimDecisao = 50;

	// matriz[U][3]
	private long[][] matrizIndUsuarios; // matriz{{ind_usu, ind_grup, cod_usu}, { idem } ...}
	// matriz[F][2]
	private long[][] matrizIndFatores; // matriz{{ind_fat, cod_fat}, { idem }, ...}

	// ###### Objetos de Base de Dados ######
	private AgendaModel oAgendaModel; // precisa apenas do c�digo da Agenda para este C�lculo
	private AgendaPareceresModel oAgendaPareceresModel;

	private ArrayList<UsuarioModel> arrUsuariosModel = new ArrayList<UsuarioModel>();
	private ArrayList<FatorModel> arrFatoresModel = new ArrayList<FatorModel>();

	private AgendaFatoresModel oAgendaFatoresModel; // utilizado no c�lculo dos Graus
	private AgendaFatoresControl oAgendaFatoresControl; // utilizado no c�lculo dos Graus
	private AgendaFatoresPersistencia oAgendaFatoresPersistencia;
	private AgendaUsuariosPersistencia oAgendaUsuariosPersistencia;
	private AgendaPareceresPersistencia oAgendaPareceresPersistencia;

	// ###### Matrizes que servir�o em v�rios momentos ######
	// matriz[F][G][U] - Matrizes Iniciais
	private double[][][] matCerteza; // Matriz com todos os valores de Certeza (da Agenda)
	private double[][][] matContradicao; // Matriz com todos os valores de Contradi��o (da Agenda)
	// matriz[F][G] - Para Maximiza��o
	private double[][] matrizMaxCert; // matriz result da Maximiza��o {{maiCert, maiCert, ...}, { idem }, ...}
	private double[][] matrizMaxCont; // matriz result da Maximiza��o {{menCont, menCont, ...}, { idem }, ...}
	// matriz[F] - Para Minimiza��o
	private double[] matrizMinCert; // matriz result da Minimiza��o {menCert, menCert, ...}
	private double[] matrizMinCont; // matriz result da Minimiza��o {maiCont, maiCont, ...}
	// matriz[F] - Para C�lculo do Grau (para cada Fator)
	private double[] matrizGrauCert; // matriz result de Graus de Certezas {grauF1, grauF2, ...}
	private double[] matrizGrauIncert; // matriz result de Graus de Incertezas {grauF1, grauF2, ...}
	// guardando os graus calculados para a Agenda
	double grauCertAgenda = 0;
	double grauIncertAgenda = 0;

	// ###### M�todos P�blicos ######
	public String geraResultados(AgendaModel auxAgendaModel, int tipoAmostra) {
		this.oAgendaModel = auxAgendaModel;
		String ret = "NOK";
		if (!(oAgendaModel == null)) {
			if (oAgendaModel.getA04_codigo() > 0) {
				// System.out.println("calc 00");
				ret = this.getArrFatoresModel();
				if (ret.equals("OK")) {
					// System.out.println("calc 01");
					ret = this.getArrEspecialistasModel();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 02");
					ret = this.getArrPareceresAgenda();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 03");
					ret = this.montaMatrizesIndices();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 04");
					ret = this.montaMatriz_Fase01();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 05");
					ret = this.realizaMaximizacao();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 06");
					ret = this.realizaMinimizacao();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 07");
					ret = this.calcularGrausFatores();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 08");
					ret = this.calcularGrausAgenda();
				}
				if (ret.equals("OK")) {
					// System.out.println("calc 09");
					ret = this.atualizarAgendaEFatores();
				}
				if (ret.equals("OK") && tipoAmostra == 1) {
					this.mostraMatrizesValores();
				}
			}
		}
		return ret;
	}

	// ###### M�todos GET (P�blicos) ######
	public void setValLimDecisao(double d) {
		valLimDecisao = d;
	}

	public int getQtdFatores() {
		return qtdFatores;
	}

	public int getQtdGrupos() {
		return qtdGrupos;
	}

	public int getQtdPareceres() {
		return qtdPareceres;
	}

	// ###### M�todos SET (P�blicos) ######
	public int getQtdUsuarios() {
		return qtdUsuarios;
	}

	// ###### M�todos Privados ######
	private String getArrFatoresModel() {
		String ret = "NOK";
		oAgendaFatoresPersistencia = new AgendaFatoresPersistencia();
		try {
			arrFatoresModel = oAgendaFatoresPersistencia.getArrFatoresModel(oAgendaModel);
			if (!(arrFatoresModel == null)) {
				if (arrFatoresModel.size() > 0)
					ret = "OK";
			}
		} catch (Exception e) {
		}
		return ret;
	}

	private String getArrEspecialistasModel() {
		String ret = "NOK";
		oAgendaUsuariosPersistencia = new AgendaUsuariosPersistencia();
		try {
			arrUsuariosModel = oAgendaUsuariosPersistencia.getArrEspecialistasModel(oAgendaModel);
			if (!(arrUsuariosModel == null)) {
				if (arrUsuariosModel.size() > 0)
					ret = "OK";
			}
		} catch (Exception e) {
		}
		return ret;
	}

	private String getArrPareceresAgenda() {
		String ret = "NOK";
		oAgendaPareceresModel = new AgendaPareceresModel();
		oAgendaPareceresPersistencia = new AgendaPareceresPersistencia();
		try {
			// System.out.println("oi1");
			oAgendaPareceresModel.setoAgendaModel(oAgendaModel);
			oAgendaPareceresModel = oAgendaPareceresPersistencia.selectPareceresDaAgenda(oAgendaPareceresModel);
			// System.out.println("oi2");
			qtdPareceres = oAgendaPareceresModel.getArrParecerFatorUsuarioModel().size();
			// System.out.println("oi3");
			if (qtdPareceres > 0)
				ret = "OK";
		} catch (Exception e) {
		}
		return ret;
	}

	private String montaMatrizesIndices() {
		String ret = "NOK";
		int[][] padrao = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 0 }, { 2, 1 } };
		// -------------------------------------------
		qtdUsuarios = arrUsuariosModel.size();
		qtdFatores = arrFatoresModel.size();
		// -------------------------------------------
		matrizIndUsuarios = new long[qtdUsuarios][3];
		matrizIndFatores = new long[qtdFatores][2];
		// -------------------------------------------
		qtdGrupos = 2;
		if (qtdUsuarios > 6) {
			if (qtdUsuarios % 3 == 0)
				qtdGrupos = qtdUsuarios / 3;
			else
				qtdGrupos = qtdUsuarios / 3 + 1;
		}
		// -------------------------------------------
		if (qtdUsuarios > 0 && qtdFatores > 0) {
			try {
				int numU = -1;
				int numG = 1;
				for (int ii = 0; ii < qtdUsuarios; ii++) {
					if (ii <= 6) {
						matrizIndUsuarios[ii][0] = padrao[ii][0]; // Indice Usuario
						matrizIndUsuarios[ii][1] = padrao[ii][1]; // Indice Grupo
						matrizIndUsuarios[ii][2] = arrUsuariosModel.get(ii).getA02_codigo(); // Cod Usu
					} else {
						numU++;
						if (numU == 0)
							numG++;
						matrizIndUsuarios[ii][0] = numU; // Indice Usuario
						matrizIndUsuarios[ii][1] = numG; // Indice Grupo
						matrizIndUsuarios[ii][2] = arrUsuariosModel.get(ii).getA02_codigo(); // Cod Usu
						if (numU == 2)
							numU = -1;
					}
				}
				// -------------------------------------------
				for (int ii = 0; ii < qtdFatores; ii++) {
					matrizIndFatores[ii][0] = ii; // Indice Fator
					matrizIndFatores[ii][1] = arrFatoresModel.get(ii).getA06_codigo();
				}
				ret = "OK";
			} catch (Exception e) {
			}
		}
		return ret;
	}

	private String montaMatriz_Fase01() {
		String ret = "NOK";
		matCerteza = new double[qtdFatores][qtdGrupos][3];
		matContradicao = new double[qtdFatores][qtdGrupos][3];
		this.preparaMatriz(matCerteza, -10);
		this.preparaMatriz(matContradicao, 110);
		// System.out.println("qtdUsuarios: " + qtdUsuarios);
		// System.out.println("qtdFatores: " + qtdFatores);
		// System.out.println("qtdGrupos: " + qtdGrupos);
		// System.out.println("qtdPareceres: " + qtdPareceres);
		if (qtdPareceres > 0) {
			try {
				ArrayList<UsuarioModel> arrU = oAgendaPareceresModel.getArrUsuarioModel();
				ArrayList<ParecerFatorUsuarioModel> arrP = oAgendaPareceresModel.getArrParecerFatorUsuarioModel();
				long codU = 0;
				long codF = 0;
				int posU = 0;
				int posG = 0;
				int posF = 0;
				int[] posUG = new int[2];
				for (int ii = 0; ii < qtdPareceres; ii++) {
					// pegando as posicoes
					codU = arrU.get(ii).getA02_codigo();
					posUG = this.getIndiceUsuarioGupo(codU);
					posU = posUG[0];
					posG = posUG[1];
					codF = arrP.get(ii).getA06_codigo();
					posF = this.getIndiceFator(codF);
					// pegando os valores de certeza e contradicao
					if (posU > -1) { // s� para usu�rio Especialista
						// System.out.println("[F:" + codF + "][G][U]: " + "[" + posF + "]" + "[" + posG
						// + "]" + "[" + posU + "]");
						matCerteza[posF][posG][posU] = arrP.get(ii).getA07_certeza();
						matContradicao[posF][posG][posU] = arrP.get(ii).getA07_contradicao();
					}
				}
				ret = "OK";
			} catch (Exception e) {
			}
		}
		return ret;
	}

	private String realizaMaximizacao() {
		String ret = "NOK";
		// matriz[F][G]
		matrizMaxCert = new double[qtdFatores][qtdGrupos];
		matrizMaxCont = new double[qtdFatores][qtdGrupos];
		double maior = 0;
		double menor = 0;
		try {
			for (int f = 0; f < qtdFatores; f++) {
				for (int g = 0; g < qtdGrupos; g++) {
					maior = matCerteza[f][g][0];
					menor = matContradicao[f][g][0];
					for (int u = 0; u < 3; u++) {
						if (matCerteza[f][g][u] > maior)
							maior = matCerteza[f][g][u];
						if (matContradicao[f][g][u] < menor)
							menor = matContradicao[f][g][u];
					}
					matrizMaxCert[f][g] = maior;
					matrizMaxCont[f][g] = menor;
				}
			}
			ret = "OK";
		} catch (Exception e) {
		}
		return ret;
	}

	private String realizaMinimizacao() {
		String ret = "NOK";
		// matriz[F]
		matrizMinCert = new double[qtdFatores];
		matrizMinCont = new double[qtdFatores];
		double menor = 0;
		double maior = 0;
		try {
			for (int f = 0; f < qtdFatores; f++) {
				menor = matrizMaxCert[f][0];
				maior = matrizMaxCont[f][0];
				for (int g = 0; g < qtdGrupos; g++) {
					if (matrizMaxCert[f][g] < menor)
						menor = matrizMaxCert[f][g];
					if (matrizMaxCont[f][g] > maior)
						maior = matrizMaxCont[f][g];
				}
				matrizMinCert[f] = menor;
				matrizMinCont[f] = maior;
			}
			ret = "OK";
		} catch (Exception e) {
		}
		return ret;
	}

	private String calcularGrausFatores() {
		String ret = "NOK";
		matrizGrauCert = new double[qtdFatores];
		matrizGrauIncert = new double[qtdFatores];
		try {
			oAgendaFatoresModel = new AgendaFatoresModel();
			oAgendaFatoresControl = new AgendaFatoresControl();
			FatorModel oFatorModel;
			ArrayList<FatorModel> arrFatorModel = new ArrayList<FatorModel>();
			for (int f = 0; f < qtdFatores; f++) {
				oFatorModel = new FatorModel();
				oFatorModel.setA06_codigo(matrizIndFatores[f][1]);
				matrizGrauCert[f] = matrizMinCert[f] - matrizMinCont[f];
				matrizGrauIncert[f] = matrizMinCert[f] + matrizMinCont[f] - 100;
				oFatorModel.setA06_certeza_resultante_fator(matrizGrauCert[f]);
				oFatorModel.setA06_contradicao_resultante_fator(matrizGrauIncert[f]);
				oFatorModel.setA06_resultado_fator(this.calcularResultadosFinais(matrizGrauCert[f], matrizGrauIncert[f]));
				arrFatorModel.add(oFatorModel);
			}
			oAgendaFatoresModel.setArrFatorModel(arrFatorModel);
			ret = "OK";
		} catch (Exception e) {
		}
		return ret;
	}

	private String calcularGrausAgenda() {
		String ret = "NOK";
		grauCertAgenda = 0;
		grauIncertAgenda = 0;
		try {
			// Realizando as Somat�rias
			for (int f = 0; f < qtdFatores; f++) {
				grauCertAgenda += matrizGrauCert[f];
				grauIncertAgenda += matrizGrauIncert[f];
			}
			// Calculando as M�dias
			grauCertAgenda /= (double) qtdFatores;
			// System.out.println(grauCertAgenda);
			grauIncertAgenda /= (double) qtdFatores;
			// System.out.println(grauIncertAgenda);
			// Guardando valores na Agenda
			oAgendaModel.setA04_certeza_resultado(grauCertAgenda);
			oAgendaModel.setA04_contradicao_resultado(grauIncertAgenda);
			oAgendaModel.setA04_resultado(this.calcularResultadosFinais(grauCertAgenda, grauIncertAgenda));
			oAgendaFatoresModel.setoAgendaModel(oAgendaModel);
			ret = "OK";
		} catch (Exception e) {
		}
		return ret;
	}

	private String calcularResultadosFinais(double gCert, double gIncert) {
		String ret = "Invi�vel";
		if (gCert > Math.abs(valLimDecisao)) {
			ret = "Vi�vel";
		} else if (gCert > 0) {
			ret = "Tend�ncia a ser Vi�vel";
		} else if (gCert < (-1)*Math.abs(valLimDecisao)) {
			ret = "Invi�vel";
		} else if (gCert <= 0) {
			ret = "Tend�ncia a ser Invi�vel";
		}
		if (Math.abs(gIncert) > Math.abs(valLimDecisao)) {
			ret = "Invi�vel";
		} else if (Math.abs(gIncert) > 10) {
			ret += ", com risco de Insucesso.";
		}
		return ret;
	}

	private String atualizarAgendaEFatores() {
		String ret = "NOK";
		try {
			ret = oAgendaFatoresControl.updateGrausFatoresDaAgenda(oAgendaFatoresModel);
		} catch (Exception e) {
		}
		return ret;
	}

	// ###### M�todos Auxiliares ######
	private int[] getIndiceUsuarioGupo(long codUsu) {
		int[] posic = { -1, -1 }; // (posUsuario, posGrupo)
		try {
			for (int ii = 0; ii < qtdUsuarios; ii++) {
				if (codUsu == matrizIndUsuarios[ii][2]) {
					posic[0] = (int) matrizIndUsuarios[ii][0];
					posic[1] = (int) matrizIndUsuarios[ii][1];
					break;
				}
			}
		} catch (Exception e) {
		}
		return posic;
	}

	private int getIndiceFator(long codFat) {
		int posic = -1;
		try {
			for (int ii = 0; ii < qtdFatores; ii++) {
				if (codFat == matrizIndFatores[ii][1]) {
					posic = (int) matrizIndFatores[ii][0];
					break;
				}
			}
		} catch (Exception e) {
		}
		return posic;
	}

	private void preparaMatriz(double[][][] matriz, double valor) {
		try {
			int x = matriz.length;
			int y = matriz[0].length;
			int z = matriz[0][0].length;
			for (int xx = 0; xx < x; xx++) {
				for (int yy = 0; yy < y; yy++) {
					for (int zz = 0; zz < z; zz++) {
						matriz[xx][yy][zz] = valor;
					}
				}
			}
		} catch (Exception e) {
		}
	}

	private void mostraMatrizesValores() {
		String txtCert = "";
		String txtContr = "";
		for (int f = 0; f < qtdFatores; f++) {
			txtCert += "[";
			txtContr += "[";
			for (int g = 0; g < qtdGrupos; g++) {
				txtCert += "[";
				txtContr += "[";
				for (int u = 0; u < 3; u++) {
					String sCert = String.format("%,.1f", matCerteza[f][g][u]);
					txtCert += sCert + ";\t";
					String sContr = String.format("%,.1f", matContradicao[f][g][u]);
					txtContr += sContr + ";\t";
				}
				txtCert += "]";
				txtContr += "]";
			}
			txtCert += "]\n";
			txtContr += "]\n";
		}
		System.out.println("Valor de Certeza");
		System.out.println(txtCert);
		System.out.println("Valor de Contradi��o");
		System.out.println(txtContr);
		// -----------------------------------------------------
		txtCert = "";
		txtContr = "";
		for (int f = 0; f < qtdFatores; f++) {
			txtCert += "[";
			txtContr += "[";
			for (int g = 0; g < qtdGrupos; g++) {
				txtCert += "[";
				txtContr += "[";
				String sCert = String.format("%,.1f", matrizMaxCert[f][g]);
				txtCert += sCert + "\t";
				String sContr = String.format("%,.1f", matrizMaxCont[f][g]);
				txtContr += sContr + "\t";
				txtCert += "]";
				txtContr += "]";
			}
			txtCert += "]\n";
			txtContr += "]\n";
		}
		System.out.println("MAXIMIZA��O:");
		System.out.println("Maior Certeza");
		System.out.println(txtCert);
		System.out.println("Menor Contradi��o");
		System.out.println(txtContr);
		// -----------------------------------------------------
		txtCert = "";
		txtContr = "";
		for (int f = 0; f < qtdFatores; f++) {
			txtCert += "[";
			txtContr += "[";
			String sCert = String.format("%,.1f", matrizMinCert[f]);
			txtCert += sCert + "\t";
			String sContr = String.format("%,.1f", matrizMinCont[f]);
			txtContr += sContr + "\t";
			txtCert += "]\n";
			txtContr += "]\n";
		}
		System.out.println("MINIMIZA��O:");
		System.out.println("Menor Certeza");
		System.out.println(txtCert);
		System.out.println("Maior Contradi��o");
		System.out.println(txtContr);
		// -----------------------------------------------------
		txtCert = "";
		txtContr = "";
		for (int f = 0; f < qtdFatores; f++) {
			txtCert += "[";
			txtContr += "[";
			String sCert = String.format("%,.1f", matrizGrauCert[f]);
			txtCert += sCert + "\t";
			String sContr = String.format("%,.1f", matrizGrauIncert[f]);
			txtContr += sContr + "\t";
			txtCert += "]\n";
			txtContr += "]\n";
		}
		System.out.println("GRAU POR FATOR:");
		System.out.println("Grau de Certeza");
		System.out.println(txtCert);
		System.out.println("Grau de Incerteza");
		System.out.println(txtContr);
		// -----------------------------------------------------
		txtCert = "";
		txtContr = "";
		String sCert = String.format("%,.1f", grauCertAgenda);
		txtCert += sCert;
		String sContr = String.format("%,.1f", grauIncertAgenda);
		txtContr += sContr;
		System.out.println("GRAU DA AGENDA:");
		System.out.println("Grau de Certeza");
		System.out.println(txtCert);
		System.out.println("Grau de Incerteza");
		System.out.println(txtContr);
	}
}
