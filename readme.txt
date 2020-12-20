# Product And Review Microservices

## To run the application


1. Execute `mvn package` or `mvn package -Dmaven.test.skip=true` inside **product** and **review** pom location to generate executable standalone jar in `targer` folder. 
2. `java -jar <JAR_FILE>` to run microservices.

By default, Application uses **in-memory database(H2)** to store data.

To configure MySQL to store data, 
1. Create a database in MySQL instance named `product`.
2. Update the database credentials in `application-prod.properties`.
3. Execute `mvn package` or `mvn package -Dmaven.test.skip=true` inside **product** and **review** pom location to generate executable standalone jar in `targer` folder.
2. `java -jar -Dspring.profiles.active=prod  <JAR_FILE>` to run microservices.

Once both service up, You can able to access swagger using below endpoint.

| Service  | Swagger  | 
| ------------ | ------------ |
| Review  | http://localhost:8080/swagger-ui/#  |
| Product  | http://localhost:8090/swagger-ui/#  |

## JWT Token
JWT token can be generated from swagger.


### Product Service
| API  | JWT Token  | 
| ------------ | ------------ |
| Create and Update Products  | Product token required |
| Add review for Product  | User token required  |
| Get Product and Get Review  | No token required  |

### Review Service
| API  | JWT Token  | 
| ------------ | ------------ |
| Create review group  | Product token required |
| Add review | User token required  |
| Get review group and Get review | User token required  |
