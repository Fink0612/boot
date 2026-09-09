#!/usr/bin/env bash
# Instala y valida las dependencias del backend (Maven) y del frontend (npm).
# Funciona en Debian/Ubuntu sin necesidad de root y sin internet: usa el Maven
# portable y el repositorio Maven local que se copian junto con la carpeta
# del proyecto (dependencias/back/). Si encuentra internet, refresca esos caches.
#
# Uso:
#   ./scripts/instalar-dependencias.sh                # empaqueta el backend offline
#   ./scripts/instalar-dependencias.sh --online       # permite descargas a la red
#   ./scripts/instalar-dependencias.sh --frontend     # además instala deps del frontend
#   ./scripts/instalar-dependencias.sh --solo-jar     # no recompila; usa el JAR ya generado
set -euo pipefail

raiz_proyecto="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
version_maven="3.9.6"
modo_offline=true
instalar_frontend=false
solo_jar=false
MVN=""

for argumento in "$@"; do
  case "$argumento" in
    --online) modo_offline=false ;;
    --frontend|-f) instalar_frontend=true ;;
    --solo-jar) solo_jar=true ;;
    *) echo "Argumento desconocido: $argumento" >&2; exit 2 ;;
  esac
done

function informar() { printf '[info] %s\n' "$*"; }
function error() { printf '[error] %s\n' "$*" >&2; exit 1; }

function hay_internet() {
  curl -fsS --connect-timeout 5 --max-time 8 https://repo.maven.apache.org/maven2/ >/dev/null 2>&1
}

function verificar_java() {
  if ! command -v java >/dev/null 2>&1; then
    error "No se encontró Java. Asegúrese de que el JDK esté disponible (ej: export PATH=\"\$HOME/bin:\$PATH\") o copie el JDK en dependencias/."
  fi
  local version
  version="$(java -version 2>&1 | head -n1)"
  informar "Java detectado: $version"
}

function resolver_maven() {
  local portable="$raiz_proyecto/dependencias/back/maven/apache-maven-$version_maven/bin/mvn"
  if [ -x "$portable" ]; then
    MVN="$portable"
    informar "Maven portable encontrado: $portable"
    return 0
  fi
  if command -v mvn >/dev/null 2>&1; then
    MVN="$(command -v mvn)"
    informar "Usando Maven del sistema: $MVN"
    return 0
  fi
  return 1
}

function instalar_maven_portatil() {
  local dir="$raiz_proyecto/dependencias/back/maven"
  local portable="$dir/apache-maven-$version_maven/bin/mvn"
  if [ -x "$portable" ]; then
    return 0
  fi
  if ! hay_internet; then
    return 1
  fi
  if [ "$modo_offline" = true ]; then
    return 1
  fi
  local base="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$version_maven"
  local archivo="$dir/apache-maven-$version_maven-bin.tar.gz"
  informar "Descargando Maven portable $version_maven..."
  mkdir -p "$dir"
  curl -fSL "$base/apache-maven-$version_maven-bin.tar.gz" -o "$archivo" \
    || error "No se pudo descargar Maven. Verifique la red."
  local esperado obtenido
  esperado="$(curl -fsSL "$base/apache-maven-$version_maven-bin.tar.gz.sha512" | awk '{print $1}')"
  obtenido="$(sha512sum "$archivo" | awk '{print $1}')"
  if [ "$esperado" != "$obtenido" ]; then
    rm -f "$archivo"
    error "Checksum de Maven inválido."
  fi
  tar -xzf "$archivo" -C "$dir"
  rm -f "$archivo"
  informar "Maven portable listo en $dir."
}

function compilar_backend() {
  local args=(-B -ntp "-Dmaven.repo.local=$raiz_proyecto/dependencias/back/repository" clean package)
  if [ "$modo_offline" = true ]; then
    args=(-o "${args[@]}")
    informar "Compilando el backend en modo OFFLINE con el repositorio local..."
  else
    informar "Compilando el backend (online) para refrescar el repositorio local..."
  fi
  ( cd "$raiz_proyecto" && "$MVN" "${args[@]}" )
}

function instalar_dependencias_frontend() {
  local dir_front="$raiz_proyecto/../frontend"
  if [ ! -d "$dir_front" ]; then
    dir_front="$raiz_proyecto/frontend"
  fi
  [ -d "$dir_front" ] || error "No se encontró la carpeta frontend junto al backend."
  local cache="$raiz_proyecto/dependencias/front/cache"
  informar "Instalando dependencias del frontend..."
  if [ "$modo_offline" = true ] || { [ ! -d "$cache" ] && ! hay_internet; }; then
    if [ -d "$cache" ]; then
      ( cd "$dir_front" && npm ci --offline --cache "$cache" --no-audit --no-fund )
    else
      error "Sin cache npm y sin internet. Copie dependencias/front/cache junto con el proyecto."
    fi
  else
    ( cd "$dir_front" && npm ci --cache "$cache" --no-audit --no-fund )
  fi
  informar "Dependencias del frontend instaladas."
}

function verificar_repositorio_local() {
  local repo="$raiz_proyecto/dependencias/back/repository"
  if [ ! -d "$repo" ] || [ -z "$(ls -A "$repo" 2>/dev/null)" ]; then
    error "No existe el repositorio local $repo. Copie la carpeta del proyecto entera (incluye dependencias/back/repository y dependencias/back/maven)."
  fi
}

informar "=== Instalación de dependencias de ParaDecision ==="
verificar_java

if [ "$solo_jar" = true ]; then
  jar="$raiz_proyecto/target/boot-0.0.1-SNAPSHOT.jar"
  if [ -f "$jar" ]; then
    informar "Modo --solo-jar: el JAR ya existe en $jar. Nada que instalar."
  else
    error "Modo --solo-jar pero no existe $jar. Ejecute el script sin --solo-jar para generarlo."
  fi
else
  if ! resolver_maven; then
    if [ "$modo_offline" = true ]; then
      error "Sin Maven portable (dependencias/back/maven) y en modo offline. Copie esa carpeta junto con el proyecto o ejecute con --online."
    fi
    informar "No hay Maven portable ni en el sistema. Intentando descargarlo..."
    instalar_maven_portatil || error "No se pudo instalar Maven."
  else
    instalar_maven_portatil || informar "El Maven portable ya está listo."
  fi
  verificar_repositorio_local
  compilar_backend
fi

if [ "$instalar_frontend" = true ]; then
  instalar_dependencias_frontend
fi

informar "Dependencias listas."
if [ -f "$raiz_proyecto/target/boot-0.0.1-SNAPSHOT.jar" ]; then
  informar "JAR generado: target/boot-0.0.1-SNAPSHOT.jar — ejecute con: java -jar target/boot-0.0.1-SNAPSHOT.jar"
fi