package kr.or.cola.backend.user.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Table(name="users")
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // email
    @Column(nullable = false)
    private String email;

    private String name;

    @Column(name="ajou_email")
    private String ajouEmail;

    @Column(name="git_email")
    private String gitEmail;

    private String department;

    @Column(name="profile_path", length = 4096)
    private String profilePath;

    @Column(name="is_verified")
    private boolean isVerified;

    @Builder
    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void emailVerifiedSuccess() {
        isVerified = true;
    }


}
