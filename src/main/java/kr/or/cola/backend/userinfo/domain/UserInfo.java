package kr.or.cola.backend.userinfo.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import kr.or.cola.backend.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name="user_info")
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="name")
    private String name;

    @Column(name="ajou_email", unique = true, nullable = false)
    private String ajouEmail;

    @Column(name="git_email")
    private String gitEmail;

    @Column(name="department")
    private String department;

    @Column(name="profile", length = 4096)
    private String profile;

    @Column(name="is_varified")
    private boolean emailVerified;

    @Column(name="deleted_at")
    private Timestamp deleted_at;

    public void emailVerifiedSuccess() {
        emailVerified = true;
    }
}
