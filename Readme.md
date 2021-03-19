
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

