package com.kang.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 특정 Todo를 찾지 못했을 때 사용할 우리 프로젝트 전용 예외입니다.
// @ResponseStatus 덕분에 이 예외가 Controller까지 올라가면 HTTP 404로 응답됩니다.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException() {
        super("Todo를 찾을 수 없습니다.");
    }
}
