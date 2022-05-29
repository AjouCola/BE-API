package kr.or.cola.backend.todo;

import kr.or.cola.backend.todo.dto.TodoFolderResponseDto;
import kr.or.cola.backend.todo.dto.TodoResponseDto;
import kr.or.cola.backend.todo.folder.domain.Folder;
import kr.or.cola.backend.todo.folder.domain.FolderRepository;
import kr.or.cola.backend.todo.item.dto.ItemsResponseDto;
import kr.or.cola.backend.todo.item.domain.Item;
import kr.or.cola.backend.todo.item.domain.ItemRepository;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;
    private final ItemRepository itemRepository;

    public TodoResponseDto getTodolist(Long userId, LocalDate date) {
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Invalid user id"));
        List<Long> folderOrders = user.getFolderOrder();

        Map<Long, Folder> folders = new HashMap<>();
        folderRepository.findAllByUserId(userId)
                .forEach(folder -> folders.put(folder.getFolderId(), folder));

        Map<Long, Item> items = new HashMap<>();
        itemRepository. findAllByDateAndFolderIdIn(date, folderOrders)
                .forEach(item -> items.put(item.getFolderId(), item));;

        List<TodoFolderResponseDto> folderResponses = new ArrayList<>();

        folderOrders.forEach(folderId -> {
            TodoFolderResponseDto folderResponseDto = TodoFolderResponseDto.builder()
                    .folder(folders.get(folderId))
                    .itemsResponseDto(new ItemsResponseDto(items.get(folderId)))
                    .build();

            folderResponses.add(folderResponseDto);
        });

        return new TodoResponseDto(date, folderResponses);
    }
}
