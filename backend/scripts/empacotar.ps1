param([switch]$Offline)
# Wrapper histórico: delega en instalar-dependencias.ps1.
# Por defecto el nuevo script ya trabaja offline; -Offline fuerza ese modo explícitamente.
$args = @()
if (-not $Offline) { $args += '-Online' }  # si NO pidieron offline, permitimos red
& (Join-Path $PSScriptRoot 'instalar-dependencias.ps1') @args
exit $LASTEXITCODE