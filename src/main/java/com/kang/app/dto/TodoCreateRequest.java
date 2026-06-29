// Controller 에서 사용자의 요청 body를 받을 때 쓰는 DTO
package com.kang.app.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoCreateRequest { // 사용자가 보낸 생성요청 데이터를 받는 클래스

    @NotBlank(message = "Todo 내용은 비어 있을 수 없습니다.")
    private String content;
}
