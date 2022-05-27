package kr.or.cola.backend.like.domain.pk;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class LikePostPK implements Serializable {
    private Long userId;
    private Long postId;

    @Builder
    public LikePostPK(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
