package com.example.demo01.common.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt")


public class JwtProperties {
    private String header;
    private String base64Secret;
    private Long tokenValidityInSeconds;

    @Override
    public String toString() {
        return "JwtProperties{" +
                "header='" + header + '\'' +
                ", base64Secret='" + base64Secret + '\'' +
                ", tokenValidityInSeconds=" + tokenValidityInSeconds +
                '}';
    }
}
