package com.code2am.stocklog.domain.auth.oauth.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuthToken {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private Integer expiresIn;
    private Integer refreshTokenExpiresIn;
    private String error;
    private String errorDescription;
}