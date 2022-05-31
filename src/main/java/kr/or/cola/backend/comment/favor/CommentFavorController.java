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

    @GetMapping("/{postId}")
    public ResponseEntity<Void> createOrUpdateLike(@LoginUser SessionUser sessionUser, @PathVariable Long postId){
        commentFavorService.createOrUpdateLike(sessionUser.getUserId(), postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteLike(@LoginUser SessionUser sessionUser, @PathVariable Long postId) {
        commentFavorService.deleteLike(sessionUser.getUserId(), postId);
        return ResponseEntity.ok().build();
    }
}
