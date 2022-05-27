package kr.or.cola.backend.favor.comment.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentFavorPK implements Serializable {
    private Long userId;
    private Long commentId;

    @Builder
    public CommentFavorPK(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
