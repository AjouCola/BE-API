package kr.or.cola.backend.todo.item.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Progress {
    private Long folderId;
    private LocalDate date;
    private int progress;

    @Builder
    public Progress(Item item) {
        this.date = item.getDate();
        this.folderId = item.getFolderId();
        this.progress = item.getProgress();
    }

}
