package com.gateway.apigateway.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@FeignClient(value="auth-service")
public interface UserClient {
    @RequestMapping(path="/users/register",  method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody User user) throws NoSuchAlgorithmException;

    @RequestMapping(path="/users/login", method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody User user);

    @RequestMapping(path="/users", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAll();

    @RequestMapping(path="/users/{id}", method = RequestMethod.GET)
    public @ResponseBody User getById(@PathVariable int id);

    @RequestMapping(path="/users/find/{email}", method = RequestMethod.GET)
    public @ResponseBody User getIdByEmail(@PathVariable String email);

    @RequestMapping(path="/users/isAdmin", method = RequestMethod.GET)
    public @ResponseBody Boolean isAdmin(@RequestHeader(value = "Authorization") String token);

    @RequestMapping(path="/users/isUser", method = RequestMethod.GET)
    public @ResponseBody Boolean isUser(@RequestHeader(value = "Authorization") String token);
}