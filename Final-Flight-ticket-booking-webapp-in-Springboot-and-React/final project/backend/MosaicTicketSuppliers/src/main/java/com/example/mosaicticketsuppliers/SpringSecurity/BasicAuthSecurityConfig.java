package com.example.mosaicticketsuppliers.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig {

    @Bean
    protected SecurityFilterChain FilterChain(HttpSecurity httpSecurity) throws Exception{
        System.out.println("SpringSecurityConfigurationBasicAuth loaded");
        httpSecurity.
                csrf().disable()
                .authorizeHttpRequests((authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/CanadaTickets/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/AmericaTickets/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/ChinaTickets/**").permitAll()
                                .anyRequest().authenticated()));
        httpSecurity.httpBasic();
        return httpSecurity.build();
    }

}
