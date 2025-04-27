package com.kentkart.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt.token")
public class JwtProperties {

    private long expiration;
    
    private String secretKey;
}
