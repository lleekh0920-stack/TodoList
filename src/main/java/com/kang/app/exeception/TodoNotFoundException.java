package com.kang.app.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException{

    public TodoNotFoundException(){
        super("Todo를 찾을 수 없습니다.");
    }
}
