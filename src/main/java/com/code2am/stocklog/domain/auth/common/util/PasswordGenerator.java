package com.code2am.stocklog.domain.auth.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class PasswordGenerator {

    private final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    public String generatePassword() {
        SecureRandom random = new SecureRandom();

        // 랜덤으로 비밀번호 길이를 10에서 20 사이에서 선택
        int passwordLength = random.nextInt(11) + 10;

        StringBuilder password = new StringBuilder(passwordLength);

        for (int i = 0; i < passwordLength; i++) {
            // ALLOWED_CHARACTERS에서 랜덤으로 문자 선택
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);

            // 비밀번호에 추가
            password.append(randomChar);
        }

        return password.toString();
    }
}

