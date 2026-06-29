package com.kang.app.dto;

import com.kang.app.entity.Todo;
import lombok.Getter;

@Getter
public class TodoResponse {

    private Long id;
    private String content;
    private boolean completed;

    public TodoResponse(Todo todo){
        this.id= todo.getId();
        this.content=todo.getContent();
        this.completed=todo.isCompleted();
    }

}
