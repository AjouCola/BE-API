package kr.or.cola.backend.todo.presentation.dto.response;

import kr.or.cola.backend.todo.folder.dto.FolderResponseDto;
import kr.or.cola.backend.todo.item.dto.SimpleItemResponseDto;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * {
 *     date : '2022-5-21',
 *     folderList : [
 *          {
 *              id : 2,
 *              folderName : "한 일",
 *              order : 1
 *          },
 *          {
 *              id : 1,
 *              folderName : "할 일",
 *              order : 2
 *          }
 *     ]
 *     todoList : [
 *          {
 *              id : 100,
 *              folderId : 1,
 *              todos : "[
 *                  {
 *
 *                  },
 *                  {
 *
 *                  }
 *              ]"
 *          }
 *          {
 *              id : 101,
 *              folderId : 2,
 *              folderName : "할일"
 *              todos : "[
 *                  {
 *
 *                  },
 *                  {
 *
 *                  }
 *              ]"
 *          }
 *     ]
 * }
 */

@Getter
public class TodoListResponseDto {
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private LocalDate date;

    private List<FolderResponseDto> folderList;
    private List<SimpleItemResponseDto> itemList;

    public TodoListResponseDto(LocalDate date, List<FolderResponseDto> folderList, List<SimpleItemResponseDto> itemList) {
        this.date = date;
        this.folderList = folderList;
        this.itemList = itemList;
    }
}
