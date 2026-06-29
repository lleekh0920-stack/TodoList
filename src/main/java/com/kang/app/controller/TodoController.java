// Controller는 외부에서 들어오는 HTTP 요청을 받는 입구

package com.kang.app.controller;
import com.kang.app.dto.TodoCreateRequest;
import com.kang.app.dto.TodoResponse;
import com.kang.app.dto.TodoUpdateRequest;
import com.kang.app.entity.Todo;
import com.kang.app.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
// Controller의 기본주소를 /todos로 정함,
// 클래스 안의 API들은 전부 todos로 시작

public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoResponse createTodo(@Valid @RequestBody TodoCreateRequest request){
        Todo todo = todoService.createTodo(request.getContent());
        return new TodoResponse(todo);
    }
    //Todo 전체 목록 조회 API
    //Service 에서 전체 Todo를 가져와서 그대로 응답을 반환
    @GetMapping
    public List<TodoResponse> getTodos(
            @RequestParam(required = false)String keyword,
            @RequestParam(required = false) Boolean completed
    ){
        return todoService.getTodos(keyword, completed)
                .stream()
                .map(TodoResponse::new)
                .toList();
    }
    @PatchMapping("/{id}/toggle")
    public TodoResponse toggleTodo(@PathVariable Long id){ //Todo 완료 상태를 바꾸는 API
        // @PathVariable Long id => 주소 안에 있는 id 값을 변수로 꺼내는 역할을 함
        Todo todo = todoService.toggleTodo(id);
        return new TodoResponse(todo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}")
    public TodoResponse updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request
    ){
        Todo todo = todoService.updateTodo(id, request.getContent());
        return new TodoResponse(todo);
    }
    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id){
        Todo todo = todoService.getTodo(id);
        return new TodoResponse(todo);
    }

}
