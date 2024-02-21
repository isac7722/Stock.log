package com.code2am.stocklog.domain.users.controller;

import com.code2am.stocklog.domain.users.models.entity.Users;
import com.code2am.stocklog.domain.users.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
@Tag(name = "", description = "매매 노트를 관리하는 API")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public ResponseEntity userCreateByUsers(@RequestBody Users user){

        Users signUp = usersService.usersCreateByUsers(user);
        if(Objects.isNull(signUp)){
            return ResponseEntity.status(500).body("가입이 실패되었습니다.");
        }

        return ResponseEntity.ok(signUp);
    }

}
