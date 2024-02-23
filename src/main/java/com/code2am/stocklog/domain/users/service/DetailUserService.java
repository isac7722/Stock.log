package com.code2am.stocklog.domain.users.service;

import com.code2am.stocklog.domain.users.models.DetailUser;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailUserService implements UserDetailsService {

    private final UsersService usersService;

    public DetailUserService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(email == null || email.equals("")){
            throw new AuthenticationServiceException(email + "is Empty");
        }
        // DB에서 username에 해당하는 정보를 꺼내온다.
        return usersService.readUserByUserId(email)
                .map(data -> new DetailUser(Optional.of(data)))
                .orElseThrow(() -> new AuthenticationServiceException(email));

    }
}
