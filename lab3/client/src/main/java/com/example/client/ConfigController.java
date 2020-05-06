package com.example.client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${some.property}")
    private String property;

    @Value("${some.value}")
    private String value;

    // GET All Users
    @RequestMapping(path="/configs", method = RequestMethod.GET)
    public @ResponseBody String getConfigs(){
        return "{"+"\"property\": \""+this.property+"\", \"value\": \""+this.value+"\"}";
    }

}
