# Zeen Storm / ParaDecision

Aplicação de tomada de decisão colaborativa: usuários pertencem a empresas, organizam agendas, cadastram fatores e registram pareceres de certeza e contradição. O projeto foi organizado para estudar Spring MVC em grupo, com controllers, services, repositories, entidades e DTOs separados por módulo.

O frontend usa **Spring MVC + Thymeleaf**, com CSS próprio e responsivo, sem Bootstrap. O visual compartilhado fica em `static/compartilhado/css/principal.css`. São 38 templates HTML, incluindo cinco fragmentos compartilhados. Não há JSP, Jasper ou JSTL. Este é um monólito modular: um projeto Maven, uma aplicação Spring e um JAR.

- [Mapa completo dos módulos, camadas e rotas](docs/MAPA_DO_PROJETO.md)
- [O que mudou e como foi validado](docs/MIGRACAO_FRONTEND.md)
- [Separação do backend em camadas](docs/REFATORACAO_BACKEND.md)

## Executar

Requisitos: JDK 21 e MySQL 8.4. Para preparar o banco local no Windows, execute ` .\scripts\preparar-banco.ps1`. Veja [Banco local](banco/README.md). O Maven Wrapper está incluído.

```powershell
# Nesta máquina existe este JDK 21. Em outro computador, ajuste o caminho.
$env:JAVA_HOME = "$env:USERPROFILE\.jdks\ms-21.0.9"
.\mvnw.cmd spring-boot:run
```

Abra [a aplicação local](http://localhost:8080/). A entrada carrega a tela de login; as telas internas recebem os campos de navegação dos formulários anteriores.

A configuração JDBC local fica em `config/banco-local.properties`, fora do Git. O esquema está em `banco/schema.sql`. Login e cadastros dependem do MySQL.

## Validar e empacotar

```powershell
.\mvnw.cmd clean package
java -jar target/boot-0.0.1-SNAPSHOT.jar
```

Os testes HTTP exercitam controllers e services reais com repositories simulados, sem gravar no banco. Há testes de arquitetura, serviços e cálculo concorrente. A renderização dos templates gera exemplos em `target/frontend-preview/`.

## Onde editar

| Trabalho | Local |
|---|---|
| HTML de uma tela | `src/main/resources/templates/<modulo>/` |
| Receber HTTP e escolher a tela | `src/main/java/paradecision/boot/modulos/<modulo>/controller/` |
| Regras e operações de negócio | `modulos/<modulo>/service/` |
| Coordenar o formulário e preparar dados da tela | `modulos/<modulo>/service/pagina/` |
| Entidades do domínio, como Usuario e Agenda | `modulos/<modulo>/entity/` |
| Dados agrupados de consultas | `modulos/<modulo>/dto/` |
| SQL/JDBC existente | `modulos/<modulo>/repository/` |
| CSS e JavaScript | `src/main/resources/static/<modulo>/` |
| Cabeçalho, confirmação e campos de navegação | `templates/compartilhado/` |

Para adicionar uma tela, crie seu template no módulo, um `@Controller` em `controller` que delegue a um service e retorne o nome do template, e atualize a navegação em `static/compartilhado/js/funcoesFluxo.js`. Os services recebem dependências pelo construtor. Entidades contêm dados e não conhecem services, HTTP ou repositories.

## Publicação no Render

Docker e Blueprint preparados. Veja [o passo a passo](deploy/RENDER.md).
