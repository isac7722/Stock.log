package com.code2am.stocklog.domain.auth.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${mail.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${mail.properties.mail.smtp.starttls.required}")
    private boolean starttlsRequired;

    @Value("${mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;

    @Value("${mail.properties.mail.smtp.timeout}")
    private int timeout;

    @Value("${mail.properties.mail.smtp.writetimeout}")
    private int writeTimeout;


    @Bean
    public JavaMailSender mailSender() {//JAVA MAILSENDER 인터페이스를 구현한 객체를 빈으로 등록하기 위함.

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();//JavaMailSender 의 구현체를 생성하고
        mailSender.setHost(host);// 속성을 넣기 시작합니다. 이메일 전송에 사용할 SMTP 서버 호스트를 설정
        mailSender.setPort(port);// 587로 포트를 지정
        mailSender.setUsername(username);//구글계정을 넣습니다.
        mailSender.setPassword(password);//구글 앱 비밀번호를 넣습니다.

        Properties javaMailProperties = new Properties();//JavaMail의 속성을 설정하기 위해 Properties 객체를 생성
        javaMailProperties.put("mail.transport.protocol", "smtp");//프로토콜로 smtp 사용
        javaMailProperties.put("mail.smtp.auth", auth);//smtp 서버에 인증이 필요
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//SSL 소켓 팩토리 클래스 사용
        javaMailProperties.put("mail.smtp.starttls.enable", starttlsEnable);//STARTTLS(TLS를 시작하는 명령)를 사용하여 암호화된 통신을 활성화
        javaMailProperties.put("mail.debug", "true");//디버깅 정보 출력
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.naver.com");//smtp 서버의 ssl 인증서를 신뢰
        javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");//사용할 ssl 프로토콜 버젼

        mailSender.setJavaMailProperties(javaMailProperties);//mailSender에 우리가 만든 properties 넣고

        return mailSender;//빈으로 등록한다.
    }
}
