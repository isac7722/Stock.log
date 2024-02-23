package com.code2am.stocklog.domain.strategies.models.entity;

import com.code2am.stocklog.domain.users.models.entity.Users;
import lombok.Data;

import java.io.Serializable;

@Data
public class UsersAndStrategiesId implements Serializable {

    private Users users;

    private Strategies strategies;
}
