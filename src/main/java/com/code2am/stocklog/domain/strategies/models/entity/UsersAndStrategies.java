package com.code2am.stocklog.domain.strategies.models.entity;


import com.code2am.stocklog.domain.users.models.entity.Users;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass(UsersAndStrategiesId.class) // 복합키 사용을 위한 식별자 클래스
@Table(name = "TBL_USER_AND_STRATEGIES")
@Data
public class UsersAndStrategies {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private Users users;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STRATEGY_ID")
    private Strategies strategies;

}
