package kr.or.cola.backend.comment.favor;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment/favor")
public class CommentFavorController {
    private final CommentFavorService commentFavorService;

    @GetMapping("/{commentId}")
    public ResponseEntity<Void> createOrUpdateLike(@LoginUser SessionUser sessionUser, @PathVariable Long commentId){
        commentFavorService.createOrUpdateLike(sessionUser.getUserId(), commentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteLike(@LoginUser SessionUser sessionUser, @PathVariable Long commentId) {
        commentFavorService.deleteLike(sessionUser.getUserId(), commentId);
        return ResponseEntity.ok().build();
    }
}
