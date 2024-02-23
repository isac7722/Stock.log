package com.code2am.stocklog.common.config;

import com.code2am.stocklog.common.filter.CustomAuthenticationFilter;
import com.code2am.stocklog.common.security.handler.CustomAuthFailureHandler;
import com.code2am.stocklog.common.security.handler.CustomAuthSuccessHandler;
import com.code2am.stocklog.common.security.handler.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 환경 구성?
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

    // 유저의 비밀번호를 암호화하는 메소드
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 사용자의 아이디와 패스워드를 DB와 검증하는 handler
    @Bean
    public CustomAuthenticationProvider customAuthenticatopnProvider(){
        return new CustomAuthenticationProvider();
    }

    // Authentication의 인증 메소드를 제공하는 매니저
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(customAuthenticatopnProvider()); // 매니저를 구현할
    }

    @Bean
    public CustomAuthSuccessHandler customAuthSuccessHandler(){
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public CustomAuthFailureHandler customAuthFailureHandler(){
        return new CustomAuthFailureHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Cross-Site Request Forgery
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    // URL 기반으로 접근 허용
                    auth.requestMatchers("/**").permitAll();
                    auth.anyRequest().authenticated();
                });

        // SpringFilterChain에 걸어줌 build
        return http.build();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(){
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        // 어떤 요청 리소스가 가로채면 되는건지 로그인 요청을 가로챈다.
        //authenticationFilter.setFilterProcessesUrl("/login");
        authenticationFilter.setAuthenticationSuccessHandler(customAuthSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(customAuthFailureHandler());
        return authenticationFilter;
    }
}
