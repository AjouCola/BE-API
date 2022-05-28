package kr.or.cola.backend.user.presentation.dto;

import kr.or.cola.backend.user.domain.Department;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private String name;

    private String gitEmail;

    private Department department;

}
