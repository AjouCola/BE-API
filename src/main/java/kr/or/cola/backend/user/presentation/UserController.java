package kr.or.cola.backend.user.presentation;

import java.util.List;
import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import kr.or.cola.backend.user.UserService;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.presentation.dto.UserUpdateRequestDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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

    @PutMapping("")
    public ResponseEntity<Void> updateUserInfo(@LoginUser SessionUser sessionUser,
        UserUpdateRequestDto requestDto) {
        userService.updateContent(sessionUser.getUserId(), requestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> updateUserProfile(@LoginUser SessionUser sessionUser,
                                                  @RequestPart MultipartFile profileImage) {
        userService.updateProfile(sessionUser.getUserId(), profileImage);
        return ResponseEntity.ok().build();
    }

}