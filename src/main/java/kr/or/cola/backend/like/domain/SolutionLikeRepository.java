package kr.or.cola.backend.like.domain;

import kr.or.cola.backend.like.domain.pk.LikeSolutionPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionLikeRepository extends JpaRepository<SolutionLike, LikeSolutionPK> {
}
