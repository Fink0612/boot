# Banco ativo: Aiven

A aplicação usa agora o banco `defaultdb` no MySQL da Aiven, porta 16979.
Configuração ativa: `config/banco-local.properties` (nome preservado por compatibilidade, fora do Git).

O Connector/J usa SSL VERIFY_IDENTITY com a CA fornecida e o truststore `.local/aiven-truststore.p12`.
Os dados anteriores permanecem no MySQL local. A configuração anterior foi preservada em `.local/banco-antes-aiven.properties`.
Para voltar ao local, copie esse arquivo para `config/banco-local.properties` e reinicie a aplicação.

O administrador do sistema e sua senha foram preservados. O login MySQL é separado do login da aplicação.
A conexão do Workbench se chama `Zeen Storm - Aiven`, com esquema padrão `defaultdb`.

Documentação oficial: https://aiven.io/docs/products/mysql/howto/connect-from-mysql-workbench
