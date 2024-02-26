package com.code2am.stocklog.domain.auth.email.repository;

import com.code2am.stocklog.domain.auth.email.model.entity.EmailVerification;
import com.code2am.stocklog.domain.users.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailVerification, String> {

    Optional<EmailVerification> findByEmail(String email);
}
