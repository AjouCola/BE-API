package kr.or.cola.backend.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "guest"),
    USER("ROLE_USER", "user"),
    ADMIN("ROLE_ADMIN", "administrator");

    private final String key;
    private final String title;
}
