package com.code2am.stocklog.domain.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // Cross-Site Request Forgery
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> { // 서버의 리소스에 접근 가능한 권한을 설정함
                    //여기부터 로그인 권한을 설정하는 공간
                    auth.requestMatchers("/**").permitAll();    // url 기반으로 접근 허용
                    auth.anyRequest().authenticated();
                })

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());
        // SpringFilterChain에 걸어줌 build
        return http.build();
    }


}
