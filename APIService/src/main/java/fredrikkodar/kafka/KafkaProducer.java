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

@Service
public class KafkaProducer {

    //Logger för att logga meddelanden
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    //KafkaTemplate för att skicka meddelanden
    private KafkaTemplate<String, UserEntity> kafkaTemplate;

    //Konstruktor som tar emot KafkaTemplate
    public KafkaProducer(KafkaTemplate<String, UserEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //Metod som skickar meddelanden till UserTopic
    public void sendUserMessage(UserEntity data) {
        LOGGER.info(String.format("Message sent %s", data.toString()));
        Message<UserEntity> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "UserTopic")
                .build();
        kafkaTemplate.send(message);
    }

    //Metod som skickar meddelanden till ExerciseTopic
    public void sendExerciseMessage(ExerciseEntity data) {
        LOGGER.info(String.format("Message sent %s", data.toString()));
        Message<ExerciseEntity> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "ExerciseTopic")
                .build();
        kafkaTemplate.send(message);
    }

    //Metod som skickar meddelanden till MuscleGroupTopic
    public void sendMuscleGroupMessage(MuscleGroupEntity data) {
        LOGGER.info(String.format("Message sent %s", data.toString()));
        Message<MuscleGroupEntity> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "MuscleGroupTopic")
                .build();
        kafkaTemplate.send(message);
    }
}