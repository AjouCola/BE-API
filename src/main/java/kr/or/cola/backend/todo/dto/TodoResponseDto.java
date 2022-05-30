package kr.or.cola.backend.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class TodoResponseDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private List<TodoFolderResponseDto> folders;



    @Builder
    public TodoResponseDto(LocalDate date, List<TodoFolderResponseDto> folders){
        this.date = date;
        this.folders = folders;
    }


}
