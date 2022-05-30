package kr.or.cola.backend.todo;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.dto.TodoProgressResponseDto;
import kr.or.cola.backend.todo.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{date}")
    public ResponseEntity<TodoResponseDto> getTodoList(@LoginUser SessionUser sessionUser,
                                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(todoService.getTodolist(sessionUser.getUserId(), date));
    }

    @GetMapping("/progress/{month}")
    public ResponseEntity<List<TodoProgressResponseDto>> getProgressList(@LoginUser SessionUser sessionUser,
                                                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate month) {
        return ResponseEntity.ok(todoService.getProgressList(sessionUser.getUserId(), month));
    }
}
