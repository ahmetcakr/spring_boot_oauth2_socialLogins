package com.cakir.oauth2login.config.logins;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("google")
@Data
public class GoogleConfig {
    private String clientId;
    private String clientSecret;
}
