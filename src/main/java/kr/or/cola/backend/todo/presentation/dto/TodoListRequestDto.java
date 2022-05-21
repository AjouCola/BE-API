package kr.or.cola.backend.todo.presentation.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class TodoListRequestDto {
    private LocalDate date;

    public TodoListRequestDto(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
    }
}
