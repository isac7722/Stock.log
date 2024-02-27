package com.code2am.stocklog.domain.auth.oauth.util;

import com.code2am.stocklog.domain.auth.oauth.model.dto.OAuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class KakaoAPI {

    /* REGISTRATION */
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.kakao.client-authentication-method}")
    private String AUTHENTICATION_METHOD;

    /* PROVIDER */
    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String AUTHORIZATION_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String USER_INFO_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
    private String USER_NAME_ATTRIBUTE;


    public OAuthToken getToken(String code) {

        System.out.println(2);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", GRANT_TYPE);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("client_id", CLIENT_ID);
        params.add("code", code);
        params.add("client_secret", CLIENT_SECRET);

        WebClient webClient = WebClient.create(TOKEN_URI);

        System.out.println(3);

        System.out.println("토큰을 요청하는 중...");

        // POST 방식으로 key-value 데이터 요청
        OAuthToken oauthTokenRes = webClient.post()
                .uri(TOKEN_URI)
                .body(BodyInserters.fromFormData(params))
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(OAuthToken.class).block();


        System.out.println("토큰 발급 완료!");
        System.out.println(oauthTokenRes);

        return oauthTokenRes;
    }







    /* 액세스 토큰을 받아온다 */



    /* 사용자 정보를 받아온다 */



}
