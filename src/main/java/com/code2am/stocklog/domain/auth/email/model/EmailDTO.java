package com.code2am.stocklog.domain.auth.email.model;

import lombok.Data;

@Data
public class EmailDTO {

    private String email;
    private String authCode;

}
