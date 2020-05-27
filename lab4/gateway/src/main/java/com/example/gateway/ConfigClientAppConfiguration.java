package com.example.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "some")
public class ConfigClientAppConfiguration {
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    private String property;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String v) {
        this.value = v;
    }
}
