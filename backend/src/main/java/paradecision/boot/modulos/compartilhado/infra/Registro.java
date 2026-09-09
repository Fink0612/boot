package paradecision.boot.modulos.compartilhado.infra;

import java.sql.Date;
import java.util.*;

public record Registro(Map<String,Object> valores) {
  public String texto(String chave) { var v=valores.get(chave); return v==null?null:v.toString(); }
  public long numero(String chave) { var v=valores.get(chave); return v==null?0:((Number)v).longValue(); }
  public int inteiro(String chave) { return (int)numero(chave); }
  public double decimal(String chave) { var v=valores.get(chave); return v==null?0:((Number)v).doubleValue(); }
  public Date data(String chave) { var v=texto(chave); return v==null?null:Date.valueOf(v.substring(0,10)); }
  public static Map<String,Object> campos(Object... pares) {
    Map<String,Object> dados=new LinkedHashMap<>();
    for(int i=0;i<pares.length;i+=2) dados.put((String)pares[i],pares[i+1]);
    return dados;
  }
}
