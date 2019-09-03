
# Create the product project

# Create the registry service project

- http://localhost:8761/
-

# Create the product client project

- See https://www.baeldung.com/spring-cloud-netflix-eureka

# Installation of the Mongo DB via docker

- Pull the mongodb image from docker hub
```
docker pull mongo
```

## Start the mongo DB
- [Docker hub for mong](https://hub.docker.com/_/mongo)
- [Docker hub for mongo-express](https://hub.docker.com/_/mongo-express)
- Create the stack.yml with the content on mongo db page at docker hub.
- Edit the file and expose the port 27017
- Run 
	- up: build and recreate the container
	- down: stop and destroy the container
	- start: start exiting container
	- stop: stop exiting container
```
docker-compose -f stack.yml start
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
Spring data does not support the lastested version for th Elasticsearch. 

- [Elastic Search On Docker Hub](https://hub.docker.com/_/elasticsearch)
- Install Elastic Search 2.4.3
docker pull elasticsearch:2.4.3

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
- Create a file with products to import on elastic search. ps the last blank line is necessary
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
- Do a Post with the file content to: http://localhost:9200/product/external/_bulk 
- Check the content. Do a post to: http://localhost:9200/product/_search?q=*&pretty
- 

# Criando projeto product search

- Use spring-data-elasticsearch 3.1.X due compatibility with Elastic Search 6.2.2 [See Compatilby Matrix](https://github.com/spring-projects/spring-data-elasticsearch#quick-start) 
- Copy mongo project and change POM.xml to use elasticsearch dependencies
- Change on services

## Create insert, update delete

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

## Create error messages 

# Installation of ActivityMQ

- Fazer um tutorial como este: https://dzone.com/articles/event-driven-microservices-with-spring-boot-and-ac

- We will use ActiveMQ as a reliable messaging mechanism
- [Docker hub for ActiveMP](https://hub.docker.com/r/rmohr/activemq). 
```
docker pull rmohr/activemq:5.15.9
```
- Run the container
```
docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq:5.15.9
```
- [Acess the admin page](http://localhost:8161/admin/) The user and password for url access is admin/admin
- Create a topic for receive product updates

# Implement CQRS

- Project mongo db will be the golder source of the data
- Update the mongo DB project with insert, update, delete methots and expection handler from HSQLDB project

## Use Spring JMS to publish event on ActiviveMQ

## Update Product Search to read the event and update the elasticsearch

# BDD with Cumcumber on Spring boot application

- Tutorial intellij https://www.hindsightsoftware.com/blog/cucumber-jvm-intellij
- https://cucumber.io/docs/guides/10-minute-tutorial/

## Integrating JoCoCo report


# Instalation of Posgress Database
- [Docker Hub](https://docs.docker.com/samples/library/postgres/)
- For user and password see the file Dockerfile.postgres
```
docker build -t cloudnativejava/datastore -f Dockerfile.postgres .
docker run -d -p 5432:5432 --network app_nw  --name datastore   cloudnativejava/datastore
```

# Deployment 

## Containerization

- Create network 
```
docker network create app_nw
```

### Build and Run Eureka server
```
mvn clean package
docker build -t cloudnativejava/eureka-server .
docker run -d -p 8761:8761 --network app_nw --name eureka cloudnativejava/eureka-server
```

### Build and Run Project product
```
mvn clean package -Dmaven.test.skip=true
docker build -t cloudnativejava/product-api .
```

- Run using HSQL
```
mvn clean package -Dmaven.test.skip=true
docker build -t cloudnativejava/product-api .
docker run --rm -d -p 8011:8082 --network app_nw cloudnativejava/product-api
```
- Run using Postgres
```
docker run --rm -d -p 8011:8082 --network app_nw cloudnativejava/product-api --spring.profiles.active=postgres
```


# Create CI/CD Pipeline on Jenkins

- [Install](https://hub.docker.com/r/jenkins/jenkins)  and [Run](https://github.com/jenkinsci/docker/blob/master/README.md) Jenkins using docker
```
docker pull jenkins/jenkins:2.193
docker run -d -p 8080:8080 -p 50000:50000 -v c:/Projetos/Cloud-Native-App-Java/jenkins_home:/var/jenkins_home --name jenkins jenkins/jenkins:2.193
```
- Jenkins will start in the Browser language. 
- Acess localhost:8080 copy the admin password from `/jenkins_home/secrets/initialAdminPassword`
- 
- Senha esoclhida: adminuser/adminpwd
- Waning: In the first login right after start page blank shown. I restarted the jenkins.

## Configuring pipelie

- Created `Jenkinsfile'
- 

