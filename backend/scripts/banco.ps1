param([ValidateSet('iniciar','parar','status')][string]$Acao = 'iniciar')
$ErrorActionPreference = 'Stop'
$taskRoot = Split-Path $PSScriptRoot -Parent
$taskLocal = Join-Path $taskRoot '.local'
$taskBin = Join-Path $taskLocal 'mysql-8.4.11-winx64\bin'
$taskPidFile = Join-Path $taskLocal 'mysql.pid'
$taskConfig = Join-Path $taskLocal 'my.ini'
$taskClient = Join-Path $taskLocal 'mysql-root.ini'

function Get-LocalDatabaseProcess {
    if (Test-Path -LiteralPath $taskPidFile) {
        $taskDatabaseId = [int](Get-Content -LiteralPath $taskPidFile)
        $taskProcess = Get-CimInstance Win32_Process -Filter "ProcessId=$taskDatabaseId"
        if ($taskProcess -and $taskProcess.ExecutablePath -eq (Join-Path $taskBin 'mysqld.exe')) { return $taskProcess }
    }
    return $null
}

if ($Acao -eq 'status') {
    if (Get-LocalDatabaseProcess) { Write-Output 'MySQL local em execucao: 127.0.0.1:3306' }
    else { Write-Output 'MySQL local parado.' }
    return
}
if ($Acao -eq 'parar') {
    if (Get-LocalDatabaseProcess) {
        & (Join-Path $taskBin 'mysqladmin.exe') "--defaults-extra-file=$taskClient" shutdown
        if ($LASTEXITCODE -ne 0) { throw 'Nao foi possivel encerrar o MySQL.' }
        Write-Output 'MySQL encerrado. Os dados foram preservados.'
    }
    return
}
if (Get-LocalDatabaseProcess) { Write-Output 'MySQL ja esta em execucao.'; return }
if (-not (Test-Path -LiteralPath (Join-Path $taskLocal 'mysql-data\auto.cnf'))) {
    throw 'Execute primeiro scripts\preparar-banco.ps1.'
}
if (Get-NetTCPConnection -LocalPort 3306 -State Listen -ErrorAction SilentlyContinue) {
    throw 'A porta 3306 esta ocupada por outro processo.'
}
Start-Process -FilePath (Join-Path $taskBin 'mysqld.exe') -ArgumentList ('--defaults-file="' + $taskConfig + '"') -WorkingDirectory $taskRoot -WindowStyle Hidden | Out-Null
Write-Output 'MySQL iniciando em 127.0.0.1:3306. Log: .local\mysql.log'
