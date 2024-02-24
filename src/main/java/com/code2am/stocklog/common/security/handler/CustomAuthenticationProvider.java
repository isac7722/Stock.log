package com.code2am.stocklog.common.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private DetailUserService detailUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 사용자의 정보를 조회하고 결과를 확인하는 메소드
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 1. username password Token(사용자가 로그인 요청시 날린 아이디와 비밀번호를 가지고 있는 임시 객체)
        UsernamePasswordAuthenticationToken loginToken = (UsernamePasswordAuthenticationToken) authentication;
        String email = loginToken.getName();
        String password = (String) loginToken.getCredentials(); // 패스워드를 반환, 사용자가 입력한 패스워드 오브젝트 타입이라 스트링으로 캐스트

        //토큰에서 프로바이더랑 비교하려고 값 꺼내옴

        // 2. DB에서 username에 해당하는 정보를 조회한다.
        DetailUser foundUser = (DetailUser) detailUserService.loadUserByUsername(email);

        // 사용자가 입력한 username, password와 아이디의 비밀번호와 비교하는 로직을 수행함
        // 단방향 암호화는 복호화가 안 된다 (양방향은 가능) 그래서 일치하는지 확인하는 메서드
        if(!passwordEncoder.matches(password, foundUser.getPassword())){
            throw new BadCredentialsException("password가 일치하지 않습니다.");
        }
        // 인증이 완료된 및 권한이
        return new UsernamePasswordAuthenticationToken(foundUser, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
