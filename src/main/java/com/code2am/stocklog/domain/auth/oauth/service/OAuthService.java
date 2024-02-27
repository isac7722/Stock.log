package com.code2am.stocklog.domain.auth.oauth.service;

import com.code2am.stocklog.domain.auth.common.enums.UserRole;
import com.code2am.stocklog.domain.auth.common.service.AuthService;
import com.code2am.stocklog.domain.auth.common.util.PasswordGenerator;
import com.code2am.stocklog.domain.auth.jwt.model.dto.TokenDTO;
import com.code2am.stocklog.domain.auth.jwt.model.entity.RefreshToken;
import com.code2am.stocklog.domain.auth.jwt.repository.RefreshTokenRepository;
import com.code2am.stocklog.domain.auth.jwt.util.TokenUtils;
import com.code2am.stocklog.domain.auth.oauth.model.dto.OAuthToken;
import com.code2am.stocklog.domain.auth.oauth.model.profile.KakaoProfile;
import com.code2am.stocklog.domain.auth.oauth.util.KakaoAPI;
import com.code2am.stocklog.domain.users.models.dto.UserDTO;
import com.code2am.stocklog.domain.users.models.entity.Users;
import com.code2am.stocklog.domain.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final KakaoAPI kakaoAPI;
    private final PasswordGenerator passwordGenerator;
    private final UsersRepository usersRepository;
    private final AuthService authService;


    public TokenDTO kakaoLogin(OAuthToken token){

        //사용자의 정보를 불어온다
        KakaoProfile profile = kakaoAPI.getMemberInfo(token.getAccessToken());

        // 사용자의 email 이 DB에 등록 되어 있는지 확인, 없다면 회원가입

        String email = profile.getKakao_account().getEmail();

        // DB 조회 결과 없다면
        if (!(usersRepository.existsByEmail(email))){
            System.out.println("이메일 조회 결과 없음, 등록 시작");

            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(email);
            userDTO.setPassword(passwordGenerator.generatePassword());
            userDTO.setUserRole(UserRole.ROLE_USER);
            userDTO.setSocial("KAKAO");

            // 신규 유저 회원 가입
            UserDTO newUser = authService.signup(userDTO);

            System.out.println("등록 성공");

            System.out.println("등록된 유저는"+newUser);

            if (usersRepository.existsByEmail(email)){
                System.out.println("등록된 유저가 있음");
            }

            TokenDTO tokenDTO = authService.login(userDTO);
            return tokenDTO;
        }
        else {
            System.out.println("조회 결과 없음");
            Users kakaoUser = usersRepository.findByEmail(email).get();
            UserDTO userDTO = UserDTO.of(kakaoUser);

            TokenDTO tokenDTO = authService.login(userDTO);
            return tokenDTO;

        }

    }

}
