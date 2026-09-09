package paradecision.boot.modulos.compartilhado.infra;

import java.util.*;

/** Contrato de persistência: consultas parametrizadas, sem regras de negócio. */
public interface BancoDados {
  List<Registro> listar(String tabela, Map<String,Object> filtros);
  Registro inserir(String tabela, Map<String,Object> dados);
  void atualizar(String tabela, Map<String,Object> filtros, Map<String,Object> dados);
  void excluir(String tabela, Map<String,Object> filtros);
}
