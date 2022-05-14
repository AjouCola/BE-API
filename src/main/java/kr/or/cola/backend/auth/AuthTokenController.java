package kr.or.cola.backend.auth;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthTokenController {
    private final AuthTokenService authTokenService;
    private final UserService userService;

    @PostMapping("/mail")
    public void sendAuthMail(@LoginUser SessionUser loginUser, @RequestBody String email) {
        authTokenService.sendAuthMail(loginUser.getId(), email);
    }

    @PostMapping("/confirm")
    public String viewConfirmEmail(@Valid @RequestBody String token){
        return authTokenService.confirmEmail(token);
    }
}

