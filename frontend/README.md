# ParaDecision Frontend

Frontend React (Vite) da API REST do projeto Zeen Storm. Publicado de forma independente do backend.

## Executar em desenvolvimento

O servidor do Vite faz proxy das rotas `/api`, `/swagger-ui` e `/v3/api-docs` para o backend local em `http://127.0.0.1:8080`.

```powershell
npm ci
npm run dev
```

Abra http://localhost:5173/. O backend segue em http://localhost:8080/ (consulte `backend/README.md`).

## API removida

Por padrão o frontend usa a mesma origem (proxy de desenvolvimento). Para apontar para uma API externa, defina a variável de ambiente `VITE_API_URL` (no build de produção ou em um arquivo `.env.local`):

```powershell
# .env.local
VITE_API_URL=https://zeen-storm-api.onrender.com
```

As variáveis marcadas com `VITE_` no Vite só são embutidas em tempo de build; alterá-las exige novo `npm run build`.

## Publicar

```powershell
npm ci
npm run build
```

O resultado fica em `dist/`. O `render.yaml` na raiz do repositório define o serviço estático `zeen-storm-front` com `staticPublishPath: ./dist`.