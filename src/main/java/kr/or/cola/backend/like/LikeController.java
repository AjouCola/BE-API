package kr.or.cola.backend.like;

import kr.or.cola.backend.like.dto.CULikeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("")
    public ResponseEntity<Void> createOrUpdateLike(@RequestBody CULikeRequestDto requestDto){
        likeService.createOrUpdateLike(requestDto);
        return ResponseEntity.ok().build();
    }

}
