package kr.or.cola.backend.favor.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentFavorRepository extends JpaRepository<CommentFavor, CommentFavorPK> {
    List<CommentFavor> findAllByCommentId(Long commentId);
    List<CommentFavor> findAllByUserId(Long userId);
}
