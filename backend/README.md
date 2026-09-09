# ParaDecision Backend (API REST)

API REST de tomada de decisão colaborativa em Java 21 e Spring Boot. Sem Thymeleaf, sem MySQL: apenas a API `/api/**` sobre Supabase/PostgreSQL.

- [Mapa da API, módulos e camadas](docs/MAPA_DO_PROJETO.md)
- [Banco Supabase](banco/README.md)
- [Publicação no Render](deploy/RENDER.md)

## Executar

Requisitos: JDK 21 e um projeto Supabase com o esquema aplicado (ver `banco/README.md`).

Configure o acesso em `config/banco-local.properties` (fora do Git). Use `config/banco-exemplo.properties` como modelo:

```properties
supabase.url=https://SEU-PROJETO.supabase.co
supabase.secret-key=sb_secret_...
```

```powershell
# Nesta máquina existe este JDK 21. Em outro computador, ajuste o caminho.
$env:JAVA_HOME = "$env:USERPROFILE\.jdks\ms-21.0.9"
.\mvnw.cmd spring-boot:run
```

A API fica em http://localhost:8080/api/** e a documentação interativa em http://localhost:8080/swagger-ui.html. Para liberar o frontend publicado em outra origem, defina `app.cors-origins` (ou `CORS_ORIGINS`) com a URL pública do frontend.

## Validar e empacotar

```powershell
.\mvnw.cmd clean package
java -jar target/boot-0.0.1-SNAPSHOT.jar
```

Os testes HTTP exercitam controllers e services reais com repositories simulados. Há testes de arquitetura, serviços, cálculo concorrente e do adaptador Supabase.

## Onde editar

| Trabalho | Local |
|---|---|
| Receber HTTP e devolver JSON | `src/main/java/paradecision/boot/modulos/<modulo>/controller/` |
| Regras e operações de negócio | `modulos/<modulo>/service/` |
| Entidades do domínio | `modulos/<modulo>/entity/` |
| Dados agrupados de consultas | `modulos/<modulo>/dto/` |
| Acesso a dados (adapter Supabase/PostgREST) | `modulos/<modulo>/repository/` + `infra/` |

A camada de persistência é um contrato único (`BancoDados` em `modulos/compartilhado/infra/`), implementado por `SupabaseBancoDados` via HTTP/PostgREST. Não há JDBC.