import fredrikkodar.entities.UserEntity;
//import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/*@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KafkaTests {

    private static UserEntity user;
    private static JSONObject myObj;

    @BeforeAll
    static void beforeAll() {
        user = new UserEntity();
        user.setName("NewestTest");
        user.setPassword("NewestTest");
        user.setId(250L);

        myObj = new JSONObject();
        myObj.put("id", user.getId());
        myObj.put("firstName", user.getName());
        myObj.put("lastName", user.getPassword());
    }

    @Test
    @Order(1)
    public void sendToWebAPITest() {
        //Anropa metod för att skicka den
        String resp = Main.sendToWebAPI(myObj);

        //Jämföra response-värden
        assertEquals(resp, "Json Message send to Kafka Topic");
    }

    @Test
    @Order(2)
    public void getDataFromKafkaTest() {
        //Anropa metod för att hämta Users
        ArrayList<UserEntity> users = Main.getDataFromKafka("javaJsonGuides");
        UserEntity testUser = users.get(users.size() - 1);

        assertEquals( testUser.getFirstName() , user.getFirstName());
        assertEquals( testUser.getLastName() , user.getLastName());
        assertEquals( testUser.getId() , user.getId());
    }
}*/