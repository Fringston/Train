# WorkoutGenerator

## Description

This is an application that will help you create workouts.

I wanted to make an app for those times that I didn't have any inspiration for my workout, which happens from time to time.
I wanted to:
1. Be able to get a completely random workout if I wanted to.
2. Be able to choose which specific muscle groups to train and get a workout for those muscle groups (not finished yet).
3. Be able to keep track of your previous workouts so that the next one is different and your muscles have the time to recover (not finished yet).

All the features are not finished yet, but I will continue to work on them. This project was a lot more complicated than I thought it would be.
So I have just focused on getting the basic functionality to work first. As in:
- Be able to create a user.
- Be able to log in.
- Be able to (as an admin) get all users from the kafka topic.

The project is divided into to modules. One works as a Web-API and the other is the client-app.


Börja att göra appen för en admin-inloggning som kan göra mycket mer än en vanlig användare.

### Admin ska kunna:
- Allt en vanlig användare kan göra, plus:


- Skapa/skicka exercices till topics --> databas.
    - Skapa en metod som skickar en exercice till EcerciseTopic --> databasen. (Måste innehålla fk musclegroup).
- Hämta **alla användare** från UserTopic via en consumer. (Uppfyller kravet på att kunna hämta data från en topic via en consumer i klientappen)
    - Skapa en consumer som hämtar alla användare från UserTopic och skriver ut dem i terminalen.
- Skapa ny muskelgrupp.
    - Skapa en metod som skapar en ny muskelgrupp i MuscleGroupTopic --> databasen.


### Vanlig användare ska kunna:
- Skapa ett konto. (Uppfyller kravet på att skicka data i JSON-format från klienten via ett Web-API till en producer som sparar data i en kafka-topic)
    - Skapa en metod som skapar en user i UserTopic och en consumer som i sin tur sparar user i databasen.
- Hämta en random exercice från databasen.
    - Skapa en metod som hämtar en random exercice från databasen.
- Hämta en specifik exercice från databasen baserat på vilken muskelgrupp den tillhör.
    - Skapa en metod som hämtar en specifik exercice från databasen baserat på vilken muskelgrupp den tillhör.



## Table of Contents (Optional)

If your README is long, add a table of contents to make it easy for users to find what they need.

- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [License](#license)

## Installation

What are the steps required to install your project? Provide a step-by-step description of how to get the development environment running.
Before you can start this project, you must follow these steps:

- Install Apache Kafka on your local machine.
- Clone this repository to your local machine.


## Usage

Provide instructions and examples for use. Include screenshots as needed.

- Open the project in your IDE.
- Start the Zookeeper and Kafka server.
- Start the Web-API.
- Start the client-app.
- Follow the instructions in the terminal to use the app.


To add a screenshot, create an `assets/images` folder in your repository and upload your screenshot to it. Then, using the relative filepath, add it to your README using the following syntax:

    ```md
    ![alt text](assets/images/screenshot.png)
    ```

## Credits

List your collaborators, if any, with links to their GitHub profiles.
* [member 1](https://github.com/person1)
* [member 2](https://github.com/person1)

If you used any third-party assets that require attribution, list the creators with links to their primary web presence in this section.
* [junit jupiter 5](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter/5.7.0)

If you followed tutorials, include links to those here as well.

## License

The last section of a high-quality README file is the license. This lets other developers know what they can and cannot do with your project. If you need help choosing a license, refer to [MIT License](https://choosealicense.com/licenses/mit/).

---

🏆 The previous sections are the bare minimum, and your project will ultimately determine the content of this document. You might also want to consider adding the following sections.

## Badges

![badmath](https://img.shields.io/github/languages/top/lernantino/badmath)

Badges aren't necessary, per se, but they demonstrate street cred. Badges let other developers know that you know what you're doing. Check out the badges hosted by [shields.io](https://shields.io/). You may not understand what they all represent now, but you will in time.

## Features

If your project has a lot of features, list them here.


## Tests

Go the extra mile and write tests for your application. Then provide examples on how to run them here.
<hr><sub>Licens: Apache 2.0 | © 2023 Marcus Medina, Campus Mölndal. Alla rättigheter förbehållna.<br>Du får använda och modifiera detta verk enligt villkoren i Apache License, Version 2.0. Du får inte använda detta verk för kommersiella ändamål utan tillstånd från upphovsmannen.</sub>
