package kr.or.cola.backend.user.presentation.dto;

import kr.or.cola.backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SimpleUserResponseDto {
    private final Long userId;
    private final String userName;

    @Builder
    public SimpleUserResponseDto(User entity) {
        this.userId = entity.getId();
        this.userName = entity.getName();
    }
}
