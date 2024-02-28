package com.code2am.stocklog.domain.auth.common.util;

import com.code2am.stocklog.domain.users.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityUtil {

    @Autowired
    UsersRepository usersRepository;

   private SecurityUtil() { }

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw  new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }


    public Integer getUserId(){

        // 사용자의 ID를 얻는 방법
        Integer userId = usersRepository.findByEmail(getUserEmail()).get().getUserId();

        return userId;
    }

    public String getUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자의 ID를 얻는 방법
        String email = authentication.getName();
        return email;
    }
}
