package kr.or.cola.backend.todo.item.dto;

import lombok.Builder;

import java.util.List;

public class DateItemResponseDto {
    private List<SimpleItemResponseDto> itemList;

    @Builder
    public DateItemResponseDto(List<SimpleItemResponseDto> itemList) {
        this.itemList = itemList;
    }
}
