package kr.or.cola.backend.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class TodoProgressResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private List<ProgressDto> todoProgress;

    public TodoProgressResponseDto(LocalDate date, List<ProgressDto> todoProgress) {
        this.date = date;
        this.todoProgress = todoProgress;
    }

}
