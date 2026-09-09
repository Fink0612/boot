# Zeen Storm / ParaDecision

Aplicação de tomada de decisão colaborativa. O repositório contém dois projetos independentes:

| Projeto | Pasta | Tecnologia |
|---|---|---|
| **Backend** | `backend/` | API REST Java 21, Spring Boot, Supabase (PostgreSQL) via PostgREST |
| **Frontend** | `frontend/` | Aplicação React (Vite), consumindo a API por HTTPS |

## Estrutura

```
boot/
├── backend/     API REST (pom.xml, src/, Dockerfile, banco/supabase.sql, config/...)
├── frontend/    Aplicação React (package.json, vite.config.js, src/...)
├── render.yaml  Blueprint do Render: API em Docker + frontend estático
└── README.md
```

## Backend (API REST)

Requisitos: JDK 21. O backend se conecta ao Supabase com a chave do servidor. Veja [backend/README.md](backend/README.md) e [banco](backend/banco/README.md).

```powershell
$env:JAVA_HOME = "$env:USERPROFILE\.jdks\ms-21.0.9"   # ajuste o caminho do seu JDK 21
cd backend
.\mvnw.cmd spring-boot:run
```

A API fica em http://localhost:8080/api/**, com documentação interativa em http://localhost:8080/swagger-ui.html. A entrada é `POST /api/autenticacao/login`.

As telas internas não são servidas pelo backend: o frontend é publicado separadamente (ver `frontend/README.md`).

## Publicação no Render

Docker, Blueprint e frontend estático preparados. Veja [backend/deploy/RENDER.md](backend/deploy/RENDER.md).