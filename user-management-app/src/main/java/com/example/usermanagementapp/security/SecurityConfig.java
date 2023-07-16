package com.example.usermanagementapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


    // as the entity in this crud is also user I had to use a custom naming scheme
    // for the spring security users table and this is the configuration for it
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from credentials where user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");


        return jdbcUserDetailsManager;
    }
/*
   // For some reason this does not work here, the classic - works in another repo with the same config but not here...
   // After some more digging im not the only one having this issue, afaik the httpsecurity bean is not created bc of
   // a bug
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/users").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")


        );

        http.httpBasic(Customizer.withDefaults());

            //Couldn't get Postman to receive a Cookie with csrf or any csrf at all,
             // Tried all suggestions in stackoverflow for taking the csrf from a browser session and so on but nothing worked...,
             // so I disabled it, per spring documentation it is not required for stateless rest api and non browser clients of rest-api
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
*/



}
