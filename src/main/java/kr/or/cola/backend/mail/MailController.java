package kr.or.cola.backend.mail;

import kr.or.cola.backend.mail.auth.AuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {
    private final AuthTokenService authTokenService;

    @PostMapping("/auth")
    void sendAuthMail(Long userId) {
        authTokenService.createEmailAuthToken(userId,"test@test.com");
    }
}

