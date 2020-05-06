package com.example.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
//
//    @Value("${some.other.property}")
//    private String someOtherProperty;

    @Autowired
    private ConfigClientAppConfiguration configs;


    // GET All Users
    @RequestMapping(path="/configs", method = RequestMethod.GET)
    public @ResponseBody String getConfigs(){
        return "{"+"\"property\": \""+configs.getProperty()+"\", \"value\": \""+configs.getValue()+"\"}";
    }

}
