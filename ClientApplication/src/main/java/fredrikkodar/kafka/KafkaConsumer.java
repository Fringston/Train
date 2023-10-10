package fredrikkodar.kafka;

import fredrikkodar.entities.ExerciseEntity;
import fredrikkodar.entities.UserEntity;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

//Klass som konsumerar meddelanden från Kafka och skriver ut till användaren
public class KafkaConsumer {

    //Metod som konsumerar alla meddelanden från UserTopic och returnerar en lista av Users
    public static ArrayList<UserEntity> getUserDataFromKafka(String topicName) {

        //Skapar en Properties instans för att konfigurera Consumer
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fetchingGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        //Skapa en instans av den inbyggda klassen Consumer för att konsumera meddelanden från Kafka
        Consumer<String, UserEntity> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        //Gå till början av Topic
        consumer.seekToBeginning(consumer.assignment());

        //Skapa en lista av users
        ArrayList<UserEntity> users = new ArrayList<UserEntity>();

        //WhileLoop som hämtar i JSON format
        while (true) {
            ConsumerRecords<String, UserEntity> records = consumer.poll(Duration.ofMillis(100));
            //Om det inte finns några records, fortsätt
            if (records.isEmpty()) continue;
            //Annars, lägg till records i users-listan
            for (ConsumerRecord<String, UserEntity> record : records) {
                users.add(record.value());
            }
            break;
        }

        //Skriv ut alla users
        for (UserEntity user : users) {
            System.out.println("User: " + user.getName());
        }
        return users;
    }


    //Metod som konsumerar meddelanden från ExerciseTopic och returnerar en slumpmässig övning
    public static String getExerciseDataFromKafka(String topicName) {

        //Skapar en Properties instans för att konfigurera Consumer
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fetchingGroup");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        //Skapa en instans av den inbyggda klassen Consumer för att konsumera meddelanden från Kafka
        Consumer<String, ExerciseEntity> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        //Gå till början av Topic
        consumer.seekToBeginning(consumer.assignment());

        //Skapa en lista av övningar
        ArrayList<ExerciseEntity> exercises = new ArrayList<ExerciseEntity>();

        //WhileLoop som hämtar i JSON format
        while (true) {
            ConsumerRecords<String, ExerciseEntity> records = consumer.poll(Duration.ofMillis(100));
            //Om det inte finns några records, fortsätt
            if (records.isEmpty()) continue;
            //Annars, lägg till records i exercises-listan
            for (ConsumerRecord<String, ExerciseEntity> record : records) {
                exercises.add(record.value());
            }
            break;
        }

        //Skapa en slumpmässig generator
        Random random = new Random();

        //Generera ett slumpmässigt index för att hämta en övning
        int randomIndex = random.nextInt(exercises.size());

        //Hämta en slumpmässig övning från listan
        String randomExercise = exercises.get(randomIndex).getName();

        //Skriv ut övningen
        return randomExercise;
    }
}
