package com.code2am.stocklog.domain.auth.email.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailCheckDTO {
    @Email
    @NotEmpty(message = "이메일을 입력해주세요")
    private String userEmail;

    @NotEmpty(message = "인증번호를 입력해주세요")
    private Integer authNum;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getAuthNum() {
        return authNum;
    }

    public void setAuthNum(Integer authNum) {
        this.authNum = authNum;
    }
}
