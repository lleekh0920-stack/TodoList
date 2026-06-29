package com.kang.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// @Entity: JPA가 이 클래스를 DB 테이블과 연결된 객체로 인식하게 합니다.
@Entity
@Getter
@NoArgsConstructor
// 생성일/수정일 자동 기록을 위해 JPA Auditing Listener를 연결합니다.
@EntityListeners(AuditingEntityListener.class)
public class Todo {

    // @Id: 이 필드가 테이블의 기본키(PK)라는 뜻입니다.
    @Id
    // IDENTITY: DB가 id 값을 1, 2, 3...처럼 자동 증가시킵니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean completed;

    // 처음 저장될 때 시간이 자동으로 들어갑니다.
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정될 때마다 시간이 자동으로 갱신됩니다.
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Todo(String content) {
        this.content = content;
        this.completed = false;
    }

    // Entity 내부 상태 변경은 메서드로 표현해두면 의미가 분명해집니다.
    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
