package com.example.gateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Component
public class ProxyService {
    private static final String BACKEND_A = "client";

    @Autowired
    private UserClient userClient; // feign client


    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public ResponseEntity<?> getUsers() {
        return userClient.getUsers();
    }

    @Retry(name = BACKEND_A)
    public User createUser(User u) throws CustomExceptionHandler {
        return userClient.createUser(u);
    }

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public ResponseEntity<?> getUserById(Long id) {
        return userClient.getUserById(id);
    }

    @Retry(name = BACKEND_A)
    public User updateUser(Long userId, User userDetails) throws CustomExceptionHandler{
        return userClient.updateUser(userId,userDetails);
    }

    @Retry(name = BACKEND_A)
    ResponseEntity<?> deleteUser(Long userId) throws CustomExceptionHandler{
        return userClient.deleteUser(userId);
    }

    public ResponseEntity<?> fallback(Throwable e) {
        return new ResponseEntity<>(new ArrayList<User>(),HttpStatus.OK);
    }

}

