# Dependências para a escola

- `back/repository`: repositório Maven completo (JARs, POMs, plugins e dependências transitivas).
- `back/maven`: distribuição Maven portátil, preparada pelo script.
- `front/cache`: cache npm utilizado com o `front/package-lock.json`.

Em uma rede liberada, execute `scripts/preparar-dependencias.ps1`. Depois copie **a pasta do projeto inteira**, incluindo estes diretórios ignorados pelo Git. Copiar apenas os JARs não é suficiente para Maven offline. Os binários ficam fora do Git para não sobrecarregar o repositório.

Na escola: `scripts/empacotar.ps1 -Offline`. Isso reinstala o frontend a partir do cache, gera os arquivos React, executa os testes e produz o JAR com o frontend incluído. Execute com JDK 21: `java -jar target/boot-0.0.1-SNAPSHOT.jar`.

É necessário preparar o cache npm no mesmo sistema/arquitetura de destino (este cache é Windows x64). JDK 21 e Node 22.12+ precisam estar instalados. O JAR pronto exige apenas JDK 21; não usa Maven, npm ou CDN em execução.

Supabase por HTTPS ainda requer internet. Para trabalhar totalmente sem internet, configure MySQL local.
