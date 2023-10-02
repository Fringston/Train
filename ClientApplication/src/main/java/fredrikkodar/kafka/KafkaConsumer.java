package fredrikkodar.kafka;

import fredrikkodar.entities.UserEntity;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {

    public static ArrayList<UserEntity> getDataFromKafka(String topicName) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fetchingGroup");
        //props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonDeserializer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put("spring.json.trusted.packages", "*");

        Consumer<String, UserEntity> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        //consumer.subscribe(Collections.singletonList(topicName));
        consumer.assign(Collections.singletonList(new TopicPartition(topicName, 0)));

        //Gå till början av Topic
        consumer.seekToBeginning(consumer.assignment());

        //Create User list
        ArrayList<UserEntity> users = new ArrayList<UserEntity>();

        //WhileLoop som hämtar i JSON format
        while (true) {
            ConsumerRecords<String, UserEntity> records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) continue;
            for (ConsumerRecord<String, UserEntity> record : records) {
                users.add(record.value());
                //System.out.println(record.value());
                //System.out.println(record.value().getClass().toString());
/*
                //Spara datan tillbaka till ett JSONObject
                JSONObject fetchData = (JSONObject) new JSONParser().parse(record.value());

                //Skriva ut data
                System.out.println(fetchData.get("id"));
                System.out.println(fetchData.get("firstName"));
                System.out.println(fetchData.get("lastName"));

*/
            }
            break;
        }

        for (UserEntity user : users) {
            System.out.println(user.getName());
        }
/*
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());

                //Spara datan tillbaka till ett JSONObject
                JSONObject fetchData = (JSONObject) new JSONParser().parse(record.value());

                //Skriva ut data
                System.out.println(fetchData.get("id"));
                System.out.println(fetchData.get("firstName"));
                System.out.println(fetchData.get("lastName"));
            }
        }*/

        return users;
    }
}
