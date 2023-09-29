package fredrikkodar.controller;

import fredrikkodar.DTO.UserDTO;
import fredrikkodar.entities.ExerciseEntity;
import fredrikkodar.entities.MuscleGroupEntity;
import fredrikkodar.entities.UserEntity;
import fredrikkodar.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Klass som hanterar alla inkommande API-anrop från t. ex. Postman eller klientapplikationen.
//Annotation som talar om att det är en RestController, vilket innebär att den hanterar HTTP-anrop
//och returnerar svar i JSON-format
@RestController
//Annotation som talar om att alla inkommande anrop ska börja med /api/v1/kafka
@RequestMapping("/api/v1/kafka")
public class MessageController {

    //Skapa en instans av KafkaProducer
    private KafkaProducer kafkaProducer;

    //Konstruktor som tar emot KafkaProducer
    @Autowired
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //Testar ny publishUserMessage-metod som chatGPT föreslår
    @PostMapping("/publishUserMessage")
    public ResponseEntity<String> publishUserMessage (
            @RequestBody UserDTO userDTO) {
        //Konvertera UserDTO till User-entity
        UserEntity user = new UserEntity();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        System.out.println(user.getName() + " " + user.getPassword());

        //Skicka användaren till Kafka-topic
        kafkaProducer.sendUserMessage(user);
        return ResponseEntity.ok("Json message sent to Kafka Topic");
    }

    //Metod som skickar meddelanden till ExerciseTopic
    //http:localhost:8080/api/v1/kafka/publishExerciseMessage?message=
    @PostMapping("/publishExerciseMessage")
    public ResponseEntity<String> publishExerciseMessage (
            @RequestBody ExerciseEntity exercise) {
        System.out.println("MessageController.publishExerciseMessage: " + exercise.toString());
        kafkaProducer.sendExerciseMessage(exercise);
        return ResponseEntity.ok("Json message sent to Kafka Topic");
    }

    //Metod som skickar meddelanden till MuscleGroupTopic
    //http:localhost:8080/api/v1/kafka/publishMuscleGroupMessage?message=
    @PostMapping("/publishMuscleGroupMessage")
    public ResponseEntity<String> publishMuscleGroupMessage (
            @RequestBody MuscleGroupEntity muscleGroup) {
        kafkaProducer.sendMuscleGroupMessage(muscleGroup);
        return ResponseEntity.ok("Json message sent to Kafka Topic");
    }
}
