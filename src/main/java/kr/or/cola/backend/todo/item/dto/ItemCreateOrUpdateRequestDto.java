package kr.or.cola.backend.todo.item.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemCreateOrUpdateRequestDto {
    private List<ItemDto> itemDtos;
}
