FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /build
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw
COPY src/ src/
RUN ./mvnw -B -ntp package
COPY deploy/aiven-ca.pem /build/aiven-ca.pem
# O certificado e a senha deste truststore sao publicos; nao contem chaves privadas.
RUN keytool -importcert -noprompt -alias aiven-ca -file aiven-ca.pem     -keystore aiven-truststore.p12 -storetype PKCS12 -storepass changeit && chmod 644 aiven-truststore.p12

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /build/target/boot-0.0.1-SNAPSHOT.jar /app/app.jar
COPY --from=build /build/aiven-truststore.p12 /app/aiven-truststore.p12
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=65.0 -XX:+ExitOnOutOfMemoryError"
USER 10001:10001
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
