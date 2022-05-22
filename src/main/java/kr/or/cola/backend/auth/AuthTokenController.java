package kr.or.cola.backend.auth;

import kr.or.cola.backend.auth.dto.AuthConfirmRequestDto;
import kr.or.cola.backend.auth.dto.AuthMailRequestDto;
import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthTokenController {
    private final AuthTokenService authTokenService;
    private final UserService userService;

    @PostMapping("/mail")
    public ResponseEntity<String> sendAuthMail(@LoginUser SessionUser loginUser, @RequestBody AuthMailRequestDto authMailRequestDto) {
        authTokenService.sendAuthMail(loginUser.getUserId(), authMailRequestDto.getEmail());

        return ResponseEntity.ok(new String("인증 이메일이 전송되었습니다."));
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> viewConfirmEmail(@RequestBody AuthConfirmRequestDto authConfirmRequestDto){
        authTokenService.confirmEmail(authConfirmRequestDto.getToken());
        return ResponseEntity.ok(new String("인증이 완료되었습니다."));
    }
}

