package kr.or.cola.backend.user.presentation.dto;

import kr.or.cola.backend.user.domain.Department;
import kr.or.cola.backend.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long userId;

    private final String email;

    private final String name;

    private final String ajouEmail;

    private final String gitEmail;

    private final Department department;

    private final String profilePath;

    @Builder
    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.ajouEmail = user.getAjouEmail();
        this.gitEmail = user.getGitEmail();
        this.department = user.getDepartment();
        this.profilePath = user.getProfilePath();
    }

}
