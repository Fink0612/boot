package Pck_Util;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public abstract class MetodosUteis {

	public static String vChave = "HOENIRTYRMAGNISNOTRAVARFORSETISKULDNEHALLENIAKVASIR";
	
	public static String gerarCodigo(int tam) {
		Random rnd = new Random();
		String cod = "";
		String txtAux = "";
		int qtd = (tam / 10);
		if (tam % 10 > 0) qtd++;
		for (int i = 0; i < qtd; i++) {
			txtAux = Double.toString(rnd.nextDouble());
			txtAux = txtAux.replace("0.", "");
			cod += txtAux;
		}
		cod = cod.substring(0, tam);
		return cod;
	}
	
	public static String padronizarMaiusculoCE (String txt) {
		// Obs.: padrão COM espaços
		// retirando espaços iniciais e finais, e deixando "CAIXA-ALTA"
		String auxTxt = txt.toUpperCase().trim();
		// retirando espaços extras internos a String
		String espExt = "  ";
		int posic = auxTxt.indexOf(espExt);
		while (posic >= 0) {
			auxTxt = auxTxt.replaceAll(espExt, " ");
			posic = auxTxt.indexOf(espExt);
		}
		return auxTxt;
	}

	public static String padronizarMinusculoSE (String txt) {
		// Obs.: padrão SEM espaços
		// retirando espaços iniciais e finais, e deixando "caixa-baixa"
		String auxTxt = txt.toLowerCase().trim();
		// retirando espaços internos a String
		String espExt = " ";
		int posic = auxTxt.indexOf(espExt);
		while (posic >= 0) {
			auxTxt = auxTxt.replaceAll(espExt, "");
			posic = auxTxt.indexOf(espExt);
		}
		return auxTxt;
	}
	
	public static String padronizarMinusculoCE (String txt) {
		// Obs.: padrão COM espaços
		// retirando espaços iniciais e finais, e deixando "caixa-baixa"
		String auxTxt = txt.toLowerCase().trim();
		// retirando espaços extras internos a String
		String espExt = "  ";
		int posic = auxTxt.indexOf(espExt);
		while (posic >= 0) {
			auxTxt = auxTxt.replaceAll(espExt, " ");
			posic = auxTxt.indexOf(espExt);
		}
		return auxTxt;
	}

	public static String padronizarEspacos (String txt) {
		// Obs.: padrão COM espaços
		// retirando espaços iniciais e finais
		String auxTxt = txt.trim();
		// retirando espaços extras internos a String
		String espExt = "  ";
		int posic = auxTxt.indexOf(espExt);
		while (posic >= 0) {
			auxTxt = auxTxt.replaceAll(espExt, " ");
			posic = auxTxt.indexOf(espExt);
		}
		return auxTxt;
	}

	public static java.sql.Date retornaDate (String strDt, String formatDt) {
		// Neste caso, se a data estiver em formato errado, retornará nulo
		// Exemplo de chamada a este método:
		// Date variavel_Date = MetodosUteis.retornaDate(variavel_String_de_Data, "yyyy-MM-dd");
		java.util.Date dataUtil = new java.util.Date();
		java.sql.Date dataSql = null;
        try {
        	dataUtil = new SimpleDateFormat(formatDt).parse(strDt);
        	dataSql = new java.sql.Date(dataUtil.getTime());
		} catch (ParseException e) {
		}
        return dataSql;
	}

	public static java.sql.Date retornaDataAgora () {
		// Retorna a data atual (de hoje)
		java.util.Date dataUtil = new java.util.Date();
		java.sql.Date dataSql = null;
        try {
        	dataSql = new java.sql.Date(dataUtil.getTime());
		} catch (Exception e) {
		}
        return dataSql;
	}
	
	public static String getDatNowBD () {
		String ret = "";
		// Retorna a data atual (de hoje)
		java.util.Date dataUtil = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ret = formatter.format(dataUtil);
        return ret;
	}
	
	public static int retornaInt (String strVal) {
		int ret = -1;
		try {
			int numVal = Integer.parseInt(strVal);
			ret = numVal;
		} catch (Exception e) {
		}
		return ret;
	}

	public static long retornaLong (String strVal) {
		long ret = -1;
		try {
			long numVal = Long.parseLong(strVal);
			ret = numVal;
		} catch (Exception e) {
		}
		return ret;
	}

	public static double retornaDouble (String strVal) {
		double ret = -1;
		try {
			double numVal = Double.parseDouble(strVal);
			ret = numVal;
		} catch (Exception e) {
		}
		return ret;
	}

	public static String getCorNivelCert (int numParec) {
		String ret = "";
		if (numParec <= 3) {
			ret = "vm"; // vermelho
		} else if (numParec <= 5) {
			ret = "lr"; // laranja
		} else if (numParec <= 8) {
			ret = "vd"; // verde
		} else if (numParec <= 10) {
			ret = "az"; // azul
		}
		return ret;
	}
	
	public static String getCorNivelContr (int numParec) {
		String ret = "";
		if (numParec >= 7) {
			ret = "vm"; // vermelho
		} else if (numParec >= 5) {
			ret = "lr"; // laranja
		} else if (numParec >= 2) {
			ret = "vd"; // verde
		} else if (numParec >= 0) {
			ret = "az"; // azul
		}
		return ret;
	}
	
	public static String retornaTxtStatusAgenda (String sttAg) {
		String ret = "";
		if (sttAg == null) {
			ret = "Aguardando Encaminhamento";
		} else if (sttAg.equals("")) {
			ret = "Aguardando Encaminhamento";
		} else if (sttAg.equals("0")) {
			ret = "Aguardando Encaminhamento";
		} else if (sttAg.equals("1")) {
			ret = "Aguardando Fatores";
		} else if (sttAg.equals("2")) {
			ret = "Aguardando Pareceres";
		} else if (sttAg.equals("9")) {
			ret = "Fechada";
		} else {
			ret = "Indefinido";
		}
		return ret;
	}
	
	public static String getIpAddress() {
		String ret = "";
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
			ret = addr.getHostAddress();
		} catch (Exception e) {
			ret = "Não foi possível resgatar o IP desta máquina.";
		}
		return ret;
	}
	
}
