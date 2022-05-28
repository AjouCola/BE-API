package kr.or.cola.backend.todo.item.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "todo_item_list")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long folderId;

    private String todos;

    private int progress;

    @Builder
    public Item(LocalDate date, Long folderId, String todos, int progress){
        this.date = date;
        this.folderId = folderId;
        this.todos = todos;
        this.progress = progress;
    }

    public void update(LocalDate date, Long folderId, String todos, int progress) {
        this.date = date;
        this.folderId = folderId;
        this.todos = todos;
        this.progress = progress;
    }
}
