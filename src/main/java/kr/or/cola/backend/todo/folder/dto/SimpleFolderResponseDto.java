package kr.or.cola.backend.todo.folder.dto;

import kr.or.cola.backend.todo.folder.domain.Folder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleFolderResponseDto {
    private Long folderId;
    private String name;
    private String color;
    private int order;

    @Builder
    public SimpleFolderResponseDto(Folder folder) {
        this.folderId = folder.getId();
        this.color = folder.getColor();
        this.name = folder.getName();
        this.order = folder.getOrder();
    }
}
