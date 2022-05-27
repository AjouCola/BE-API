package kr.or.cola.backend.like.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import kr.or.cola.backend.like.domain.pk.LikeCommentPK;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(LikeCommentPK.class)
public class CommentLike extends BaseTimeEntity {

    @Id
    private Long userId;

    @Id
    private Long commentId;

}
