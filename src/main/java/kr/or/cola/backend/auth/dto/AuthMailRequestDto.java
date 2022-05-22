package kr.or.cola.backend.auth.dto;

import lombok.Getter;

@Getter
public class AuthMailRequestDto {
    private String email;

    public AuthMailRequestDto(String email){
        this.email = email;
    }
}
