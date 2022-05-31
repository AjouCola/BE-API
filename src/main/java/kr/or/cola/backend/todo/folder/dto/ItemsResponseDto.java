package kr.or.cola.backend.todo.folder.dto;

import kr.or.cola.backend.todo.item.domain.Item;
import lombok.Getter;

@Getter
public class ItemsResponseDto {

    private final Long itemsId;

    private final int progress;

    private final String todos;

    public ItemsResponseDto(Item item) {
        this.itemsId = item.getId();
        this.progress = item.getProgress();
        this.todos = item.getTodos();
    }

}
