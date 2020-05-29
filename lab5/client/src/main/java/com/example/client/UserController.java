package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    final
    UserRepository userRepository;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

//    @Value("${some.other.property}")
//    private String someOtherProperty;

    @Autowired
    KafkaProducerDemo kafkaProducer;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All Users
    @GetMapping("/users")
    public Signed<List<User>> getAllUsers() {
        return new Signed<>(userRepository.findAll(), instanceId);
    }

    // Create a new User
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) throws Throwable {
        kafkaProducer.sendMessage("create.entity", user);
        return userRepository.save(user);
    }

    // Get a Single User
    @GetMapping("/users/{id}")
    public Signed<User> getUserById(@PathVariable(value = "id") Long userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return new Signed<>(u, instanceId);
    }

    // Update a User
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) throws Throwable {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setBirthday(userDetails.getBirthday());
        user.setCity(userDetails.getCity());
        user.setCountry(userDetails.getCountry());
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setPassword(userDetails.getPassword());
        user.setPhone(userDetails.getPhone());

        kafkaProducer.sendMessage("update.entity", user);
        return userRepository.save(user);

    }

    // Delete a User
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
}
