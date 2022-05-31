package kr.or.cola.backend.post.favor.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class PostFavor extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postFavorId;

    private Long userId;
    private Long postId;
    private Boolean status;

    @Builder
    public PostFavor(Long userId, Long postId, Boolean status) {
        this.userId = userId;
        this.postId = postId;
        this.status = status;
    }

    public void update(Boolean status) {
        this.status = status;
    }
}