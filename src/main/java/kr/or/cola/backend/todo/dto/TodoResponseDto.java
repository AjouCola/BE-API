package kr.or.cola.backend.todo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import kr.or.cola.backend.todo.folder.dto.FolderResponseDto;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

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
