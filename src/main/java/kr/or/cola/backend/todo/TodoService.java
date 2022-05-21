package kr.or.cola.backend.todo;

import kr.or.cola.backend.todo.domain.TodoFolderRepository;
import kr.or.cola.backend.todo.domain.TodoItemRepository;
import kr.or.cola.backend.todo.presentation.dto.TodoResponseDto;
import kr.or.cola.backend.todo.presentation.dto.TodoUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoItemRepository todoItemRepository;
    private final TodoFolderRepository todoFolderRepository;

    public void updateTodo(TodoUpdateRequestDto requestDto) {

    }

    public TodoResponseDto getTodoListByDate(LocalDate date) {
        return null;
    }
}
