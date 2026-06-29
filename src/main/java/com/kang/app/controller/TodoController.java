package com.kang.app.controller;

import com.kang.app.dto.TodoCreateRequest;
import com.kang.app.dto.TodoResponse;
import com.kang.app.dto.TodoUpdateRequest;
import com.kang.app.entity.Todo;
import com.kang.app.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller는 HTTP 요청을 받는 입구입니다.
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    // POST /todos: 새 Todo를 생성합니다.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@Valid @RequestBody TodoCreateRequest request) {
        Todo todo = todoService.createTodo(request.getContent());
        return new TodoResponse(todo);
    }

    // GET /todos?keyword=...&completed=...: 전체 조회, 검색, 완료 필터를 한 API에서 처리합니다.
    @GetMapping
    public List<TodoResponse> getTodos(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean completed
    ) {
        return todoService.getTodos(keyword, completed)
                .stream()
                .map(TodoResponse::new)
                .toList();
    }

    // GET /todos/{id}: 특정 Todo 하나를 조회합니다.
    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id) {
        Todo todo = todoService.getTodo(id);
        return new TodoResponse(todo);
    }

    // PATCH /todos/{id}: Todo 내용을 수정합니다.
    @PatchMapping("/{id}")
    public TodoResponse updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request
    ) {
        Todo todo = todoService.updateTodo(id, request.getContent());
        return new TodoResponse(todo);
    }

    // PATCH /todos/{id}/toggle: 완료/미완료 상태를 반대로 바꿉니다.
    @PatchMapping("/{id}/toggle")
    public TodoResponse toggleTodo(@PathVariable Long id) {
        Todo todo = todoService.toggleTodo(id);
        return new TodoResponse(todo);
    }

    // DELETE /todos/{id}: 삭제 성공 시 응답 본문이 없으므로 204를 사용합니다.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
