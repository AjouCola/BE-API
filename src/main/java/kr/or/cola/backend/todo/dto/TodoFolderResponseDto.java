package kr.or.cola.backend.todo.dto;

import kr.or.cola.backend.todo.folder.domain.Folder;
import kr.or.cola.backend.todo.folder.dto.ItemsResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoFolderResponseDto {
    private final ItemsResponseDto item;
    private final String name;
    private final String color;
    private final Long folder_id;

    @Builder
    public TodoFolderResponseDto(Folder folder, ItemsResponseDto itemsResponseDto) {
        this.folder_id = folder.getFolderId();
        this.color = folder.getColor();
        this.name = folder.getName();
        this.item = itemsResponseDto;
    }
}
