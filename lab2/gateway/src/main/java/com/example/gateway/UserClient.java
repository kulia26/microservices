package com.example.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "eureka-client")
public interface UserClient {

    //get all users
    @RequestMapping(path="/api/users", method = RequestMethod.GET)
    @ResponseBody Signed<List<User>> getUsers() throws CustomExceptionHandler;

    // Create a new User
    @RequestMapping(path="/api/users", method = RequestMethod.POST)
    @ResponseBody User createUser(@RequestBody User user) throws CustomExceptionHandler;

    // Get a Single User
    @RequestMapping(path="/api/users/{id}", method = RequestMethod.GET)
    @ResponseBody Signed<User> getUserById(@PathVariable(value = "id") Long userId);

    // Update a User
    @RequestMapping(path="/api/users/{id}", method = RequestMethod.PUT)
    @ResponseBody User updateUser(@PathVariable(value = "id") Long userId,
                                           @RequestBody User userDetails) throws CustomExceptionHandler;

    // Delete a User
    @RequestMapping(path="/api/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) throws CustomExceptionHandler;

}
