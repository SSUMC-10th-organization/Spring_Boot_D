package com.example.umc_week4.global.config;

import com.example.umc_week4.global.apiPayload.handler.CustomAccessDenied;
import com.example.umc_week4.global.apiPayload.handler.CustomEntryPoint;
import com.example.umc_week4.global.security.filter.JwtAuthFilter;
import com.example.umc_week4.global.security.util.JwtUtil;
import com.example.umc_week4.global.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    };

    private final JwtUtil jwtutil;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()

                        // 회원가입 API만 Public
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()

                        // 로그인 화면, 로그인 처리 허용
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()

                        // 나머지는 Private
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginProcessingUrl("/login")

                        // form login에서 email, password 파라미터 사용
                        .usernameParameter("email")
                        .passwordParameter("password")

                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                //세션
                .sessionManagement(AbstractHttpConfigurer::disable)
                // JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                //예외 상황 핸들러
                .exceptionHandling(exception -> exception
                        // API 인증 실패: JSON 응답
                        .defaultAuthenticationEntryPointFor(
                                customEntryPoint(),
                                request -> request.getServletPath().startsWith("/api/")
                        )

                        // API가 아닌 화면 요청 인증 실패: 로그인 화면으로 redirect
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                request -> !request.getServletPath().startsWith("/api/")
                        )

                        // 인가 실패: JSON 응답
                        .accessDeniedHandler(customAccessDenied())
                );

        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtutil, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}