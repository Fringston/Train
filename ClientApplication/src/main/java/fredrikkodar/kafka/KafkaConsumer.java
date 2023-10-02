package fredrikkodar.kafka;

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

//Klass som konsumerar meddelanden från Kafka och skriver ut till användaren
public class KafkaConsumer {

    //Metod som konsumerar meddelanden från UserTopic
    public static ArrayList<UserEntity> getDataFromKafka(String topicName) {

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
}
