package kr.or.cola.backend.todo.presentation;


import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.presentation.dto.request.TodoListRequestDto;
import kr.or.cola.backend.todo.presentation.dto.response.TodoListResponseDto;
import kr.or.cola.backend.todo.presentation.dto.request.TodoUpdateRequestDto;
//import kr.or.cola.backend.todo.presentation.temp.TodoProgressResponseDto;
import kr.or.cola.backend.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    /**
     * 진행률 조회 API(캘린더)\
     */
//    @PostMapping("/prog")
//    public ResponseEntity<TodoProgressResponseDto> getTodoProgress(@LoginUser SessionUser user, @RequestBody TodoListRequestDto requestDto) {
//      return null;
//    }
    /**
     * todolist 조회
     */
    @GetMapping("/{date}")
    public ResponseEntity<TodoListResponseDto> getTodoList(@LoginUser SessionUser user, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-DD") LocalDate date) {
        TodoListResponseDto responseDto = todoService.getTodoListByDate(user.getUserId(), date);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * todolist 저장 및 업데이트
     */
    @PostMapping("/update")
    public ResponseEntity<Void> updateTodo(@RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        todoService.updateTodoItem(todoUpdateRequestDto.getDate(), todoUpdateRequestDto.getTodoList());
        return ResponseEntity.ok().build();
    }
}
