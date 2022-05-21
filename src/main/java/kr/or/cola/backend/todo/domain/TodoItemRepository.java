package kr.or.cola.backend.todo.domain;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoItemRepository {
    List<TodoItem> findByDate(LocalDate date);
}
