package com.kang.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity  //DB테이블이 될 자바 클래스
@Getter
@NoArgsConstructor // JPA는 기본 생성자가 필요하다..?
@EntityListeners(AuditingEntityListener.class)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 값을 DB가 자동으로 증가시키게 해줌
    private Long id;
    private String content; // content => ToDo의 내용
    private boolean completed; // 완료 여부

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    public Todo(String content){ // ToDo를 새로 만들었을때 사용할 생성자
        this.content = content;
        this.completed = false;
    }
    public void toggleCompleted(){  // 완료상태를 반대로 바꾸는 메서드;
        this.completed = !this.completed;
    }
    public void updateContent(String content){
        this.content = content;
    }
}
