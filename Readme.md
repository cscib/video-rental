
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
3. Thought the currency can be customized the shop only supports a single currency
4. The bonus points and prices for Premium & Basic can be customized through csv files.


## Build

1. First build the project to generate a Fat Jar.

gradle build

2. Run the jar file in /build/libs library)

## Additional Checks
-----------------

1. CRest API Health Check
http://localhost:8080/swagger-ui.html?


## Movies API

 - `POST    /v1/rental/rent`
 - `POST     /v1/rental/return`

## Example

To create a movie:

`curl -H "Content-Type: application/json" -X POST --data-binary @movie.json http://localhost:8080/v1/rental/rent`

Sample payload for /v1/rental/rent:

{"clientId":"555666M", "rented":[{"movie":"Matrix 11","rentedOn":"2021-03-18@07:53:34.740+0000","expectedReturnOn":"2021-03-21@07:53:34.740+0000"},{"movie":"Spider Man","rentedOn":"2021-03-18@07:53:34.740+0000","expectedReturnOn":"2021-03-21@07:53:34.740+0000"}]}

{"clientId": "555666M","returned": [{
      "expectedReturnOn": "yyyy-MM-dd@HH:mm:ss.SSSZ",
      "movie": "Matrix 11",
      "rentedOn": "2021-03-18@07:53:34.740+0000"
    }
  ]
}