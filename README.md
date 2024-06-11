# Project SBNZ



## Internet provider
Application designed for interacting with services of an internet provider,
with drools support.
* Making and cancelling contracts
* Contract discounts
* Real time updates about service availability
* Tech support Technical issue diagnostics
* Support for complaints
  


## Requirements
* Java 17
* Node.js and npm
* Maven
* [PostgreSQL database](https://www.postgresql.org/) listening on port 5432


## Setup

1. Install Java and Maven
2. Install and start PostgreSQL
   - Set credentials for authentication in application.properties (`spring.datasource.username` and `spring.datasource.password`) and create database with name `SBNZ2024` 
3. Load dependencies of back (`pom.xml`) with Maven
4. Do `mvn install` od kjar and models application in back
5. Set your self in front folder. Run `npm install` and start application with `npm start`. And go to localhost://3000
7. Run applications in following order:
   1) PostgreSQL database
   2) Back model application
   3) Back service application
   4) Front application
      

## Specification
Project specification is given in specifikacije.docs file


## Authors
* [Katarina Spremić]()
* [Selena Milutin]()


## Project status
Complete. 🎉
