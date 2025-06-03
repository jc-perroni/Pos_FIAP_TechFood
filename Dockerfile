FROM eclipse-temurin:21 as jre-build

WORKDIR /techfood

COPY target/techfood-*.jar techfood.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "techfood.jar"]