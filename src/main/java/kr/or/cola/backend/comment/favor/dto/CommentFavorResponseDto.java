package kr.or.cola.backend.comment.favor.dto;

import kr.or.cola.backend.comment.favor.domain.CommentFavor;
import lombok.Getter;

@Getter
public class CommentFavorResponseDto {
    private Long userId;
    private Long contentId;

    public CommentFavorResponseDto(CommentFavor commentFavor){
        this.contentId = commentFavor.getCommentId();
        this.userId = commentFavor.getUserId();
    }
}
