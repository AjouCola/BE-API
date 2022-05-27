package kr.or.cola.backend.like.domain;

import kr.or.cola.backend.like.domain.pk.LikePostPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, LikePostPK> {
}
