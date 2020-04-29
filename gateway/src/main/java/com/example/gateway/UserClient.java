package com.example.gateway;

import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "eureka-client")
public interface UserClient {

    @RequestMapping(path="/api/users", method = RequestMethod.GET)
    @ResponseBody Signed<List<User>> getUsers();

}
