package com.code2am.stocklog.domain.auth.email.controller;

import com.code2am.stocklog.domain.auth.email.model.dto.EmailCheckDTO;
import com.code2am.stocklog.domain.auth.email.model.dto.EmailRequestDTO;
import com.code2am.stocklog.domain.auth.email.service.EmailService;
import com.code2am.stocklog.domain.auth.email.model.EmailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@Tag(name = "메일 API", description = "서버에서 메일을 보내는 API")
public class EmailController {

    private final EmailService emailService;


    /* 메일을 통해 인증 번호를 보낸다 */
    @Operation(
            summary = "인증번호 발송",
            description = "인증번호를 발송합니다.",
            tags = {"EmailController","post","Email"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이메일을 성공적으로 발송함"),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 입력되지 앟음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @Parameter(name = "verification code", description = "인증번호")
    @PostMapping("/sendCode")
    public ResponseEntity<?> mailSend(@RequestParam("userEmail") String userEmail) {

        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setEmail(userEmail);

        return ResponseEntity.ok(emailService.joinEmail(emailRequestDTO.getEmail()));
    }

    /* 입력한 값과 받은 인증번호를 확인한다 */
    @Operation(
            summary = "인증번호 확인",
            description = "인증번호를 확인합니다.",
            tags = {"EmailController","post","Email"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증번호를 확인했습니다."),
            @ApiResponse(responseCode = "404", description = "요청에 필요한 값이 정상적으로 입력되지 앟음."),
            @ApiResponse(responseCode = "500", description = "요청받은 서버가 정상적으로 동작하지 않음.")
    })
    @PostMapping("/mailVerify")
    public ResponseEntity<Integer> authCheck(@RequestParam("userEmail")String userEmail, @RequestParam("authNum") Integer authNum){

        // 받아온 userEmail 과 authNum 을 emailCheckDTO에 저장
        EmailCheckDTO emailCheckDTO = new EmailCheckDTO();

        emailCheckDTO.setUserEmail(userEmail);
        emailCheckDTO.setAuthNum(authNum);

        Boolean emailConfirmed = emailService.checkAuthNum(emailCheckDTO.getUserEmail(), emailCheckDTO.getAuthNum());

        if(emailConfirmed){
            // 성공하면 1을 반환
            return ResponseEntity.ok(1);
        }else {
            // 실패하면 0을 반환
            return ResponseEntity.ok(0);
        }
    }
}
