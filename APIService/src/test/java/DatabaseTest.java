import fredrikkodar.entities.UserEntity;
import fredrikkodar.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseTest {

    @Autowired
    private UserRepository userRepository;

    static UserEntity testUser;

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests");
    }

    @Test
    @Order(1)
    void createUser() {
        //Skapa ett objekt av User med specifik data
        UserEntity user = new UserEntity();
        user.setName("A");
        user.setPassword("B");

        //Spara user till DB
        testUser = userRepository.save(user);

        assertNotNull(userRepository.findById(testUser.getId()).get().getName());

        System.out.println(testUser.getId());
    }

    @Test
    @Order(2)
    void findUserByName() {
        // Hämta User med namn
        Optional<UserEntity> fetchedUserOptional = userRepository.findByName(testUser.getName());
        // Kontrollera att User finns
        assertTrue(fetchedUserOptional.isPresent());
        //Hämta User-objektet från Optional
        UserEntity fetchedUser = fetchedUserOptional.get();
        // Kontrollera att namnet är rätt
        assertEquals("A", fetchedUser.getName());
    }

    @Test
    @Order(3)
    void updateUser(){
        // Hämta User med id
        UserEntity fetchedUser = userRepository.findById(testUser.getId()).get();
        assertNotNull(fetchedUser);

        //Uppdatera värdet på fetchedUser
        fetchedUser.setName("C");
        userRepository.save(fetchedUser);
        assertEquals("C", userRepository.findById(testUser.getId())
                .get().getName());
    }

    @Test
    @Order(4)
    void removeUser() {
        //Kontrollera att user med ID:t finns i databasen
        assertNotNull(userRepository.findById(testUser.getId()).get());

        //Ta bort User
        userRepository.deleteById(testUser.getId());

        //Kontrollera att User är borttagen
        assertTrue(userRepository.findById(testUser.getId()).isEmpty());
    }
}