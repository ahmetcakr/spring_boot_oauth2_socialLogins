package com.cakir.oauth2login.config;

import com.cakir.oauth2login.config.logins.FacebookConfig;
import com.cakir.oauth2login.config.logins.GithubConfig;
import com.cakir.oauth2login.config.logins.GoogleConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final GithubConfig githubConfig;
    private final FacebookConfig facebookConfig;
    private final GoogleConfig googleConfig;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .authorizeHttpRequests(reguest->
                        reguest
                                .requestMatchers("/secure")
                                .authenticated()
                                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration github = githubClientRegistration();
        ClientRegistration facebook = facebookClientRegistration();
        ClientRegistration google = googleClientRegistration();

        return new InMemoryClientRegistrationRepository(github, facebook, google);
    }


    private ClientRegistration githubClientRegistration(){
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId(githubConfig.getClientId())
                .clientSecret(githubConfig.getClientSecret()).build();
    }

    private ClientRegistration facebookClientRegistration(){
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
                .clientId(facebookConfig.getClientId())
                .clientSecret(facebookConfig.getClientSecret()).build();
    }

    private ClientRegistration googleClientRegistration(){
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
                .clientId(googleConfig.getClientId())
                .clientSecret(googleConfig.getClientSecret()).build();
    }

}
