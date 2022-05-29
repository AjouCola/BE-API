package kr.or.cola.backend.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class TodoResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private List<TodoFolderResponseDto> folders;

    @Builder
    public TodoResponseDto(LocalDate date, List<TodoFolderResponseDto> folders){
        this.date = date;
        this.folders = folders;
    }


}
