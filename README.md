# Stock exchange sample project

## Task summary

* Java 11 target runtime and features
* Junit 5 implementation for Controller and Service layer
* 100% code coverage along with Jacoco test report generation when Run -> Maven Install followed by Run -> Maven test.
* Swagger UI implementation for documentation and Open API
* Implemented automatic Google Checkstyle when Run -> Maven Install operation is performed.
* Implemented Spring Validators at Controller level.
* H2 in memory database with pre-loaded data as per requirements.
* Lombok library to reduce boiler plate code.
* Spring-Data-JPA model of implementation.

## Other information

This application uses a H2 in memory database. You can use
the H2 console to browse what schema and data is in the database.

### Browse the database contents

With the Spring Boot application running, browse to:

* http://localhost:8080/ or http://localhost:8080/h2-console

Then connect using the following:

Saved Settings: Generic H2 (Embedded)
Setting Name:   Generic H2 (Embedded)
-------------------------------------
Driver Class:   org.h2.Driver
JDBC URL:       jdbc:h2:mem:test
User Name:      sa
Password:     

### Database initialisation

The Spring Boot application initialises the database with the data found in 
the resource file:

src/main/resources/data.sql

### Service endpoints

#### get a list of stocks #### 

HTTP GET /api/stocks

or 

HTTP GET/api/stocks?pageSize=25&pageNo=0&sortBy=id


#### create a stock #### 

HTTP POST /api/stocks

{
    "name": "sheldon",
    "currentPrice": 100.00
}

#### get one stock from the list #### 

HTTP GET /api/stocks/1

#### update the price of a single stock #### 

HTTP PATCH /api/stocks/1

{
    "price": 200.50
}

#### delete a single stock #### 

HTTP DELETE/api/stocks/1
