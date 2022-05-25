package kr.or.cola.backend.todo.folder.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class FolderUpdateRequestDto {

    private String name;
    private String color;

    public FolderUpdateRequestDto(String name, String color){
        this.color = color;
        this.name = name;
    }
}
