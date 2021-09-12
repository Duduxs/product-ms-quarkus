cd "$(find ~ -type d -name product-ms-quarkus | head -1)"

mvn clean package -Dmaven.test.skip=true

docker image build -t product-ms-quarkus . -f ./src/main/docker/Dockerfile

docker-compose -f src/main/docker/docker-compose.yaml up -d product-ms-database
docker-compose -f src/main/docker/docker-compose.yaml up -d product-ms-quarkus

echo "containers gerados com sucesso!"
