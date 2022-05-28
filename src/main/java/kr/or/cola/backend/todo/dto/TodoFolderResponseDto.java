package kr.or.cola.backend.todo.dto;

import kr.or.cola.backend.todo.folder.domain.Folder;
import kr.or.cola.backend.todo.folder.dto.ItemsResponseDto;
import lombok.Builder;

public class TodoFolderResponseDto {
    private ItemsResponseDto item;
    private String name;
    private String color;

    @Builder
    public TodoFolderResponseDto(Folder folder, ItemsResponseDto itemsResponseDto) {
        this.color = folder.getColor();
        this.name = folder.getName();
        this.item = itemsResponseDto;
    }
}
