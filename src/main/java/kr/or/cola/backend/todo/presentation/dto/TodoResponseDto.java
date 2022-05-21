package kr.or.cola.backend.todo.presentation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

public class TodoResponseDto {
    private String date;
    private List<TodoFolderDto> todoFolderDtoList;
    private List<TodoItemDto> todoItemDtoList;

    public Date toDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime date = LocalDateTime.parse(this.date, formatter);

        this.date.split("-");
        return new Date(this.date);
    }


}
