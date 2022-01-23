## Zeraki Backend api

### Requirements

- Java 11
- Maven 3.6
- MySQL
- Postman (for testing only)

### Run

```
mvn spring-boot:run
```

### Build docker image and run

`mvn clean install`
`docker build -t zeraki-backend .`
`docker run --network="host" -t -d zeraki-backend`

### Links

- Postman collection : https://www.getpostman.com/collections/1c03ab9231a65b32a8c9
- Swagger Documentation : http://137.135.85.216:8085/api/swagger-ui/

### Note

- All the end points are protected, use log in request under auth folder
  to get token , replace zeraki-token variable inside the collection with the token value from the responce.
- Student inter institution transfer can be achieved by editing student details, change the courseId to the course available at the new institution.
