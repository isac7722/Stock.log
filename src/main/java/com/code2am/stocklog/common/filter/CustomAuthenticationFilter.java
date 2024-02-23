package com.code2am.stocklog.common.filter;

import com.code2am.stocklog.domain.users.models.dto.LoginDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        super.setAuthenticationManager(authenticationManager);
    }

    // 지정된 url 요청시 해당 요청을 가로채서 검증 로직을 수행하는 매서드
    // 로그인 요청시
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken;

        try {
            authenticationToken = getAuthRequest(request);
            setDetails(request, authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws IOException {
        // simplme json을 이용한 것
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        LoginDTO loginUser = objectMapper.readValue(request.getInputStream(), LoginDTO.class);
        return new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword());
    }
}