package kr.or.cola.backend.favor.comment;

import kr.or.cola.backend.favor.dto.FavorCURequestDto;
import kr.or.cola.backend.favor.dto.FavorResponseDto;
import kr.or.cola.backend.favor.post.PostFavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favor/comment")
public class CommentFavorController {
    private final CommentFavorService commentFavorService;

    @PostMapping("")
    public ResponseEntity<Void> createOrUpdateLike(@RequestBody FavorCURequestDto requestDto){
        commentFavorService.createOrUpdateLike(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<FavorResponseDto>> getLikesInfo(@RequestParam(value = "comment", required = false) Long commentId, @RequestParam(value = "user", required = false) Long userId) {
        List<FavorResponseDto> responseDtos = commentFavorService.getLikes(commentId, userId);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long commentId) {
        return ResponseEntity.ok(0);
    }
}
