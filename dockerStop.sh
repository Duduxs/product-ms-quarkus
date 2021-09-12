docker container rm --force product-ms-quarkus && docker container rm --force product-ms-quarkus-database

docker image rm $(docker images 'product-ms-quarkus')
docker image rm $(docker images 'mongo')

echo "containers & suas imagens foram parados e removidos com sucesso!"
