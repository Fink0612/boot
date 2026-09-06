# Publicar no Render

1. Envie este projeto para seu repositório Git, incluindo Dockerfile, render.yaml e deploy/aiven-ca.pem.
2. No Render, escolha **New > Blueprint**, conecte o repositório e selecione a branch.
3. Informe **DB_PASSWORD** com a senha MySQL da Aiven e confirme a criação.
4. Após o deploy terminar, abra a URL `.onrender.com` fornecida pelo Render.

Se usar **New > Web Service**, selecione o runtime **Docker**, Dockerfile `./Dockerfile` e copie DB_URL e DB_USER do render.yaml. Adicione DB_PASSWORD separadamente. Não é necessário informar comando de build ou start.

A raiz selecionada no Render deve conter o pom.xml e o Dockerfile. O build usa Java 21, compila pelo Maven Wrapper e executa os testes. A imagem final executa apenas o JAR, com usuário sem privilégios. A aplicação escuta a variável PORT do Render; fora dele usa 8080.

O MySQL continua hospedado na Aiven. Nenhum dado ou senha local entra na imagem. O certificado público está em deploy/aiven-ca.pem; o truststore é gerado durante o build e usado para validar o certificado e o hostname. Atualize o certificado se a CA do serviço mudar.

O health check `/` verifica a resposta HTTP; não testa o banco. Após publicar, teste o login com sua conta existente. Se a Aiven tiver restrição de IP, permita os endereços de saída informados pelo Render.

Referências: https://render.com/docs/docker e https://render.com/docs/blueprint-spec
