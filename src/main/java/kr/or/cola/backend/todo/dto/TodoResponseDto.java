package kr.or.cola.backend.todo.dto;


import kr.or.cola.backend.todo.folder.dto.FolderResponseDto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class TodoResponseDto {
    private LocalDate date;
    private List<TodoFolderResponseDto> folders;


    @Builder
    public TodoResponseDto(LocalDate date, List<TodoFolderResponseDto> folders){
        this.date = date;
        this.folders = folders;
    }


}
