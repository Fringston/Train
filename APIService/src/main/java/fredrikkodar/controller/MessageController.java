package fredrikkodar.controller;

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

//Klass som hanterar inkommande API-anrop från t. ex. Postman eller klientapplikationen.
//RestController innebär att den hanterar HTTP-anrop.
@RestController
//Annotation som talar om att alla inkommande anrop ska börja med /api/v1/kafka
@RequestMapping("/api/v1/kafka")
public class MessageController {

    //Skapar en instans av KafkaProducer för att kunna skicka meddelanden till Kafka
    private KafkaProducer kafkaProducer;

    //Konstruktor som tar emot KafkaProducer
    @Autowired
    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    //Metod/handler som skickar meddelanden till UserTopic
    @PostMapping("/publishUserMessage")
    public ResponseEntity<String> publishUserMessage (@RequestBody UserEntity user) {

        //Skapa en ny användare
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        System.out.println(user.getName() + " " + user.getPassword());

        //Skicka användaren till Kafka-topic
        kafkaProducer.sendUserMessage(user);
        return ResponseEntity.ok("Json message sent to UserTopic");
    }

    //Metod/handler som skickar meddelanden till ExerciseTopic
    @PostMapping("/publishExerciseMessage")
    public ResponseEntity<String> publishExerciseMessage (@RequestBody ExerciseEntity exercise) {

        //Skapa en ny övning
        exercise.setName(exercise.getName());
        //exercise.setMuscleGroup(exercise.getMuscleGroup()); ???
        System.out.println(exercise.getName());

        //Skicka övningen till Kafka-topic
        kafkaProducer.sendExerciseMessage(exercise);
        return ResponseEntity.ok("Json message sent to ExerciseTopic");
    }

    //Metod/handler som skickar meddelanden till MuscleGroupTopic
    @PostMapping("/publishMuscleGroupMessage")
    public ResponseEntity<String> publishMuscleGroupMessage (@RequestBody MuscleGroupEntity muscleGroup) {
        kafkaProducer.sendMuscleGroupMessage(muscleGroup);
        return ResponseEntity.ok("Json message sent to MuscleGroupTopic");
    }
}
