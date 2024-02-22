package com.code2am.stocklog.domain.users.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TBL_KAKAO_USERS")
public class KakaoUsers {

    @Id
    @Column(name = "KAKAO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kakaoId;

    @Column(name = "KAKAO_EMAIL")
    private String kakaoEmail;

}
