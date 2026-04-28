package com.example.umc10th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {

    HttpStatus getStatus(); // HTTP 상태를 반환 (성공은 주로 OK)
    String getCode();       // 애플리케이션 내부 성공 코드를 반환
    String getMessage();    // 사용자에게 보여줄 성공 메시지를 반환
}

