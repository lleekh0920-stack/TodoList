package com.kang.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// 모든 Controller에서 발생한 예외를 한곳에서 처리하는 클래스입니다.
// 에러 응답 모양을 { "message": "..." } 형태로 통일하면 프론트에서 다루기 쉽습니다.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid 검증 실패, 예: content가 빈 문자열일 때 발생합니다.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return Map.of("message", message);
    }

    // 없는 id를 조회/수정/삭제하려고 할 때 발생하는 예외를 처리합니다.
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleTodoNotFoundException(TodoNotFoundException e) {
        return Map.of("message", e.getMessage());
    }
}
