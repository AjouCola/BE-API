package kr.or.cola.backend.auth.dto;


import kr.or.cola.backend.user.domain.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String name;
    private Department department;
    private String ajouEmail;
    private String gitEmail;
}
