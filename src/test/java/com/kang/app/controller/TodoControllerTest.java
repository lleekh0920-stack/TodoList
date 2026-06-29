package com.kang.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTodo() throws Exception {
        // POST /todos가 정상 JSON을 받으면 Todo를 생성하고 201 Created를 반환해야 합니다.
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"테스트 Todo\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("테스트 Todo"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void createTodoWithBlankContent() throws Exception {
        // @Valid + @NotBlank 검증이 동작하면 빈 content 요청은 400이 됩니다.
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Todo 내용은 비어 있을 수 없습니다."));
    }

    @Test
    void getMissingTodo() throws Exception {
        // 없는 id를 조회하면 TodoNotFoundException이 발생하고 404로 변환됩니다.
        mockMvc.perform(get("/todos/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Todo를 찾을 수 없습니다."));
    }
}
