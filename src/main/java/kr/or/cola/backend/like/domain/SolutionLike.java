package kr.or.cola.backend.like.domain;

import kr.or.cola.backend.common.BaseTimeEntity;
import kr.or.cola.backend.like.domain.pk.LikeSolutionPK;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(LikeSolutionPK.class)
public class SolutionLike extends BaseTimeEntity {

    @Id
    private Long userId;

    @Id
    private Long solutionId;

}