# Dependências para a escola / rede bloqueada

Este projeto **não precisa de internet** para compilar: las dependencias se transportan junto con la carpeta del proyecto en `dependencias/`.

- `back/repository`: repositorio Maven completo (JARs, POMs, plugins y dependencias transitivas) usado en modo `-o` (offline).
- `back/maven`: distribución Maven portátil (apache-maven-3.9.6), sin instalación ni root.
- `front/cache`: cache npm para `npm ci --offline` (solo si además desarrollas el frontend en esa máquina).

## Preparar (en una red liberada, la primera vez)

```powershell
cd backend
.\scripts\instalar-dependencias.ps1 -Online       # en Windows
# o en Debian/Ubuntu:
./scripts/instalar-dependencias.sh --online       # recarga caches de Maven/npm
```

Luego copia **la carpeta del proyecto entera** (incluye `dependencias/`, que está fuera del Git). Copiar solo los JARs no basta.

## En la escuela (Debian, sin internet, sin root)

```bash
cd backend
./scripts/instalar-dependencias.sh          # en modo offline por defecto
./scripts/instalar-dependencias.sh --solo-jar   # si ya tienes el JAR, no recompila
```

En Windows equivalente:

```powershell
.\scripts\instalar-dependencias.ps1
.\scripts\instalar-dependencias.ps1 -SoloJar
```

Requiere únicamente tener `java` (JDK 21) en el PATH. No hace falta Maven instalado ni permisos de administrador.

## Ejecutar el backend

```bash
java -jar target/boot-0.0.1-SNAPSHOT.jar
```

Nota: el JAR ya contiene la API; si publicas el frontend en otra máquina, ese frontend debe apuntar a la API con `VITE_API_URL` y el backend debe tener `app.cors-origins` con la URL del frontend. Para solo usar la API, basta el JAR.