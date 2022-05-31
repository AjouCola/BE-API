package kr.or.cola.backend.post.favor.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostFavorInfoResponseDto {
    private final Long postId;
    private final int count;
    private final boolean isFavor;
}
