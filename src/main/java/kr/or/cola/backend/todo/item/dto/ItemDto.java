package kr.or.cola.backend.todo.item.dto;

import kr.or.cola.backend.todo.item.domain.Item;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ItemDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long folderId;

    private String todos;

    private int progress;

    public ItemDto(Item item) {
        this.folderId = item.getFolderId();
        this.progress = item.getProgress();
        this.todos = item.getTodos();
        this.date = item.getDate();
    }
}
