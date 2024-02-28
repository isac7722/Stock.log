package com.code2am.stocklog.domain.auth.common.service;

import com.code2am.stocklog.domain.users.models.entity.Users;
import com.code2am.stocklog.domain.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("2-1");
        System.out.println(username);


        Users user = usersRepository.findByEmail(username).get();

        if (username.equals(user.getEmail())){
            System.out.println("DB까지 통과");
        }

        UserDetails userDetails = createUserDetails(user);

        System.out.println(userDetails);

        if (username.equals(userDetails.getUsername())){
            System.out.println(true);
        }


        return userDetails;
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Users users) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(users.getUserRole().toString());

        System.out.println("2-2");

        User user = new User(
                String.valueOf(users.getEmail()),
                users.getPassword(),
                Collections.singleton(grantedAuthority)
        );

        System.out.println(user);

        return user;


    }
}
