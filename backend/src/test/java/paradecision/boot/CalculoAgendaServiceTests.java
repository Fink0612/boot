package paradecision.boot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;
import paradecision.boot.modulos.agendas.dto.AgendaPareceresDados;
import paradecision.boot.modulos.agendas.entity.Agenda;
import paradecision.boot.modulos.agendas.repository.*;
import paradecision.boot.modulos.agendas.service.*;
import paradecision.boot.modulos.fatores.entity.Fator;
import paradecision.boot.modulos.pareceres.entity.ParecerFatorUsuario;
import paradecision.boot.modulos.usuarios.entity.Usuario;

class CalculoAgendaServiceTests {
  @Test
  void calculaComSeteEspecialistas() {
    var service = preparar(7, null);
    var agenda = agenda(1);
    assertEquals("OK", service.geraResultados(agenda, 0));
    assertEquals(60, agenda.getA04_certeza_resultado(), 0.001);
    assertEquals(0, agenda.getA04_contradicao_resultado(), 0.001);
  }

  @Test
  void chamadasSimultaneasNaoMisturamMatrizes() throws Exception {
    var service = preparar(2, new CyclicBarrier(2));
    var primeira = agenda(1);
    var segunda = agenda(2);
    try (var executor = Executors.newFixedThreadPool(2)) {
      var a = executor.submit(() -> service.geraResultados(primeira, 0));
      var b = executor.submit(() -> service.geraResultados(segunda, 0));
      assertEquals("OK", a.get(10, TimeUnit.SECONDS));
      assertEquals("OK", b.get(10, TimeUnit.SECONDS));
    }
    assertEquals(60, primeira.getA04_certeza_resultado(), 0.001);
    assertEquals(-40, segunda.getA04_certeza_resultado(), 0.001);
  }

  private Agenda agenda(long codigo) {
    var a = new Agenda();
    a.setA04_codigo(codigo);
    return a;
  }

  private CalculoResultadoAgendaService preparar(int quantidade, CyclicBarrier barreira) {
    var fatores = mock(AgendaFatoresRepository.class);
    var usuarios = mock(AgendaUsuariosRepository.class);
    var pareceres = mock(AgendaPareceresRepository.class);
    when(fatores.getArrFatoresModel(any()))
        .thenAnswer(
            call -> {
              Agenda a = call.getArgument(0);
              var f = new Fator();
              f.setA06_codigo(100 + a.getA04_codigo());
              if (barreira != null) barreira.await(5, TimeUnit.SECONDS);
              return new ArrayList<>(java.util.List.of(f));
            });
    when(usuarios.getArrEspecialistasModel(any()))
        .thenAnswer(
            call -> {
              var lista = new ArrayList<Usuario>();
              for (int i = 0; i < quantidade; i++) {
                var u = new Usuario();
                u.setA02_codigo(i + 1);
                lista.add(u);
              }
              return lista;
            });
    when(pareceres.selectPareceresDaAgenda(any()))
        .thenAnswer(
            call -> {
              AgendaPareceresDados dados = call.getArgument(0);
              long codigo = dados.getoAgendaModel().getA04_codigo();
              for (int i = 0; i < quantidade; i++) {
                var u = new Usuario();
                u.setA02_codigo(i + 1);
                dados.getArrUsuarioModel().add(u);
                var p = new ParecerFatorUsuario();
                p.setA06_codigo(100 + codigo);
                p.setA07_certeza(codigo == 1 ? 80 : 30);
                p.setA07_contradicao(codigo == 1 ? 20 : 70);
                dados.getArrParecerFatorUsuarioModel().add(p);
              }
              return dados;
            });
    when(fatores.updateGrausFatoresDaAgenda(any())).thenReturn("OK");
    return new CalculoResultadoAgendaService(
        fatores, usuarios, pareceres, new AgendaFatoresService(fatores));
  }
}
