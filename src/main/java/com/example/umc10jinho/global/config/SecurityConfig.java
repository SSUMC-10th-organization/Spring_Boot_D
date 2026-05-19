package com.example.umc10jinho.global.config;

import com.example.umc10jinho.global.security.handler.CustomAccessDeniedHandler;
import com.example.umc10jinho.global.security.handler.CustomAuthenticationEntryPoint;
import com.example.umc10jinho.global.security.handler.CustomAuthenticationFailureHandler;
import com.example.umc10jinho.global.security.handler.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public APIs
                        .requestMatchers(HttpMethod.POST, "/api/members/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/members/login").permitAll()

                        // login page
                        .requestMatchers("/login", "/login.html").permitAll()

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Private APIs
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/api/members/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
