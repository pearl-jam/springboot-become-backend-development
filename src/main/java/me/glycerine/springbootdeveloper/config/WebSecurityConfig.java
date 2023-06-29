package me.glycerine.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.glycerine.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final UserDetailService userService;
    
    // 스프링 시큐리티 기능 비활성화
    // - 인증, 인가 서비스를 모든 곳에 모두 적용하지 않음, 일반적으로 정적 리소스(이미지, HTML 파일)에 설정
    // - 정적 리소스만 스프링 시큐리티 사용을 비활성화하는 데 static 하위 경로에 있는 리소스와 h2의 데이터를 확인하는데 사용하는 h2-console 하위 url을 대상으로 ignoring() 메서드 사용
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }
    
    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    // - 특정 HTTP 요청에 대해 웹 기반 보안 구성, 인증/인가 및 로그인, 로그아웃 관련 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests() // 인증, 인가 설정
                .requestMatchers("/login", "/signup", "/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // 폼 기반 로그인 설정
                .loginPage("/login")
                .defaultSuccessUrl("/articles")
                .and()
                .logout() // 로그아웃 설정
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable() // csrf 비활성화
                .build();
    }

    // 인증 관리자 관련 설정
    // - 사용자 정보를 가져올 서비스를 재정의하거나, 인증 방법, 예를 들어 LDAP, JDBC 기반 인증 등을 설정할 때 사용
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
