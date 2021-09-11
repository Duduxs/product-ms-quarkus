<h4 align="center">
  <p>Product MicroService</p>
  
  <p>This application was developed during the UOL Compass' selection process.</p>

  <p>This project is based in CRUD(Create, Read, Update and Delete) with Quarkus and Java.</p>
  
</h4>

<p align="center">
  <a href="#rocket-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#information_source-how-to-use">How To Use</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#thumbsup-how-to-contribute">How To Contribute</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#memo-license">License</a>
</p>

<p align="center">
<img alt="Collage" src="https://i.imgur.com/1tEDin1.png"> 
</p>

## :rocket: Technologies

This project was developed with the following technologies:

- API:

  - [Java](https://www.oracle.com/br/java/)
  - [Quarkus](https://quarkus.io/)
  - [Mongo](https://www.mongodb.com/pt-br)
  - [Morphia](https://github.com/MorphiaOrg/morphia)
  - [JAX-RS](https://en.wikipedia.org/wiki/Jakarta_RESTful_Web_Services)
  - [Hibernate Validator](http://hibernate.org/validator/)
  
- Utils:

  - [Swagger](https://quarkus.io/guides/openapi-swaggerui)
  
- Tests:
 
  - [JUnit5](https://junit.org/junit5/docs/current/user-guide/)
  - [RestAssured](https://rest-assured.io/)
  - [TestContainers](https://www.testcontainers.org/)


## :information_source: How to use
To clone and run this application, you'll need [Git](https://git-scm.com), [Docker](https://www.docker.com/) and [DockerCompose](https://docs.docker.com/compose/) installed on your computer. From your command line:

```bash
# Clone this repository
$ git clone https://github.com/duduxs/product-ms-quarkus

# Go into the repository
$ cd product-ms-quarkus
```

To run the API server:

```bash
$ ./mvnw compile quarkus:dev 
```

To run the DB:
```bash
# execute the sh script called init.sh a container with mongo will appear to persist your datas
# to exclude the container with all datas, exec stop.sh script
```

Now access on your browser: http://localhost:9999 [Tests -> 8888]

<p align="center">
  docs -> http://localhost:9999/q/doc
<img alt="Doc" src="https://i.imgur.com/XshHSza.png"> 
</p>

## :thumbsup: How To Contribute

-  Make a fork;
-  Create a branch with your feature: `git checkout -b my-feature`;
-  Commit changes: `git commit -m 'feat: My new feature'`;
-  Make a push to your branch: `git push origin my-feature`.

## :memo: License
This project is under the MIT license. See the [LICENSE](https://github.com/Duduxs/product-ms-quarkus/blob/master/LICENSE) for more information.

---

<h4 align="center">
    Made by Eduardo José 😆 <a href="https://www.linkedin.com/in/eduarddojose/" target="_blank">Contact me!</a>
</h4>
