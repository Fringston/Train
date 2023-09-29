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

@Service
public class KafkaConsumerDB {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private MuscleGroupRepository muscleGroupRepository;


    @KafkaListener(topics = "UserTopic", groupId = "myGroup2")
    public void writeToUserDB(UserEntity user) {

        //Skicka data till databasen
        userRepository.save(user);
    }

    @KafkaListener(topics = "ExerciseTopic", groupId = "myGroup2")
    public void writeToExerciseDB(ExerciseEntity exercise) {

        //Skicka data till databasen
        exerciseRepository.save(exercise);
    }
    @KafkaListener(topics = "MuscleGroupTopic", groupId = "myGroup2")
    public void writeToMuscleGroupDB(MuscleGroupEntity muscleGroup) {

        //Skicka data till databasen
        muscleGroupRepository.save(muscleGroup);
    }
}
