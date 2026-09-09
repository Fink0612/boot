param([switch]$Offline)
$ErrorActionPreference = 'Stop'
$raizProjeto = Split-Path $PSScriptRoot -Parent
$mavenPortatil = Join-Path $raizProjeto 'dependencias/back/maven/apache-maven-3.9.6/bin/mvn.cmd'
$mavenComando = if (Test-Path $mavenPortatil) { $mavenPortatil } else { (Get-Command mvn.cmd -ErrorAction Stop).Source }
$argumentosMaven = @('-B','-ntp',"-Dmaven.repo.local=$(Join-Path $raizProjeto 'dependencias/back/repository')",'clean','package')
$argumentosNpm = @('ci','--cache',(Join-Path $raizProjeto 'dependencias/front/cache'),'--no-audit','--no-fund')
if ($Offline) { $argumentosMaven = @('-o') + $argumentosMaven; $argumentosNpm += '--offline' }
Push-Location (Join-Path $raizProjeto 'front')
try {
  & npm.cmd @argumentosNpm
  if ($LASTEXITCODE -ne 0) { throw 'Falha ao instalar dependências do frontend.' }
  & npm.cmd run build
  if ($LASTEXITCODE -ne 0) { throw 'Falha no build React.' }
} finally { Pop-Location }
$destinoReact = Join-Path $raizProjeto 'src/main/resources/static/app'
New-Item -ItemType Directory -Force $destinoReact | Out-Null
Copy-Item -Path (Join-Path $raizProjeto 'front/dist/*') -Destination $destinoReact -Recurse -Force
Push-Location $raizProjeto
try { & $mavenComando @argumentosMaven; if ($LASTEXITCODE -ne 0) { throw 'Falha nos testes ou no empacotamento do backend.' } }
finally { Pop-Location }
Write-Host 'JAR pronto: target/boot-0.0.1-SNAPSHOT.jar'
