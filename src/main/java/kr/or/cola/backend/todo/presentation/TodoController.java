package kr.or.cola.backend.todo.presentation;


import kr.or.cola.backend.post.presentation.dto.PostResponseDto;
import kr.or.cola.backend.todo.TodoService;
import kr.or.cola.backend.todo.presentation.dto.TodoListRequestDto;
import kr.or.cola.backend.todo.presentation.dto.TodoResponseDto;
import kr.or.cola.backend.todo.presentation.dto.TodoUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    /**
     * 진행률 조회 API(캘린더)\
     */

    /**
     * todolist 조회
     */
    public ResponseEntity<TodoResponseDto> getTodoList(TodoListRequestDto requestDto) {
        TodoResponseDto responseDto = todoService.getTodoListByDate(requestDto.getDate());
        return ResponseEntity.ok(responseDto);
    }

    /**
     * todolist 저장 및 업데이트
     */
    public ResponseEntity<Void> updateTodo(TodoUpdateRequestDto todoUpdateRequestDto) {
        todoService.updateTodo(todoUpdateRequestDto);
        return ResponseEntity.ok().build();
    }
}
