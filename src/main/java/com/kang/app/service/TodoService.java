package com.kang.app.service;

import com.kang.app.entity.Todo;
import com.kang.app.exeception.TodoNotFoundException;
import com.kang.app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//Service 는 직접 DB와 대화하지 않고 Repository에게 부탁함
@Service // 이 클래스가 Service 역할을 한다고 Spring에게 알려주는 어노테이션
@RequiredArgsConstructor // Lombok이 생성자를 자동으로 만들어주는 어노테이션

public class TodoService {

    private final TodoRepository todoRepository; // TodoRepository를 사용하기 위한 필드

    // Todo 생성요청
    public Todo createTodo(String content){
        Todo todo = new Todo(content); // 새 Todo 객체를만드는 코드.
        return todoRepository.save(todo); // 만든 Todo를 DB에 저장, return 하는 이유는 DB에서 자동 생성된 id까지 포함된 결과를 돌려받기 위해서
    }

    //Todo 조회요청
    public List<Todo> getTodos(String keyword, Boolean completed){ // Todo 여러 개를 담는 목록
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

       boolean hasKeyword= keyword != null && !keyword.isBlank();
       boolean hasCompleted = completed != null;

       if(hasKeyword && hasCompleted){
           return todoRepository.findByContentContainingAndCompleted(keyword, completed, sort);
       }
       if (hasKeyword){
           return todoRepository.findByContentContaining(keyword, sort);
       }
       if(hasCompleted){
           return todoRepository.findByCompleted(completed, sort);
       }
       return todoRepository.findAll(sort);

    }

    //Todo 완료 상태 변경 요청
    public Todo toggleTodo(Long id){ // 특정 Todo의 완료 상태를 바꾸는 메서드

        Todo todo = todoRepository.findById(id) //DB에서 id로 Todo를 찾음
                .orElseThrow(TodoNotFoundException::new);

        todo.toggleCompleted(); // Todo Entity 안에 만들었던 메서드를 호출

        return todoRepository.save(todo); // 변경된 Todo를 다시 저장
    }

    // Todo 삭제요청
    public void deleteTodo(Long id){ //todo를 삭제하는 메서드 , 삭제는 결과로 돌려줄 데이터가 없으니까 void
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(TodoNotFoundException::new);

        todoRepository.deleteById(id);

    }
    public Todo updateTodo(Long id, String content){
        Todo todo = todoRepository.findById(id)
                        .orElseThrow(TodoNotFoundException::new);
        todo.updateContent(content);

        return todoRepository.save(todo);
    }

    public Todo getTodo(Long id){
        return todoRepository.findById(id)
                .orElseThrow(TodoNotFoundException::new);
    }
}
