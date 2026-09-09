package paradecision.boot;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import paradecision.boot.modulos.compartilhado.infra.*;
class SupabaseBancoDadosTests {
  HttpServer servidor;
  @AfterEach void fechar(){if(servidor!=null)servidor.stop(0);}
  @Test void filtraApiEMantemCasasDecimaisENulos() throws Exception {
    var caminho=new AtomicReference<String>();var apiKey=new AtomicReference<String>();
    servidor=HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);
    servidor.createContext("/rest/v1/",e->{caminho.set(e.getRequestURI().toString());apiKey.set(e.getRequestHeaders().getFirst("apikey"));byte[] bytes="[{\"a06_codigo\":4,\"a06_certeza_resultante_fator\":0.375,\"a06_contradicao_resultante_fator\":0.125,\"a06_dt_cadastro\":\"2026-09-09T10:30:00\"}]".getBytes(StandardCharsets.UTF_8);e.sendResponseHeaders(200,bytes.length);e.getResponseBody().write(bytes);e.close();});servidor.start();
    var banco=new SupabaseBancoDados("http://127.0.0.1:"+servidor.getAddress().getPort(),"sb_secret_teste");var lista=banco.listar("fator_06",Map.of("a04_codigo",7));
    assertTrue(caminho.get().contains("a04_codigo=eq.7"));assertEquals("sb_secret_teste",apiKey.get());var f=Mapeamento.Fator(lista.getFirst());assertEquals(.375,f.getA06_certeza_resultante_fator());assertEquals("2026-09-09",f.getA06_dt_cadastro().toString());
    var parecer=Mapeamento.ParecerFatorUsuario(new Registro(Registro.campos("a07_certeza",null,"a07_contradicao",0)));assertNull(parecer.getStr_a07_certeza());assertEquals("0",parecer.getStr_a07_contradicao());
  }
  @Test void falhaHttpNaoViraListaVazia() throws Exception {
    servidor=HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);servidor.createContext("/rest/v1/",e->{e.sendResponseHeaders(403,-1);e.close();});servidor.start();
    var banco=new SupabaseBancoDados("http://127.0.0.1:"+servidor.getAddress().getPort(),"teste");assertThrows(FalhaPersistencia.class,()->banco.listar("usuario_02",Map.of("a02_codigo",1)));
  }
}
