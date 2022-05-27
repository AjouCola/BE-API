package kr.or.cola.backend.favor.post;

import kr.or.cola.backend.favor.dto.FavorCURequestDto;
import kr.or.cola.backend.favor.dto.FavorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/favor/posts")
public class PostFavorController {
    private final PostFavorService postFavorService;

    @PostMapping("")
    public ResponseEntity<Void> createOrUpdateLike(@RequestBody FavorCURequestDto requestDto){
        postFavorService.createOrUpdateLike(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<FavorResponseDto>> getLikesInfo(@RequestParam(value = "content", required = false) Long contentId, @RequestParam(value = "user", required = false) Long userId) {
        List<FavorResponseDto> responseDtos = postFavorService.getLikes(contentId, userId);
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long contentId) {
        return ResponseEntity.ok(0);
    }
}
