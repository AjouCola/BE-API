package kr.or.cola.backend.todo.folder.dto;

import kr.or.cola.backend.todo.folder.domain.Folder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FolderResponseDto {
    private final Long folderId;
    private final String name;
    private final String color;

    @Builder
    public FolderResponseDto(Folder folder) {
        this.folderId = folder.getFolderId();
        this.color = folder.getColor();
        this.name = folder.getName();
    }
}
