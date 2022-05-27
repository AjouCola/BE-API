package kr.or.cola.backend.favor.comment.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(CommentFavorPK.class)
public class CommentFavor extends BaseTimeEntity {

    @Id
    private Long userId;

    @Id
    private Long commentId;

}
