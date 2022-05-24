package kr.or.cola.backend.todo.item.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemUpdateRequestDto {
    private Long folderId;
    private LocalDateTime date;
    private int progress;
    private String todos;
}
