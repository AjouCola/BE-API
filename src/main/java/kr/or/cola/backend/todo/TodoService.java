package kr.or.cola.backend.todo;

import kr.or.cola.backend.todo.folder.domain.Folder;
import kr.or.cola.backend.todo.folder.domain.FolderRepository;
import kr.or.cola.backend.todo.item.domain.Item;
import kr.or.cola.backend.todo.item.domain.ItemRepository;
import kr.or.cola.backend.todo.folder.dto.SimpleFolderResponseDto;
import kr.or.cola.backend.todo.item.dto.SimpleItemResponseDto;
import kr.or.cola.backend.todo.presentation.dto.response.TodoListResponseDto;
//import kr.or.cola.backend.todo.presentation.temp.TodoProgressDto;
//import kr.or.cola.backend.todo.presentation.temp.TodoProgressListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ItemRepository itemRepository;
    private final FolderRepository folderRepository;

    public void updateTodoItem(LocalDate date, List<SimpleItemResponseDto> itemList) {
        itemList.forEach(i -> {
            Item item = itemRepository.findByDateAndFolderId(date, i.getFolderId());
            if (item == null){
                item = new Item();
            }
            item.update(date, i.getFolderId(), i.getTodos(), i.getProgress());
            itemRepository.save(item).getId();
        });

        return ;
    }

    public TodoListResponseDto getTodoListByDate(Long userId, LocalDate date) {
        List<Folder> folderList = folderRepository.findAllByUserId(userId);
        List<SimpleFolderResponseDto> responseFolderDto = new ArrayList<>();
        folderList.forEach(i -> {
            responseFolderDto.add(new SimpleFolderResponseDto(i));
        });
        List<Item> itemList = itemRepository.findByDate(date);
        List<SimpleItemResponseDto> responseItemDto = new ArrayList<>();
        itemList.forEach(i -> {
            responseItemDto.add(new SimpleItemResponseDto(i));
        });

        return new TodoListResponseDto(date, responseFolderDto, responseItemDto);
    }
}
