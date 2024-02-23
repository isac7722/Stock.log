package com.code2am.stocklog.common.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        JSONObject jsonObject;
        String failMsg = null;

        if(exception instanceof AuthenticationServiceException){
            failMsg = "존재하지 않는 사용자입니다.";
        } else if (exception instanceof UsernameNotFoundException) {
            failMsg = "존재하지 않는 이메일입니다.";
        } else{
            failMsg = "로그인에 실패하였습니다";
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("failType", failMsg);

        jsonObject = new JSONObject(resultMap);

        printWriter.println(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}
