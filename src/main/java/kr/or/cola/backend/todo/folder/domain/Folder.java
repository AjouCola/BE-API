package kr.or.cola.backend.todo.folder.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import kr.or.cola.backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Folder extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    private String color;

    private int order;

    private LocalDateTime deletedAt;


    @Builder
    public Folder(String name, String color, int order, User user) {
        this.name = name;
        this.color = color;
        this.order = order;
        this.user = user;
    }

    public void update(String name, String color, int order) {
        this.name = name;
        this.color = color;
        this.order = order;
    }
}
