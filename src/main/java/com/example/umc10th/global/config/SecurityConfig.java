package com.example.umc10th.global.config;

import com.example.umc10th.global.security.filter.JwtAuthFilter;
import com.example.umc10th.global.security.handler.CustomAccessDenied;
import com.example.umc10th.global.security.handler.CustomEntryPoint;
import com.example.umc10th.global.security.handler.OAuthSuccessHandler;
import com.example.umc10th.global.security.service.CustomOAuthService;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            // Public API
            "/auth/**",

            // OAuth2 커스텀 로그인 시작/콜백 허용
            "/oauth/authorize/**",
            "/oauth/callback/**",

            // 에러 경로 허용
            "/error"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                // Form Login 비활성화
                .formLogin(AbstractHttpConfigurer::disable)

                // HTTP Basic 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)

                // OAuth2 로그인 과정에서는 세션이 필요할 수 있음
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )

                // URI 접근 권한 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )

                // OAuth2 로그인 설정
                .oauth2Login(oauth -> oauth
                        // 로그인 시작 주소: /oauth/authorize/kakao
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth/authorize")
                        )

                        // 카카오 콜백 주소: /oauth/callback/kakao
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/oauth/callback/*")
                        )

                        // 카카오 사용자 정보 처리 서비스
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )

                        // OAuth 로그인 성공 후 JWT 발급
                        .successHandler(oAuthSuccessHandler())
                )

                // JWT 인증 필터 등록
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)

                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                )

                // 인증/인가 예외 처리
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customEntryPoint())
                        .accessDeniedHandler(customAccessDenied())
                );

        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}