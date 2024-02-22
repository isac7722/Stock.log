package com.code2am.stocklog.domain.users.models.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
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
    @Nullable
    private String social;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createDate;
}
