# Banco local

MySQL 8.4.11 portátil, escutando apenas em 127.0.0.1:3306. Banco: `ssdparaviverbem`.

```powershell
.\scripts\preparar-banco.ps1
.\scripts\banco.ps1 status
.\scripts\banco.ps1 parar
.\scripts\banco.ps1 iniciar
```

Execute na raiz do projeto. A preparação cria uma Empresa de Estudos e o administrador `admin`. As senhas geradas ficam em `.local/acessos.json`. O usuário MySQL da aplicação é `zeen_app`, com permissões limitadas ao banco. A configuração fica em `config/banco-local.properties`. Ambos estão fora do Git.

Os dados persistem em `.local/mysql-data`, inclusive após `mvn clean` ou reiniciar o banco. Após reiniciar o computador, execute o comando iniciar. A preparação pode ser repetida sem apagar dados nem trocar senhas.

O esquema foi reconstruído das consultas Java porque o projeto não continha o SQL original. São sete tabelas, quatro views e duas procedures de compatibilidade. Não foram importados dados antigos.

A aplicação deve ser iniciada a partir da raiz do projeto. Também aceita DB_URL, DB_USER e DB_PASSWORD ou `-Ddb.config=caminho` antes de `-jar`.

Distribuição oficial: https://dev.mysql.com/downloads/mysql/8.4.html . O script confere o MD5 publicado do arquivo fixado em 8.4.11 antes de extrair.
