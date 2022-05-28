package kr.or.cola.backend.post.post.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.or.cola.backend.comment.presentation.dto.CommentResponseDto;
import kr.or.cola.backend.post.post.domain.PostType;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PostResponseDto {

    private final Long postId;

    private final PostType postType;

    private final String title;

    private final String content;

    private final SimpleUserResponseDto userInfo;

    private final List<CommentResponseDto> comments;

    private final List<String> tags;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifiedDate;

}
