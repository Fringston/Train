package fredrikkodar;

import fredrikkodar.ui.UserInterface;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        UserInterface userInterface = new UserInterface();
        userInterface.displayMainMenu();
    }
}