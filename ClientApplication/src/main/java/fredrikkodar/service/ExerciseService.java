package fredrikkodar.service;

import fredrikkodar.entities.ExerciseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExerciseService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExerciseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Metod som skapar en ny övning genom att anropa APIService-modulen
    //som i sin tur skickar ett meddelande till Kafka
    public static int addExerciseToTopic(ExerciseEntity exercise) {

        // Skapa en instans av RestTemplate för att göra HTTP-anrop
        RestTemplate restTemplate = new RestTemplate();

        // Skapa en URL för create-förfrågan
        String createUserUrl = "http://localhost:8080/api/v1/kafka/publishExerciseMessage";

        // Gör en HTTP-POST-förfrågan för att skapa en ny övning
        ResponseEntity<String> response = restTemplate.postForEntity(createUserUrl, exercise, String.class);

        //Om statuskoden är 200 OK så har användaren skapats, annars inte
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Exercise added");
        } else {
            System.out.println("Exercise not added");
        }
        return response.getStatusCodeValue();
    }
}

