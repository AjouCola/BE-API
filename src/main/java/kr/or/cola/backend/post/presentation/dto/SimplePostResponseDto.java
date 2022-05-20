package kr.or.cola.backend.post.presentation.dto;

import java.time.LocalDateTime;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.Getter;

@Getter
public class SimplePostResponseDto {
    private final Long postId;
    private final String title;
    private final SimpleUserResponseDto userInfo;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public SimplePostResponseDto(Post entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.userInfo = new SimpleUserResponseDto(entity.getUser());
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}