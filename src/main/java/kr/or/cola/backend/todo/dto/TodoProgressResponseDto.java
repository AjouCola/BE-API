package kr.or.cola.backend.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class TodoProgressResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private List<ProgressDto> todoProgress;

    public TodoProgressResponseDto(LocalDate date, List<ProgressDto> todoProgress) {
        this.date = date;
        this.todoProgress = todoProgress;
    }

    public void update(List<ProgressDto> todoProgress) {
        this.todoProgress = todoProgress;
    }
}


/**
 * 1. 비정규화
 * {
 *      date : "2022-05-01"
 *     progress : [
 *          {
 *              color : "#aaaaaa",
 *              name : "할 일",
 *              progress : 0
 *          },
 *          {
 *              color : "#aaaaba",
 *              name : "한 일",
 *              progress : 100
 *          }
 *     ]
 * }
 */

/**
 * 1. 비정규화
 * {
 *      date : "2022-05-01"
 *      folders : [
 *          {
 *              folderId : 1,
 *              name : "할 일",
 *              color : "#aaaaaa"
 *          },
 *          {
 *              folderId : 2,
 *              name : "한 일",
 *              color : "#aaaaaa"
 *          }
 *      ]
 *     progress : [
 *          {
 *              folderId : 1,
 *              progress : 0
 *          },
 *          {
 *              folderId : 2,
 *              progress : 100
 *          }
 *     ]
 * }
 */