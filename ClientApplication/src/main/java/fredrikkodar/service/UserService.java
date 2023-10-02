package fredrikkodar.service;

import fredrikkodar.entities.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserService {

    public static boolean authenticate(String username, String password) {
        // Skapa en instans av RestTemplate för att göra HTTP-anrop
        RestTemplate restTemplate = new RestTemplate();

        // Skapa en URL för autentiseringsförfrågan
        String authenticationUrl = "http://localhost:8080/api/v1/user/authenticateUser";

        // Skapa en instans av UserDTO med användarnamn och lösenord
        UserEntity user = new UserEntity();
        user.setName(username);
        user.setPassword(password);

        // Gör en HTTP-POST-förfrågan för att autentisera användaren
        ResponseEntity<String> response = restTemplate.postForEntity(authenticationUrl, user, String.class);

        // Kolla om autentiseringen lyckades baserat på HTTP-svarskoden
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("User authenticated");
            return true; // Autentiseringen lyckades
        }

        System.out.println("User not authenticated");
        return false; // Autentiseringen misslyckades

    }

    //Metod som skapar en ny användare
    public static void createUser(String username, String password) {
        // Skapa en instans av RestTemplate för att göra HTTP-anrop
        RestTemplate restTemplate = new RestTemplate();

        // Skapa en URL för create-förfrågan
        String createUserUrl = "http://localhost:8080/api/v1/kafka/publishUserMessage";

        // Skapa en instans av UserDTO med användarnamn och lösenord
        UserEntity newUser = new UserEntity();
        newUser.setName(username);
        newUser.setPassword(password);

        // Gör en HTTP-POST-förfrågan för att skapa en ny användare
        ResponseEntity<String> response = restTemplate.postForEntity(createUserUrl, newUser, String.class);

        //Om statuskoden är 200 OK så har användaren skapats, annars inte
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("User created");

        } else {
            System.out.println("User not created");
        }
    }

    //Metod för att hämta alla användare
    public static void getAllUsers() {
        // Skapa en instans av RestTemplate för att göra HTTP-anrop
        RestTemplate restTemplate = new RestTemplate();

        // Skapa en URL för create-förfrågan
        String getAllUsersUrl = "http://localhost:8080/api/v1/kafka/getAllUsers";

        // Gör en HTTP-GET-förfrågan för att hämta alla användare
        ResponseEntity<String> response = restTemplate.getForEntity(getAllUsersUrl, String.class);

        //Om statuskoden är 200 OK så har användaren skapats, annars inte
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Users retrieved");

        } else {
            System.out.println("Users not retrieved");
        }
    }

}