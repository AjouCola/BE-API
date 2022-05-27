package kr.or.cola.backend.like.domain;

import kr.or.cola.backend.like.domain.pk.LikeCommentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, LikeCommentPK> {
}
