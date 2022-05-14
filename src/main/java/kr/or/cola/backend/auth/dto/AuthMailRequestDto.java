package kr.or.cola.backend.auth.dto;

import lombok.Getter;

@Getter
public class AuthMailRequestDto {
    private final String email;

    public AuthMailRequestDto(String email){
        this.email = email;
    }
}
