package kr.or.cola.backend.user.presentation;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<User> getUserInfo(@NonNull @LoginUser SessionUser sessionUser) {
        User user = userService.findUserById(sessionUser.getUserId());
        return ResponseEntity.ok(user);
    }

}