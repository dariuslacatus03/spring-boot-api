package com.springbootapi.springbootapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class WebSecurityConfiguration {
    private final AccountAuthenticationProvider authenticationProvider;



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/products/**"))
//                        .permitAll())
//                .authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/customers/**"))
//                        .hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }
//    @Bean
//    @Order(0)
//    public SecurityFilterChain publicEndpoints(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/api/accounts/**")
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().permitAll());
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        http.csrf((AbstractHttpConfigurer::disable));
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/accounts/**").permitAll());
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest()
                .hasAnyRole("USER", "ADMIN"));
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement((httpSecuritySessionManagementConfigurer) -> httpSecuritySessionManagementConfigurer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
}
