## Memsource service

### Local Development

* Ensure Docker desktop is running.

* Run docker-compose for preparing database
    * `docker-compose up -d`
    
* Run appplication with spring profile `local`:
    * Either pass `-Dspring.profiles.active=local` as VM argument or use Spring Boot run configuration to set active profiles field. 
    * Or run from command line with: `./gradlew bootRun --args='--spring.profiles.active=local'`

* Actuator info endpoint:
    http://localhost:8001/actuator/info

* Swagger endpoint:
    http://localhost:8001/swagger/swagger-ui.html

* Postman collection in the project: `memsource-service.postman_coll.json`

* Connecting to database
    * `docker-compose run db bash`
    
* Stop database with:
    * `docker-compose down`
