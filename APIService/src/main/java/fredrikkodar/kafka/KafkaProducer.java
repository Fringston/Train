package fredrikkodar.kafka;

import fredrikkodar.entities.ExerciseEntity;
import fredrikkodar.entities.MuscleGroupEntity;
import fredrikkodar.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

//Klass som producerar meddelanden till Kafka
@Service
public class KafkaProducer {

    //Logger för att logga meddelanden
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    //Använder KafkaTemplate för att skicka meddelanden till Kafka
    private KafkaTemplate<String, UserEntity> kafkaTemplate;

    //Konstruktor som tar emot KafkaTemplate
    public KafkaProducer(KafkaTemplate<String, UserEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Metod som skickar meddelanden till UserTopic i form av UserEntity-objekt
    public void sendUserMessage(UserEntity data) {
        //Loggar meddelandet
        LOGGER.info(String.format("Message sent %s", data.toString()));
        //Skapar ett meddelande med User som payload
        Message<UserEntity> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "UserTopic")
                .build();
        //Skickar meddelandet till UserTopic
        kafkaTemplate.send(message);
    }

    //Metod som skickar meddelanden till ExerciseTopic i form av ExerciseEntity-objekt
    public void sendExerciseMessage(ExerciseEntity data) {
        LOGGER.info(String.format("Message sent %s", data.toString()));
        Message<ExerciseEntity> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "ExerciseTopic")
                .build();
        kafkaTemplate.send(message);
    }

    //Metod som skickar meddelanden till MuscleGroupTopic i form av MuscleGroupEntity-objekt
    public void sendMuscleGroupMessage(MuscleGroupEntity data) {
        LOGGER.info(String.format("Message sent %s", data.toString()));
        Message<MuscleGroupEntity> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "MuscleGroupTopic")
                .build();
        kafkaTemplate.send(message);
    }
}