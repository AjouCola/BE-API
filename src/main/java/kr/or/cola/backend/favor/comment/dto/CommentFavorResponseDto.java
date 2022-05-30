package kr.or.cola.backend.favor.comment.dto;

import kr.or.cola.backend.favor.comment.domain.CommentFavor;
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
