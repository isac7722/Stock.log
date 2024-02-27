package com.code2am.stocklog.domain.users.models.dto;

import com.code2am.stocklog.domain.auth.common.enums.UserRole;
import com.code2am.stocklog.domain.users.models.entity.Users;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private Integer userId;

    private String email;

    private String password;

    private String status;

    private Integer capital;

    private String social;

    private LocalDateTime createDate;

    private UserRole userRole;

    //생성자
    public UserDTO(Integer userId, String email, String password, String status, Integer capital, String social, LocalDateTime createDate, UserRole userRole) {
    }


    public static UserDTO of(Users user){

        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus(),
                user.getCapital(),
                user.getSocial(),
                user.getCreateDate(),
                user.getUserRole());

    }


    public Users toUsers(PasswordEncoder passwordEncoder){
        Users user = Users.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .status(status)
                        .capital(capital)
                        .social(social)
                        .createDate(createDate)
                        .userRole(userRole)
                        .build();
        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        List<GrantedAuthority> authority = Collections.singletonList(new SimpleGrantedAuthority(UserRole.ROLE_USER.toString()));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                this.email,
                this.password,
                authority);


        System.out.println(usernamePasswordAuthenticationToken);


        return usernamePasswordAuthenticationToken;
    }
}

