package kr.or.cola.backend.post.post.presentation.dto;

import java.time.LocalDateTime;

import kr.or.cola.backend.post.favor.dto.PostFavorInfoResponseDto;
import kr.or.cola.backend.post.post.domain.Post;
import kr.or.cola.backend.post.post.domain.PostType;
import kr.or.cola.backend.user.presentation.dto.SimpleUserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SimplePostResponseDto {
    private final Long postId;
    private final String title;
    private final SimpleUserResponseDto userInfo;
    private final String preview;
    private final String thumbnailPath;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final PostType type;
    private final int favorCount;
    private final PostFavorInfoResponseDto favorInfoResponseDto;

    @Builder
    public SimplePostResponseDto(Post entity, PostFavorInfoResponseDto favorInfoResponseDto) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.userInfo = new SimpleUserResponseDto(entity.getUser());
        this.preview = entity.getPreview();
        this.thumbnailPath = entity.getThumbnailPath();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.type = entity.getPostType();
        this.favorCount = entity.getFavorCount();
        this.favorInfoResponseDto = favorInfoResponseDto;
    }
}
