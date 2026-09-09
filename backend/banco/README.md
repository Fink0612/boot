# Banco: Supabase (PostgreSQL)

El backend usa Supabase como único banco de datos, vía la Data API/PostgREST por HTTPS. No hay MySQL ni JDBC.

## Esquema

- `banco/supabase.sql` — esquema PostgreSQL completo: siete tablas, seis views de la API REST, RLS (solo `service_role`) y el seed inicial (empresa de estudios y admin).

Para aplicarlo, abra el SQL Editor de Supabase y pegue el contenido de `banco/supabase.sql`, o ejecútelo con `psql`:

```powershell
psql "$env:DATABASE_URL" -f banco\supabase.sql
```

## Configuración

La aplicación se conecta con la **chave do servidor** (`sb_secret_...`). Copie `config/banco-exemplo.properties` a `config/banco-local.properties` y complete:

```properties
supabase.url=https://SEU-PROJETO.supabase.co
supabase.secret-key=sb_secret_...
```

La URL y la chave también pueden venir de las variables `SUPABASE_URL` y `SUPABASE_SECRET_KEY` (usadas por Render). La chave nunca sale del backend; `anon` y `authenticated` no tienen acceso a las tablas.