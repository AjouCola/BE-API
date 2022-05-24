package kr.or.cola.backend.todo.folder.dto;

import kr.or.cola.backend.todo.folder.domain.Folder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FolderResponseDto {
    private Long folderId;
    private String name;
    private String color;
    private int order;

    @Builder
    public FolderResponseDto(Folder folder) {
        this.folderId = folder.getId();
        this.color = folder.getColor();
        this.name = folder.getName();
        this.order = folder.getOrder();
    }
}
