package kr.or.cola.backend.favor.dto;

import kr.or.cola.backend.favor.comment.domain.CommentFavor;
import kr.or.cola.backend.favor.post.domain.PostFavor;
import lombok.Getter;

@Getter
public class FavorResponseDto {
    private Long userId;
    private Long contentId;
    private FavorType favorType;

    public FavorResponseDto(CommentFavor commentFavor){
        this.contentId = commentFavor.getCommentId();
        this.userId = commentFavor.getUserId();
        this.favorType = FavorType.COMMENT;
    }
    public FavorResponseDto(PostFavor postFavor){
        this.contentId = postFavor.getPostId();
        this.userId = postFavor.getUserId();
        this.favorType = FavorType.POST;
    }

}
