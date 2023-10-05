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

    //Skapa instanser av UserEntity och KafkaConsumer som ska användas i testen
    private static UserEntity user;

    //Skapa en instans av UserEntity som ska användas i testen
    @BeforeAll
    static void setUp() {
        user = new UserEntity();
        user.setName("NewestTest");
        user.setPassword("NewestTest");
        user.setId(250L);
    }

    //Test för metoden createUser i UserService
    @Test
    @Order(1)
    public void sendToWebAPITest() {
        //Anropa metod för att skicka User till WebAPI --> Kafka topic
        int resp = UserService.createUser(user);

        //Kontrollera att User finns i Kafka
        assertEquals(resp, 200);
    }

    //Test för metoden getUserDataFromKafka i KafkaConsumer
    @Test
    @Order(2)
    public void getDataFromKafkaTest() {
        //Anropa metod för att hämta Users från Kafka topic
        ArrayList<UserEntity> users = KafkaConsumer.getUserDataFromKafka("UserTopic");
        UserEntity testUser = users.get(users.size() - 1);

        //Kontrollera att User finns i Kafka
        assertEquals(user.getName(),testUser.getName());
        assertEquals(user.getPassword(),testUser.getPassword());
        assertEquals(user.getId(),testUser.getId());
    }
}