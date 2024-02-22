package com.code2am.stocklog.domain.users.models.dto;

import lombok.Data;

@Data
public class UsersDTO {

    private Integer userId;

    private String userEmail;

    private String userPass;

    private String userStatus;

    private Integer userCapital;
}
