package com.example.gateway;

import com.netflix.ribbon.proxy.annotation.Http;
import feign.HeaderMap;
import feign.Headers;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class UserController {

    @Autowired
    UserClient userClient;

    // GET All Users
    @RequestMapping(path="/users", method = RequestMethod.GET)
    public @ResponseBody Signed<List<User>> getAll() {
        return userClient.getUsers();
    }

}
