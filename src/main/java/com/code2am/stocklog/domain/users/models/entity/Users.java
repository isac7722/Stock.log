package com.code2am.stocklog.domain.users.models.entity;

import com.code2am.stocklog.domain.auth.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TBL_USERS")
public class Users {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CAPITAL")
    private Integer capital;

    @Column(name = "SOCIAL")
    private String social;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createDate;

    // 유저 권한
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    @Builder
    public Users(String email, String password, String status, Integer capital, String social, LocalDateTime createDate, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.status = status;
        this.capital = capital;
        this.social = social;
        this.createDate = createDate;
        this.userRole = UserRole.ROLE_USER;
    }
}
