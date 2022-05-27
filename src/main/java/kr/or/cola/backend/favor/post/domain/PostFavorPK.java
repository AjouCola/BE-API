package kr.or.cola.backend.favor.post.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostFavorPK implements Serializable {
    private Long userId;
    private Long postId;

    @Builder
    public PostFavorPK(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
