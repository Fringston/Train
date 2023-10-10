package fredrikkodar.service;

import fredrikkodar.entities.UserEntity;
import fredrikkodar.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Metod som autentiserar en användare genom att anropa APIService-modulen
    public boolean authenticate(String username, String password) {

        // Skapa en URL för autentiseringsförfrågan
        String authenticationUrl = "http://localhost:8080/api/v1/user/authenticateUser";

        // Skapa en instans av UserEntity med användarnamn och lösenord
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

    //Metod som skapar en ny användare genom att anropa APIService-modulen
    //som i sin tur skickar ett meddelande till Kafka
    public static int createUser(UserEntity user) {

        // Skapa en instans av RestTemplate för att göra HTTP-anrop
        RestTemplate restTemplate = new RestTemplate();

        // Skapa en URL för create-förfrågan
        String createUserUrl = "http://localhost:8080/api/v1/kafka/publishUserMessage";

        // Gör en HTTP-POST-förfrågan för att skapa en ny användare
        ResponseEntity<String> response = restTemplate.postForEntity(createUserUrl, user, String.class);

        //Om statuskoden är 200 OK så har användaren skapats, annars inte
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("User created");
        } else {
            System.out.println("User not created");
        }
        return response.getStatusCodeValue();
    }
}