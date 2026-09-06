package paradecision.boot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import paradecision.boot.modulos.agendas.dto.AgendaFatoresDados;
import paradecision.boot.modulos.agendas.entity.*;
import paradecision.boot.modulos.agendas.repository.*;
import paradecision.boot.modulos.empresas.entity.*;
import paradecision.boot.modulos.empresas.repository.EmpresaUsuarioPerfilRepository;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.pareceres.repository.ParecerFatorUsuarioRepository;
import paradecision.boot.modulos.usuarios.dto.UsuarioEmpresasDados;
import paradecision.boot.modulos.usuarios.entity.Usuario;
import paradecision.boot.modulos.usuarios.repository.*;

/** HTTP, services reais e Thymeleaf; apenas o acesso ao banco é simulado. */
@SpringBootTest
class FrontendFluxosTests {
  @Autowired WebApplicationContext context;
  @MockitoBean UsuarioRepository usuarios;
  @MockitoBean UsuarioEmpresasRepository empresas;
  @MockitoBean EmpresaUsuarioPerfilRepository perfisEmpresa;
  @MockitoBean AgendaRepository agendas;
  @MockitoBean AgendaUsuarioPerfilRepository perfisAgenda;
  @MockitoBean AgendaFatoresRepository fatores;
  @MockitoBean ParecerFatorUsuarioRepository pareceres;
  MockMvc mvc;

  @BeforeEach
  void preparar() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void inicioERecursosFuncionamComContextPath() throws Exception {
    String html =
        mvc.perform(get("/estudo/").contextPath("/estudo"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andReturn()
            .getResponse()
            .getContentAsString();
    assertTrue(html.replace("\\/", "/").contains("/estudo/autenticacao/login"));
    assertTrue(html.contains("/estudo/compartilhado/css/principal.css"));
    verifyNoInteractions(usuarios);
    mvc.perform(get("/compartilhado/css/principal.css")).andExpect(status().isOk());
    mvc.perform(get("/compartilhado/js/funcoesFluxo.js")).andExpect(status().isOk());
    for (String tela : java.util.List.of("usuarios/CadastroUsuario", "agendas/CadastroAgenda", "fatores/CadastroFator")) {
      mvc.perform(get("/" + tela)).andExpect(status().isOk()).andExpect(view().name(tela));
      mvc.perform(post("/" + tela)).andExpect(status().isOk()).andExpect(view().name(tela));
    }
  }

  @Test
  void loginInvalidoMantemMensagemDoLegado() throws Exception {
    when(usuarios.selectUserLogin(any())).thenReturn(new Usuario());
    String html =
        mvc.perform(
                post("/autenticacao/login")
                    .param("pdAcao", "envLogin")
                    .param("pdUsuario", "amigo")
                    .param("pdSenha", "incorreta"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    assertTrue(html.contains("Usuario e/ou Senha Invalidos"));
    verify(usuarios).selectUserLogin(argThat(u -> u.getA02_usuario().equals("amigo")));
    verifyNoInteractions(empresas);
  }

  @Test
  void loginValidoCarregaEmpresaEPerfilSemMudarContrato() throws Exception {
    Usuario usuario = new Usuario();
    usuario.setA02_codigo(7);
    usuario.setA02_nome("João d'Água");
    usuario.setA02_usuario("joao");
    usuario.setA02_senha("teste");
    Empresa empresa = new Empresa();
    empresa.setA01_codigo(3);
    empresa.setA01_nome("Amigos");
    EmpresaUsuarioPerfil perfil = new EmpresaUsuarioPerfil();
    perfil.setA03_perfil_administrador(1);
    UsuarioEmpresasDados lista = new UsuarioEmpresasDados();
    lista.getArrEmpresaModel().add(empresa);
    lista.getArrEmpresaUsuarioPerfilModel().add(perfil);
    when(usuarios.selectUserLogin(any())).thenReturn(usuario);
    when(empresas.selectEmpresasDoUsuario(any())).thenReturn(lista);
    String html =
        mvc.perform(
                post("/autenticacao/login")
                    .param("pdAcao", "envLogin")
                    .param("pdUsuario", "joao")
                    .param("pdSenha", "teste"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    assertTrue(html.contains("Amigos"));
    assertTrue(html.contains("enviarPdLogin(2)"));
    verify(empresas)
        .selectEmpresasDoUsuario(argThat(e -> e.getoUsuarioModel().getA02_codigo() == 7));
  }

  @Test
  void cadastroEnviaUsuarioEPerfilPelosServices() throws Exception {
    when(usuarios.insertUsuario(any()))
        .thenAnswer(
            call -> {
              Usuario u = call.getArgument(0);
              u.setA02_codigo(12);
              return u;
            });
    when(perfisEmpresa.insertEmpresaUsuarioPerfil(any())).thenReturn(1);
    mvc.perform(
            post("/usuarios/interCadastroUsuario")
                .param("u_a02_nome", "Amigo")
                .param("u_a02_email", "amigo@example.test")
                .param("u_a02_usuario", "amigo")
                .param("u_a02_senha", "teste")
                .param("u_a02_status", "1")
                .param("ct_A01_CODIGO", "3")
                .param("eup_a03_perfil_padrao", "1"))
        .andExpect(status().isOk())
        .andExpect(view().name("usuarios/interCadastroUsuario"));
    verify(usuarios)
        .insertUsuario(
            argThat(u -> u.getA02_usuario().equals("amigo") && u.getA02_senha().equals("teste")));
    verify(perfisEmpresa)
        .insertEmpresaUsuarioPerfil(
            argThat(
                p ->
                    p.getA01_codigo() == 3
                        && p.getA02_codigo() == 12
                        && p.getA03_perfil_padrao() == 1));
  }

  @Test
  void pareceresPreservamUmaGradePorFator() throws Exception {
    Agenda agenda = new Agenda();
    agenda.setA04_codigo(5);
    agenda.setA01_codigo(3);
    agenda.setA04_titulo("Agenda de estudo");
    agenda.setA04_status(2);
    AgendaFatoresDados lista = new AgendaFatoresDados();
    for (int i = 0; i < 2; i++) {
      Fator f = new Fator();
      f.setA06_codigo(i + 10);
      f.setA06_titulo("Fator " + i);
      f.setA06_descricao("Descrição " + i);
      lista.getArrFatorModel().add(f);
      lista.getArrUsuarioModel().add(new Usuario());
    }
    when(agendas.selectAgenda(any())).thenReturn(agenda);
    when(perfisAgenda.selectAgendaUsuarioPerfil(any())).thenReturn(new AgendaUsuarioPerfil());
    when(fatores.selectFatoresDaAgenda(any())).thenReturn(lista);
    when(pareceres.selectParecerFatorUsuario(any())).thenReturn(new ParecerFatorUsuario());
    String html =
        mvc.perform(
                post("/pareceres/AgendaFatoresPareceres")
                    .param("ct_A04_CODIGO", "5")
                    .param("ct_A02_CODIGO", "7"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
    for (int fator = 0; fator < 2; fator++)
      for (int nivel = 0; nivel <= 10; nivel++) {
        assertTrue(html.contains("id=\"CertL" + fator + "C" + nivel + "\""));
        assertTrue(html.contains("id=\"ContL" + fator + "C" + nivel + "\""));
      }
    assertTrue(html.contains("name=\"afp_A07_CERTEZA_1\""));
    verify(pareceres, times(2)).selectParecerFatorUsuario(any());
  }
}
