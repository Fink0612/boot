# Migração do frontend

Este documento registra a primeira etapa, em que o backend foi preservado. Depois dela, a camada Java foi separada em services, repositories, entidades e DTOs. A estrutura atual está no [mapa do projeto](MAPA_DO_PROJETO.md) e a segunda etapa em [refatoração do backend](REFATORACAO_BACKEND.md).

## Escopo realizado

- 38 páginas/fragmentos do legado convertidos para HTML Thymeleaf; todos os arquivos JSP foram removidos.
- Java de apresentação e chamadas dos formulários extraídos para controllers Spring em `web`.
- Cinco fragmentos reutilizáveis: cabeçalho, confirmação, controle geral, controle de agenda e controle de fator.
- Templates, JavaScript e CSS organizados por módulo. Bootstrap permanece em `static/bootstrap` como biblioteca compartilhada.
- Navegação e ações de formulário usam rotas Spring sem extensão. Nomes dos campos enviados ao backend, IDs usados pelos scripts e fluxos de navegação foram mantidos.
- Os links antigos para páginas inexistentes `AgendaEditar`, `AgendaFatoresEditar` e `EmpresaEmpresas` foram apontados para as telas correspondentes de edição de agenda, edição de fator e empresas do usuário.
- Expressões dinâmicas são escapadas pelo Thymeleaf. Argumentos de eventos ficam em atributos `data-*`; dados em scripts usam `th:inline="javascript"`. Os arquivos de frontend passaram para UTF-8.
- Declarações que antes eram campos da página passaram a variáveis locais da requisição. O controller Spring não guarda o usuário/código inicial entre requisições.
- O JAR antigo do driver MySQL, que ficava em `WebContent/WEB-INF/lib`, foi removido. O projeto já declarava o driver pelo Maven.

## Backend preservado

Foram realocadas 50 classes Java existentes. A comparação das versões anterior e atual, retirando somente declarações de pacote e imports, confirmou corpos idênticos nas 50 classes.

Os métodos, SQL, cálculos, campos, construtores e regras existentes não foram refatorados. Os pacotes antigos `Pck_*` foram substituídos pelos pacotes reais por módulo. Não foram introduzidos JPA, repositories Spring Data, autenticação nova ou uma nova camada de serviços.

Os controllers em `web` são novos adaptadores de apresentação. Os antigos `*Control` em `controller` continuam responsáveis pelas mesmas operações do legado.

## Validação

- Compilação com JDK 21.
- 76 verificações de templates: 38 templates com listas vazias e preenchidas, incluindo acentos, aspas e caracteres de HTML.
- Cinco testes HTTP: página inicial e recursos com context path; login inválido; login válido com empresa/perfil; cadastro de usuário e perfil; duas grades de pareceres com 11 níveis cada de certeza e contradição.
- Um teste de inicialização do contexto Spring. Total: 82 testes.
- Verificação sintática com Node dos sete scripts próprios e dos scripts produzidos pela renderização.
- `clean package` concluído: JAR inspecionado sem JSP, `WebContent`, pacotes antigos ou cópias antigas dos assets.
- JAR executado localmente: início, login e CSS retornaram HTTP 200. A execução de conferência usa a porta 18080; a configuração padrão do projeto continua na porta 8080.

Os testes de fluxo simulam os controles antigos para evitar consultas e gravações reais. Eles verificam os contratos entre formulário, controller e template. Não comprovam o funcionamento do MySQL, seus dados ou todas as regras do backend em uso real.

## Características mantidas

O layout com iframe, os nomes de negócio, os formulários intermediários e os campos ocultos de navegação continuam presentes. Esta migração organiza e troca a tecnologia da apresentação; a lógica legada pode ser estudada e melhorada separadamente pelo grupo.

As rotas internas precisam do contexto enviado pelo fluxo anterior. Abrir diretamente uma tela que espera códigos de empresa, usuário ou agenda pode continuar falhando, como no fluxo legado. O ponto de entrada normal é `/`.
