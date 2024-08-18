package com.cakir.oauth2login.config.logins;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("facebook")
@Data
public class FacebookConfig {
    private String clientId;
    private String clientSecret;
}
