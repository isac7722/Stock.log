package com.code2am.stocklog.domain.auth.email.model.entity;

import com.code2am.stocklog.domain.auth.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TBL_EMAIL_VERIFICATION")
public class EmailVerification {

    @Id
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "VERIFICATION_CODE")
    private Integer verificationCode;

    @Builder
    public EmailVerification (String email, Integer verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }
}
