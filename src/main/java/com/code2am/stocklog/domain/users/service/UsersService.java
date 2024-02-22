package com.code2am.stocklog.domain.users.service;

import com.code2am.stocklog.domain.users.models.dto.UsersDTO;
import com.code2am.stocklog.domain.users.models.entity.Users;
import com.code2am.stocklog.domain.users.models.entity.UsersRepositotory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsersService {

//    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
//    private String kakaoApiKey;
//
//    @Value("${kakao.redirect-uri}")
//    private String kakaoRedirect;

    private final BCryptPasswordEncoder encoder;
    private final UsersRepositotory usersRepositotory;

    public UsersService(BCryptPasswordEncoder encoder, UsersRepositotory usersRepositotory) {
        this.encoder = encoder;
        this.usersRepositotory = usersRepositotory;
    }

    // 자체 회원가입
    public Users createAccount(Users user){
        // 비밀번호 암호화
        user.setPassword(encoder.encode(user.getPassword()));
        // 유저 상태 지정
        user.setStatus("Y");
        // 생성시간 현재 시간으로 지정
        user.setCreateDate(LocalDateTime.now());
        return user;
    }

    public Optional<Users> readUserByUserId(String email){
        Optional<Users> user = usersRepositotory.readUsersByEmail(email);
        return user;
    }

}
