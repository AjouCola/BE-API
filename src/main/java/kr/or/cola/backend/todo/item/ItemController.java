package kr.or.cola.backend.todo.item;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.todo.item.dto.ItemsResponseDto;
import kr.or.cola.backend.todo.item.dto.ItemCreateOrUpdateRequestDto;
import kr.or.cola.backend.todo.item.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveItem(@RequestBody List<ItemsResponseDto> itemsResponseDtos) {
        itemService.saveItems(itemsResponseDtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<ItemDto>> getDateItem(@LoginUser SessionUser sessionUser,
                                                     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return ResponseEntity.ok(itemService.readItems(sessionUser.getUserId(), date));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllByFolderId(@RequestParam Long folderId) {
        itemService.deleteAll(folderId);
        return ResponseEntity.ok().build();
    }

}
