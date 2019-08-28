
# Create the product project

# Create the registry service project

- http://localhost:8761/

# Create the product client project



# Installation of the Mongo DB via docker

- Pull the mongodb image from docker hub
```
docker pull mongo
```

## Start the mongo DB

- Create the stack.yml with the content on mongo db page at docker hub.
- Edit the file and expose the port 27017
- Run
```
docker-compose -f stack.yml up
```
- To stop
```
docker-compose -f stack.yml stop
```

## Test the connection

- Acess the Mongo DB express 
http://localhost:8081/

- Test authentication on Intellij Using MongoDb Plugin 
Username: mongoadmin
Password: mongopwd
Database: admin
Auth. Mechanism: SCRAM-SHA-1

- Test connection by commandline
```
docker run -it --rm --network mongo_default mongo mongo --host mongo -u mongoadmin -p mongopwd --authenticationDatabase admin product-db
```

## Links

- [Docker hub for mong](https://hub.docker.com/_/mongo)
- [Docker hub for mongo-express](https://hub.docker.com/_/mongo-express)
- Comand line to connect: 

## Populating  Mongo DB

- Acess the mongo express: http://localhost:8081/
- Create a `masterdb` database
- Create a `product` collection
- Create a `product.json` file with the following content
```
{"_id":"1","name":"Apples","catId":1},
{"_id":"2","name":"Oranges","catId":1},
{"_id":"3","name":"Bananas","catId":1},
{"_id":"4","name":"Carrot","catId":2}
```
- Import this file into the collection `product`
- Refresh the page to see the content

# Create a Product-mongodb-service
- Change the product service that use HSQLDB to use mongo DB
- Duplicate the project
- Clean up the pom: Change project name
- Clean up the pom: replace  ..data-jpa by ..data-mongodb / remove hsqldb / remove cache
- Change the Product Entity 

# Installation of Elasticsearch
Spring data does not support the lastested version for the Elasticsearch so you need to install the verion 2.4.3 [See Compatilby Matrix](https://github.com/spring-projects/spring-data-elasticsearch/wiki/Spring-Data-Elasticsearch---Spring-Boot---version-matrix) 

- [Elastic Search On Docker Hub](https://hub.docker.com/_/elasticsearch)
```
docker pull elasticsearch:2.4.3
```

## Iniciar Elasticsearch

- Inicializar primeiro a imagem do MongoDB. Será criado uma rede _mongo_default_ . Para verificar `docker network ls`
- Inicializar elast search
```
docker run -d --name elasticsearch --net mongo_default -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:2.4.3
```
- See de logs 
```
docker logs -f elasticsearch
```
## Populating Elasticsearch
- Do a POST with content bellow to the url  http://localhost:9200/product/external/_bulk 
```
{"index":{"_id":"1"}} 
{"id":"1","name":"Apples","catId":1} 
 
{"index":{"_id":"2"}} 
{"id":"2","name":"Oranges","catId":1} 
 
{"index":{"_id":"3"}} 
{"id":"3","name":"Bananas","catId":1} 
 
{"index":{"_id":"4"}} 
{"id":"4","name":"Carrot","catId":2}

```
- Check the content. Do a GET  to: http://localhost:9200/product/_search?q=*&pretty


# Criando projeto product search

- copiado o projeto que conecta com o mongodb `product-mongo`
- Removido do pom dependencia com mongodb e adicionado eleasticsearch 

# Adicionando, insert, update, delete


- For Inserting POST to http://localhost:8083/product
```
{
	"name":"Grapes",
	"catId":1
}
```
- For updating PUT to http://localhost:8083/product/3
```
{
	"name":"New name",
	"catId":1
}
```
- For deleting DELETE to http://localhost:8083/product/3
- 
# Eureka

```
mvn clean package
docker build -t cloudnativejava/eureka-server .
docker run -d -p 8761:8761 --network app_nw --name eureka cloudnativejava/eureka-server
```
#Product with HSQL

```
mvn clean package -Dmaven.test.skip=true
docker build -t cloudnativejava/product-api .
docker run --rm -d -p 8011:8082 --network app_nw cloudnativejava/product-api
```

# Postgres Database

- [Docker Hub](https://docs.docker.com/samples/library/postgres/)
- For user and password see the file Dockerfile.postgres
```
build -t cloudnativejava/datastore -f Dockerfile.postgres .
docker run -d -p 5432:5432 --network app_nw  --name datastore   cloudnativejava/datastore
```

# Product with Postgres

```
docker run --rm --name product-postgres1 -d -p 8013:8080  --network app_nw  cloudnativejava/product-api --spring.profiles.active=postgres
```






