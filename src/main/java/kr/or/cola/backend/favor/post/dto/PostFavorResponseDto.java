package kr.or.cola.backend.favor.post.dto;

import kr.or.cola.backend.favor.post.domain.PostFavor;
import lombok.Getter;

@Getter
public class PostFavorResponseDto {
    private Long userId;
    private Long postId;

    public PostFavorResponseDto(PostFavor postFavor) {
        this.userId = postFavor.getUserId();
        this.postId = postFavor.getPostId();
    }

}
