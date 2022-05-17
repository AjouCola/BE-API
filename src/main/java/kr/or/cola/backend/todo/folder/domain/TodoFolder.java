package kr.or.cola.backend.todo.folder.domain;

import kr.or.cola.backend.user.domain.User;

import javax.persistence.*;

@Table(name="todo_foldr")
@Entity
public class TodoFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;


}
