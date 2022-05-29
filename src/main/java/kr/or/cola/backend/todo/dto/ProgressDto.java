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
    private final String color;
    private final String name;
    private final int progress;

    @Builder
    public ProgressDto(String color, String name, int progress) {
        this.color = color;
        this.progress = progress;
        this.name = name;
    }
}
