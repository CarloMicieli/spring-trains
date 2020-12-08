# Spring Trains

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)[![forthebadge](https://forthebadge.com/images/badges/powered-by-coffee.svg)](https://forthebadge.com)

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![GitHub last commit](https://img.shields.io/github/last-commit/CarloMicieli/spring-trains)
![Build](https://github.com/CarloMicieli/spring-trains/workflows/Build/badge.svg)
![Release](https://github.com/CarloMicieli/spring-trains/workflows/Release/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/CarloMicieli/spring-trains/badge.svg?branch=main)](https://coveralls.io/github/CarloMicieli/spring-trains?branch=main)

A web api for model railway collections with `Spring Boot 2.4`.

## Requirements

- Java 11
- Docker and Docker compose

## How to run

To run the application using `maven` or `docker`, a running postgres instance must be available on localhost.

This command will run postgres inside a docker container:

```
$ ./run-psql.sh
```

### Maven

To run the application using `maven`:

```
$ ./mvnw spring-boot:run
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.4.0)
```

To run the application as a docker container:

```
$ ./mvnw clean install
$ docker build . -t spring-trains:latest
```

at this point, to run the container:

```
$ docker run -p8080:8080 spring-trains:latest
```

### Docker compose

```
$ docker-compose build
$ docker-compose up -d
```

To stop the application:

```
$ docker-compose down
```

## Test the web api

```
$ pip install -U httpie
$ pip install -U httpie-jwt-auth
```

Create a new user

```
$ http POST :8080/api/auth/register username=george password=Stephenson
HTTP/1.1 200 
```

in order to make api calls, it is required a JWT token. To get a token is required for the user to make a login:

```
$ http POST :8080/api/auth/signin username=george password=Stephenson
HTTP/1.1 204 
Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaWNjaW5hMiIsImlzcyI6ImV4YW1wbGUuaW8iLCJpYXQiOjE2MDU0Mjk3OTAsImV4cCI6MTYwNjAzNDU5MH0.PI40WTav4GwKvGYRdXujZh00NKk40CCQkXXrZY4Q4txrYv279edEwuIE9gc3vZPI00dl9_5hy3L_cMRXhZlZbQ
```

```
$ export JWT_AUTH_TOKEN=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiO....
$ http --auth-type=jwt :8080/api/collections
HTTP/1.1 200 
```

```
$ http --auth-type=jwt POST :8080/api/catalogItems brand=ACME itemNumber=1234 description="My first item" category="C"
HTTP/1.1 201 
Location: http://localhost:8080/api/catalogItems/4
```

```
$ http --auth-type=jwt :8080/api/catalogItems/4
HTTP/1.1 200 
Content-Type: application/hal+json

{
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/catalogItems/4"
        }
    },
    "catalogItem": {
        "brand": "ACME",
        "category": "C",
        "description": "My first item",
        "id": 4,
        "itemNumber": "1234"
    }
}
 
```

## Modules

* `common`: contains generic data type for the application (like `Length`s) and all interfaces for the **clean architecture** implementation (use cases and queries)
* `catalog`: domain entities and business logic for the **catalog** module
* `collecting`: domain entities and business logic for the **collecting** module
* `infrastructure`: contains the actual persistence implementation for use case and queries, it also contains the infrastructure code for security and the web layer. This is the first module with a dependency on the spring framework
* `webapi`: the restuful web api layer

## Clean architecture

### Use cases

**Use cases** are managing commands in the application. They are basically functions that given a __use case input__ are producing a __use case output__. In this implementation the output is produced as a side effect on a corresponding __output port__.

```java
public interface UseCaseInput {}

@FunctionalInterface
public interface UseCase<InType extends UseCaseInput> {
  void execute(InType input);
}
```

### Queries

On the other hand, **Queries** are handling the reading side of the application. The application is using different kind of queries

```java

public interface Query<C extends Criteria, T> {}

public interface SingleResultQuery<C extends Criteria, T> extends Query<C, T> {
  Optional<T> execute(C criteria);
}

public interface PaginatedQueryWithCriteria<C extends Criteria, T> extends Query<C, T> {
  PaginatedResult<T> execute(C criteria, Page currentPage, Sorting orderBy);
}
```
