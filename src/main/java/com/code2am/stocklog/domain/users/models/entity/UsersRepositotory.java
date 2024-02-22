package com.code2am.stocklog.domain.users.models.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositotory extends JpaRepository<Users, Integer> {

    Optional<Users> readUsersByEmail(String email);
}
