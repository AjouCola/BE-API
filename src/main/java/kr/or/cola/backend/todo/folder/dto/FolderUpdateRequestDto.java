package kr.or.cola.backend.todo.folder.dto;

import lombok.Getter;

@Getter
public class FolderUpdateRequestDto {

    private final String name;
    private final String color;

    public FolderUpdateRequestDto(String name, String color){
        this.name = name;
        this.color = color;
    }
}
