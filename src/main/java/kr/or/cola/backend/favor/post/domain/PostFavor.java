package kr.or.cola.backend.favor.post.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(PostFavorPK.class)
public class PostFavor extends BaseTimeEntity {

    @Id
    private Long userId;

    @Id
    private Long postId;

}