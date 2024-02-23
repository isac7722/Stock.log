package com.code2am.stocklog.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {

    private String clientId;
    private String redirectUri;
}
