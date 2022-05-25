package kr.or.cola.backend.user.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name="profile_path", length = 4096)
    private String profilePath;

    @Column(name="is_verified")
    private boolean isVerified;

    private String folderOrder;

    @Builder
    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public void signUp(@NotNull Role role,
                       @NotNull String name,
                       @NotNull String ajouEmail,
                       @Nullable String gitEmail,
                       @NotNull Department department,
                       @NotNull String folderOrder,
                       @Nullable String profilePath,
                       @NotNull Boolean isVerified) {

        this.role = role;
        this.name = name;
        this.ajouEmail = ajouEmail;
        this.department = department;
        this.isVerified = isVerified;
        this.gitEmail = gitEmail;
        this.profilePath = profilePath;
        this.folderOrder = folderOrder;
    }

    public List<Long> getFolderOrder() {
        return Arrays.stream(this.folderOrder.split(",")).map(Long::getLong).collect(Collectors.toList());
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
