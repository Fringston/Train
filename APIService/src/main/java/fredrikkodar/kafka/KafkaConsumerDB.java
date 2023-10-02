package fredrikkodar.kafka;

import fredrikkodar.entities.ExerciseEntity;
import fredrikkodar.entities.MuscleGroupEntity;
import fredrikkodar.entities.UserEntity;
import fredrikkodar.repository.ExerciseRepository;
import fredrikkodar.repository.MuscleGroupRepository;
import fredrikkodar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//Klass som konsumerar meddelanden från Kafka och skriver till databasen
@Service
public class KafkaConsumerDB {

    //Skapar instanser av repositories för att kunna skriva till databasen
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;


    //Metod som konsumerar meddelanden från UserTopic
    @KafkaListener(topics = "UserTopic", groupId = "myGroup2")
    public void writeToUserDB(UserEntity user) {

        //Skickar data till databasen
        userRepository.save(user);
    }

    //Metod som konsumerar meddelanden från ExerciseTopic
    @KafkaListener(topics = "ExerciseTopic", groupId = "myGroup2")
    public void writeToExerciseDB(ExerciseEntity exercise) {

        //Skickar data till databasen
        exerciseRepository.save(exercise);
    }

    //Metod som konsumerar meddelanden från MuscleGroupTopic
    @KafkaListener(topics = "MuscleGroupTopic", groupId = "myGroup2")
    public void writeToMuscleGroupDB(MuscleGroupEntity muscleGroup) {

        //Skickar data till databasen
        muscleGroupRepository.save(muscleGroup);
    }
}
