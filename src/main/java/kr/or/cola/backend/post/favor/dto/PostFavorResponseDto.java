package kr.or.cola.backend.post.favor.dto;

import kr.or.cola.backend.post.favor.domain.PostFavor;
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
