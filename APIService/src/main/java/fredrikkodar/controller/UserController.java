package fredrikkodar.controller;


import fredrikkodar.entities.UserEntity;
import fredrikkodar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//Klass som hanterar inkommande user-objekt från klientapplikationen
//och utför CRUD-operationer på databasen
@RestController
//Annotation som talar om att alla inkommande anrop ska börja med /api/v1/user
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Metod som tittar om en användare finns i databasen
    @PostMapping("/authenticateUser")
    public ResponseEntity<String> authenticateUser(@RequestBody UserEntity user) {
        // Hämta användaren från databasen baserat på användarnamnet
        Optional<UserEntity> userOptional = userRepository.findByName(user.getName());

        // Kontrollera om användaren finns
        if (userOptional.isPresent()) {
            user = userOptional.get();

            // Jämför det angivna lösenordet med användarens lösenord i databasen
            if (user.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("User authenticated"); // Autentiseringen lyckades
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }
}