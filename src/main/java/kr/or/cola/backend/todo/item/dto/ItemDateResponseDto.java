package kr.or.cola.backend.todo.item.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemDateResponseDto {
    private List<ItemDto> itemDtos;

    @Builder
    public ItemDateResponseDto(List<ItemDto> dtos) {
        this.itemDtos = dtos;
    }
}
