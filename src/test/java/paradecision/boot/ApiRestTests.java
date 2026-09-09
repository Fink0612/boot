package paradecision.boot;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import paradecision.boot.modulos.usuarios.repository.UsuarioRepository;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.service.Senhas;
import paradecision.boot.modulos.empresas.repository.EmpresaUsuarioPerfilRepository;
import paradecision.boot.modulos.empresas.entity.EmpresaUsuarioPerfil;
import paradecision.boot.modulos.agendas.repository.*;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.compartilhado.infra.FalhaPersistencia;
@SpringBootTest(properties={"spring.config.import=optional:file:config/teste-inexistente.properties","app.banco=mysql"})
class ApiRestTests {
  @Autowired WebApplicationContext context;
  @MockitoBean UsuarioRepository usuarios;
  @MockitoBean EmpresaUsuarioPerfilRepository empresas;
  @MockitoBean AgendaRepository agendas;
  @MockitoBean AgendaUsuarioPerfilRepository participantes;
  MockMvc mvc;
  @BeforeEach void setup(){mvc=MockMvcBuilders.webAppContextSetup(context).build();}
  MockHttpSession sessao(){var s=new MockHttpSession();s.setAttribute("usuarioId",1L);return s;}
  @Test void exigeLoginEProtecaoDeEscrita() throws Exception {
    mvc.perform(get("/api/empresas")).andExpect(status().isUnauthorized()).andExpect(jsonPath("$.detail").value("Entre para continuar."));
    mvc.perform(post("/api/autenticacao/login").contentType("application/json").content("{\"login\":\"admin\",\"senha\":\"admin\"}")).andExpect(status().isForbidden());
    verifyNoInteractions(usuarios);
  }
  @Test void autenticaHashSemExporSenhaERegeneraSessao() throws Exception {
    var u=new Usuario();u.setA02_codigo(1);u.setA02_usuario("admin");u.setA02_nome("Administrador");u.setA02_status(1);u.setA02_senha(Senhas.codificar("admin"));when(usuarios.selectUserLogin(any())).thenReturn(u);
    var antiga=sessao();var result=mvc.perform(post("/api/autenticacao/login").session(antiga).header("X-Requested-With","XMLHttpRequest").contentType("application/json").content("{\"login\":\"admin\",\"senha\":\"admin\"}"))
      .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.senha").doesNotExist()).andReturn();
    assertTrue(antiga.isInvalid());assertEquals(1L,result.getRequest().getSession().getAttribute("usuarioId"));assertFalse(result.getResponse().getContentAsString().contains("pbkdf2"));
  }
  @Test void senhaErradaRetorna401() throws Exception {
    var u=new Usuario();u.setA02_codigo(1);u.setA02_senha("correta");u.setA02_status(1);when(usuarios.selectUserLogin(any())).thenReturn(u);
    mvc.perform(post("/api/autenticacao/login").header("X-Requested-With","XMLHttpRequest").contentType("application/json").content("{\"login\":\"admin\",\"senha\":\"errada\"}")).andExpect(status().isUnauthorized());
  }
  @Test void validaJsonAntesDeGravar() throws Exception {
    mvc.perform(post("/api/empresas/1/agendas").session(sessao()).header("X-Requested-With","XMLHttpRequest").contentType("application/json").content("{\"titulo\":\"\"}")).andExpect(status().isBadRequest());verifyNoInteractions(agendas);
  }
  @Test void naoConfiaEmPerfilDoCliente() throws Exception {
    var a=new Agenda();a.setA04_codigo(4);a.setA01_codigo(8);when(agendas.selectAgenda(any())).thenReturn(a);when(empresas.selectEmpresaUsuario(any())).thenReturn(null);
    mvc.perform(get("/api/agendas/4").session(sessao()).param("administrador","true")).andExpect(status().isForbidden());
  }
  @Test void ausenteRetorna404EFalhaDoBanco503() throws Exception {
    when(agendas.selectAgenda(any())).thenReturn(new Agenda());mvc.perform(get("/api/agendas/99").session(sessao())).andExpect(status().isNotFound());
    when(agendas.selectAgenda(any())).thenThrow(new FalhaPersistencia("Banco indisponível"));mvc.perform(get("/api/agendas/99").session(sessao())).andExpect(status().isServiceUnavailable());
  }
  @Test void recusaTransicaoInvalidaSemGravar() throws Exception {
    var a=new Agenda();a.setA04_codigo(4);a.setA01_codigo(8);a.setA04_status(0);var p=new EmpresaUsuarioPerfil();p.setA03_perfil_administrador(1);when(agendas.selectAgenda(any())).thenReturn(a);when(empresas.selectEmpresaUsuario(any())).thenReturn(p);
    mvc.perform(patch("/api/agendas/4/status").session(sessao()).header("X-Requested-With","XMLHttpRequest").contentType("application/json").content("{\"acao\":\"encerrar\"}")).andExpect(status().isConflict());verify(agendas,never()).updateStatusAgenda(any());
  }
  @Test void swaggerDocumentaSomenteApiENaoAtivaMvcLegado() throws Exception {
    mvc.perform(get("/v3/api-docs")).andExpect(status().isOk()).andExpect(jsonPath("$.paths['/api/agendas/{id}']").exists()).andExpect(jsonPath("$.paths['/usuarios/interCadastroUsuario']").doesNotExist());
    mvc.perform(get("/usuarios/interCadastroUsuario")).andExpect(status().isNotFound());mvc.perform(get("/")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/app/index.html"));
  }
}
