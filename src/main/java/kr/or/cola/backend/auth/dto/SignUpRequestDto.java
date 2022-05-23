package kr.or.cola.backend.auth.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String name;
    private String department;
    private String ajouEmail;
    private String gitEmail;
}
