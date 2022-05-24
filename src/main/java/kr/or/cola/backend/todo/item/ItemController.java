package kr.or.cola.backend.todo.item;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.item.dto.ItemDateResponseDto;
import kr.or.cola.backend.todo.item.dto.ItemCreateOrUpdateRequestDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    private ItemService itemService;

    @PostMapping("")
    public ResponseEntity<Void> createItem(@RequestBody ItemCreateOrUpdateRequestDto requestDto) {
        itemService.createOrUpdateItems(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{date}")
    public ResponseEntity<ItemDateResponseDto> getDateItem(@LoginUser SessionUser sessionUser,
                                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-DD") LocalDate date,
                                                           @RequestParam(value = "folder") @Nullable Long folderId) {
        if (folderId == null){
            return ResponseEntity.ok()
                    .body(ItemDateResponseDto.builder()
                            .dtos(itemService.readItems(sessionUser.getUserId(), date))
                            .build());
        }
        else {
            return ResponseEntity.ok().body(ItemDateResponseDto.builder().dtos(List.of(itemService.readItem(date, folderId))).build());
        }

    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllByFolderId(@RequestParam Long folderId) {
        itemService.deleteAll(folderId);
        return ResponseEntity.ok().build();
    }

}
