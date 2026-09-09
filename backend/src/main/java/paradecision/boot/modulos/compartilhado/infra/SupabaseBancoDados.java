package paradecision.boot.modulos.compartilhado.infra;

import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.core.type.TypeReference;

/** Supabase Data API/PostgREST por HTTPS. A chave nunca sai do backend. */
@Component
@ConditionalOnProperty(name="app.banco",havingValue="supabase")
public class SupabaseBancoDados implements BancoDados {
  private final String url;
  private final String chave;
  private final HttpClient http=HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
  private final JsonMapper json=JsonMapper.builder().build();
  public SupabaseBancoDados(@Value("${supabase.url:}") String url,@Value("${supabase.secret-key:}") String chave) {
    this.url=url.replaceAll("/+$","");this.chave=chave;
  }
  private static String encode(String s) { return URLEncoder.encode(s,StandardCharsets.UTF_8); }
  private static String recurso(String tabela,Map<String,Object> filtros) {
    if(!tabela.matches("[a-z][a-z0-9_]*")) throw new IllegalArgumentException("Tabela inválida");
    StringJoiner query=new StringJoiner("&",tabela+"?","");
    filtros.forEach((k,v)->{if(!k.matches("[a-z][a-z0-9_]*"))throw new IllegalArgumentException("Coluna inválida");query.add(k+"="+encode(v==null?"is.null":"eq."+v));});
    return query.toString();
  }
  public List<Registro> listar(String tabela,Map<String,Object> filtros) {
    List<Registro> todos=new ArrayList<>();int inicio=0;
    while(true) {
      var pagina=enviar("GET",recurso(tabela,filtros),null,inicio);
      todos.addAll(pagina);if(pagina.size()<500)return todos;inicio+=pagina.size();
    }
  }
  public Registro inserir(String tabela,Map<String,Object> dados) { return enviar("POST",recurso(tabela,Map.of()),dados,0).getFirst(); }
  public void atualizar(String tabela,Map<String,Object> filtros,Map<String,Object> dados) { exigirFiltro(filtros);enviar("PATCH",recurso(tabela,filtros),dados,0); }
  public void excluir(String tabela,Map<String,Object> filtros) { exigirFiltro(filtros);enviar("DELETE",recurso(tabela,filtros),null,0); }
  private static void exigirFiltro(Map<String,Object> filtros) { if(filtros.isEmpty())throw new IllegalArgumentException("Informe filtros para a operação"); }
  private List<Registro> enviar(String metodo,String recurso,Map<String,Object> dados,int inicio) {
    if(url.isBlank()||chave.isBlank()) throw new FalhaPersistencia("Configure SUPABASE_URL e SUPABASE_SECRET_KEY no backend.");
    if(!url.startsWith("https://")&&!url.startsWith("http://localhost:")&&!url.startsWith("http://127.0.0.1:")) throw new FalhaPersistencia("Supabase exige HTTPS.");
    try {
      Map<String,Object> corpo=new LinkedHashMap<>();if(dados!=null)dados.forEach((k,v)->corpo.put(k,v instanceof java.sql.Date?v.toString():v));
      var request=HttpRequest.newBuilder(URI.create(url+"/rest/v1/"+recurso)).timeout(Duration.ofSeconds(30))
          .header("apikey",chave).header("Content-Type","application/json").header("Prefer","return=representation");
      if(!chave.startsWith("sb_secret_"))request.header("Authorization","Bearer "+chave);
      if(metodo.equals("GET"))request.header("Range",inicio+"-"+(inicio+499));
      request.method(metodo,dados==null?HttpRequest.BodyPublishers.noBody():HttpRequest.BodyPublishers.ofString(json.writeValueAsString(corpo)));
      var resposta=http.send(request.build(),HttpResponse.BodyHandlers.ofString());
      if(resposta.statusCode()<200||resposta.statusCode()>=300) throw new FalhaPersistencia("Supabase recusou a operação (HTTP "+resposta.statusCode()+"). Verifique configuração e esquema.");
      if(resposta.body().isBlank())return List.of();
      List<Map<String,Object>> linhas=json.readValue(resposta.body(),new TypeReference<List<Map<String,Object>>>(){});
      return linhas.stream().map(Registro::new).toList();
    } catch(InterruptedException e) { Thread.currentThread().interrupt();throw new FalhaPersistencia(e); }
    catch(java.io.IOException e) { throw new FalhaPersistencia(e); }
  }
}
