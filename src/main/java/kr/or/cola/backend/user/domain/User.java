package kr.or.cola.backend.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="user_login")
@SequenceGenerator(
        name = "user_seq",
        initialValue = 1,
        allocationSize=1
)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(nullable = false)
    private Role role;

    // email
    @Column(nullable = false)
    private String email;


    @Builder
    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}
