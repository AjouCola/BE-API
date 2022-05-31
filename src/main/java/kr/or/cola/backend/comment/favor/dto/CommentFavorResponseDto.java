package kr.or.cola.backend.comment.favor.dto;

import kr.or.cola.backend.comment.favor.domain.CommentFavor;
import lombok.Getter;

@Getter
public class CommentFavorResponseDto {
    private Long userId;
    private Long commentId;

    public CommentFavorResponseDto(CommentFavor commentFavor){
        this.commentId = commentFavor.getCommentId();
        this.userId = commentFavor.getUserId();
    }
}
