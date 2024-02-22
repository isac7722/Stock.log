package com.code2am.stocklog.common.util;

import com.code2am.stocklog.domain.users.models.entity.Users;
import io.jsonwebtoken.*;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    private static String jwtSecretKey;
    private static Long tokenValidateTime;

    @Value("${jwt.key}")
    public void setJwtSecretKey(String jwtSecretKey) {
        TokenUtils.jwtSecretKey = jwtSecretKey;
    }

    @Value("${jwt.time}")
    public void setTokenValidateTime(Long tokenValidateTime) {
        TokenUtils.tokenValidateTime = tokenValidateTime;
    }

    /*
     * header의 token을 분리하는 메소드
     * @Param header: Authrization의 header값을 가져온다.
     * @return token: Authrization의 token을 반환한다.
     * */
    public static String splitHeader(String header){
        // 띄어쓰기 후 1(2)번째 인덱스
        if(!header.equals("")){
            return header.split(" ")[1];
        }else{
            return null;
        }
    }

    /*
     * 유효한 토큰인지 확인하는 메서드
     * @param token : 토큰
     * @return boolean : 유효 여부
     * @throws ExpiredJwtException, {@link io.jsonwebtoken.JwtException} {@link NullPointerException}
     * */

    public static boolean isValidToken(String token){   // 토큰이 유효하면 true 반환 토큰이 만료되도 false
        // claim은 payload이다. 복호화를 하면 여기에 있는 데이터를 바로 사용할 수 있다. 토큰이 유효하지 않으면 복호화 자체가 되지 않는다.
        try{
            Claims claims = getClaimsFormToken(token);
            return true;
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            return false;
        }catch (JwtException e){
            e.printStackTrace();
            return false;
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

    /*
     * 토큰을 복호화하는 매서드
     * @Param token
     * @return claims
     * */
    public static Claims getClaimsFormToken(String token){
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }

    /*
     * token을 생성하는 메서드
     * @param user = userEntity
     * @return String token
     * */
    public static String generateJwtToken(Users user){
        Date expireTime = new Date(System.currentTimeMillis()+tokenValidateTime);
        // jwtbuilder : jwt에서 토큰 생성하는 라이브러리
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject("stock.log token : " + user.getUserId())
                .signWith(SignatureAlgorithm.HS256, createSignature())
                .setExpiration(expireTime);

        return builder.compact();

    }

    /*
     * token의 header를 설정하는 부분이다.
     * @return Map<String, Object> header의 설정 정보
     * 캡슐화 원칙때문에, 여기서만 쓰기 위해 private로
     * */
    private static Map<String, Object> createHeader(){
        Map<String, Object> header = new HashMap<>();

        header.put("type", "jwt");
        header.put("alg", "HS256");
        header.put("date", System.currentTimeMillis());

        return header;
    }

    /*
     * 사용자 정보를 기반으로 클레임을 생성해주는 메서드
     * @Param user 사용자 정보
     * @return Map<String, Object> - claims 정보
     * */
    private static Map<String, Object> createClaims(Users user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("userEmail", user.getEmail());
        return claims;
    }

    /*
     * jwt 서명을 발급해주는 매서드이다.
     * @return key
     * */
    private static Key createSignature(){
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey); // 시그니처 부분에는 시크릿
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
