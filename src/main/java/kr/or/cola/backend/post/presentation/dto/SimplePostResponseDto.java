package kr.or.cola.backend.post.presentation.dto;

import java.time.LocalDateTime;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.Getter;

@Getter
public class SimplePostResponseDto {
    private Long postId;
    private String title;
    private SimpleUserResponseDto userInfo;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public SimplePostResponseDto(Post entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.userInfo = new SimpleUserResponseDto(entity.getUser());
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
