FROM openjdk:20-ea-1-jdk-slim
VOLUME /tmp
COPY target/catalog-service-0.0.1.jar CatalogService.jar
ENTRYPOINT ["java", "-jar", "CatalogService.jar"]