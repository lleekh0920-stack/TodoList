package com.kang.app.repository;
import com.kang.app.entity.Todo;  //ToDo Entity 사용
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// ToDo를 DB에 저장하고 , 조회하고, 삭제하는 통로
public interface TodoRepository extends JpaRepository<Todo,Long> { // Todo Entity를 다루는 Repository이고 ToDo의 타입은 Long이다.
            List<Todo> findByContentContaining(String keyword, Sort sort);

            List<Todo> findByCompleted(boolean completed, Sort sort);

            List<Todo> findByContentContainingAndCompleted(String keyword, boolean completed, Sort sort);
}
