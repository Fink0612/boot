$ErrorActionPreference = 'Stop'
$raizProjeto = Split-Path $PSScriptRoot -Parent
$destinoMaven = Join-Path $raizProjeto 'dependencias/back/maven'
$executavelMaven = Join-Path $destinoMaven 'apache-maven-3.9.6/bin/mvn.cmd'
if (-not (Test-Path $executavelMaven)) {
  New-Item -ItemType Directory -Force $destinoMaven | Out-Null
  $arquivoZip = Join-Path $destinoMaven 'apache-maven-3.9.6-bin.zip'
  $baseMaven = 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip'
  Invoke-WebRequest -Uri $baseMaven -OutFile $arquivoZip
  $hashEsperado = ((Invoke-WebRequest -Uri "$baseMaven.sha512").Content).Trim().Split(' ')[0]
  if ((Get-FileHash -LiteralPath $arquivoZip -Algorithm SHA512).Hash -ne $hashEsperado) { throw 'Checksum Maven inválido.' }
  Expand-Archive -LiteralPath $arquivoZip -DestinationPath $destinoMaven -Force
}
& (Join-Path $PSScriptRoot 'empacotar.ps1')
# O build real baixa plugins de testes e empacotamento que go-offline pode deixar de fora.
& (Join-Path $PSScriptRoot 'empacotar.ps1') -Offline
Write-Host 'Cache preparado e build offline validado. Copie o projeto inteiro para a escola.'
