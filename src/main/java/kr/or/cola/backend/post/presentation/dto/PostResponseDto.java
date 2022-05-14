package kr.or.cola.backend.post.presentation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kr.or.cola.backend.comment.controller.dto.CommentResponseDto;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private final Long postId;
    private final String title;
    private final String content;
    private final SimpleUserResponseDto userInfo;
    private final List<CommentResponseDto> comments;

    @Builder
    public PostResponseDto(Post entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.userInfo = new SimpleUserResponseDto(entity.getUser());
        this.comments = entity.getComments()
            .stream()
            .map(CommentResponseDto::new)
            .collect(Collectors.toList());
    }
}
