package fredrikkodar.ui;

import fredrikkodar.entities.ExerciseEntity;
import fredrikkodar.entities.UserEntity;
import fredrikkodar.kafka.KafkaConsumer;
import fredrikkodar.service.ExerciseService;
import fredrikkodar.service.UserService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.InputMismatchException;
import java.util.Scanner;

//Klass som hanterar användargränssnittet
public class UserInterface {

    RestTemplate restTemplate = new RestTemplate();
    UserService userService = new UserService(restTemplate);

    //Skapa en scanner för att läsa in användarinput
    static Scanner scanner = new Scanner(System.in);

    //Skapa en boolean för att hålla koll på om programmet ska köras eller inte
    boolean run = true;

    //Metod för att visa huvudmenyn
    public void displayMainMenu() {
        while (run) {
            System.out.println("Welcome to the Training App!\n");
            System.out.println("Do you want to: ");
            System.out.println("1. Log in");
            System.out.println("2. Create an account");
            System.out.println("3. Exit");

            try {
                int userChoice = scanner.nextInt();
                scanner.nextLine(); // Läs bort ny rad efter nästa int

                switch (userChoice) {
                    case 1 -> logIn();
                    case 2 -> createAccount();
                    case 3 -> {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please press 1 to log in, 2 to create an account or 3 to exit.\n");
                scanner.nextLine();
            }
        }
    }

    //Metod för att visa menyn efter inloggning
    public void displayLoggedInMenu() {
        int userChoice;
        do {
            System.out.println("\nWelcome!");
            System.out.println("Do you want to: \n");
            System.out.println("1: Get user information");
            System.out.println("2: Train");
            System.out.println("3: Exit");

            userChoice = scanner.nextInt();
            scanner.nextLine(); // Läs bort ny rad efter nästa int

            switch (userChoice) {
                case 1 -> {
                    //Se alla användaruppgifter
                    System.out.println("Here are all the current users:");
                    KafkaConsumer.getUserDataFromKafka("UserTopic");
                }
                case 2 ->
                    //Skapa träningspass
                        displayTrainMenu();
                case 3 -> {
                    //Avsluta
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                //Om användaren inte väljer 1, 2 eller 3 så skrivs ett felmeddelande ut
                default ->
                        System.out.println("Invalid input. Please press 1 to update user information, 2 to create a workout or 3 to log out.");
            }
        } while (userChoice != 3);
        scanner.close();
    }

    //Metod för att skapa ett konto
    public void createAccount() {

        System.out.println("Please choose a username: ");
        String newUsername = scanner.nextLine();

        while (newUsername.equals("")) {
            System.out.println("Invalid input. Please choose a username: ");
            newUsername = scanner.nextLine();
        }

        System.out.println("Please choose a password: ");
        String newPassword = scanner.nextLine();

        while (newPassword.equals("")) {
            System.out.println("Invalid input. Please choose a password: ");
            newPassword = scanner.nextLine();
        }
        UserService.createUser(new UserEntity(newUsername, newPassword));
    }

    //Metod för att logga in
    public void logIn() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        // Loopa tills användaren anger ett giltigt användarnamn
        while (username.equals("")) {
            System.out.println("Invalid input. Please enter your username:");
            username = scanner.nextLine();
        }

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        // Loopa tills användaren anger ett giltigt lösenord
        while (password.equals("")) {
            System.out.println("Invalid input. Please enter your password:");
            password = scanner.nextLine();
        }

        try {
            // Kolla om användaren finns i databasen
            if (userService.authenticate(username, password)) {
                // Visa nästa meny efter inloggning
                displayLoggedInMenu();
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    //Metod för att hantera övningar och muskelgrupper
    public void displayTrainMenu() {
        System.out.println("Do you want to: \n");
        System.out.println("1: Choose muscle groups");
        System.out.println("2: Get a random exercise");
        System.out.println("3: Add a new exercise");
        System.out.println("0: Go back");

        int muscleChoice = scanner.nextInt();
        scanner.nextLine(); // Läs bort ny rad efter nästa int

        switch (muscleChoice) {
            case 1 -> chooseMuscleGroup();
            case 2 -> {
                System.out.println("Here is a random exercise: \n");
                KafkaConsumer.getExerciseDataFromKafka("ExerciseTopic");
            }
            case 3 -> {
                System.out.println("Add a new exercise! \n");
                createExercise();
            }
            case 0 ->
                //Använder rekursion för att gå tillbaka till föregående meny
                    displayLoggedInMenu();
        }
    }

    //Metod för att skapa en ny övning
    public void createExercise() {
        System.out.println("Please enter the name of the exercise: ");
        String exerciseName = scanner.nextLine();

        System.out.println("Please choose the muscle group of the exercise: ");
        System.out.println("1: Chest");
        System.out.println("2: Back");
        System.out.println("3: Legs");
        System.out.println("4: Shoulders");
        System.out.println("5: Arms");
        System.out.println("6: Abs\n");

        int muscleGroup = scanner.nextInt();
        scanner.nextLine(); // Läs bort ny rad efter nästa int
        String chosenMuscleGroup = "";
        try {
            switch (muscleGroup) {
                case 1 -> chosenMuscleGroup = "chest";
                case 2 -> chosenMuscleGroup = "back";
                case 3 -> chosenMuscleGroup = "legs";
                case 4 -> chosenMuscleGroup = "shoulders";
                case 5 -> chosenMuscleGroup = "arms";
                case 6 -> chosenMuscleGroup = "abs";
            }
            ExerciseEntity newExercise = new ExerciseEntity(exerciseName);
            ExerciseService.addExerciseToTopic(newExercise);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please choose a valid option.\n");
            scanner.nextLine();
        }
    }

    //Metod för att välja muskelgrupp
    public void chooseMuscleGroup() {
        // Välj muskelgrupper att träna
        System.out.println("Please choose which muscle groups to train: ");
        System.out.println("1: Chest");
        System.out.println("2: Back");
        System.out.println("3: Legs");
        System.out.println("4: Shoulders");
        System.out.println("5: Arms");
        System.out.println("6: Abs\n");
        System.out.println("0: Go back");

        int muscleGroupChoice = scanner.nextInt();
        scanner.nextLine(); // Läs bort ny rad efter nästa int

        switch (muscleGroupChoice) {
            case 1 -> {
                System.out.println("You chose to train chest!");
                System.out.println("Here is your workout: \n");
            }
            //getExercise("chest");
            case 2 -> {
                System.out.println("You chose to train back!");
                System.out.println("Here is your workout: \n");
            }
            //getExercise("back");
            case 3 -> {
                System.out.println("You chose to train legs!");
                System.out.println("Here is your workout: \n");
            }
            //getExercise("legs");
            case 4 -> {
                System.out.println("You chose to train shoulders!");
                System.out.println("Here is your workout: \n");
            }
            //getExercise("shoulders");
            case 5 -> {
                System.out.println("You chose to train arms!");
                System.out.println("Here is your workout: \n");
            }
            //getExercise("arms");
            case 6 -> {
                System.out.println("You chose to train abs!");
                System.out.println("Here is your workout: \n");
            }
            //getExercise("abs");
            case 0 ->
                //Använder rekursion för att gå tillbaka till föregående meny
                    displayTrainMenu();
            default -> System.out.println("Invalid input. Please choose a valid option.\n");
        }
    }
}