package fredrikkodar.service;

import fredrikkodar.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

//Använder Mockito för att testa mot databasen
@RestClientTest(UserService.class)
public class UserServiceTest {

    //Skapa en instans av RestTemplate som ska användas i testen
    @MockBean
    private RestTemplate restTemplate;

    //Skapa en instans av UserService som ska användas i testen
    @Autowired
    private UserService userService;

    //Test för metoden authenticate i UserService som ska returnera true
    @Test
    public void testAuthenticateUserSuccess() {
        ResponseEntity<String> successResponse = new ResponseEntity<>("User authenticated", HttpStatus.OK);
        when(restTemplate.postForEntity(eq("http://localhost:8080/api/v1/user/authenticateUser"), any(UserEntity.class), eq(String.class)))
                .thenReturn(successResponse);

        boolean result = userService.authenticate("testUser", "testPassword");
        assertTrue(result);
    }

    //Test för metoden authenticate i UserService som ska returnera false
    @Test
    public void testAuthenticateUserFailure() {
        ResponseEntity<String> failureResponse = new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
        when(restTemplate.postForEntity(eq("http://localhost:8080/api/v1/user/authenticateUser"), any(UserEntity.class), eq(String.class)))
                .thenReturn(failureResponse);

        boolean result = userService.authenticate("testUser", "testPassword");
        assertFalse(result);
    }
}