package kr.or.cola.backend.post.presentation.dto;

import java.time.LocalDateTime;
import kr.or.cola.backend.post.domain.Post;
import kr.or.cola.backend.user.domain.User;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private SimpleUserResponseDto userInfo;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.userInfo = new SimpleUserResponseDto(entity.getUser());
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
