package com.kang.app.dto;

import com.kang.app.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

// 서버가 클라이언트에게 내려주는 응답 전용 DTO입니다.
// Entity를 그대로 노출하지 않고 필요한 값만 골라서 보여주는 습관이 중요합니다.
@Getter
public class TodoResponse {

    private final Long id;
    private final String content;
    private final boolean completed;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.completed = todo.isCompleted();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
}
