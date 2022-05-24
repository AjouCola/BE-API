package kr.or.cola.backend.todo.presentation.dto.request;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class TodoListRequestDto {
    private LocalDate date;
}
