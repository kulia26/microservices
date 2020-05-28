package com.example.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    ProxyService userClient;

    // GET All Users
    @RequestMapping(path="/users", method = RequestMethod.GET)
    @ResponseBody ResponseEntity<?> getUsers() {
        return userClient.getUsers();
    }

    // Create a new User
    @RequestMapping(path="/users", method = RequestMethod.POST)
    @ResponseBody User createUser(@RequestBody User user) throws CustomExceptionHandler{
        return userClient.createUser(user);
    }

    // Get a Single User
    @RequestMapping(path="/users/{id}", method = RequestMethod.GET)
    @ResponseBody ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId){
        return userClient.getUserById(userId);
    }

    // Update a User
    @RequestMapping(path="/users/{id}", method = RequestMethod.PUT)
    @ResponseBody User updateUser(@PathVariable(value = "id") Long userId,
                                          @RequestBody User userDetails) throws CustomExceptionHandler{
        return userClient.updateUser(userId,userDetails);
    }

    // Delete a User
    @RequestMapping(path="/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) throws CustomExceptionHandler{
        return userClient.deleteUser(userId);
    }

}
