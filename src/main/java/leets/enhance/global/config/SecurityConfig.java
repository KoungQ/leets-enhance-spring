package leets.enhance.global.config;

import leets.enhance.global.jwt.JwtAuthenticationFilter;
import leets.enhance.global.jwt.JwtTokenService;
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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenService jwtTokenService;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http    // 세션 해제
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // CORS 해제
                .cors(AbstractHttpConfigurer::disable)

                // CSRF 해제
                .csrf(AbstractHttpConfigurer::disable)

                // Jwt 토큰 필터 삽입
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenService), UsernamePasswordAuthenticationFilter.class)

                // 도메인 별 권한 설정
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/items/**").authenticated()
                        .requestMatchers("/items/top10").permitAll()    // 비로그인 유저 접근 허용
                        .requestMatchers("/enhance/**").authenticated()
                        .requestMatchers("/users/**").permitAll())

                // 스프링 시큐리티 기본 로그인 해제
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean   // 해시
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
