package com.code2am.stocklog.domain.auth.controller;

import com.code2am.stocklog.domain.auth.enums.AuthConstants;
import com.code2am.stocklog.domain.auth.jwt.model.dto.TokenDTO;
import com.code2am.stocklog.domain.auth.service.AuthService;
import com.code2am.stocklog.domain.users.models.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO memberRequestDto) {
        System.out.println("진입");
        TokenDTO tokenDto = authService.login(memberRequestDto);

        System.out.println("탈출");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, AuthConstants.TOKEN_TYPE+ tokenDto.getAccessToken())
                .body(tokenDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@RequestBody TokenDTO tokenDTO) {
        return ResponseEntity.ok(authService.reissue(tokenDTO));
    }

}
