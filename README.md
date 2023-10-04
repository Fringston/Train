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

The project is divided into to modules. 
One acts as a Web-API which has the responsibility to handle all the requests from the client-app and communicate with the kafkaserver and database.
The other module is the client-application.

When the API-module is started, it will create a kafka topic called UserTopic. This topic will be used to store all the users that are created.
When a user is created, the user will be sent to the UserTopic. And a consumer will be listening to the topic and will save the user to a database.

There is also another consumer that can be called upon by the user to get all the users from the UserTopic. 
This consumer will then send all the users back to the user.


## Installation

Before you can start this project, you must follow these steps:

- Install Apache Kafka on your local machine.
- Clone this repository to your local machine.


## Usage

- Open the project in your IDE.
- Start the Zookeeper and Kafka server.
- Change the username and password in the application.properties file in the APIService-module for mySQL to your own.
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
