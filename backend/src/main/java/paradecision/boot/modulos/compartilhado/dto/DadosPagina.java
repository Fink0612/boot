package paradecision.boot.modulos.compartilhado.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Dados de apresentação por requisição; cada linha representa uma repetição no HTML. */
public final class DadosPagina {
  private DadosPagina() {}

  @SuppressWarnings("unchecked")
  public static Map<String, Object> novaLinha(Map<String, Object> pai, String nome) {
    List<Map<String, Object>> linhas =
        (List<Map<String, Object>>)
            pai.computeIfAbsent(nome, chave -> new ArrayList<Map<String, Object>>());
    Map<String, Object> linha = new LinkedHashMap<>();
    linhas.add(linha);
    return linha;
  }
}
