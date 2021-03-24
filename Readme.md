
# Video Rental Store implemented using Java and Spring Boot.

## Aim

Our aim is to build a system for managing the rental administration.

The basic features of the product are:

 - Film inventory
 - Price calculation for rentals
 - Tracking customers' “bonus” points

## Assumptions
-----------------
1. A client ID is his ID Card or Passport Number
2. If a client chooses the videos to rent that means they're physically available at the shop.
3. No validations are done to check if they're rented though it can be done with the current schema
4. The currency can be customized but the shop only supports a single currency
5. The bonus points and prices for Premium & Basic can be customized through csv files.
6. For simplicity, H2 in memory db was used and therefore data is refreshed everytime on restart
7. Because of time issues, there is only a Test for a Controller method.

## Build

1. First build the project to generate a Fat Jar.

gradle build

2. Run the jar file in /build/libs library
Example:
/opt/jdk-12/bin/java -jar ~/dev/repositories/java/video-rental/build/libs/video-rental-0.0.1-SNAPSHOT.jar

## Additional Checks
-----------------

1. Rest API Health Check
http://localhost:8080/swagger-ui.html?

2. H2 Memory Database
http://localhost:8080/h2/login.do?

## Movies API

 - POST    /v1/rental/rent
 - POST     /v1/rental/return

## Example

To rent a movie:

curl -H "Content-Type: application/json" -X POST --data-binary @rentmovie.json http://localhost:8080/v1/rental/rent

To return a movie:

curl -H "Content-Type: application/json" -X POST --data-binary @returnmovie.json http://localhost:8080/v1/rental/retur

Sample payload for /v1/rental/rent:

{
  "clientId": "555666M",
  "rented": [
     {"movie": "Matrix 11",
      "rentedOn":"2021-03-18@07:53:34.740+0000",
      "expectedReturnOn":"2021-03-21@07:53:34.740+0000"
     },
     {"movie":"Spider Man",
      "rentedOn":"2021-03-18@07:53:34.740+0000",
      "expectedReturnOn":"2021-03-21@07:53:34.740+0000"
     }
  ]
}

Sample payload for /v1/rental/return:

{
  "clientId": "555666M",
  "returned": [
    "Matrix 11"
  ]
}