# Publicar no Render

São dois serviços, definidos no `render.yaml` na raiz do repositório:

| Serviço | Pasta | O que roda |
|---|---|---|
| `zeen-storm-api` | `backend/` | API REST em Docker (Java 21, Spring Boot) |
| `zeen-storm-front` | `frontend/` | Frontend React estático (Vite) |

## Passo a passo

1. Envie este repositório para o Git, incluindo `render.yaml`, `backend/` e `frontend/`.
2. No Render, escolha **New > Blueprint**, conecte o repositório e selecione a branch.
3. No formulário, informe as variáveis marcadas com `sync: false`:
   - **API**: `SUPABASE_URL` (ex.: `https://seuprojeto.supabase.co`), `SUPABASE_SECRET_KEY` (chave do servidor) e `CORS_ORIGINS` (URL pública do frontend, ex.: `https://zeen-storm-front.onrender.com`).
   - **Frontend**: `VITE_API_URL` (URL pública da API, ex.: `https://zeen-storm-api.onrender.com`).
4. Confirme a criação. Render faz o build do Docker (`backend/Dockerfile`) e do Vite (`frontend/`).

## Antes de publicar

- Aplique o esquema no Supabase (veja `banco/README.md`).
- Se o Supabase tiver restrição de IP, permita os endereços de saída do Render.
- O frontend chama a API pelo `VITE_API_URL`. Na mesma origem de `CORS_ORIGINS`, a API responde com `Access-Control-Allow-Origin: <origem>` e aceita a cookie de sessão.

O health check `/v3/api-docs` verifica a resposta HTTP do backend; não testa o banco. Após publicar, teste o login em `VITE_API_URL/api/autenticacao/login`.

Referências: https://render.com/docs/docker, https://render.com/docs/static-sites e https://render.com/docs/blueprint-spec