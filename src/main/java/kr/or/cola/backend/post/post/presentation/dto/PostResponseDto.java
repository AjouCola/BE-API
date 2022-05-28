package kr.or.cola.backend.post.post.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import kr.or.cola.backend.comment.presentation.dto.CommentResponseDto;
import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PostResponseDto {

    private final Long postId;

    private final String title;

    private final String content;

    private final SimpleUserResponseDto userInfo;

    private final List<CommentResponseDto> comments;

    private final List<String> tags;

    private final LocalDateTime createdDate;

    private final LocalDateTime modifiedDate;

//    @Builder
//    public PostResponseDto(Post entity) {
//        this.postId = entity.getId();
//        this.title = entity.getTitle();
//        this.content = entity.getContent();
//        this.userInfo = new SimpleUserResponseDto(entity.getUser());
//        this.comments = entity.getComments()
//            .stream()
//            .map(CommentResponseDto::new)
//            .collect(Collectors.toList());
//        this.createdDate = entity.getCreatedDate();
//        this.modifiedDate = entity.getModifiedDate();
//    }
}
