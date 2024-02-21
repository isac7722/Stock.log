package com.code2am.stocklog.domain.users.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_USERS")
public class Users {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PASS")
    private String pass;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CAPITAL")
    private Integer capital;

    public Users() {
    }

    public Users(int userId, String email, String pass, String status, int capital) {
        this.userId = userId;
        this.email = email;
        this.pass = pass;
        this.status = status;
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", status='" + status + '\'' +
                ", capital=" + capital +
                '}';
    }
}
