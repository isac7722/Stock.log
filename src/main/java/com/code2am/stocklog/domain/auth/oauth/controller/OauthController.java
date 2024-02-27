package com.code2am.stocklog.domain.auth.oauth.controller;

import com.code2am.stocklog.domain.auth.oauth.model.dto.OAuthToken;
import com.code2am.stocklog.domain.auth.oauth.util.KakaoAPI;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Tag(name = "외부 인증 API", description = "외부 인증을 관리하는 API")
public class OauthController {

    private final KakaoAPI kakaoAPI;


    @PostMapping("/kakao")
    public ResponseEntity<?> getKakaoAuthorizeCode(@RequestBody OAuthToken oAuthToken){

        System.out.println(oAuthToken);



        return ResponseEntity.ok(oAuthToken);
    }

    // 카카오 인가 코드를 받는다
    @GetMapping("/kakao")
    public ResponseEntity<?> getKakaoLogin(@RequestParam String code){
        System.out.println(code);

        System.out.println(1);

        OAuthToken oAuthToken = kakaoAPI.getToken(code);

        System.out.println(oAuthToken);





        return ResponseEntity.ok(code);
    }
}
