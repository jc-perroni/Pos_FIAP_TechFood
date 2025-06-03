FROM eclipse-temurin:21 as jre-build

WORKDIR /techfood

COPY target/techfood-*.jar techfood.jar

# Exponha a porta usada pela aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "techfood.jar"]