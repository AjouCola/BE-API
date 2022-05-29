package kr.or.cola.backend.todo.item;

import kr.or.cola.backend.todo.dto.ProgressDto;
import kr.or.cola.backend.todo.folder.domain.Folder;
import kr.or.cola.backend.todo.item.dto.ItemsResponseDto;
import kr.or.cola.backend.todo.item.domain.Item;
import kr.or.cola.backend.todo.item.domain.ItemRepository;
import kr.or.cola.backend.todo.item.dto.ItemDto;
import kr.or.cola.backend.todo.item.dto.ItemCreateOrUpdateRequestDto;
import kr.or.cola.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import software.amazon.ion.NullValueException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public void saveItems(List<ItemsResponseDto> itemsResponseDtos) {
        itemsResponseDtos.forEach(itemDto -> {
            Item item = itemRepository.findById(itemDto.getItemId()).orElseThrow(()-> new IllegalArgumentException("Cannot found item"));
            item.update(item.getDate(), item.getFolderId(), itemDto.getTodos(), itemDto.getProgress());
            itemRepository.save(item);
        });
    }
    public Item createUpdateEmptyItemList(LocalDate date, Long folderId) {
        Item item =  Item.builder()
                .date(date)
                .folderId(folderId)
                .todos("")
                .progress(0)
                .build();
        itemRepository.save(
                item
        );
        return item;
    }

    // Read
    public List<ItemDto> readItems(Long userId, LocalDate date) {
        List<Long> folderIds = userRepository.findById(userId).orElseThrow().getFolderOrder();
        List<Item> items = new ArrayList<>();
        folderIds.forEach(folderId -> {
            Item item = itemRepository.findByDateAndFolderId(date, folderId).orElse(createUpdateEmptyItemList(date, folderId));
           items.add(item);
        });

        return items.stream()
                .map(ItemDto::new).collect(Collectors.toList());
    }

    // Delete
    public void deleteAll(Long folderId) {
        itemRepository.deleteAllByFolderId(folderId);
    }
}
