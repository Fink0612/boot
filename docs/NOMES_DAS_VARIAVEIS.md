# Nomes das variáveis

Os nomes internos foram revisados no backend Java, no JavaScript e nos scripts dos templates.

| Antes | Agora | Significado |
|---|---|---|
| con | conexaoBanco | Conexão JDBC |
| stmt | comandoPreparado | Comando SQL parametrizado |
| rs | resultadoConsulta | Linhas retornadas pelo banco |
| ret | resultadoOperacao | Resultado devolvido pelo método |
| oUsuarioModel | dadosUsuario | Objeto do usuário |
| a02_codigo | codigoUsuario | Identificador do usuário na entidade |
| qtdFatores | quantidadeFatores | Total de fatores |
| f, g, u (cálculo) | indiceFator, indiceGrupo, indiceUsuario | Posições nas matrizes |
| matCerteza | matrizCerteza | Valores de certeza |
| txtUsu | usuarioInformado | Usuário digitado no login |
| txtSen | senhaInformada | Senha digitada no login |
| e (evento de teclado) | eventoTeclado | Evento recebido pelo formulário |

Nomes de tabelas e colunas, IDs HTML, campos enviados, chaves do modelo Thymeleaf, rotas e assinaturas dos métodos existentes foram preservados. Por isso os getters legados ainda podem conter nomes como getA02_codigo(): isso mantém o contrato, enquanto o atributo interno já se chama codigoUsuario.

As alterações são apenas de identificadores. Fórmulas, comparações, SQL, mensagens e ordem de execução foram preservados. Validação: testes Maven, comparação de cenários JavaScript antes/depois e sintaxe dos scripts renderizados.
