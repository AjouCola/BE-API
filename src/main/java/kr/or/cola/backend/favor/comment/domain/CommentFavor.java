package kr.or.cola.backend.favor.comment.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class CommentFavor extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentFavorId;

    private Long userId;
    private Long commentId;

    public CommentFavor(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }

}
