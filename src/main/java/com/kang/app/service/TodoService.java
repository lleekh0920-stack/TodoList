package com.kang.app.service;

import com.kang.app.entity.Todo;
import com.kang.app.exception.TodoNotFoundException;
import com.kang.app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Service는 Controller와 Repository 사이에서 실제 기능 흐름을 담당합니다.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Todo createTodo(String content) {
        Todo todo = new Todo(content);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodos(String keyword, Boolean completed) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasCompleted = completed != null;

        if (hasKeyword && hasCompleted) {
            return todoRepository.findByContentContainingAndCompleted(keyword, completed, sort);
        }

        if (hasKeyword) {
            return todoRepository.findByContentContaining(keyword, sort);
        }

        if (hasCompleted) {
            return todoRepository.findByCompleted(completed, sort);
        }

        return todoRepository.findAll(sort);
    }

    public Todo getTodo(Long id) {
        return findTodo(id);
    }

    @Transactional
    public Todo toggleTodo(Long id) {
        Todo todo = findTodo(id);
        todo.toggleCompleted();
        return todo;
    }

    @Transactional
    public Todo updateTodo(Long id, String content) {
        Todo todo = findTodo(id);
        todo.updateContent(content);
        return todo;
    }

    @Transactional
    public void deleteTodo(Long id) {
        Todo todo = findTodo(id);
        todoRepository.delete(todo);
    }

    // id로 Todo를 찾는 로직이 여러 메서드에서 반복되므로 private 메서드로 모았습니다.
    private Todo findTodo(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(TodoNotFoundException::new);
    }
}
