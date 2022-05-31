package kr.or.cola.backend.post.favor;

import kr.or.cola.backend.oauth.LoginUser;
import kr.or.cola.backend.oauth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts/favor")
public class PostFavorController {
    private final PostFavorService postFavorService;

    @GetMapping("/{postId}")
    public ResponseEntity<Void> createOrUpdateLike(@LoginUser SessionUser sessionUser, @PathVariable Long postId, @RequestParam Boolean status){
        postFavorService.createOrUpdateLike(sessionUser.getUserId(), postId, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteLike(@LoginUser SessionUser sessionUser, @PathVariable Long postId){
        postFavorService.deleteLike(sessionUser.getUserId(), postId);
        return ResponseEntity.ok().build();
    }
}
