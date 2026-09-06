# Backend: MVC com services separados das entidades

## Responsabilidades

| Camada | Responsabilidade | Exemplo no módulo usuários |
|---|---|---|
| `controller` | Receber HTTP, chamar o service e selecionar a view | `InterCadastroUsuarioPaginaController` |
| `service/pagina` | Coordenar o caso de uso e preparar os dados de apresentação | `InterCadastroUsuarioPaginaService` |
| `service` | Executar as operações e regras de negócio | `UsuarioService` |
| `repository` | Consultar/gravar usando JDBC | `UsuarioRepository` |
| `entity` | Representar os dados do domínio | `Usuario` |
| `dto` | Agrupar dados de consultas ou de entrada/saída | `UsuarioEmpresasDados` |
| `templates` | Renderizar HTML pelo Thymeleaf | `usuarios/interCadastroUsuario.html` |

MVC continua presente: o controller é o adaptador HTTP, o Model corresponde aos dados e operações da aplicação, e a View é o template. A camada Model pode ser subdividida em entidades, services e repositories; colocar regras em service não deixa de ser MVC.

As entidades são classes Java simples usadas pelo JDBC, não entidades JPA. Seus campos/getters continuam correspondendo às colunas e contratos já utilizados pelo sistema. Os agrupamentos de listas foram para DTOs, separados das sete entidades principais.

## Mudanças realizadas

- Antigos `*Control` transformados em `@Service`; `controller` contém os endpoints MVC.
- Antigos `*Persistencia` transformados em `@Repository`.
- Dependências injetadas por construtor e armazenadas em campos `final`. Controllers e services não instanciam services/repositories com `new`.
- Código de processamento que estava nas páginas-controller passou para `service/pagina`. Esses serviços recebem `DadosFormulario`, uma cópia dos parâmetros, e não recebem `HttpServletRequest` ou `org.springframework.ui.Model`.
- `UsuarioModel`, `EmpresaModel`, `EmpresaUsuarioPerfilModel`, `AgendaModel`, `AgendaUsuarioPerfilModel`, `FatorModel` e `ParecerFatorUsuarioModel` passaram a entidades com nomes sem o sufixo `Model`.
- Os demais modelos que agrupavam consultas passaram a `*Dados` em `dto`. Getters de compatibilidade que terminam em `Model` foram preservados.
- O acesso de diagnóstico ao banco saiu de `model`, passando por service e repository e devolvendo o DTO `DiagnosticoBanco`.
- Cada operação JDBC usa uma conexão local; repositories não guardam conexão em um campo compartilhado. O diagnóstico fecha sua conexão com try-with-resources.
- O service de cálculo é compartilhável; cada chamada cria um `ExecutorCalculoAgenda` próprio para suas matrizes e contadores.
- Corrigido o acesso fora do vetor no cálculo com sete especialistas: a parte inicial da distribuição usa `ii < padrao.length`.
- Comparações de texto vazio nos services passaram a comparar conteúdo, em vez de referências com `==`/`!=`.

As telas e rotas foram mantidas. Não houve troca do esquema do banco nem da tecnologia JDBC. As consultas SQL dos repositories foram comparadas com a versão anterior à refatoração.

## Validação

88 testes:

- 76 renderizações de templates, com listas vazias/preenchidas e caracteres especiais;
- cinco fluxos HTTP com services reais e repositories simulados;
- dois testes de arquitetura: direção de dependências e ausência de estado de requisição em beans compartilhados;
- dois testes de cálculo: sete especialistas e duas chamadas simultâneas com resultados diferentes;
- dois testes de serviço/entrada: senha incorreta e cópia independente dos parâmetros;
- um teste de inicialização do contexto Spring.

Os testes não fazem gravações no MySQL real. O esquema/carga do banco não está no repositório; a validação integrada com esse banco continua dependendo do ambiente existente.

## Exemplo para estudar

Para acompanhar um cadastro, leia nesta ordem:

1. `modulos/usuarios/controller/InterCadastroUsuarioPaginaController.java`
2. `modulos/usuarios/service/pagina/InterCadastroUsuarioPaginaService.java`
3. `modulos/usuarios/service/UsuarioService.java`
4. `modulos/usuarios/repository/UsuarioRepository.java`
5. `modulos/usuarios/entity/Usuario.java`
6. `resources/templates/usuarios/interCadastroUsuario.html`

Não há razão para a entidade importar o service: é o service que usa a entidade para executar uma operação. O repository conhece as entidades e DTOs que carrega; não conhece controllers nem templates.
