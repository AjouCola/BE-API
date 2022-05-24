package kr.or.cola.backend.todo.item.dto;

import kr.or.cola.backend.todo.item.domain.Item;
import lombok.Getter;

@Getter
public class SimpleItemResponseDto {
    private Long ItemListId;
    private Long folderId;
    private int progress;
    private String todos;

    public SimpleItemResponseDto(Item item) {
        this.ItemListId = item.getId();
        this.folderId = item.getFolderId();
        this.progress = item.getProgress();
        this.todos = item.getContent();
    }
}
