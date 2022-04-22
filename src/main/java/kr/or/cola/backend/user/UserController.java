package kr.or.cola.backend.user;

import kr.or.cola.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("confirm-email")
    public String viewConfirmEmail(@Valid @RequestBody String token){

        return userService.confirmEmail(token);
    }
}