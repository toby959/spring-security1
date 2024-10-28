package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

// Configuration One #
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//                .csrf().disable()  -- para formulario
                .authorizeHttpRequests()
                .requestMatchers("api/v1/index2").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()// mostrar formulario
                .and()
                //.httpBasic()  //permite ingresar con user y password por Authorization Postman
                //.and()
                .build();
    }



    // Configuration Two ##
/*@Bean
public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers( "api/v1/index2").permitAll();
                auth.anyRequest().authenticated();
            })
            .formLogin()
            .successHandler(successHandler()) // url redirect
            .permitAll()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .invalidSessionUrl("/login") // redirect
            .maximumSessions(1)
            .expiredUrl("/login")
            .sessionRegistry(sessionRegistry())
            .and()
            .sessionFixation()
            .migrateSession()
            .and()
            .build();
    }
 */

    @Bean
    public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
    }


    public AuthenticationSuccessHandler successHandler() {
    return ((request, response, authentication) -> {
        response.sendRedirect("api/v1/session");
    });
    }
}
