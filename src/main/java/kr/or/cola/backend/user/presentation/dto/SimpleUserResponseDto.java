package kr.or.cola.backend.user.presentation.dto;

import kr.or.cola.backend.user.domain.Department;
import kr.or.cola.backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SimpleUserResponseDto {
    private final Long userId;

    private final String userName;

    private final String profilePath;

    private final Department department;

    @Builder
    public SimpleUserResponseDto(User entity) {
        this.userId = entity.getId();
        this.userName = entity.getName();
        this.profilePath = entity.getProfilePath();
        this.department = entity.getDepartment();
    }
}
