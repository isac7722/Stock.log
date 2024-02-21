package com.code2am.stocklog.domain.users.service;

import com.code2am.stocklog.domain.users.models.entity.Users;
import com.code2am.stocklog.domain.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    private BCryptPasswordEncoder encoder;

    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    public Users usersCreateByUsers(Users user){
        user.setPass(encoder.encode(user.getPass()));
        user.setStatus("Y");

        Users signUp = usersRepository.save(user);

        return signUp;
    }
}
