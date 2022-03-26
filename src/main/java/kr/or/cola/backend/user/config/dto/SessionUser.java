package kr.or.cola.backend.user.config.dto;

import kr.or.cola.backend.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String email;

    public SessionUser(User user) {
        this.email = user.getEmail();
    }
}
