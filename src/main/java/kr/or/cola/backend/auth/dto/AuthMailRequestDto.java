package kr.or.cola.backend.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthMailRequestDto {
    private final String email;

    @Builder
    public AuthMailRequestDto(String email){
        this.email = email;
    }
}
