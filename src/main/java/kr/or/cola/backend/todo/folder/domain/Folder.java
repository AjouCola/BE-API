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
    @Column(nullable = false)
    private Long folderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    private String color;

    private LocalDateTime deletedAt;

    @Builder
    public Folder(String name, String color, User user) {
        this.name = name;
        this.color = color;
        this.user = user;
    }

    public void update(String name, String color) {
        this.name = name;
        this.color = color;
    }
}
