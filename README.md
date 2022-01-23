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

`docker build -t zeraki-backend .`
`docker run --network="host" -t -d zeraki-backend`
