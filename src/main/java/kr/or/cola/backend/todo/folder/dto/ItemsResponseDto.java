package kr.or.cola.backend.todo.folder.dto;

import kr.or.cola.backend.todo.item.domain.Item;

public class ItemsResponseDto {

    private Long itemId;

    private int progress;

    private String todos;

    public ItemsResponseDto(Item item) {
        this.itemId = item.getId();
        this.progress = item.getProgress();
        this.todos = item.getTodos();
    }

}
