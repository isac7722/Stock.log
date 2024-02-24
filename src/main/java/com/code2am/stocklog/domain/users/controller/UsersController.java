package com.code2am.stocklog.domain.users.controller;

import com.code2am.stocklog.domain.users.models.dto.LoginDTO;
import com.code2am.stocklog.domain.users.models.entity.Users;
import com.code2am.stocklog.domain.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
@Tag(name = "회원 관리 API", description = "회원 관리하는 API 목록")
public class UsersController {

//    @Autowired
//    private UsersService usersServices;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Operation(
//            summary = "회원가입",
//            description = "신규 회원을 등록")
//    @ApiResponses(value ={
//            @ApiResponse(responseCode = "200", description = "회원가입이 완료되었습니다."),
//            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 잘못 입력되었습니다."),
//            @ApiResponse(responseCode = "500", description = "서버에서 오류가 발생되었습니다")
//    })
//    @PostMapping("/signup")
//    public ResponseEntity createAccount(@RequestBody Users user){
//
//        Users signUp = usersServices.createAccount(user);
//        if(Objects.isNull(signUp)){
//            return ResponseEntity.status(500).body("가입이 실패되었습니다.");
//        }
//
//        return ResponseEntity.ok(signUp);
//    }
//
//    @Operation(
//            summary = "자체 로그인",
//            description = "등록된 회원이 로그인")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "로그인이 완료되었습니다."),
//            @ApiResponse(responseCode = "404", description = "잘못된 값이 입력되었습니다."),
//            @ApiResponse(responseCode = "500", description = "서버에서 오류가 되었습니다.")
//    })
//    @PostMapping("/login")
//    public ResponseEntity loginUsers(@RequestBody LoginDTO loginDTO) {
//        // 사용자 인증
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
//        );
//
//        return ResponseEntity.ok(authentication);
//    }
}
