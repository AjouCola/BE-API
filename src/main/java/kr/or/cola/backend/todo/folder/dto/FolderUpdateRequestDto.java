package kr.or.cola.backend.todo.folder.dto;

import kr.or.cola.backend.todo.folder.domain.Folder;
import lombok.Getter;

@Getter
public class FolderUpdateRequestDto {

    private final String name;
    private final String color;

    public FolderUpdateRequestDto(Folder folder){
        this.color = folder.getColor();
        this.name = folder.getName();
    }
}
