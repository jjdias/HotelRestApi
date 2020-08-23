# HotelRestApi
Simple rest api using Spring boot, postgres, docker, flyway.

Missing features:
  Some unit tests.
  Exeption and error catches.
  Front end.
  I messed up the names of almost everything.

## Docker
Install docker and run the following comend on terminal.
``` PowerShell
# pull fo postgres image
docker pull postgres:11-alpine
# Verify images
docker images
# Creating dock
docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:11-alpine
# Type docker ps to get the docs id
docker ps
# And finally. Enter the dock
docker exec -it "The image id" bin/bash
```

## Seting up the Database inside Postgres
We will create the database that the app will use.
``` Bash
# From inside the bash type.
psql -U postgres
# You should get a message
psql (11.9)
Type "help" for help.
# We can now create the DB
CREATE DATABASE hotelCalifornia;
# To check if everything worked try typing 
\l
# And thats mostly it. You can jump into the hotel db by typing
\c hotelcalifornia
```

## Running in inteliJ
Clone the repository and the table setup should be done by Flyway ther first time you run it.
