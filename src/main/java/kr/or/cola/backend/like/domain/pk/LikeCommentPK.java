package kr.or.cola.backend.like.domain.pk;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class LikeCommentPK implements Serializable {
    private Long userId;
    private Long commentId;

    @Builder
    public LikeCommentPK(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
