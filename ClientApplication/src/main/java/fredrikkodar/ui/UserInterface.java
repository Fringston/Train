package fredrikkodar.ui;

import fredrikkodar.kafka.KafkaConsumer;
import fredrikkodar.service.UserService;
import java.util.Scanner;


public class UserInterface {

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

            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Läs bort ny rad efter nästa int

            switch (userChoice) {
                case 1:
                    logIn();
                    //displayLoggedInMenu(); ?
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please press 1 to log in, 2 to create an account or 3 to exit.\n");
                    break;
            }
        }
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

        // Kolla om användaren finns i databasen
        if (UserService.authenticate(username, password)) {
            // Visa nästa meny efter inloggning
            displayLoggedInMeny();
        } else if (!UserService.authenticate(username, password)) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    //Metod för att visa menyn efter inloggning
    public void displayLoggedInMeny() {
        int userChoice2;
        do {
            System.out.println("\nWelcome!");
            System.out.println("Do you want to: \n");
            System.out.println("1: Get user information");
            System.out.println("2: Create a workout");
            System.out.println("3: Exit");

            userChoice2 = scanner.nextInt();
            scanner.nextLine(); // Läs bort ny rad efter nästa int

            switch (userChoice2) {
                case 1:
                    //Se alla användaruppgifter
                    System.out.println("Here are all the current users:");
                    KafkaConsumer.getDataFromKafka("UserTopic");
                    break;
                case 2:
                    // Skapa träningspass
                    createWorkout();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please press 1 to update user information, 2 to create a workout or 3 to log out.");
                    break;
            }
        } while (userChoice2 != 3);
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
        //Kolla om användarnamnet redan finns i databasen
        //Om det finns, skriv ut att användarnamnet redan finns och be användaren försöka igen
        //Om det inte finns, skapa en ny användare
        /*if (UserService.authenticate(newUsername, newPassword)) {
            System.out.println("Username already exists. Please try again.");
        } else {*/
        // Skapa en ny User och skicka den genom kafka till databasen
        UserService.createUser(newUsername, newPassword);
    }
    //}

    //Metod för att skapa ett träningspass
    public void createWorkout() {
        System.out.println("Create a workout.");
        System.out.println("Do you want to: \n");
        System.out.println("1: Choose which muscle groups to train");
        System.out.println("2: Get a random workout");

        int workoutChoice = scanner.nextInt();
        scanner.nextLine(); // Läs bort ny rad efter nästa int

        switch (workoutChoice) {
            case 1:
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
                    case 1:
                        System.out.println("You chose to train chest!");
                        System.out.println("Here is your workout: \n");
                        //getWorkout("chest");
                        break;
                    case 2:
                        System.out.println("You chose to train back!");
                        System.out.println("Here is your workout: \n");
                        //getWorkout("back");
                        break;
                    case 3:
                        System.out.println("You chose to train legs!");
                        System.out.println("Here is your workout: \n");
                        //getWorkout("legs");
                        break;
                    case 4:
                        System.out.println("You chose to train shoulders!");
                        System.out.println("Here is your workout: \n");
                        //getWorkout("shoulders");
                        break;
                    case 5:
                        System.out.println("You chose to train arms!");
                        System.out.println("Here is your workout: \n");
                        //getWorkout("arms");
                        break;
                    case 6:
                        System.out.println("You chose to train abs!");
                        System.out.println("Here is your workout: \n");
                        //getWorkout("abs");
                        break;
                    case 0:
                        //Använder rekursion för att gå tillbaka till föregående meny
                        createWorkout();
                        break;
                    default:
                        System.out.println("Invalid input. Please choose a valid option.\n");
                } case 2:
                System.out.println("Here is a random workout: \n");
                //getExercise();
        }
    }
}
