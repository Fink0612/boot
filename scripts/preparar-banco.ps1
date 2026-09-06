$ErrorActionPreference = 'Stop'
$taskRoot = Split-Path $PSScriptRoot -Parent
$taskLocal = Join-Path $taskRoot '.local'
$taskBase = Join-Path $taskLocal 'mysql-8.4.11-winx64'
$taskBin = Join-Path $taskBase 'bin'
$taskData = Join-Path $taskLocal 'mysql-data'
$taskIni = Join-Path $taskLocal 'my.ini'
$taskCredentials = Join-Path $taskLocal 'acessos.json'
$taskClient = Join-Path $taskLocal 'mysql-root.ini'
$taskBootstrap = Join-Path $taskLocal 'bootstrap.sql'
$taskMarker = Join-Path $taskLocal 'banco-pronto.txt'

function Write-Utf8([string]$Path, [string]$Value) {
    [IO.File]::WriteAllText($Path, $Value, [Text.UTF8Encoding]::new($false))
}
function New-LocalPassword {
    $taskBytes = New-Object byte[] 16
    $taskRandom = [Security.Cryptography.RandomNumberGenerator]::Create()
    try { $taskRandom.GetBytes($taskBytes) } finally { $taskRandom.Dispose() }
    return -join ($taskBytes | ForEach-Object { $_.ToString('x2') })
}
if (Test-Path -LiteralPath $taskMarker) {
    & (Join-Path $PSScriptRoot 'banco.ps1') iniciar
    Write-Output 'Banco ja preparado. Dados e senhas preservados em .local\acessos.json.'
    return
}
if (Get-NetTCPConnection -LocalPort 3306 -State Listen -ErrorAction SilentlyContinue) {
    throw 'A porta 3306 esta ocupada. Este instalador nao altera servidores existentes.'
}
New-Item -ItemType Directory -Path $taskLocal -Force | Out-Null
if (-not (Test-Path -LiteralPath (Join-Path $taskBin 'mysqld.exe'))) {
    $taskArchive = Join-Path $taskLocal 'mysql-8.4.11-winx64.zip'
    if (-not (Test-Path -LiteralPath $taskArchive)) {
        Invoke-WebRequest 'https://cdn.mysql.com/Downloads/MySQL-8.4/mysql-8.4.11-winx64.zip' -OutFile $taskArchive
    }
    if ((Get-FileHash -LiteralPath $taskArchive -Algorithm MD5).Hash -ne '2e833921898a9a030ea6bfe81bd811bc') {
        throw 'O checksum do arquivo MySQL nao corresponde ao publicado pela Oracle.'
    }
    Expand-Archive -LiteralPath $taskArchive -DestinationPath $taskLocal
}
if (Test-Path -LiteralPath $taskCredentials) {
    $taskAccess = Get-Content -LiteralPath $taskCredentials -Raw | ConvertFrom-Json
} else {
    $taskAccess = [PSCustomObject]@{
        aplicativo_usuario = 'admin'
        aplicativo_senha = ('Estudo-' + (New-LocalPassword).Substring(0,12) + '!')
        banco_host = '127.0.0.1'
        banco_porta = 3306
        banco_nome = 'ssdparaviverbem'
        banco_usuario = 'zeen_app'
        banco_senha = (New-LocalPassword)
        root_senha = (New-LocalPassword)
    }
    Write-Utf8 $taskCredentials ($taskAccess | ConvertTo-Json)
}
$taskBaseSql = $taskBase.Replace('\','/')
$taskDataSql = $taskData.Replace('\','/')
$taskLocalSql = $taskLocal.Replace('\','/')
Write-Utf8 $taskIni @"
[mysqld]
basedir="$taskBaseSql"
datadir="$taskDataSql"
port=3306
bind-address=127.0.0.1
mysqlx=0
pid-file="$taskLocalSql/mysql.pid"
log-error="$taskLocalSql/mysql.log"
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
innodb-buffer-pool-size=128M
"@
Write-Utf8 $taskClient @"
[client]
user=root
password=$($taskAccess.root_senha)
host=127.0.0.1
port=3306
protocol=TCP
default-character-set=utf8mb4
"@
Write-Utf8 $taskBootstrap @"
ALTER USER 'root'@'localhost' IDENTIFIED BY '$($taskAccess.root_senha)';
CREATE DATABASE IF NOT EXISTS ssdparaviverbem CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'zeen_app'@'localhost' IDENTIFIED BY '$($taskAccess.banco_senha)';
CREATE USER IF NOT EXISTS 'zeen_app'@'127.0.0.1' IDENTIFIED BY '$($taskAccess.banco_senha)';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON ssdparaviverbem.* TO 'zeen_app'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON ssdparaviverbem.* TO 'zeen_app'@'127.0.0.1';
"@
if (-not (Test-Path -LiteralPath (Join-Path $taskData 'auto.cnf'))) {
    & (Join-Path $taskBin 'mysqld.exe') "--defaults-file=$taskIni" --initialize-insecure
    if ($LASTEXITCODE -ne 0) { throw 'Falha ao inicializar o MySQL. Consulte .local\mysql.log.' }
}
Start-Process -FilePath (Join-Path $taskBin 'mysqld.exe') -ArgumentList @('--defaults-file="' + $taskIni + '"', '--init-file="' + $taskBootstrap + '"') -WorkingDirectory $taskRoot -WindowStyle Hidden | Out-Null
$taskReady = $false
for ($taskAttempt = 0; $taskAttempt -lt 30; $taskAttempt++) {
    Start-Sleep -Seconds 1
    & (Join-Path $taskBin 'mysqladmin.exe') "--defaults-extra-file=$taskClient" ping --silent 2>$null | Out-Null
    if ($LASTEXITCODE -eq 0) { $taskReady = $true; break }
}
if (-not $taskReady) { throw 'MySQL nao ficou pronto. Consulte .local\mysql.log.' }
$taskSchema = (Join-Path $taskRoot 'banco\schema.sql').Replace('\','/')
& (Join-Path $taskBin 'mysql.exe') "--defaults-extra-file=$taskClient" "--execute=source $taskSchema"
if ($LASTEXITCODE -ne 0) { throw 'Falha ao criar o esquema.' }
$taskSeed = Join-Path $taskLocal 'seed.sql'
Write-Utf8 $taskSeed @"
USE ssdparaviverbem;
INSERT IGNORE INTO EMPRESA_01(A01_CODIGO,A01_NOME,A01_DESCRICAO,A01_STATUS) VALUES(1,'Empresa de Estudos','Ambiente local para aprender com os amigos',1);
INSERT IGNORE INTO USUARIO_02(A02_USUARIO,A02_NOME,A02_SENHA,A02_EMAIL,A02_STATUS,A02_CODIGO_LINK) VALUES('admin','Administrador','$($taskAccess.aplicativo_senha)','admin@estudo.local',1,UUID());
INSERT IGNORE INTO EMPRESA_USUARIO_PERFIL_03(A01_CODIGO,A02_CODIGO,A03_PERFIL_PARAVIVERBEM,A03_PERFIL_ADMINISTRADOR,A03_PERFIL_CHEFE,A03_PERFIL_PADRAO) SELECT 1,A02_CODIGO,1,1,1,1 FROM USUARIO_02 WHERE A02_USUARIO='admin';
"@
& (Join-Path $taskBin 'mysql.exe') "--defaults-extra-file=$taskClient" ("--execute=source " + $taskSeed.Replace('\','/'))
if ($LASTEXITCODE -ne 0) { throw 'Falha ao criar o administrador.' }
$taskConfigDir = Join-Path $taskRoot 'config'
New-Item -ItemType Directory -Path $taskConfigDir -Force | Out-Null
Write-Utf8 (Join-Path $taskConfigDir 'banco-local.properties') @"
db.url=jdbc:mysql://127.0.0.1:3306/ssdparaviverbem?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&sslMode=DISABLED
db.user=$($taskAccess.banco_usuario)
db.password=$($taskAccess.banco_senha)
"@
Write-Utf8 (Join-Path $taskLocal 'mysql-app.ini') @"
[client]
user=$($taskAccess.banco_usuario)
password=$($taskAccess.banco_senha)
host=127.0.0.1
port=3306
protocol=TCP
default-character-set=utf8mb4
"@
Remove-Item -LiteralPath $taskBootstrap
Remove-Item -LiteralPath $taskSeed
Write-Utf8 $taskMarker 'Esquema local e administrador preparados.'
Write-Output 'Banco pronto em 127.0.0.1:3306. Credenciais: .local\acessos.json'
