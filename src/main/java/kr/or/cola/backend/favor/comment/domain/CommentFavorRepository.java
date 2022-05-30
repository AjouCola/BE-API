package kr.or.cola.backend.favor.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentFavorRepository extends JpaRepository<CommentFavor, Long> {
    List<CommentFavor> findAllByCommentId(Long commentId);
    List<CommentFavor> findAllByUserId(Long userId);

    Optional<CommentFavor> findByUserIdAndCommentId(Long userId, Long commentId);
}
