package kr.or.cola.backend.oauth.dto;
import kr.or.cola.backend.user.domain.Role;
import kr.or.cola.backend.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long userId;
    private String email;
    private Role role;

    public SessionUser(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
