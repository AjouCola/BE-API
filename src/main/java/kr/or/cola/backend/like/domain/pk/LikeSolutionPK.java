package kr.or.cola.backend.like.domain.pk;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class LikeSolutionPK implements Serializable {
    private Long userId;
    private Long solutionId;

    @Builder
    public LikeSolutionPK(Long userId, Long solutionId) {
        this.userId = userId;
        this.solutionId = solutionId;
    }
}
