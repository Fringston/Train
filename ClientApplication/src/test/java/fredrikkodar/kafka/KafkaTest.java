package fredrikkodar.kafka;

import fredrikkodar.entities.UserEntity;
import fredrikkodar.service.UserService;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KafkaTest {


    private static UserEntity user;
    private static KafkaConsumer kafkaConsumer;

    @BeforeAll
    static void beforeAll() {
        user = new UserEntity();
        user.setName("NewestTest");
        user.setPassword("NewestTest");
        user.setId(250L);
    }

    @Test
    @Order(1)
    public void sendToWebAPITest() {
        //Anropa metod för att skicka User till WebAPI --> Kafka topic
        int resp = UserService.createUser(user);

        //Kontrollera att User finns i Kafka
        assertEquals(resp, 200);
    }

    @Test
    @Order(2)
    public void getDataFromKafkaTest() {
        //Anropa metod för att hämta Users från Kafka topic
        ArrayList<UserEntity> users = kafkaConsumer.getUserDataFromKafka("UserTopic");
        UserEntity testUser = users.get(users.size() - 1);

        //Kontrollera att User finns i Kafka
        assertEquals( testUser.getName() , user.getName());
        assertEquals( testUser.getPassword() , user.getPassword());
        assertEquals( testUser.getId() , user.getId());
    }
}