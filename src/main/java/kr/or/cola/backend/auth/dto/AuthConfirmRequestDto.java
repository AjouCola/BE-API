package kr.or.cola.backend.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthConfirmRequestDto {
    private String token;

    @Builder
    public AuthConfirmRequestDto(String token) {
        this.token = token;
    }
}
