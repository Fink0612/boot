param(
  [switch]$Online,
  [switch]$Frontend,
  [switch]$SoloJar
)
# Instala y valida las dependencias del backend (Maven) y del frontend (npm).
# Funciona en Windows sin necesidad de permisos de administrador y sin internet:
# usa el Maven portable y el repositorio Maven local (dependencias/back/).
# Uso:
#   .\scripts\instalar-dependencias.ps1                 # empaqueta el backend offline
#   .\scripts\instalar-dependencias.ps1 -Online         # permite descargas a la red
#   .\scripts\instalar-dependencias.ps1 -Frontend       # además instala deps del frontend
#   .\scripts\instalar-dependencias.ps1 -SoloJar        # no recompila; usa el JAR ya generado
$ErrorActionPreference = 'Stop'

$raizProyecto = Split-Path $PSScriptRoot -Parent
$versionMaven = '3.9.6'
$modoOffline = -not [bool]$Online
$instalarFrontend = [bool]$Frontend
$soloJar = [bool]$SoloJar
$MVN = ''

function Informar { Write-Host ("[info] " + $args[0]) }
function ErrorDet { Write-Error $args[0]; exit 1 }

function HayInternet {
  try {
    Invoke-WebRequest -Uri 'https://repo.maven.apache.org/maven2/' -UseBasicParsing -TimeoutSec 8 | Out-Null
    return $true
  } catch { return $false }
}

function VerificarJava {
  $java = Get-Command java -ErrorAction SilentlyContinue
  if (-not $java) { ErrorDet 'No se encontró Java. Ajuste el PATH o la variable JAVA_HOME al JDK 21.' }
  $version = (cmd /c "java -version 2>&1" | Select-Object -First 1)
  Informar ("Java detectado: " + $version)
}

function ResolverMaven {
  $portable = Join-Path $raizProyecto "dependencias/back/maven/apache-maven-$versionMaven/bin/mvn.cmd"
  if (Test-Path $portable) {
    $script:MVN = $portable
    Informar ("Maven portable encontrado: " + $portable)
    return $true
  }
  $sistema = Get-Command mvn.cmd -ErrorAction SilentlyContinue
  if ($sistema) {
    $script:MVN = $sistema.Source
    Informar ("Usando Maven del sistema: " + $sistema.Source)
    return $true
  }
  return $false
}

function InstalarMavenPortatil {
  if ($modoOffline -or -not (HayInternet)) { return $false }
  $dir = Join-Path $raizProyecto 'dependencias/back/maven'
  $portable = Join-Path $dir "apache-maven-$versionMaven/bin/mvn.cmd"
  if (Test-Path $portable) { return $true }
  $base = "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$versionMaven"
  $archivo = Join-Path $dir "apache-maven-$versionMaven-bin.zip"
  Informar "Descargando Maven portable $versionMaven..."
  New-Item -ItemType Directory -Force $dir | Out-Null
  try { Invoke-WebRequest -Uri "$base/apache-maven-$versionMaven-bin.zip" -OutFile $archivo -UseBasicParsing }
  catch { ErrorDet 'No se pudo descargar Maven. Verifique la red.' }
  $esperado = ((Invoke-WebRequest -Uri "$base/apache-maven-$versionMaven-bin.zip.sha512" -UseBasicParsing).Content).Trim().Split(' ')[0]
  $obtenido = (Get-FileHash -LiteralPath $archivo -Algorithm SHA512).Hash
  if ($esperado -ne $obtenido) { Remove-Item -LiteralPath $archivo -Force; ErrorDet 'Checksum de Maven inválido.' }
  Expand-Archive -LiteralPath $archivo -DestinationPath $dir -Force
  Remove-Item -LiteralPath $archivo -Force
  Informar ("Maven portable listo en " + $dir)
  return $true
}

function CompilarBackend {
  $argsMaven = @('-B','-ntp',"-Dmaven.repo.local=$(Join-Path $raizProyecto 'dependencias/back/repository')",'clean','package')
  if ($modoOffline) {
    $argsMaven = @('-o') + $argsMaven
    Informar 'Compilando el backend en modo OFFLINE con el repositorio local...'
  } else {
    Informar 'Compilando el backend (online) para refrescar el repositorio local...'
  }
  Push-Location $raizProyecto
  try { & $MVN @argsMaven; if ($LASTEXITCODE -ne 0) { throw 'Falha na compilação do backend.' } }
  finally { Pop-Location }
}

function InstalarFrontend {
  $dirFront = Join-Path (Split-Path $raizProyecto -Parent) 'frontend'
  if (-not (Test-Path $dirFront)) { ErrorDet 'No se encontró la carpeta frontend junto al backend.' }
  $cache = Join-Path $raizProyecto 'dependencias/front/cache'
  Informar 'Instalando dependencias del frontend...'
  Push-Location $dirFront
  try {
    if ($modoOffline -or (-not (Test-Path $cache) -and -not (HayInternet))) {
      if (Test-Path $cache) {
        & npm.cmd ci --offline --cache $cache --no-audit --no-fund
      } else {
        ErrorDet 'Sin cache npm y sin internet. Copie dependencias/front/cache junto con el proyecto.'
      }
    } else {
      & npm.cmd ci --cache $cache --no-audit --no-fund
    }
  } finally { Pop-Location }
  Informar 'Dependencias del frontend instaladas.'
}

function VerificarRepositorioLocal {
  $repo = Join-Path $raizProyecto 'dependencias/back/repository'
  if (-not (Test-Path $repo) -or ((Get-ChildItem $repo | Measure-Object).Count -eq 0)) {
    ErrorDet 'No existe el repositorio local. Copie la carpeta del proyecto entera (incluye dependencias/back/repository y dependencias/back/maven).'
  }
}

Informar '=== Instalación de dependencias de ParaDecision ==='
VerificarJava

if ($soloJar) {
  $jar = Join-Path $raizProyecto 'target/boot-0.0.1-SNAPSHOT.jar'
  if (Test-Path $jar) {
    Informar "Modo -SoloJar: el JAR ya existe en $jar. Nada que instalar."
  } else {
    ErrorDet "Modo -SoloJar pero no existe $jar. Ejecute el script sin -SoloJar para generarlo."
  }
} else {
  if (ResolverMaven) {
    InstalarMavenPortatil | Out-Null
  } else {
    if ($modoOffline) {
      ErrorDet 'Sin Maven portable (dependencias/back/maven) y en modo offline. Copie esa carpeta junto con el proyecto o ejecute con -Online.'
    }
    Informar 'No hay Maven portable ni en el sistema. Intentando descargarlo...'
    if (-not (InstalarMavenPortatil)) { ErrorDet 'No se pudo instalar Maven.' }
  }
  VerificarRepositorioLocal
  CompilarBackend
}

if ($instalarFrontend) { InstalarFrontend }

Informar 'Dependencias listas.'
if (Test-Path (Join-Path $raizProyecto 'target/boot-0.0.1-SNAPSHOT.jar')) {
  Informar 'JAR generado: target/boot-0.0.1-SNAPSHOT.jar — ejecute con: java -jar target/boot-0.0.1-SNAPSHOT.jar'
}