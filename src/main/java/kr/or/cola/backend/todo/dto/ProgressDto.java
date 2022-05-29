package kr.or.cola.backend.todo.dto;

import lombok.Builder;

/**
 * {
 *      color : "#aaaaaa",
 *      name : "할 일",
 *      progress : 0
 * }
 */

public class ProgressDto {
    private String color;
    private String name;
    private int progress;

    @Builder
    public ProgressDto(String color, String name, int progress) {
        this.color = color;
        this.progress = progress;
        this.name = name;
    }
}
