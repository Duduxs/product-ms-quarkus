FROM openjdk:11

ENV MONGODB_URL="mongodb://admin:admin123@product-ms-database:27015/product-ms"

WORKDIR /deployments

COPY target/quarkus-app/lib/ /deployments/lib/
COPY target/quarkus-app/*.jar /deployments/
COPY target/quarkus-app/app/ /deployments/app/
COPY target/quarkus-app/quarkus/ /deployments/quarkus/

CMD ["java", "-jar", "quarkus-run.jar"]

RUN ls -l /deployments

RUN echo '\n\n * * * Imagem gerada com sucesso * * * \n'


