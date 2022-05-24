package kr.or.cola.backend.todo.item.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "todo_item_list")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-DD")
    @Column(name = "date")
    private LocalDate date;

    private Long folderId;

    private String content;

    private int progress;

    public void update(LocalDate date, Long folderId, String content, int progress) {
        this.date = date;
        this.folderId = folderId;
        this.content = content;
        this.progress = progress;
    }
}
