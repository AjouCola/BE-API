package kr.or.cola.backend.auth.dto;

import lombok.Getter;

@Getter
public class AuthConfirmRequestDto {
    private String token;

    public AuthConfirmRequestDto(String token) {
        this.token = token;
    }
}
