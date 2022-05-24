package kr.or.cola.backend.todo.presentation.dto.request;

import kr.or.cola.backend.todo.item.dto.SimpleItemResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TodoUpdateRequestDto {
    private LocalDate date;
    private List<SimpleItemResponseDto> todoList;

    @Builder
    public TodoUpdateRequestDto(LocalDate date, List<SimpleItemResponseDto> todoList) {
        this.date = date;
        this.todoList = todoList;
    }
}
