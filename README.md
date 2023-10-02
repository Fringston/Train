# WorkoutGenerator

## Description

This is an application that will help you create workouts.

I wanted to make an app for those times that I didn't have any inspiration for my workout, which happens from time to time.
I wanted to:
1. Be able to get a completely random workout if I wanted to.
2. Be able to choose which specific muscle groups to train and get a workout for those muscle groups (this feature is not finished yet).
3. Be able to keep track of your previous workouts so that the next one is different and your muscles have the time to recover (this feature is not finished yet).

All the features are not finished yet, but I will continue to work on them. This project was a lot more complicated than I thought it would be.
So I have just focused on getting the basic functionality to work first. As in:
- Be able to create a user.
- Be able to log in.
- Be able to get all users from the kafka topic.

The project is divided into to modules. One works as a Web-API and the other is the client-app.


## Installation

What are the steps required to install your project? Provide a step-by-step description of how to get the development environment running.
Before you can start this project, you must follow these steps:

- Install Apache Kafka on your local machine.
- Clone this repository to your local machine.


## Usage

Provide instructions and examples for use. Include screenshots as needed.

- Open the project in your IDE.
- Start the Zookeeper and Kafka server.
- Start the Web-API module.
- Start the client-app module.
- Follow the instructions in the terminal to use the app.


## Credits

I received help and inspiration from the following sources:

* [Marcus Henriksson](https://github.com/MarcusRestoryAi)
* [ChatGPT](https://openAI.com)

## Dependencies

* [JSON Simple](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple)
* [Apache Http Client 5](https://mvnrepository.com/artifact/org.apache.httpcomponents.client5/httpclient5)
* [Spring Framework](https://mvnrepository.com/artifact/org.springframework.kafka/spring-kafka/3.0.11)
* [Spring JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.1.4)
* [Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.1.4)
* [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test/3.1.4)
* [Junit Jupiter 5](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter/5.7.0)
* [mySQL Connector](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.23)


## License

[MIT License](https://choosealicense.com/licenses/mit/).

---


## Tests


Go the extra mile and write tests for your application. Then provide examples on how to run them here.


<hr><sub>Licens: Apache 2.0 | © 2023 Marcus Medina, Campus Mölndal. Alla rättigheter förbehållna.<br>Du får använda och modifiera detta verk enligt villkoren i Apache License, Version 2.0. Du får inte använda detta verk för kommersiella ändamål utan tillstånd från upphovsmannen.</sub>
