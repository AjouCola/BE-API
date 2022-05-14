package kr.or.cola.backend.comment.presentation.dto;

import kr.or.cola.backend.comment.domain.Comment;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long commentId;

    private final SimpleUserResponseDto userInfo;

    private final String content;

    public CommentResponseDto(Comment entity) {
        this.commentId = entity.getId();
        this.userInfo = new SimpleUserResponseDto(entity.getUser());
        this.content = entity.getContent();
    }

}
