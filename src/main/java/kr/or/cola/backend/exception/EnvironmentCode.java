package kr.or.cola.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnvironmentCode {

    INVALID_REQUEST_DATA("INVALID_REQUEST_DATA", "invalid request data"),
    TOKEN_NOT_FOUND("TOKEN_NOT_FOUND", "Auth Token Not Found");

    private final String code;
    private final String message;
}
