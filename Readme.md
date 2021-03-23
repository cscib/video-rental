
# Video Rental Store implemented using Java and Spring Boot.

## Aim

Our aim is to build a system for managing the rental administration.

The basic features of the product are:

 - Film inventory
 - Price calculation for rentals
 - Tracking customers' “bonus” points

## Build

1. First build the project to generate a Fat Jar & build the docker image using the Jar file.

gradle build && docker build -t java-app/latest .

2. To launch on docker (Spring Boot and MySQL processes)

docker-compose up -d

## Additional Checks
-----------------
1. To check DB instance [use password 'video123!']
mysql -u video -p --protocol=TCP --port=3309

## Assumptions
-----------------
1. A person will return all the items rented at one go not x items today and y items tomorrow.

## Movies API

 - `POST    /v1/movie/create`
 - `GET     /v1//movie/all`
 - `GET     /v1//movie/{movieId}`


## Example

To create a movie:

`curl -H "Content-Type: application/json" -X POST --data-binary @movie.json http://localhost:8080/v1/movie/create`

Returned:

