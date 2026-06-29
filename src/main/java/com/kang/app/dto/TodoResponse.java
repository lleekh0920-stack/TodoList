package com.kang.app.dto;

import com.kang.app.entity.Todo;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class TodoResponse {

    private Long id;
    private String content;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public TodoResponse(Todo todo){
        this.id= todo.getId();
        this.content=todo.getContent();
        this.completed=todo.isCompleted();
        this.createdAt=todo.getCreatedAt();
        this.updateAt=todo.getUpdateAt();
    }

}
